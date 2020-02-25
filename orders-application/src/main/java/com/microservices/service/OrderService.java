package com.microservices.service;

import com.microservices.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    Item sendHttpToItem(Integer id, Integer amount) throws IOException;
    List<ItemDTO> getItemDTOS(int id) throws SQLException;
    void send(ItemDTO itemDTOS);
    void consume(StatusDTO statusDTO) throws SQLException;
    ArrayList<Order> getAllOrders() throws SQLException;
    Order getOrderById(int id) throws SQLException;
    OrderDTO changeOrderStatus(int id, OrderStatus status) throws SQLException;
    int addItemToOrder(String order_id, int item_id, int item_amount, float item_price, String item_name, String username) throws SQLException;
    void decreaseItemAmount(int order_id, int item_id, int item_amount) throws SQLException;
}
