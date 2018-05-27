public class Content {
    public static void main(String[] args) throws Exception {
        new Creater("jdbc:mysql://localhost:3306/czxy_blog","root",null)
                .allTable()
                .setBeanPackage("org.zj.demo.bean")
                .setMapperPackage("org.zj.demo.dao")
                .handle();
    }
}
