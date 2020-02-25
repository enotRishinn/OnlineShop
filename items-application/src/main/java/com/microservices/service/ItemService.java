package com.microservices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.model.CreateItem;
import com.microservices.model.Item;
import com.microservices.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemService {
    boolean checkItemAmount(int id, int amount) throws SQLException;
    void consume (ItemDTO itemDTO) throws SQLException, JsonProcessingException;
    Item addNewItem(CreateItem createItem) throws SQLException;
    Item getItemById(int id) throws SQLException;
    Item changeItemAmount(int id, int amount) throws SQLException;
    ArrayList<Item> getItemByName(String name) throws SQLException;
    ArrayList<Item> getAllItems() throws SQLException;
}