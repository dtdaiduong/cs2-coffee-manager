package com.md2.function;

import com.md2.mainItems.Order;
import com.md2.ulits.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    public static String path = "data/order.csv";
    List<Order> orders = new ArrayList<>();

    public OrderService() {
        getOrders();
    }

    @Override
    public List<Order> getOrders() {
        List<Order> newOrders = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newOrders.add(new Order(record));
        }
        return orders= newOrders;
    }

    @Override
    public void add(Order newOrder) {
        orders.add(newOrder);
        CSVUtils.write(path, orders);
    }

    @Override
    public void update() {
        CSVUtils.write(path, orders);
    }


    @Override
    public Order getOrderById(double id) {
        for (Order order : orders) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public boolean exist(double id) {
        return getOrderById(id) != null;
    }

    @Override
    public boolean checkDuplicateId(double id) {
        for (Order order : getOrders()) {
            if (order.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public void remove(Order order) {
        orders.remove(order);
        update();
    }
}
