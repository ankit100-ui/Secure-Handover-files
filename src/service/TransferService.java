package service;
import dao.*;import model.*;import security.*;import java.nio.file.*;import java.util.*;
public class TransferService{
 private final UserDao users=new UserDao();private final DocumentDao docs=new DocumentDao();private final TransferDao dao=new TransferDao();private final AuditDao audit=new AuditDao();private final FileService files;
 public TransferService(FileService f){files=f;}
 public String create(int sender,int docId,String email)throws Exception{Document d=docs.owned(docId,sender);if(d==null)throw new IllegalArgumentException("Document not found or not owned by you");User r=users.byEmail(email);if(r==null)throw new IllegalArgumentException("Receiver is not registered");if(r.id()==sender)throw new IllegalArgumentException("Choose another receiver");String code=CryptoUtil.code(),salt=PasswordUtil.salt(),hash=PasswordUtil.hash(code,salt);int id=dao.create(docId,sender,r.id(),salt+":"+hash);audit.log(sender,"TRANSFER_CREATED","Transfer #"+id);return code;}
 public String receive(int receiver,int id,String code)throws Exception{Transfer t=dao.pending(id,receiver);if(t==null)throw new IllegalArgumentException("Pending transfer not found");String s=dao.codeHash(id);int p=s.indexOf(':');if(p<0||!PasswordUtil.verify(code,s.substring(0,p),s.substring(p+1)))throw new IllegalArgumentException("Invalid handover code");Path out=files.decrypt(docs.byId(t.documentId()));dao.received(id);audit.verify(id,CryptoUtil.sha256(Files.readAllBytes(out)));audit.log(receiver,"FILE_RECEIVED","Transfer #"+id);return out.toString();}
 public List<Transfer> sent(int u)throws Exception{return dao.list(u,true);}public List<Transfer> received(int u)throws Exception{return dao.list(u,false);}
}
