package com.md2.function;

import com.md2.mainItems.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders();

    void add(Order newOrder);

    void update();


    Order getOrderById(double id);

    boolean exist(double id);

    boolean checkDuplicateId(double id);

    void remove(Order order);
}
