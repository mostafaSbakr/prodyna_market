package com.prodyna.prodynamarket.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

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
    @Valid
    private User user;

    @Column(name = "order_date")
    @NonNull
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NonNull
    @Valid
    private Product product;

    @NonNull
    @DecimalMin(value = "0.01")
    private double price;
}
