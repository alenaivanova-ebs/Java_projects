package com.runner.dao.model;



import javax.persistence.*;
import java.util.Objects;


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

    public void setId(Long id) {
        this.id = id;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", discountType='" + discountType + '\'' +
                ", discountPercent=" + discountPercent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id.equals(discount.id) && discountType.equals(discount.discountType) && discountPercent.equals(discount.discountPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discountType, discountPercent);
    }
}
