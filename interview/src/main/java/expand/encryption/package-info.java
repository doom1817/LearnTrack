/**
 * @author: doom
 * @date: 2026/05/28/11:55
 * @description: Java 加密方法示例集合
 *
 * <h2>概述</h2>
 * 本包包含除 bcrypt 外的常见加密方法实现示例，涵盖对称加密、非对称加密、
 * 哈希函数、密钥派生函数、消息认证码和数字签名六大类。
 *
 * <h2>加密方法分类</h2>
 *
 * <h3>1. 对称加密 (Symmetric Encryption)</h3>
 * <ul>
 *   <li><b>文件</b>: {@link AesExample}</li>
 *   <li><b>算法</b>: AES-GCM (Advanced Encryption Standard)</li>
 *   <li><b>原理</b>: 使用相同密钥进行加密和解密</li>
 *   <li><b>特点</b>: 速度快，适合大量数据加密</li>
 *   <li><b>场景</b>: 文件加密、数据库加密、通信加密</li>
 * </ul>
 *
 * <h3>2. 非对称加密 (Asymmetric Encryption)</h3>
 * <ul>
 *   <li><b>文件</b>: {@link RsaExample}</li>
 *   <li><b>算法</b>: RSA (Rivest-Shamir-Adleman)</li>
 *   <li><b>原理</b>: 公钥加密，私钥解密</li>
 *   <li><b>特点</b>: 安全性高，但速度较慢</li>
 *   <li><b>场景</b>: 密钥交换、SSL/TLS 证书</li>
 * </ul>
 *
 * <h3>3. 哈希函数 (Hash Functions)</h3>
 * <ul>
 *   <li><b>文件</b>: {@link HashExample}</li>
 *   <li><b>算法</b>: SHA-256, SHA-512, MD5</li>
 *   <li><b>原理</b>: 单向函数，将任意长度输入转换为固定长度输出</li>
 *   <li><b>特点</b>: 不可逆，确定性，雪崩效应</li>
 *   <li><b>场景</b>: 数据完整性校验、数字指纹</li>
 *   <li><b>警告</b>: 不要直接用于密码存储！</li>
 * </ul>
 *
 * <h3>4. 密钥派生函数 (Key Derivation Functions)</h3>
 * <ul>
 *   <li><b>文件</b>: {@link Pbkdf2Example}</li>
 *   <li><b>算法</b>: PBKDF2 (Password-Based Key Derivation Function 2)</li>
 *   <li><b>原理</b>: 从密码派生密钥，通过多次迭代增加破解难度</li>
 *   <li><b>特点</b>: bcrypt 的替代方案，NIST 标准</li>
 *   <li><b>场景</b>: 密码存储、密钥派生</li>
 *   <li><b>参数</b>: 迭代次数 ≥ 65536，密钥长度 256 位</li>
 * </ul>
 *
 * <h3>5. 消息认证码 (Message Authentication Codes)</h3>
 * <ul>
 *   <li><b>文件</b>: {@link HmacExample}</li>
 *   <li><b>算法</b>: HMAC-SHA256</li>
 *   <li><b>原理</b>: 使用密钥 + 哈希函数验证消息完整性和真实性</li>
 *   <li><b>特点</b>: 同时验证完整性和来源</li>
 *   <li><b>场景</b>: API 签名、JWT Token、消息认证</li>
 * </ul>
 *
 * <h3>6. 数字签名 (Digital Signatures)</h3>
 * <ul>
 *   <li><b>文件</b>: {@link DigitalSignatureExample}</li>
 *   <li><b>算法</b>: SHA256withRSA, SHA512withRSA</li>
 *   <li><b>原理</b>: 私钥签名，公钥验证</li>
 *   <li><b>特点</b>: 确保消息完整性和不可否认性</li>
 *   <li><b>场景</b>: 软件签名、电子合同、数字证书</li>
 * </ul>
 *
 * <h2>加密方法对比</h2>
 * <table border="1">
 *   <tr><th>方法</th><th>类型</th><th>可逆</th><th>速度</th><th>主要用途</th></tr>
 *   <tr><td>AES</td><td>对称加密</td><td>是</td><td>快</td><td>数据加密</td></tr>
 *   <tr><td>RSA</td><td>非对称加密</td><td>是</td><td>慢</td><td>密钥交换</td></tr>
 *   <tr><td>SHA-256</td><td>哈希</td><td>否</td><td>快</td><td>完整性校验</td></tr>
 *   <tr><td>PBKDF2</td><td>密钥派生</td><td>否</td><td>慢</td><td>密码存储</td></tr>
 *   <tr><td>HMAC</td><td>消息认证</td><td>否</td><td>快</td><td>API 签名</td></tr>
 *   <tr><td>数字签名</td><td>签名</td><td>否</td><td>慢</td><td>身份认证</td></tr>
 * </table>
 *
 * <h2>使用建议</h2>
 * <ul>
 *   <li><b>密码存储</b>: Argon2 > bcrypt > PBKDF2 > scrypt</li>
 *   <li><b>数据加密</b>: AES-256-GCM 或 ChaCha20-Poly1305</li>
 *   <li><b>密钥交换</b>: RSA-2048+ 或 ECDH</li>
 *   <li><b>数据完整性</b>: SHA-256 或 SHA-512</li>
 *   <li><b>API 签名</b>: HMAC-SHA256</li>
 *   <li><b>数字签名</b>: RSA-SHA256 或 ECDSA</li>
 * </ul>
 *
 * <h2>安全警告</h2>
 * <ul>
 *   <li>❌ 不要使用 MD5 或 SHA-1 进行安全相关操作</li>
 *   <li>❌ 不要使用 DES 或 3DES 进行加密</li>
 *   <li>❌ 不要使用 AES-ECB 模式</li>
 *   <li>❌ 不要在代码中硬编码密钥</li>
 *   <li>❌ 不要使用固定 IV（初始化向量）</li>
 *   <li>❌ 不要自己发明加密算法</li>
 * </ul>
 *
 * <h2>详细文档</h2>
 * 请参考 {@code README.md} 文件获取完整的加密方法详解和代码示例。
 *
 * <h2>参考资料</h2>
 * <ul>
 *   <li><a href="https://csrc.nist.gov/">NIST 密码标准</a></li>
 *   <li><a href="https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html">OWASP 密码存储备忘录</a></li>
 *   <li><a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html">Java Cryptography Architecture</a></li>
 * </ul>
 *
 * @see AesExample
 * @see RsaExample
 * @see HashExample
 * @see Pbkdf2Example
 * @see HmacExample
 * @see DigitalSignatureExample
 */
package expand.encryption;
