import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 自动生成bean工具
 *
 * @author 张君
 */
public class Creater {
    private List<String> importList = new ArrayList<>();
    private String path;
    private String packageName;
    private String username;
    private String password;
    private List<String> tables = new ArrayList<>();
    private String url;
    private HashMap<String, List<FieldBean>> map = new HashMap<>();

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public Creater setPath(String path) {

        this.packageName=path;

        String srcPath = System.getProperty("user.dir") + "\\src";
        System.out.println(srcPath+"           srcpath");
        path = path.replace(".", "\\");
        this.path = srcPath + "\\" + path;

        File file=new File(this.path);
        file.mkdirs();

        System.out.println(this.path+"-------------------");
        return this;
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
        private String type;
        private String name;

        public FieldBean() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public FieldBean(String type, String name) {
            this.type = type;
            this.name = name;
        }
    }


    //获得所有数据
    private void getData() throws Exception {
        //创建连接

        Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
        Statement statement = conn.createStatement();
        //遍历每个表
        for (String tableName : tables) {
            String sql = "select * from `" + tableName + "`";
            ResultSet resultSet = statement.executeQuery(sql);
            //遍历表里的每个字段，放到map中

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<FieldBean> list = new ArrayList<>();
            //获得的索引从1开始
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(i);
                String columnName = metaData.getColumnName(i);
                int columnType = metaData.getColumnType(i);
                list.add(new FieldBean(getJavaTypeString(columnType), columnName));
                addToImportList(columnType);
            }
            map.put(tableName, list);

        }
    }

    //根据接收到的sqltype 数值来判断类型并返回
    private String getJavaTypeString(int type) {
        if (type == Types.DATALINK || type == Types.DATE) {
            return "Date";
        }
        return "String";
    }

    private void addToImportList(int type) {
        //类型判断是否需要导包
        //然后放到importList

        switch (type) {
            case Types.DATALINK:
            case Types.DATE:
                importList.add("java.util.Date");
                break;
        }


    }


    //创建bean文件
    public Creater handle() {
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建java文件

        path = path == null || path == "" ? "e:\\" : path;
        for (String key : map.keySet()) {
            System.out.println(key);
            //这里创建文件
            File file = new File(path +"\\"+ toCamelCase(1, key) + ".java");

            setFile(file);

            //package 声明
            append(file,"package "+packageName+";\r\n");

            //导包文本添加
            for (String str : importList) {
                append(file, "import " + str + ";\r\n");
            }

            append(file, "public class " + toCamelCase(1, key) + "{\r\n");
            for (FieldBean fieldBean : map.get(key)) {
                //写入文本
                //写入变量声明部分
                append(file, "private " + fieldBean.getType() + " " + toCamelCase(0, fieldBean.getName()) + ";\r\n");
            }
            append(file, "\r\n");
            for (FieldBean fieldBean : map.get(key)) {
                //写入setter getter
                append(file, "public void set" + toCamelCase(1, fieldBean.getName()) + "(" + fieldBean.getType() + " " + toCamelCase(0, fieldBean.getName()) + "){ \r\nthis." + toCamelCase(0, fieldBean.getName()) + "=" + toCamelCase(0, fieldBean.getName()) + ";\r\n}\r\n");
                append(file, "public "+fieldBean.getType()+" get" + toCamelCase(1, fieldBean.getName()) + "(){ \r\nreturn " + toCamelCase(0, fieldBean.getName()) + ";\r\n}\r\n");
                System.out.println("  " + fieldBean.getName() + ":" + fieldBean.getType());
            }
            append(file, "\r\n}");
        }
        return this;
    }

    //如果文件存在，那就删除然后新建，不存在就直接新建
    private void setFile(File file) {
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
