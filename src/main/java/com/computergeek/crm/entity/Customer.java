package com.computergeek.crm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @NotNull(message = "first name cannot be empty")
    @Size(min = 1)
    private String firstName;

    private String lastName;

    @NotNull(message = "email cannot be empty")
    @Email
    @Size(min = 1)
    private String email;
}
