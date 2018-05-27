package org.zj.demo.bean;
import javax.persistence.Id;
import java.util.Date;
public class Blog{
@Id
private int id;
private String title;
private String content;
private Date date;
private int typeId;
private String picLink;
private int status;

public void setId(int id){ 
this.id=id;
}
public int getId(){ 
return id;
}
public void setTitle(String title){ 
this.title=title;
}
public String getTitle(){ 
return title;
}
public void setContent(String content){ 
this.content=content;
}
public String getContent(){ 
return content;
}
public void setDate(Date date){ 
this.date=date;
}
public Date getDate(){ 
return date;
}
public void setTypeId(int typeId){ 
this.typeId=typeId;
}
public int getTypeId(){ 
return typeId;
}
public void setPicLink(String picLink){ 
this.picLink=picLink;
}
public String getPicLink(){ 
return picLink;
}
public void setStatus(int status){ 
this.status=status;
}
public int getStatus(){ 
return status;
}

}