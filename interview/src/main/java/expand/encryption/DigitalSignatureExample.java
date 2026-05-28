package expand.encryption;

import java.security.*;
import java.util.Base64;

/**
 * 数字签名示例
 * 数字签名用于验证消息完整性和来源
 * 常用于软件签名、合同签署、电子证书
 */
public class DigitalSignatureExample {

    /**
     * 生成 RSA 密钥对
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    /**
     * 使用 SHA256withRSA 签名
     */
    public static String signWithSHA256(String data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signedBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    /**
     * 使用 SHA256withRSA 验证签名
     */
    public static boolean verifyWithSHA256(String data, String signatureStr, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        return signature.verify(Base64.getDecoder().decode(signatureStr));
    }

    /**
     * 使用 SHA512withRSA 签名
     */
    public static String signWithSHA512(String data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA512withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signedBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    /**
     * 使用 SHA512withRSA 验证签名
     */
    public static boolean verifyWithSHA512(String data, String signatureStr, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA512withRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        return signature.verify(Base64.getDecoder().decode(signatureStr));
    }

    /**
     * 测试示例
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== 数字签名示例 ===");

            // 生成密钥对
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            System.out.println("公钥: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            System.out.println("私钥: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

            // 原始数据
            String data = "This is a document to be signed";
            System.out.println("\n原始数据: " + data);

            // SHA256withRSA 签名
            System.out.println("\n=== SHA256withRSA 签名 ===");
            String signatureSHA256 = signWithSHA256(data, privateKey);
            System.out.println("签名: " + signatureSHA256);

            // 验证 SHA256withRSA 签名
            boolean isValidSHA256 = verifyWithSHA256(data, signatureSHA256, publicKey);
            System.out.println("验证结果: " + isValidSHA256);

            // 篡改数据验证
            String tamperedData = "This is a tampered document";
            boolean isTamperedValidSHA256 = verifyWithSHA256(tamperedData, signatureSHA256, publicKey);
            System.out.println("篡改数据验证: " + isTamperedValidSHA256);

            // SHA512withRSA 签名
            System.out.println("\n=== SHA512withRSA 签名 ===");
            String signatureSHA512 = signWithSHA512(data, privateKey);
            System.out.println("签名: " + signatureSHA512);

            // 验证 SHA512withRSA 签名
            boolean isValidSHA512 = verifyWithSHA512(data, signatureSHA512, publicKey);
            System.out.println("验证结果: " + isValidSHA512);

            // 文件签名示例
            System.out.println("\n=== 文件签名示例 ===");
            String fileContent = "This is the content of a file";
            String fileSignature = signWithSHA256(fileContent, privateKey);
            System.out.println("文件内容: " + fileContent);
            System.out.println("文件签名: " + fileSignature);

            // 验证文件签名
            boolean fileValid = verifyWithSHA256(fileContent, fileSignature, publicKey);
            System.out.println("文件签名验证: " + fileValid);

            // 篡改文件内容验证
            String tamperedFileContent = "This is the tampered content of a file";
            boolean tamperedFileValid = verifyWithSHA256(tamperedFileContent, fileSignature, publicKey);
            System.out.println("篡改文件验证: " + tamperedFileValid);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
    }
}