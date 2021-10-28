import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

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

            System.out.println("公钥:" + pubKeyBase64);
            System.out.println("私钥:" + priKeyBase64);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
