package com.prodyna.prodynamarket.Services;

import com.prodyna.prodynamarket.Models.Order;
import com.prodyna.prodynamarket.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        if (orderRepository.findById(id).isPresent())
            return orderRepository.findById(id).get();
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
