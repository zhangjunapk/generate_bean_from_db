package org.zj.demo.bean;
import javax.persistence.Id;
import java.util.Date;
public class ReadHistory{
@Id
private int id;
private int blogId;
private Date date;

public void setId(int id){ 
this.id=id;
}
public int getId(){ 
return id;
}
public void setBlogId(int blogId){ 
this.blogId=blogId;
}
public int getBlogId(){ 
return blogId;
}
public void setDate(Date date){ 
this.date=date;
}
public Date getDate(){ 
return date;
}

}