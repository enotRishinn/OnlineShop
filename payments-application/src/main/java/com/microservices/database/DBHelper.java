package com.microservices.database;

import com.microservices.model.AddPayment;
import com.microservices.model.OrderDTO;
import com.microservices.model.Payment;
import com.microservices.model.PaymentStatus;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper {
    private static final String ID = "id";
    private static final String BD_NAME = "payments";
    private static final String TABLE_NAME = "payment";
    private static final String STATUS = "paymentStatus";
    private static final String ORDER = "orderID";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BD_NAME;
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static Connection connection;


    private void getConnection() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.err.println("Не удалось загрузить класс драйвера");
        }
    }

    public OrderDTO createPayment(AddPayment addPayment) throws SQLException {
        getConnection();
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO " + TABLE_NAME + " (" + STATUS + "," + ORDER + ") VALUES ('" +
                addPayment.status + "'," + addPayment.orderId + ");";
        statement.execute(sql);
        connection.close();
        return new OrderDTO(addPayment.orderId);
    }

    private ArrayList<Payment> getItems(String sql) throws SQLException {
        getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        ArrayList<Payment> payments= new ArrayList<>();

        if (rs != null) {
            while (rs.next()){
                Payment payment = new Payment(rs.getInt(ID), PaymentStatus.valueOf(rs.getString(STATUS)), rs.getInt(ORDER));
                payments.add(payment);
            }
        }
        connection.close();
        return payments;
    }

    public ArrayList<Payment> showAllPayments() throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return getItems(sql);
    }

    public Payment getPaymentById(int id) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + "=" + id;
        return getItems(sql).get(0);
    }

    public ArrayList<Payment> searchItemsByOrderId(int orderID) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ORDER + "=" + orderID;
        return getItems(sql);
    }
}