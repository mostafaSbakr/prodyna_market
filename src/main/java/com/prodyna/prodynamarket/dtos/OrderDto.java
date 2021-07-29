package com.prodyna.prodynamarket.dtos;

import com.prodyna.prodynamarket.models.Product;
import com.prodyna.prodynamarket.models.User;
import lombok.Data;


import java.util.Date;

//@Value // better for Dtos, no setters --> check --> throws exception
//@Builder(toBuilder = true) ==> to change immulatlll
@Data
public class OrderDto {
    private int orderId;
    private User user;
    private Date orderDate;
    private Product product;
    private double price;
}
