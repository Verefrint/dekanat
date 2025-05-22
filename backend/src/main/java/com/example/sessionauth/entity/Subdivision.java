package com.example.sessionauth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Подразделение
@Table(name = "subdivision")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Subdivision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
