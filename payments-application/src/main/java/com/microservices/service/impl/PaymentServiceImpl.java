package com.microservices.service.impl;

import com.microservices.database.DBHelper;
import com.microservices.model.AddPayment;
import com.microservices.model.OrderDTO;
import com.microservices.model.Payment;
import com.microservices.model.StatusDTO;
import com.microservices.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class PaymentServiceImpl implements PaymentService {
    private DBHelper dbHelper = new DBHelper();
    private final KafkaTemplate kafkaOrderTemplateSend;

    @Autowired
    public PaymentServiceImpl(KafkaTemplate kafkaOrderTemplateSend) {
        this.kafkaOrderTemplateSend = kafkaOrderTemplateSend;
    }

    @Override
    public void send(StatusDTO statusDTO) {
        kafkaOrderTemplateSend.send("orders", statusDTO);
    }

    @Override
    public Payment getPaymentById(int id) throws SQLException {
        return dbHelper.getPaymentById(id);
    }

    @Override
    public ArrayList<Payment> getPaymentsByOrderId(int orderId) throws SQLException {
        return dbHelper.searchItemsByOrderId(orderId);
    }

    @Override
    public ArrayList<Payment> showAllPayments() throws SQLException {
        return dbHelper.showAllPayments();
    }

    @Override
    public OrderDTO addPayment(AddPayment addPayment) throws SQLException {
        return dbHelper.createPayment(addPayment);
    }
}
