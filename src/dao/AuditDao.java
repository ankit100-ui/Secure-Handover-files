package dao;
import config.Database;import java.sql.*;
public class AuditDao{
 public void log(Integer u,String a,String d){try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO audit_logs(user_id,action,details) VALUES(?,?,?)")){if(u==null)p.setNull(1,Types.INTEGER);else p.setInt(1,u);p.setString(2,a);p.setString(3,d);p.executeUpdate();}catch(SQLException e){e.printStackTrace();}}
 public void verify(int t,String h){try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO verification_records(transfer_id,verification_hash) VALUES(?,?)")){p.setInt(1,t);p.setString(2,h);p.executeUpdate();}catch(SQLException e){e.printStackTrace();}}
}
