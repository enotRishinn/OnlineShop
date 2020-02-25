package com.microservices.database;

import com.microservices.model.Item;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper {
    private static final String ID = "id";
    private static final String BD_NAME = "items";
    private static final String TABLE_NAME = "item";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String AMOUNT = "amount";
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

    public boolean checkItemAmount(int id, int amount) throws SQLException {
        getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet != null) {
            resultSet.first();
            return resultSet.getInt(AMOUNT) + amount >= 0;
        } else {
            return false;
        }
    }

    public Item createItem(String name, float price, int amount) throws SQLException {
        getConnection();
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO " + TABLE_NAME + " (" + NAME + "," + PRICE + "," + AMOUNT + ") VALUES ('" +
                name + "'," + price + "," + amount + ");";
        statement.execute(sql);
        Statement statement1 = connection.createStatement();
        sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = (SELECT MAX(" + ID + " ) FROM "
                + TABLE_NAME + ")";
        ResultSet rs = statement1.executeQuery(sql);
        int id = 0;
        if (rs != null) {
            rs.absolute(1);
            id = rs.getInt(ID);
        }
        connection.close();
        return new Item(id, name, price, amount);
    }

    public Item changeItemAmount(int id, int amount) throws SQLException {
        getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + "=" + id + ";";
        ResultSet resultSet = statement.executeQuery(sql);
        int newAmount = amount;
        String name = "";
        float price = 0;
        while (resultSet.next()) {
            newAmount += resultSet.getInt(AMOUNT);
            name = resultSet.getString(NAME);
            price = resultSet.getFloat(PRICE);
        }
        if (newAmount >= 0) {
            sql = "UPDATE " + TABLE_NAME + " SET " + AMOUNT + " = " + newAmount + " WHERE " + ID + "=" + id + ";";
            statement.execute(sql);
            connection.close();
            return new Item(id, name, price, newAmount);
        } else {
            return null;
        }
    }

    private ArrayList<Item> getItems(String sql) throws SQLException {
        getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        ArrayList<Item> items= new ArrayList<>();

        if (rs != null) {
            while (rs.next()){
                Item item = new Item(rs.getInt(ID), rs.getString(NAME), rs.getFloat(PRICE), rs.getInt(AMOUNT));
                items.add(item);
            }
        }
        connection.close();
        return items;
    }

    public Item searchItemById(int id) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;
        return getItems(sql).get(0);
    }

    public ArrayList<Item> showAllItems() throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + AMOUNT + " > 0";
        return getItems(sql);
    }

    public ArrayList<Item> searchItemsByName(String name) throws SQLException {
        name = "%" + name + "%";
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME + " LIKE '" + name + "' && " + AMOUNT + " > 0";
        return getItems(sql);
    }
}