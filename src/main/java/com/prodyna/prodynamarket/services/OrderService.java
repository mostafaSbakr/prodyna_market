package com.prodyna.prodynamarket.services;

import com.prodyna.prodynamarket.models.Order;
import com.prodyna.prodynamarket.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void createNewOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(int id) {
        return orderRepository.findOrdersByUserId(id);
    }

    public Order getOrder(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent())
            return order.get();
        else
            throw new NoSuchElementException();
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrders() {
        List<Order> ordersList = new ArrayList<>();
        orderRepository.findAll().forEach(ordersList::add);
        return ordersList;
    }
}
