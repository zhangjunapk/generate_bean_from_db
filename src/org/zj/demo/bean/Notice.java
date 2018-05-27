package org.zj.demo.bean;
import javax.persistence.Id;
public class Notice{
@Id
private int id;
private String title;
private String content;
private int order;
private String status;

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
public void setOrder(int order){ 
this.order=order;
}
public int getOrder(){ 
return order;
}
public void setStatus(String status){ 
this.status=status;
}
public String getStatus(){ 
return status;
}

}