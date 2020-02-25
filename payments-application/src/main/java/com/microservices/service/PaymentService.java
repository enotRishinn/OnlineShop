package com.microservices.service;

import com.microservices.model.AddPayment;
import com.microservices.model.OrderDTO;
import com.microservices.model.Payment;
import com.microservices.model.StatusDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentService {
    void send(StatusDTO statusDTO);
    Payment getPaymentById(int id) throws SQLException;
    ArrayList<Payment> getPaymentsByOrderId(int orderId) throws SQLException;
    ArrayList<Payment> showAllPayments() throws SQLException;
    OrderDTO addPayment(AddPayment addPayment) throws SQLException;
}
