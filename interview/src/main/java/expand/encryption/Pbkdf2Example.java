package expand.encryption;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * PBKDF2 密钥派生函数示例
 * PBKDF2 (Password-Based Key Derivation Function 2) 是 bcrypt 的替代方案
 * 通过多次迭代增加暴力破解难度
 */
public class Pbkdf2Example {

    private static final int ITERATIONS = 65536;  // 迭代次数
    private static final int KEY_LENGTH = 256;     // 密钥长度
    private static final int SALT_LENGTH = 16;     // 盐值长度

    /**
     * 使用 PBKDF2 生成密钥
     */
    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    /**
     * 生成随机盐值
     */
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 验证密码
     */
    public static boolean verifyPassword(String password, String storedHash, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String inputHash = hashPassword(password, salt);
        return storedHash.equals(inputHash);
    }

    /**
     * 将盐值和哈希值组合存储 (用于数据库存储)
     */
    public static String hashPasswordForStorage(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = generateSalt();
        String hash = hashPassword(password, salt);
        String saltBase64 = Base64.getEncoder().encodeToString(salt);
        return saltBase64 + ":" + hash;
    }

    /**
     * 从存储的字符串中验证密码
     */
    public static boolean verifyPasswordFromStorage(String password, String storedString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedString.split(":");
        if (parts.length != 2) {
            return false;
        }
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        return verifyPassword(password, parts[1], salt);
    }

    /**
     * 测试示例
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== PBKDF2 密钥派生函数示例 ===");

            String password = "mySecurePassword123";
            System.out.println("原始密码: " + password);

            // 生成盐值并哈希
            byte[] salt = generateSalt();
            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            String hash = hashPassword(password, salt);

            System.out.println("盐值 (Base64): " + saltBase64);
            System.out.println("哈希值 (Base64): " + hash);

            // 验证正确密码
            boolean isValid = verifyPassword(password, hash, salt);
            System.out.println("验证正确密码: " + isValid);

            // 验证错误密码
            boolean isInvalid = verifyPassword("wrongPassword", hash, salt);
            System.out.println("验证错误密码: " + isInvalid);

            // 存储格式示例
            System.out.println("\n=== 存储格式示例 ===");
            String storedString = hashPasswordForStorage(password);
            System.out.println("存储字符串: " + storedString);

            // 从存储字符串验证
            boolean storageValid = verifyPasswordFromStorage(password, storedString);
            System.out.println("从存储验证: " + storageValid);

            // 错误密码验证
            boolean storageInvalid = verifyPasswordFromStorage("wrongPassword", storedString);
            System.out.println("错误密码验证: " + storageInvalid);

            // 性能测试
            System.out.println("\n=== 性能测试 ===");
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                hashPasswordForStorage(password);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("10次哈希耗时: " + (endTime - startTime) + "ms");

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}