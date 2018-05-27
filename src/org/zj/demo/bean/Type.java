package org.zj.demo.bean;
import javax.persistence.Id;
public class Type{
@Id
private int id;
private int parentId;
private String title;
private String info;

public void setId(int id){ 
this.id=id;
}
public int getId(){ 
return id;
}
public void setParentId(int parentId){ 
this.parentId=parentId;
}
public int getParentId(){ 
return parentId;
}
public void setTitle(String title){ 
this.title=title;
}
public String getTitle(){ 
return title;
}
public void setInfo(String info){ 
this.info=info;
}
public String getInfo(){ 
return info;
}

}