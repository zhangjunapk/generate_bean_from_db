public class Content {
    public static void main(String[] args) throws Exception {
        new Creater("jdbc:mysql://localhost:3306/test_bean","root",null).addTable("tt_").setPath("e:\\").handle();
    }
}
