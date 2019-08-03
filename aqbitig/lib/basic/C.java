package aqbitig.lib.basic;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class C {

    private static final String initVector = "#AqBitig1404.tr;"; // 16 bytes IV

    public static String encrypt(String key, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key), iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String key, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key), iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static SecretKeySpec getSecretKey(String key) {
        try {
            SecretKeySpec secret = new SecretKeySpec(C.sha(key).substring(0, 16).getBytes("UTF-8"), "AES");
            return secret;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = ".Aqbitig@1404.tr"; // 32 bytes IV

        System.out.println(encrypt(key, "Hello World"));
        System.out.println(decrypt(key, encrypt(key, "Hello World")));

    }

    public static String sha(String text) {
        return sha256(text, 256);
    }

    public static String sha(String text, int bit) {
        return sha256(text, bit);
    }

    public static String sha256(String text, int bit) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-" + bit);
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(C.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) {
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    public static String b64encode(byte[] bytes) {
        Base64.Encoder encoder = Base64.getUrlEncoder();
        // Encoding URL
        if (bytes == null) {
            return "";
        } else {
            return encoder.encodeToString(bytes);
        }
    }

    public static byte[] b64decode(String str) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        // Decoding URl  
        return decoder.decode(str);
    }
}
