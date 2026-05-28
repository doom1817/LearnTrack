package expand.encryption;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

/**
 * RSA 非对称加密示例
 * RSA 是最常用的非对称加密算法
 * 使用公钥加密，私钥解密
 */
public class RsaExample {

    private static final int RSA_KEY_SIZE = 2048;

    /**
     * 生成 RSA 密钥对
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(RSA_KEY_SIZE);
        return keyGen.generateKeyPair();
    }

    /**
     * RSA 公钥加密
     */
    public static String encrypt(String plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(plaintext.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    /**
     * RSA 私钥解密
     */
    public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(plainText, "UTF-8");
    }

    /**
     * 使用私钥签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes("UTF-8"));
        byte[] signedBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    /**
     * 使用公钥验证签名
     */
    public static boolean verify(String data, String signatureStr, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes("UTF-8"));
        return signature.verify(Base64.getDecoder().decode(signatureStr));
    }

    /**
     * 测试示例
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== RSA 非对称加密示例 ===");

            // 生成密钥对
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            System.out.println("公钥: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            System.out.println("私钥: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

            // 原始文本
            String originalText = "Hello, RSA Encryption!";
            System.out.println("\n原始文本: " + originalText);

            // 加密
            String encrypted = encrypt(originalText, publicKey);
            System.out.println("公钥加密后: " + encrypted);

            // 解密
            String decrypted = decrypt(encrypted, privateKey);
            System.out.println("私钥解密后: " + decrypted);

            // 验证
            System.out.println("解密验证: " + originalText.equals(decrypted));

            // 数字签名示例
            System.out.println("\n=== 数字签名示例 ===");
            String message = "This is a message to be signed";
            String signature = sign(message, privateKey);
            System.out.println("签名: " + signature);

            boolean isValid = verify(message, signature, publicKey);
            System.out.println("签名验证: " + isValid);

            // 篡改消息验证
            String tamperedMessage = "This is a tampered message";
            boolean isTamperedValid = verify(tamperedMessage, signature, publicKey);
            System.out.println("篡改消息验证: " + isTamperedValid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}