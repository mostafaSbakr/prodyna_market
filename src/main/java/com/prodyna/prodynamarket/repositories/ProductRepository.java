package com.prodyna.prodynamarket.repositories;

import com.prodyna.prodynamarket.models.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Product WHERE name = ?1")
    void deleteByName(String name);

    @Query("FROM Product WHERE name = ?1 ")
    List<Product> findProductByName(String name);
}
