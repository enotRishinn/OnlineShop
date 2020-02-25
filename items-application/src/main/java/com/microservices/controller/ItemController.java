package com.microservices.controller;

import com.microservices.model.CreateItem;
import com.microservices.model.Item;
import com.microservices.service.ItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("warehouse")
public class ItemController {
    private ItemService itemService;
    private static final Logger log = Logger.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping (value = "items")
    @ResponseStatus(HttpStatus.CREATED)
    public Item addNewItem(@Valid @RequestBody CreateItem createItem) {
        try {
            return itemService.addNewItem(createItem);
            //log.info("New item was added: " + createItem.toString());
        } catch (SQLException e) {
            log.error("Error with adding the item: " + e.toString());
            return null;
        }
    }

    @GetMapping(value = "items")
    public ArrayList<Item> getAllItems() {
        try {
            ArrayList<Item> temp = itemService.getAllItems();
            log.info("All existing items were found");
            return temp;
        } catch (SQLException e) {
            log.error("Error with finding items: " + e.toString());
            return null;
        }
    }

    @GetMapping(value = "/items/{item_id}")
    public Item getItemById(@PathVariable int item_id) {
        try {
            Item temp = itemService.getItemById(item_id);
            log.info("Item with id = " + item_id + " was found: " + temp.toString());
            return temp;
        } catch (SQLException e) {
            log.error("Item with id = " + item_id + "was not found: " + e.toString());
            return null;
        }
    }

    @PutMapping(value = "items/{id}/addition/{amount}")
    public Item changeItem(@PathVariable int id, @PathVariable int amount) {
        try {
            if (!itemService.checkItemAmount(id, amount)) {
                return null;
            } else {
                return itemService.changeItemAmount(id, amount);
            }
            //log.info("To amount of item with " + id + " was  added" + amount);
        } catch (SQLException e) {
            log.error("Error with updating amount of item with id = " + id + ": " + e.toString());
            return null;
        }
    }

    @GetMapping(value = "items/name/{name}")
    public ArrayList<Item> getItemsByName(@PathVariable String name) {
        try {
            ArrayList<Item> temp = itemService.getItemByName(name);
            log.info("Items included substring '" + name + "' was found");
            return temp;
        } catch (SQLException e) {
            log.error("Items included substring '" + name + "' was not found: " + e.toString());
            return null;
        }
    }
}


