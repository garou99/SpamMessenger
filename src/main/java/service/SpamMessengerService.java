package service;

import dto.Details;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class SpamMessengerService {

    public String getMessageResponse(Details details) {

        try {
            // Construct data
            String apiKey = "apikey=" + details.getApiKey();
            String message = "&message=" + details.getMessage();
            String sender = "&sender=" + details.getSender();
            String numbers = "&numbers=" + details.getPhoneNumber();

            // Send data

            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));

            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            log.info("Error while sending SMS " + e.getLocalizedMessage());
            return "Error";
        }

    }
}
