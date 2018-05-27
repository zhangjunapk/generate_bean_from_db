package org.zj.demo.bean;
public class User{
private int id;
private String username;
private String password;
private String picLink;
private String nickname;
private String email;
private String status;

public void setId(int id){ 
this.id=id;
}
public int getId(){ 
return id;
}
public void setUsername(String username){ 
this.username=username;
}
public String getUsername(){ 
return username;
}
public void setPassword(String password){ 
this.password=password;
}
public String getPassword(){ 
return password;
}
public void setPicLink(String picLink){ 
this.picLink=picLink;
}
public String getPicLink(){ 
return picLink;
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
public void setStatus(String status){ 
this.status=status;
}
public String getStatus(){ 
return status;
}

}