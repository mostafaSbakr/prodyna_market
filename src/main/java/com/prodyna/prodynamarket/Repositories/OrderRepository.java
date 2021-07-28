package com.prodyna.prodynamarket.Repositories;

import com.prodyna.prodynamarket.Models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository  extends CrudRepository<Order, Integer> {

    @Query ("FROM Order WHERE user_id = ?1")
    List<Order> findOrdersById(int id);
}
