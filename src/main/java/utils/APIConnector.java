package utils;

import com.google.gson.Gson;
import conf.TestConfig;
import conf.Wallet;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIConnector {
        private final OkHttpClient httpClient = new OkHttpClient();
        private final Gson gson = new Gson();
        private TestConfig config;


    public APIConnector(TestConfig config) {
        this.config = config;
    }


    public void deleteSecretFile(String accountID, String passphrase){
        StringBuilder reqestBody = new StringBuilder();
        reqestBody.append("requestType=deleteKey");
        reqestBody.append("&account="+accountID);
        reqestBody.append("&passphrase="+passphrase);
        sendReq(reqestBody.toString(),null);
    }


    private <T> T sendReq(String requestString, Class clazz, RequestBody body) {
        Request request = null;
        try {
            request = new Request.Builder()
                    .url(requestString)
                    .post(body)
                    .build();
            Response response = httpClient.newCall(request).execute();
            String responseText = response.body().string();
            //System.out.println(request.url()+" : "+responseText);
            return (T) gson.fromJson(responseText, clazz);
        } catch (Exception e) {
            System.out.println(request.url());
            e.printStackTrace();

        }
        return  null;
    }

    private void sendReq(String requestString, RequestBody body) {
        if (body == null){
            body = RequestBody.create(null, new byte[]{});
        }
        Request request = null;
        try {
            request = new Request.Builder()
                    .url(config.getHost()+"/apl?"+requestString)
                    .post(body)
                    .build();
            Response response = httpClient.newCall(request).execute();
            String responseText = response.body().string();
            //System.out.println(responseText);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
