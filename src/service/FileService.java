package service;
import dao.DocumentDao;import model.Document;import security.CryptoUtil;import java.nio.file.*;import java.util.UUID;
public class FileService{
 private final Path storage=Paths.get("storage"),received=Paths.get("received");private final DocumentDao dao=new DocumentDao();
 public FileService()throws Exception{Files.createDirectories(storage);Files.createDirectories(received);}
 public Document upload(int owner,String name,byte[] data)throws Exception{if(data.length>25*1024*1024)throw new IllegalArgumentException("Maximum file size is 25 MB");String clean=Paths.get(name).getFileName().toString(),stored=UUID.randomUUID()+".enc";Path tmp=storage.resolve(UUID.randomUUID()+".tmp"),out=storage.resolve(stored);Files.write(tmp,data);try{CryptoUtil.encrypt(tmp,out);}finally{Files.deleteIfExists(tmp);}int id=dao.create(owner,clean,stored,data.length);return dao.byId(id);}
 public Path decrypt(Document d)throws Exception{Path out=received.resolve(UUID.randomUUID()+"_"+Paths.get(d.originalName()).getFileName());CryptoUtil.decrypt(storage.resolve(d.storedName()),out);return out;}
}
