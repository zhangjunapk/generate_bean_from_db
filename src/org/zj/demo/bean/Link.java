package org.zj.demo.bean;
import javax.persistence.Id;
import java.util.Date;
public class Link{
@Id
private int id;
private String name;
private String url;
private String info;
private int order;
private String lxfs;
private Date date;

public void setId(int id){ 
this.id=id;
}
public int getId(){ 
return id;
}
public void setName(String name){ 
this.name=name;
}
public String getName(){ 
return name;
}
public void setUrl(String url){ 
this.url=url;
}
public String getUrl(){ 
return url;
}
public void setInfo(String info){ 
this.info=info;
}
public String getInfo(){ 
return info;
}
public void setOrder(int order){ 
this.order=order;
}
public int getOrder(){ 
return order;
}
public void setLxfs(String lxfs){ 
this.lxfs=lxfs;
}
public String getLxfs(){ 
return lxfs;
}
public void setDate(Date date){ 
this.date=date;
}
public Date getDate(){ 
return date;
}

}