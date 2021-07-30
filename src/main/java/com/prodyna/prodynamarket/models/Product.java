package com.prodyna.prodynamarket.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "uniqueNameConstraint")})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int id;

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @DecimalMin(value = "0.01")
    private double price;

    @NonNull
    @Min(value = 1)
    private int quantity;
}
