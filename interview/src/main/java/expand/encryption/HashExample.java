package expand.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 哈希函数示例
 * 哈希函数是单向函数，不可逆
 * 常用于密码存储、数据完整性校验
 */
public class HashExample {

    /**
     * 计算字符串的 SHA-256 哈希值
     */
    public static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes());
        return bytesToHex(hash);
    }

    /**
     * 计算字符串的 SHA-512 哈希值
     */
    public static String sha512(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] hash = digest.digest(input.getBytes());
        return bytesToHex(hash);
    }

    /**
     * 计算字符串的 MD5 哈希值 (不推荐用于安全场景)
     */
    public static String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(input.getBytes());
        return bytesToHex(hash);
    }

    /**
     * 带盐值的哈希 (简单的密码哈希示例)
     * 注意：实际应用中应使用 bcrypt、PBKDF2 或 Argon2
     */
    public static String hashWithSalt(String password, String salt) throws NoSuchAlgorithmException {
        String saltedPassword = salt + password;
        return sha256(saltedPassword);
    }

    /**
     * 生成随机盐值
     */
    public static String generateSalt() {
        byte[] salt = new byte[16];
        new java.security.SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * 将字节数组转换为十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 测试示例
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== 哈希函数示例 ===");

            String originalText = "Hello, Hash!";
            System.out.println("原始文本: " + originalText);

            // SHA-256
            String sha256Hash = sha256(originalText);
            System.out.println("SHA-256: " + sha256Hash);

            // SHA-512
            String sha512Hash = sha512(originalText);
            System.out.println("SHA-512: " + sha512Hash);

            // MD5
            String md5Hash = md5(originalText);
            System.out.println("MD5: " + md5Hash);

            // 带盐值的哈希
            System.out.println("\n=== 带盐值的哈希 ===");
            String password = "myPassword123";
            String salt = generateSalt();
            String hashedPassword = hashWithSalt(password, salt);

            System.out.println("密码: " + password);
            System.out.println("盐值: " + salt);
            System.out.println("哈希值: " + hashedPassword);

            // 验证密码
            String inputPassword = "myPassword123";
            String inputHash = hashWithSalt(inputPassword, salt);
            System.out.println("验证密码: " + inputPassword);
            System.out.println("验证结果: " + hashedPassword.equals(inputHash));

            // 错误密码验证
            String wrongPassword = "wrongPassword";
            String wrongHash = hashWithSalt(wrongPassword, salt);
            System.out.println("错误密码: " + wrongPassword);
            System.out.println("验证结果: " + hashedPassword.equals(wrongHash));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}