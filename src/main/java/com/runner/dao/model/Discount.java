package com.runner.dao.model;



import javax.persistence.*;


@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_percent")
    private Double discountPercent;

    public Long getId() {
        return id;
    }

    public String getDiscountType() {
        return discountType;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", discountType='" + discountType + '\'' +
                ", discountPercent=" + discountPercent +
                '}';
    }
}
