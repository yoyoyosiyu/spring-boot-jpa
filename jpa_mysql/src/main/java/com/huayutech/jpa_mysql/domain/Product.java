package com.huayutech.jpa_mysql.domain;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "double default 0.00")
    private double price;
}
