package com.prodyna.prodynamarket.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = "user_name", name = "uniqueNameConstraint")})
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "user_id")
    private int userId;

    @Column (name = "first_name")
    @NonNull
    @NotBlank
    private String firstName;

    @Column (name = "last_name")
    @NonNull
    @NotBlank
    private String lastName;

    @Column (name = "user_name")
    @NonNull
    @NotBlank
    private String userName;

    @Column (name = "date_of_birth")
    @NonNull
    @NotBlank
    private String dateOfBirth;

    @NonNull
    @NotBlank
    private String phone;

    @NonNull
    @NotBlank
    private String address;

    @NonNull
    @NotBlank
    @Email
    private String email;

    @NonNull
    @NotBlank
    private String password;

    @Column (name = "credit_card")
    @NonNull
    @NotBlank
    @CreditCardNumber
    private String creditCard;
}
