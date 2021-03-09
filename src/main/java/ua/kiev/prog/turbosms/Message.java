package ua.kiev.prog.turbosms;

import com.google.gson.Gson;
import org.hibernate.engine.spi.Status;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Message {
    private String[] recipients;
    private Sms sms;

    public Message(String[] recipients, Sms sms) {
        this.recipients = recipients;
        this.sms = sms;
    }

    public Message() {
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "Message{" +
                "recipients=" + Arrays.toString(recipients) +
                ", sms=" + sms +
                '}';
    }

    public static String send(String phone, String text) throws IOException {
        Message message = new Message();
        Sms sms = new Sms();
        URL url = new URL("https://api.turbosms.ua/message/send.json");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestProperty ("Authorization", "Basic 19d1dd9ce98508b3dc89c59a86a2d479774d39f6");
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        message.setRecipients(new String[]{phone});
        sms.setSender("prog.kiev.ua");
        sms.setText(text);
        message.setSms(sms);
        String json = new Gson().toJson(message);
        byte[] postData = json.getBytes( StandardCharsets.UTF_8 );
        try( DataOutputStream wr = new DataOutputStream(http.getOutputStream())) {
            wr.write(postData);
        }
        Reader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c;(c = in.read()) >= 0; ) {
            sb.append((char)c);
        }
        String response = sb.toString();

        return response;
    }
}
