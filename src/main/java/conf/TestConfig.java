package conf;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.io.FileReader;
import java.net.URL;
import java.util.logging.Logger;

public class TestConfig {
    private JSONParser parser;
    private static TestConfig testConfig;
    private String host;
    private Wallet standartWallet;
    private Wallet vaultWallet;
    private String adminPass;
    private static Logger log = Logger.getLogger(TestConfig.class.getName());

    private TestConfig(){

        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = Test.class.getClassLoader().getResource("config.json");
            parser = new JSONParser();
            Object obj = parser.parse(new FileReader(url.getPath()));
            JSONObject jsonObject = (JSONObject) obj;
            host = (String) jsonObject.get("host");

            adminPass = (String) jsonObject.get("adminPassword");
            standartWallet= mapper.readValue(jsonObject.get("standartWallet").toString(), Wallet.class);
            vaultWallet= mapper.readValue(jsonObject.get("vaultWallet").toString(), Wallet.class);
            log.info("Configuration fihished");
        }
        catch (Exception e) {
            log.info("Configuration error: "+e.getMessage());
        }
    }
    public static TestConfig getTestConfig() {
        if (testConfig == null){
            testConfig = new TestConfig();
        }
        return testConfig;
    }

    public String getHost() {
        return host;
    }

    public Wallet getStandartWallet() {
        return standartWallet;
    }

    public Wallet getVaultWallet() {
        return vaultWallet;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public String getSecretFilePath(String walletID){
        return Test.class.getClassLoader().getResource("walletID").toString();

    }
}
