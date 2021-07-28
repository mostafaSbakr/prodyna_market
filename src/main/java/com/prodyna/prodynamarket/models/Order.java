package com.prodyna.prodynamarket.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    @Column(name = "order_date")
    @NonNull
    private Date orderDate;

//    TODO: modify frontend to accept multiple products in order
//    @ManyToMany
//    @JoinTable(name = "order_details")
//    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NonNull
    private Product product;

    @NonNull
    private double price;
}
