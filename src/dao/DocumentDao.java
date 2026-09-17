package dao;
import config.Database;import model.Document;import java.sql.*;import java.util.*;
public class DocumentDao{
 private Document map(ResultSet r)throws SQLException{return new Document(r.getInt(1),r.getInt(2),r.getString(3),r.getString(4),r.getLong(5),String.valueOf(r.getTimestamp(6)));}
 public int create(int o,String n,String s,long z)throws SQLException{try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO documents(owner_id,original_name,stored_name,size_bytes) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){p.setInt(1,o);p.setString(2,n);p.setString(3,s);p.setLong(4,z);p.executeUpdate();ResultSet r=p.getGeneratedKeys();r.next();return r.getInt(1);}}
 public Document owned(int id,int o)throws SQLException{String q="SELECT id,owner_id,original_name,stored_name,size_bytes,uploaded_at FROM documents WHERE id=? AND owner_id=?";try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setInt(1,id);p.setInt(2,o);ResultSet r=p.executeQuery();return r.next()?map(r):null;}}
 public Document byId(int id)throws SQLException{String q="SELECT id,owner_id,original_name,stored_name,size_bytes,uploaded_at FROM documents WHERE id=?";try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setInt(1,id);ResultSet r=p.executeQuery();return r.next()?map(r):null;}}
 public List<Document> all(int o)throws SQLException{String q="SELECT id,owner_id,original_name,stored_name,size_bytes,uploaded_at FROM documents WHERE owner_id=? ORDER BY uploaded_at DESC";List<Document> a=new ArrayList<>();try(Connection c=Database.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setInt(1,o);ResultSet r=p.executeQuery();while(r.next())a.add(map(r));}return a;}
}
