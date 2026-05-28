# Java 加密方法详解

## 概述

本文档介绍除 bcrypt 之外的常见加密方法，包括分类、实现原理和使用场景。

## 目录

1. [加密方法分类](#加密方法分类)
2. [对称加密 - AES](#对称加密---aes)
3. [非对称加密 - RSA](#非对称加密---rsa)
4. [哈希函数 - SHA-256](#哈希函数---sha-256)
5. [密钥派生函数 - PBKDF2](#密钥派生函数---pbkdf2)
6. [消息认证码 - HMAC](#消息认证码---hmac)
7. [数字签名](#数字签名)
8. [加密方法对比](#加密方法对比)
9. [安全建议](#安全建议)

---

## 加密方法分类

```
加密方法
├── 对称加密 (Symmetric)
│   ├── AES (推荐)
│   ├── DES (已过时)
│   ├── 3DES (已过时)
│   └── ChaCha20
├── 非对称加密 (Asymmetric)
│   ├── RSA (推荐)
│   ├── ECC (椭圆曲线)
│   └── DSA (仅签名)
├── 哈希函数 (Hash)
│   ├── SHA-256 (推荐)
│   ├── SHA-512 (推荐)
│   ├── SHA-1 (已过时)
│   └── MD5 (已过时，不安全)
├── 密钥派生函数 (KDF)
│   ├── PBKDF2 (推荐)
│   ├── bcrypt (推荐)
│   ├── scrypt
│   └── Argon2 (最推荐)
├── 消息认证码 (MAC)
│   ├── HMAC-SHA256 (推荐)
│   └── HMAC-SHA512
└── 数字签名 (Signature)
    ├── RSA-SHA256 (推荐)
    └── ECDSA (推荐)
```

---

## 对称加密 - AES

### 原理

对称加密使用**同一个密钥**进行加密和解密。

```
明文 + 密钥 → 加密算法 → 密文
密文 + 密钥 → 解密算法 → 明文
```

### AES 算法

- **全称**：Advanced Encryption Standard
- **密钥长度**：128/192/256 位
- **分组模式**：GCM（推荐）、CBC、ECB（不推荐）

### GCM 模式优势

1. **认证加密**：同时提供机密性和完整性
2. **并行计算**：加密速度快
3. **无需填充**：简化实现

### 代码示例

```java
// 生成密钥
KeyGenerator keyGen = KeyGenerator.getInstance("AES");
keyGen.init(256);
SecretKey key = keyGen.generateKey();

// 加密
Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
cipher.init(Cipher.ENCRYPT_MODE, key, iv);
byte[] encrypted = cipher.doFinal(plaintext.getBytes());

// 解密
cipher.init(Cipher.DECRYPT_MODE, key, iv);
byte[] decrypted = cipher.doFinal(encrypted);
```

### 使用场景

- 文件加密
- 数据库加密
- 通信加密
- 大量数据加密

---

## 非对称加密 - RSA

### 原理

非对称加密使用**一对密钥**：公钥加密，私钥解密。

```
公钥（公开） → 加密 → 密文
私钥（保密） → 解密 → 明文
```

### RSA 算法

- **全称**：Rivest-Shamir-Adleman
- **密钥长度**：2048/4096 位
- **安全性**：基于大数分解难题

### 工作流程

```
1. 接收方生成密钥对（公钥 + 私钥）
2. 接收方发送公钥给发送方
3. 发送方用公钥加密数据
4. 发送方发送密文给接收方
5. 接收方用私钥解密数据
```

### 代码示例

```java
// 生成密钥对
KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
keyGen.initialize(2048);
KeyPair keyPair = keyGen.generateKeyPair();

// 公钥加密
Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
cipher.init(Cipher.ENCRYPT_MODE, publicKey);
byte[] encrypted = cipher.doFinal(plaintext.getBytes());

// 私钥解密
cipher.init(Cipher.DECRYPT_MODE, privateKey);
byte[] decrypted = cipher.doFinal(encrypted);
```

### 使用场景

- 密钥交换
- 数字签名
- SSL/TLS 证书
- 小量数据加密

---

## 哈希函数 - SHA-256

### 原理

哈希函数是**单向函数**，将任意长度输入转换为固定长度输出。

```
输入 → 哈希函数 → 固定长度输出（哈希值）
```

### 特性

1. **单向性**：无法从哈希值还原原始数据
2. **确定性**：相同输入产生相同输出
3. **雪崩效应**：微小变化导致巨大差异
4. **抗碰撞性**：难以找到两个不同输入产生相同哈希

### SHA 家族

| 算法 | 输出长度 | 安全性 |
|------|----------|--------|
| SHA-1 | 160 位 | 已不安全 |
| SHA-256 | 256 位 | 安全 |
| SHA-512 | 512 位 | 安全 |

### 代码示例

```java
MessageDigest digest = MessageDigest.getInstance("SHA-256");
byte[] hash = digest.digest(input.getBytes());
String hexHash = bytesToHex(hash);
```

### 使用场景

- 数据完整性校验
- 密码存储（需加盐）
- 数字指纹
- 区块链

### ⚠️ 注意

**不要直接用于密码存储**！应使用 PBKDF2、bcrypt 或 Argon2。

---

## 密钥派生函数 - PBKDF2

### 原理

PBKDF2 从密码派生密钥，通过**多次迭代**增加暴力破解难度。

```
密码 + 盐值 → 迭代 N 次 → 派生密钥
```

### 关键参数

1. **盐值 (Salt)**：随机生成，防止彩虹表攻击
2. **迭代次数**：越多越安全，推荐 ≥ 10000
3. **密钥长度**：256 位

### PBKDF2 vs bcrypt

| 特性 | PBKDF2 | bcrypt |
|------|--------|--------|
| 标准 | NIST 标准 | 事实标准 |
| 性能 | 较快 | 较慢 |
| 内存 | 较少 | 较多 |
| 抗 GPU | 弱 | 较强 |
| 推荐度 | ★★★★ | ★★★★★ |

### 代码示例

```java
// 生成盐值
byte[] salt = new byte[16];
new SecureRandom().nextBytes(salt);

// 派生密钥
PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
byte[] hash = factory.generateSecret(spec).getEncoded();
```

### 存储格式

```
盐值(Base64):哈希值(Base64)
```

### 使用场景

- **密码存储**（bcrypt 的替代方案）
- 密钥派生
- 口令保护

---

## 消息认证码 - HMAC

### 原理

HMAC 使用**密钥 + 哈希函数**验证消息完整性和真实性。

```
消息 + 密钥 → HMAC 算法 → 认证码
```

### HMAC vs 简单哈希

| 特性 | 简单哈希 | HMAC |
|------|----------|------|
| 密钥 | 无 | 有 |
| 完整性 | ✓ | ✓ |
| 认证 | ✗ | ✓ |
| 防篡改 | ✗ | ✓ |

### 代码示例

```java
// 生成密钥
KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
SecretKey key = keyGen.generateKey();

// 计算 HMAC
Mac mac = Mac.getInstance("HmacSHA256");
mac.init(key);
byte[] hmac = mac.doFinal(message.getBytes());
```

### 使用场景

- API 签名验证
- JWT Token
- 消息完整性校验
- 数据认证

---

## 数字签名

### 原理

数字签名使用**私钥签名，公钥验证**，确保消息完整性和来源。

```
发送方：消息 + 私钥 → 签名算法 → 数字签名
接收方：消息 + 签名 + 公钥 → 验证算法 → 有效/无效
```

### 签名 vs 加密

| 操作 | 加密 | 签名 |
|------|------|------|
| 目的 | 保密性 | 完整性 + 认证 |
| 密钥使用 | 公钥加密 | 私钥签名 |
| 解密/验证 | 私钥解密 | 公钥验证 |

### 代码示例

```java
// 签名
Signature signature = Signature.getInstance("SHA256withRSA");
signature.initSign(privateKey);
signature.update(data.getBytes());
byte[] signedBytes = signature.sign();

// 验证
signature.initVerify(publicKey);
signature.update(data.getBytes());
boolean isValid = signature.verify(signedBytes);
```

### 使用场景

- 软件签名
- 电子合同
- 数字证书
- 代码签名

---

## 加密方法对比

| 方法 | 类型 | 可逆 | 速度 | 主要用途 |
|------|------|------|------|----------|
| AES | 对称加密 | ✓ | 快 | 数据加密 |
| RSA | 非对称加密 | ✓ | 慢 | 密钥交换 |
| SHA-256 | 哈希 | ✗ | 快 | 完整性校验 |
| PBKDF2 | 密钥派生 | ✗ | 慢 | 密码存储 |
| HMAC | 消息认证 | ✗ | 快 | API 签名 |
| 数字签名 | 签名 | ✗ | 慢 | 身份认证 |

---

## 安全建议

### 密码存储

```
推荐顺序：Argon2 > bcrypt > PBKDF2 > scrypt

❌ 不要使用：
- 明文存储
- 简单 MD5/SHA
- 无盐哈希

✅ 应该使用：
- PBKDF2 + 随机盐 + 足够迭代次数
- bcrypt (cost factor ≥ 12)
- Argon2 (推荐)
```

### 数据加密

```
✅ 推荐：
- AES-256-GCM
- ChaCha20-Poly1305

❌ 避免：
- DES / 3DES
- AES-ECB 模式
- 自己实现加密算法
```

### 密钥管理

```
1. 使用安全的随机数生成器
2. 密钥长度足够（AES ≥ 256, RSA ≥ 2048）
3. 定期轮换密钥
4. 安全存储密钥（HSM、密钥管理服务）
5. 不要在代码中硬编码密钥
```

### 常见错误

```
❌ MD5 用于密码存储
❌ ECB 模式加密
❌ 使用固定 IV
❌ 密钥长度不足
❌ 自己发明加密算法
❌ 在日志中输出密钥
```

---

## 参考资料

- [NIST 密码标准](https://csrc.nist.gov/)
- [OWASP 密码存储备忘录](https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html)
- [Java Cryptography Architecture](https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html)
