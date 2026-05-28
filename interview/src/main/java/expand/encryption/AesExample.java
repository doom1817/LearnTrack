package expand.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES 对称加密示例
 * AES (Advanced Encryption Standard) 是最常用的对称加密算法
 * 支持 128, 192, 256 位密钥长度
 */
public class AesExample {

    private static final int AES_KEY_SIZE = 256;
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16;

    /**
     * 生成 AES 密钥
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        return keyGen.generateKey();
    }

    /**
     * AES-GCM 加密
     * GCM 模式提供认证加密，同时保证机密性和完整性
     */
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        // 生成随机 IV (初始化向量)
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        // 初始化 Cipher
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);

        // 加密
        byte[] cipherText = cipher.doFinal(plaintext.getBytes("UTF-8"));

        // 将 IV 和密文组合
        byte[] combined = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    /**
     * AES-GCM 解密
     */
    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        byte[] combined = Base64.getDecoder().decode(encryptedText);

        // 分离 IV 和密文
        byte[] iv = new byte[GCM_IV_LENGTH];
        byte[] cipherText = new byte[combined.length - GCM_IV_LENGTH];
        System.arraycopy(combined, 0, iv, 0, iv.length);
        System.arraycopy(combined, iv.length, cipherText, 0, cipherText.length);

        // 初始化 Cipher
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);

        // 解密
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText, "UTF-8");
    }

    /**
     * 测试示例
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== AES 对称加密示例 ===");

            // 生成密钥
            SecretKey key = generateKey();
            System.out.println("生成的密钥: " + Base64.getEncoder().encodeToString(key.getEncoded()));

            // 原始文本
            String originalText = "Hello, AES Encryption!";
            System.out.println("原始文本: " + originalText);

            // 加密
            String encrypted = encrypt(originalText, key);
            System.out.println("加密后: " + encrypted);

            // 解密
            String decrypted = decrypt(encrypted, key);
            System.out.println("解密后: " + decrypted);

            // 验证
            System.out.println("解密验证: " + originalText.equals(decrypted));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}