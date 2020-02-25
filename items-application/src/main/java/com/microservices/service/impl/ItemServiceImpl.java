package com.microservices.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.controller.ItemController;
import com.microservices.database.DBHelper;
import com.microservices.model.CreateItem;
import com.microservices.model.Item;
import com.microservices.model.ItemDTO;
import com.microservices.service.ItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ItemServiceImpl implements ItemService {
    private DBHelper dbHelper = new DBHelper();
    private final ObjectMapper objectMapper;

    @Autowired
    public ItemServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @KafkaListener(id = "Item", topics = {"items"}, containerFactory = "singleFactory")
    public void consume(ItemDTO itemDTOs) throws SQLException, JsonProcessingException {
        Logger log = Logger.getLogger(ItemController.class);
        log.info(objectMapper.writeValueAsString(itemDTOs));
        dbHelper.changeItemAmount(itemDTOs.id, itemDTOs.amount);
    }

    @Override
    public Item addNewItem(CreateItem createItem) throws SQLException {
        return dbHelper.createItem(createItem.name, createItem.price, createItem.amount);
    }

    @Override
    public boolean checkItemAmount(int id, int amount) throws SQLException {
        return dbHelper.checkItemAmount(id, amount);
    }

    @Override
    public Item getItemById(int id) throws SQLException {
        return dbHelper.searchItemById(id);
    }

    @Override
    public Item changeItemAmount(int id, int amount) throws SQLException {
        return dbHelper.changeItemAmount(id, amount);
    }

    @Override
    public ArrayList<Item> getItemByName(String name) throws SQLException {
        return dbHelper.searchItemsByName(name);
    }

    @Override
    public ArrayList<Item> getAllItems() throws SQLException {
        return dbHelper.showAllItems();
    }
}