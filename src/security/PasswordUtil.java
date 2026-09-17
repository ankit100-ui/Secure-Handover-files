package security;
import java.security.*; import java.util.*; import javax.crypto.SecretKeyFactory; import javax.crypto.spec.PBEKeySpec;
public final class PasswordUtil{
 private static final int ITERATIONS=120000,KEY_LENGTH=256;
 private PasswordUtil(){}
 public static String salt(){byte[] b=new byte[16];new SecureRandom().nextBytes(b);return Base64.getEncoder().encodeToString(b);}
 public static String hash(String password,String salt)throws Exception{
  PBEKeySpec s=new PBEKeySpec(password.toCharArray(),Base64.getDecoder().decode(salt),ITERATIONS,KEY_LENGTH);
  return Base64.getEncoder().encodeToString(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(s).getEncoded());
 }
 public static boolean verify(String p,String salt,String expected)throws Exception{
  return MessageDigest.isEqual(Base64.getDecoder().decode(expected),Base64.getDecoder().decode(hash(p,salt)));
 }
}
