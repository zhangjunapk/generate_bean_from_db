import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动生成bean工具
 *
 * @author 张君
 */
public class Creater {
    private String beanPackage;
    private String mapperPackage;
    private String username;
    private String password;
    private List<String> tables = new ArrayList<>();
    private String url;



    private HashMap<String, FieldBean> map = new HashMap<>();


    public String getBeanPackage() {
        return beanPackage;
    }

    public Creater setBeanPackage(String beanPackage) {
        this.beanPackage = beanPackage;
        return this;
    }

    public String getUrl() {
        return url;
    }


    public String getMapperPackage() {
        return mapperPackage;
    }

    public Creater setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
        return this;
    }

    public Creater allTable(){
        String sql="show tables";
        try {
            Connection conn=DriverManager.getConnection(url,username,password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> table=new ArrayList<>();
            while(resultSet.next()){
               table.add(resultSet.getString(1));
            }
            this.tables=table;
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    //通过包名获得绝对路径
    private String getRelativePath(String packageName){
        String srcPath = System.getProperty("user.dir") + "\\src";
        System.out.println(packageName+"  packname");
        packageName = packageName.replace(".", "\\");
        System.out.println(packageName+"   packagename");
        System.out.println(srcPath + "\\" + packageName);
        return srcPath + "\\" + packageName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public Creater(String url, String username, String password) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public Creater addTable(String name) {
        tables.add(name);
        return this;
    }

    //放字段类型和名字
    private class FieldBean {
        //class 字段 类型的键值对
        private List<Map.Entry<String,String>> fieldMap=new ArrayList<>();
        //导入list
        private List<String> importList=new ArrayList<>();

        public FieldBean() {
        }

        public FieldBean( List<String> importList,List<Map.Entry<String, String>> fieldMap) {
            this.fieldMap = fieldMap;
            this.importList = importList;
        }

        public List<Map.Entry<String, String>> getFieldMap() {
            return fieldMap;
        }

        public void setFieldMap(List<Map.Entry<String, String>> fieldMap) {
            this.fieldMap = fieldMap;
        }

        public List<String> getImportList() {
            return importList;
        }

        public void setImportList(List<String> importList) {
            this.importList = importList;
        }
    }


    //获得所有数据
    private void getData() throws Exception {
        //创建连接

        Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
        Statement statement = conn.createStatement();
        //遍历每个表
        for (String tableName : tables) {
            System.out.println("tablename:"+tableName);
            String sql = "select * from `" + tableName + "`";
            ResultSet resultSet = statement.executeQuery(sql);
            //遍历表里的每个字段，放到map中

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<FieldBean> list = new ArrayList<>();
            //获得的索引从1开始
            List<String> importList=new ArrayList<>();
            List<Map.Entry<String,String>> fieldList=new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(i);
                String columnName = metaData.getColumnName(i);
                System.out.println(" columnName:"+columnName);
                int columnType = metaData.getColumnType(i);
                addToImportList(importList,columnType);
                fieldList.add(new Map.Entry<String, String>() {
                    @Override
                    public String getKey() {
                        return columnName;
                    }

                    @Override
                    public String getValue() {
                        return getJavaTypeString(columnType);
                    }

                    @Override
                    public String setValue(String value) {
                        return getJavaTypeString(columnType);
                    }
                });
            }
            map.put(tableName, new FieldBean(importList,fieldList));

        }
        statement.close();
        conn.close();
    }

    //根据接收到的sqltype 数值来判断类型并返回
    private String getJavaTypeString(int type) {

        System.out.println("  type:"+type);

        if (type == Types.TIME || type == Types.DATE||type==Types.TIMESTAMP) {
            return "Date";
        }
        if(type==Types.FLOAT){
            return "float";
        }
        if(type==Types.DOUBLE){
            return "double";
        }
        if(type==Types.INTEGER){
            return "int";
        }
        if(type==Types.BOOLEAN){
            return "boolean";
        }


        return "String";
    }

    private void addToImportList(List<String> list,int type) {
        //类型判断是否需要导包
        //然后放到importList

        switch (type) {
            case Types.DATALINK:
            case Types.DATE:
            case Types.TIMESTAMP:
                if(!list.contains("java.util.Date")) {
                    list.add("java.util.Date");
                }break;
        }


    }


    //创建文件
    public Creater handle() {
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建java文件

        handleBean();
        //创建通用mapper
        handleMapper();

        return this;
    }

    public void handleBean(){

        for (String key : map.keySet()) {
            System.out.println(key);
            //这里创建文件
            File file = new File(getRelativePath(this.beanPackage) +"\\"+ toCamelCase(1, key) + ".java");
            System.out.println(file.getPath()+"----------");
            setFile(file);

            //package 声明
            append(file,"package "+ beanPackage +";\r\n");

            //导包文本添加
            for (String str : map.get(key).getImportList()) {
                append(file, "import " + str + ";\r\n");
            }

            append(file, "public class " + toCamelCase(1, key) + "{\r\n");
            for (Map.Entry entry : map.get(key).getFieldMap()) {
                String type= (String) entry.getValue();
                String name= (String) entry.getKey();
                //写入文本
                //写入变量声明部分
                append(file, "private " + type + " " + toCamelCase(0, name) + ";\r\n");
            }
            append(file, "\r\n");
            for (Map.Entry entry : map.get(key).getFieldMap()) {
                String name= (String) entry.getKey();
                String type= (String) entry.getValue();
                //写入setter getter
                append(file, "public void set" + toCamelCase(1, name) + "(" + type + " " + toCamelCase(0, name) + "){ \r\nthis." + toCamelCase(0, name) + "=" + toCamelCase(0, name) + ";\r\n}\r\n");
                append(file, "public "+type+" get" + toCamelCase(1, name) + "(){ \r\nreturn " + toCamelCase(0,name) + ";\r\n}\r\n");
                System.out.println("  " + type + ":" + type);
            }
            append(file, "\r\n}");
        }
    }
    public void handleMapper(){
        for(String str:tables){
            File file=new File(getRelativePath(this.mapperPackage)+"\\"+toCamelCase(1,str)+"Mapper.java");
            setFile(file);
            append(file,"package "+this.mapperPackage+";\r\n");
            append(file,"import "+beanPackage+"."+toCamelCase(1,str)+";\r\n");
            append(file,"import tk.mybatis.mapper.common.Mapper;\r\n");
            append(file,"public interface "+toCamelCase(1,str)+"Mapper extends Mapper<"+toCamelCase(1,str)+">{\r\n}");
        }
    }
    //如果文件存在，那就删除然后新建，不存在就直接新建
    private void setFile(File file) {

        file.mkdirs();

        //如果不存在
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void append(File file, String content) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //java驼峰转换
    public String toCamelCase(int flag, String name) {
        int index = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.substring(i, i + 1).equals("_")) {
                System.out.println("前面:" + name.substring(0, i));

                /*System.out.println("后面"+name.substring(i+1));
                 */
                //判断_后面是否还有字符

                System.out.println(name.substring(i + 1) + "----0000");

                if (name.substring(i + 1).length() == 0) {
                    name = name = name.substring(0, i);
                } else {
                    System.out.println("后面的哦" + name.substring(i + 1) + "---");
                    name = name.substring(0, i) + name.substring(i + 1).substring(0, 1).toUpperCase() + name.substring(i + 2);
                }
            }
        }
        if (flag == 0) {
            if (name.length() > 1) {
                name = name.substring(0, 1).toLowerCase() + name.substring(1);
            } else {
                name = name.substring(0).toLowerCase();
            }
        } else {
            //如果flag=1 大驼峰

            if (name.length() > 1) {
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
            } else {
                name = name.substring(0).toUpperCase();
            }
        }
        return name;
    }

}
