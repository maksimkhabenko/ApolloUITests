package conf;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.net.URL;
import java.util.logging.Logger;

public class TestConfig {
    private static TestConfig testConfig;
    private String host;
    private Wallet standartWallet;
    private Wallet vaultWallet;
    private String adminPass;
    private String chainID;
    private static final Logger log = Logger.getLogger(TestConfig.class.getName());


    private static TestConfig TestConfigInit(){

        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = TestConfig.class.getClassLoader().getResource("config.json");
            testConfig  = mapper.readValue(new URL(url.toString()), TestConfig.class);
            System.out.println(url);
            return testConfig;

        }
        catch (Exception e) {
            log.info("Configuration error: "+e.getMessage());
        }

     return null;
    }
    public static TestConfig getTestConfig() {
        if (testConfig == null){
            testConfig = TestConfigInit();
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

    public String getChainID() {
        return chainID;
    }



    public String getSecretFilePath(String walletID){
        return TestConfig.class.getClassLoader().getResource("walletID").toString();

    }
}
