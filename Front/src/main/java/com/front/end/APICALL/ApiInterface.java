package com.front.end.APICALL;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.swing.text.AbstractDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiInterface {
    // HTTP-запрос GET
    public static String sendGet(String text) throws Exception {

        String url = "http://localhost:8081/" + text;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Значение по умолчанию - GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        // System.out.println("\nSending 'GET' request to URL : " + url);
        //   System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Распечатываем результат
        return response.toString();


    }

    public static String sendPost(Object obj, String text) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        HttpPost request = new HttpPost("http://localhost:8081/" + text);
        System.out.println(objectMapper.writeValueAsString(obj));
        StringEntity params = new StringEntity(objectMapper.writeValueAsString(obj));
        //request.addHeader("content-type", "application/x-www-form-urlencoded");
        request.addHeader("Content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);
        return response.toString();
    }
}

