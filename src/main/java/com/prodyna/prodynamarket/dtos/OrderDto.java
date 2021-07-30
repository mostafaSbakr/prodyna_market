package com.prodyna.prodynamarket.dtos;

import com.prodyna.prodynamarket.models.Product;
import com.prodyna.prodynamarket.models.User;
import lombok.Data;
import java.util.Date;

@Data
public class OrderDto {
    private int orderId;
    private User user;
    private Date orderDate;
    private Product product;
    private double price;
}
