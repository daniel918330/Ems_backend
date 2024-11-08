package com.example.Ems.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

//    primary key fields or columns with unique and not null constraints might be prioritized.
    @Column(name = "email_id",nullable = false,unique = true)
    private String email;

    @Column(name = "card_id")
    private String cardId;
}
