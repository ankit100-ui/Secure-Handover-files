package service;
import dao.*;import model.*;import security.PasswordUtil;import java.util.*;import java.util.concurrent.*;
public class AuthService{
 private final UserDao dao=new UserDao();private final AuditDao audit=new AuditDao();private final Map<String,Integer> sessions=new ConcurrentHashMap<>();
 public int register(String n,String e,String p)throws Exception{if(n==null||n.isBlank()||e==null||!e.contains("@")||p==null||p.length()<8)throw new IllegalArgumentException("Enter valid details; password must be 8+ characters");if(dao.byEmail(e)!=null)throw new IllegalArgumentException("Email already registered");String s=PasswordUtil.salt();int id=dao.create(n,e,PasswordUtil.hash(p,s),s);audit.log(id,"REGISTER","Account created");return id;}
 public String login(String e,String p)throws Exception{String[] x=dao.password(e);if(x==null||!PasswordUtil.verify(p,x[1],x[0]))throw new IllegalArgumentException("Invalid email or password");int id=dao.byEmail(e).id();String t=UUID.randomUUID().toString();sessions.put(t,id);audit.log(id,"LOGIN","Successful login");return t;}
 public void logout(String t){Integer id=sessions.remove(t);audit.log(id,"LOGOUT","Session ended");}
 public Integer id(String t){return t==null?null:sessions.get(t);} public User me(String t)throws Exception{Integer i=id(t);return i==null?null:dao.byId(i);}
}
