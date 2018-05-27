package org.zj.demo.bean;
import javax.persistence.Id;
import java.util.Date;
public class Comment{
@Id
private int id;
private int blogId;
private String content;
private Date date;
private String nickname;
private String email;
private String url;

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
public void setNickname(String nickname){ 
this.nickname=nickname;
}
public String getNickname(){ 
return nickname;
}
public void setEmail(String email){ 
this.email=email;
}
public String getEmail(){ 
return email;
}
public void setUrl(String url){ 
this.url=url;
}
public String getUrl(){ 
return url;
}

}