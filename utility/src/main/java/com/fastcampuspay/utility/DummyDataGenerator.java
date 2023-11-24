package com.fastcampuspay.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DummyDataGenerator {

    private static final String INCREASE_API_ENDPOINT = "http://localhost:8083/money/increase-eda";

    private static final String DECREASE_API_ENDPOINT = "http://localhost:8083/money/decrease-eda";

    private static final String CREATE_MONEY_API_ENDPOINT = "http://localhost:8083//money/create-member-money";

    private static final String REGISTER_ACCOUNT_API_ENDPOINT = "http://localhost:8083/banking/account";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/fastcampus_pay";

    private static final String DB_USER = "mysqluser";

    private static final String DB_PASSWORD = "mysqlpw";

    private static final String[] ADDRESSES = {"강남구","관악구","서초구"};

    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            generateDummyData(conn);
            
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void generateDummyData(Connection conn) throws SQLException {

        String insertQuery = "INSERT INTO membership (membership_id, address, email, is_corp, is_valid, name)" +
                " values (?,?,?,?,?,?) ";
        Random random = new Random();

        PreparedStatement pstmt = conn.prepareStatement(insertQuery);

        int numberOfDummyData = 9000;

        for (int i = 1000; i < numberOfDummyData + 1000; i++) {

            pstmt.setLong(1, i);
            pstmt.setString(2, ADDRESSES[random.nextInt(ADDRESSES.length)   ]);
            pstmt.setString(3, "email_" + i + "@example.com");
            pstmt.setBoolean(4, random.nextBoolean());
            pstmt.setBoolean(5, random.nextBoolean());
            pstmt.setString(6, "User " + i);
            pstmt.execute();
        }


    }
}
