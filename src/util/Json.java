package util;
import java.util.regex.*;
public final class Json{
 private Json(){}
 public static String get(String j,String k){if(j==null)return null;Matcher m=Pattern.compile("\"" + Pattern.quote(k) + "\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"").matcher(j);return m.find()?m.group(1).replace("\\\"","\"").replace("\\\\","\\"):null;}
 public static Integer integer(String j,String k){Matcher m=Pattern.compile("\"" + Pattern.quote(k) + "\"\\s*:\\s*(\\d+)").matcher(j);return m.find()?Integer.valueOf(m.group(1)):null;}
 public static String esc(String s){return s==null?"":s.replace("\\","\\\\").replace("\"","\\\"").replace("\n","\\n").replace("\r","\\r");}
 public static String error(String s){return "{\"success\":false,\"message\":\""+esc(s)+"\"}";}
}
