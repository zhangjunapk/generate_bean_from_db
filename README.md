# generate_bean_from_db

通过数据库以及表名来生成Bean类和通用Mapper

怎么使用呢  这样子

            new Creater("jdbc:mysql://localhost:3306/czxy_blog","root",null)
                .allTable()
                .setBeanPackage("org.zj.demo.bean")
                .setMapperPackage("org.zj.demo.dao")
                .handle();
