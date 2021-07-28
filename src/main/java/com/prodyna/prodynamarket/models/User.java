package com.prodyna.prodynamarket.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "user_id")
    private int userId;

    @Column (name = "first_name")
    @NonNull
    private String firstName;

    @Column (name = "last_name")
    @NonNull
    private String lastName;

    @Column (name = "user_name")
    @NonNull
    private String userName;

    @Column (name = "date_of_birth")
    @NonNull
    private String dateOfBirth;

    @NonNull
    private String phone;
    @NonNull
    private String address;
    @NonNull
    private String email;
    @NonNull
    private String password;

    @Column (name = "credit_card")
    @NonNull
    private String creditCard;
}
