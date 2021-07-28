package com.prodyna.prodynamarket.repositories;

import com.prodyna.prodynamarket.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository  extends CrudRepository<Order, Integer> {

    @Query ("FROM Order WHERE user_id = ?1")
    List<Order> findOrdersByUserId(int userId);
}
