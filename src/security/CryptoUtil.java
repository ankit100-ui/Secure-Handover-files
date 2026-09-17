package security;
import java.nio.file.*;import java.security.*;import java.util.*;import javax.crypto.*;import javax.crypto.spec.*;
public final class CryptoUtil{
 private static final int IV=12,TAG=128;private CryptoUtil(){}
 private static byte[] key(){
  String x=System.getenv("SECURE_HANDOVER_KEY");if(x==null||x.isBlank())throw new IllegalStateException("Set SECURE_HANDOVER_KEY");
  byte[] k=x.getBytes(java.nio.charset.StandardCharsets.UTF_8);
if(k.length!=32)throw new IllegalStateException("Key must be 32 bytes");return k;
 }
 public static void encrypt(Path in,Path out)throws Exception{byte[] iv=new byte[IV];new SecureRandom().nextBytes(iv);Cipher c=Cipher.getInstance("AES/GCM/NoPadding");c.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(key(),"AES"),new GCMParameterSpec(TAG,iv));try(var a=Files.newInputStream(in);var b=Files.newOutputStream(out)){b.write(iv);copy(c,a,b);}}
 public static void decrypt(Path in,Path out)throws Exception{try(var a=Files.newInputStream(in)){byte[] iv=a.readNBytes(IV);if(iv.length!=IV)throw new IllegalArgumentException("Invalid encrypted file");Cipher c=Cipher.getInstance("AES/GCM/NoPadding");c.init(Cipher.DECRYPT_MODE,new SecretKeySpec(key(),"AES"),new GCMParameterSpec(TAG,iv));try(var b=Files.newOutputStream(out)){copy(c,a,b);}}}
 private static void copy(Cipher c,java.io.InputStream a,java.io.OutputStream b)throws Exception{byte[] x=new byte[8192];int n;while((n=a.read(x))!=-1){byte[] y=c.update(x,0,n);if(y!=null)b.write(y);}byte[] y=c.doFinal();if(y!=null)b.write(y);}
 public static String code(){byte[] b=new byte[6];new SecureRandom().nextBytes(b);StringBuilder s=new StringBuilder();for(byte x:b)s.append(String.format("%02X",x));return s.toString();}
 public static String sha256(byte[] b)throws Exception{return Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(b));}
}
