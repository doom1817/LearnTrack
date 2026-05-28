package expand.encryption;

import javax.crypto.Mac;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * HMAC 消息认证码示例
 * HMAC (Hash-based Message Authentication Code) 用于验证消息完整性和真实性
 * 常用于 API 签名、数据完整性验证
 */
public class HmacExample {

    /**
     * 生成 HMAC 密钥
     */
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        return keyGen.generateKey();
    }

    /**
     * 计算 HMAC-SHA256
     */
    public static String hmacSha256(String message, SecretKey key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        byte[] hmacBytes = mac.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(hmacBytes);
    }

    /**
     * 验证 HMAC
     */
    public static boolean verifyHmac(String message, String hmac, SecretKey key) throws NoSuchAlgorithmException, InvalidKeyException {
        String computedHmac = hmacSha256(message, key);
        return computedHmac.equals(hmac);
    }

    /**
     * API 签名示例
     */
    public static String signApiRequest(String method, String path, String body, SecretKey key) throws NoSuchAlgorithmException, InvalidKeyException {
        String message = method + "\n" + path + "\n" + body;
        return hmacSha256(message, key);
    }

    /**
     * 测试示例
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== HMAC 消息认证码示例 ===");

            // 生成密钥
            SecretKey key = generateKey();
            System.out.println("HMAC 密钥: " + Base64.getEncoder().encodeToString(key.getEncoded()));

            // 基本 HMAC 示例
            String message = "Hello, HMAC!";
            String hmac = hmacSha256(message, key);
            System.out.println("\n消息: " + message);
            System.out.println("HMAC: " + hmac);

            // 验证 HMAC
            boolean isValid = verifyHmac(message, hmac, key);
            System.out.println("验证结果: " + isValid);

            // 篡改消息验证
            String tamperedMessage = "Hello, HMAC! (tampered)";
            boolean isTamperedValid = verifyHmac(tamperedMessage, hmac, key);
            System.out.println("篡改消息验证: " + isTamperedValid);

            // API 签名示例
            System.out.println("\n=== API 签名示例 ===");
            String method = "POST";
            String path = "/api/users";
            String body = "{\"name\":\"John\",\"email\":\"john@example.com\"}";

            String signature = signApiRequest(method, path, body, key);
            System.out.println("HTTP 方法: " + method);
            System.out.println("路径: " + path);
            System.out.println("请求体: " + body);
            System.out.println("签名: " + signature);

            // 验证 API 签名
            String verifySignature = signApiRequest(method, path, body, key);
            System.out.println("签名验证: " + signature.equals(verifySignature));

            // 篡改请求体验证
            String tamperedBody = "{\"name\":\"John\",\"email\":\"hacker@example.com\"}";
            String tamperedSignature = signApiRequest(method, path, tamperedBody, key);
            System.out.println("篡改请求体验证: " + signature.equals(tamperedSignature));

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}