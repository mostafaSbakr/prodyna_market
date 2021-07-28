package com.prodyna.prodynamarket.repositories;

import com.prodyna.prodynamarket.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("FROM User WHERE userName = ?1")
    List<User> findUserByName(String userName);
}
