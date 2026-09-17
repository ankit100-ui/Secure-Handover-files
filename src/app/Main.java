package app;
import com.sun.net.httpserver.*;import controller.ApiHandler;import service.FileService;import java.net.*;import java.nio.file.*;import java.util.concurrent.*;
public class Main{
 public static void main(String[]args)throws Exception{FileService f=new FileService();HttpServer s=HttpServer.create(new InetSocketAddress(8080),0);s.createContext("/",e->{Path root=Paths.get("web");String u=e.getRequestURI().getPath();if(u.equals("/"))u="/login.html";Path p=root.resolve(u.substring(1)).normalize();if(!p.startsWith(root)||!Files.exists(p)){e.sendResponseHeaders(404,-1);return;}String type=Files.probeContentType(p);if(type==null)type="text/plain";e.getResponseHeaders().set("Content-Type",type);byte[]b=Files.readAllBytes(p);e.sendResponseHeaders(200,b.length);try(var o=e.getResponseBody()){o.write(b);}});s.createContext("/api/",new ApiHandler(f));s.setExecutor(Executors.newFixedThreadPool(12));s.start();System.out.println("Secure Handover: http://localhost:8080");}
}
