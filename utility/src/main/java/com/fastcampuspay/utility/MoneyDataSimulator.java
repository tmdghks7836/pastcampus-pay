package com.fastcampuspay.utility;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoneyDataSimulator {

    private static final String INCREASE_API_ENDPOINT = "http://localhost:8083/money/increase-eda";

    private static final String DECREASE_API_ENDPOINT = "http://localhost:8083/money/decrease-eda";

    private static final String CREATE_MONEY_API_ENDPOINT = "http://localhost:8083//money/create-member-money";

    private static final String REGISTER_ACCOUNT_API_ENDPOINT = "http://localhost:8082/banking/account-eda";

    private static final String[] BANK_NAME = {"KBB", "신한", "우리"};

    public static void main(String[] args) throws InterruptedException, IOException {

        Random random = new Random();
        List<Integer> readyMemberList = new ArrayList<>();

        while (true) {

            //증액, 감액 하기위한 머니
            int amount = random.nextInt(20001) + 1;
            int targetMembershipId = random.nextInt(1001) + 1;


            registerAccountSimulator(REGISTER_ACCOUNT_API_ENDPOINT, targetMembershipId);
            createMemberMoneySimulator(CREATE_MONEY_API_ENDPOINT, targetMembershipId);
            Thread.sleep(1000);

            readyMemberList.add(targetMembershipId);

            increaseMemberMoneySimulator(INCREASE_API_ENDPOINT, amount, targetMembershipId);
            amount = random.nextInt(20001) + 1;

            Integer decreaseTargetMembershipId = readyMemberList.get(random.nextInt(readyMemberList.size()));
            increaseMemberMoneySimulator(DECREASE_API_ENDPOINT, amount, decreaseTargetMembershipId);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private static void increaseMemberMoneySimulator(String increaseApiEndpoint, int amount, int targetMembershipId) {
        try {
            URL url = new URL(increaseApiEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("membershipId", targetMembershipId);
            jsonRequestBody.put("amount", amount);

            call(increaseApiEndpoint, conn, jsonRequestBody);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void createMemberMoneySimulator(String createMoneyApiEndpoint, int targetMembershipId) {

        try {
            URL url = new URL(createMoneyApiEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("membershipId", targetMembershipId);

            call(createMoneyApiEndpoint, conn, jsonRequestBody);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void registerAccountSimulator(String registerAccountApiEndpoint, int targetMembershipId) throws IOException {

        try {
            URL url = new URL(registerAccountApiEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            Random random = new Random();

            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("bankAccountNumber", generateRandomAccountNumber());
            jsonRequestBody.put("bankName", BANK_NAME[random.nextInt(BANK_NAME.length)]);
            jsonRequestBody.put("membershipId", targetMembershipId);
            jsonRequestBody.put("linkedStatusIsValid", true);
            call(registerAccountApiEndpoint, conn, jsonRequestBody);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void call(String endpoint, HttpURLConnection conn, JSONObject jsonRequestBody) throws IOException {
        try (OutputStream os = conn.getOutputStream()) {
            byte[] requestBodyBytes = jsonRequestBody.toString().getBytes("utf-8");
            os.write(requestBodyBytes, 0, requestBodyBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response from " + endpoint + ": " + responseCode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to call " + endpoint + ". Response code: " + responseCode);
        }
    }

    private static String generateRandomAccountNumber() {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);

        }

        return sb.toString();

    }

}
