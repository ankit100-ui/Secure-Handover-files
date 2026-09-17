package dao;
import config.Database;import model.User;import java.sql.*;
public class UserDao{
 public User byEmail(String e)throws SQLException{try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement("SELECT id,name,email FROM users WHERE email=?")){p.setString(1,e);ResultSet r=p.executeQuery();return r.next()?new User(r.getInt(1),r.getString(2),r.getString(3)):null;}}
 public User byId(int id)throws SQLException{try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement("SELECT id,name,email FROM users WHERE id=?")){p.setInt(1,id);ResultSet r=p.executeQuery();return r.next()?new User(r.getInt(1),r.getString(2),r.getString(3)):null;}}
 public String[] password(String e)throws SQLException{try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement("SELECT password_hash,password_salt FROM users WHERE email=?")){p.setString(1,e);ResultSet r=p.executeQuery();return r.next()?new String[]{r.getString(1),r.getString(2)}:null;}}
 public int create(String n,String e,String h,String s)throws SQLException{try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO users(name,email,password_hash,password_salt) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){p.setString(1,n);p.setString(2,e);p.setString(3,h);p.setString(4,s);p.executeUpdate();ResultSet r=p.getGeneratedKeys();r.next();return r.getInt(1);}}
}
