package com.runner.dao.model;

import com.runner.dao.model.Discount;

import javax.persistence.*;
//https://github.com/MoonSulong/Ecommerce/pulls

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product")
    private String name;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Discount getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
