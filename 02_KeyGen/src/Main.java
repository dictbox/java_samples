import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

/**
 * 生成RSA公私钥的Base64字符串(PEM)。
 * 注：JAVA标准库只能生成PKCS#8格式的密钥
 */
public class Main {

    public static void main(String[] args) {
        generateRSAKey();
    }

    public static void generateRSAKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();

            PublicKey pubKey = kp.getPublic();
            PrivateKey priKey = kp.getPrivate();

            String pubKeyBase64 = Base64.getEncoder().encodeToString(pubKey.getEncoded());
            String priKeyBase64 = Base64.getEncoder().encodeToString(priKey.getEncoded());

            System.out.println("PublicKey:" + pubKeyBase64);
            System.out.println("PrivateKey:" + priKeyBase64);

            writePemFile(priKey.getEncoded(), "PRIVATE KEY", "private_key.pem");
            writePemFile(pubKey.getEncoded(), "PUBLIC KEY", "public_key.pem");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param key
     * @param description
     * @param filename
     */
    private static void writePemFile(byte[] key, String description, String filename) {
        try (FileWriter writer = new FileWriter(new File(filename))) {
            writer.write("-----BEGIN " + description + "-----\n");
            writer.write(Base64.getEncoder().encodeToString(key));
            writer.write("\n-----END " + description + "-----\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
