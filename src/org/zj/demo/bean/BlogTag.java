package org.zj.demo.bean;
import java.util.Date;
public class BlogTag{
private int blogId;
private int tagId;
private Date date;

public void setBlogId(int blogId){ 
this.blogId=blogId;
}
public int getBlogId(){ 
return blogId;
}
public void setTagId(int tagId){ 
this.tagId=tagId;
}
public int getTagId(){ 
return tagId;
}
public void setDate(Date date){ 
this.date=date;
}
public Date getDate(){ 
return date;
}

}