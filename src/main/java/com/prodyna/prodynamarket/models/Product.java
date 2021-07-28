package com.prodyna.prodynamarket.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int id;

    @NonNull
    private String name;
    @NonNull
    private double price;
    @NonNull
    private int quantity;

//    TODO: add multiple orders to products
//    @ManyToMany (mappedBy = "products")
//    @OneToMany
//    private List<Order> orders = new ArrayList<>();
}
