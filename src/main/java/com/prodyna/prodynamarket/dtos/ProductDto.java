package com.prodyna.prodynamarket.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private int id;
    private String name;
    private double price;
    private int quantity;
}
