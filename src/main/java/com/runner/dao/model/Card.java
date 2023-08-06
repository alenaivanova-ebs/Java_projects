package com.runner.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_discount")
    private Double cardDiscount;


    public Long getId() {
        return id;
    }

    public String getCardName() {
        return cardName;
    }

    public Double getCardDiscount() {
        return cardDiscount;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", card_name='" + cardName + '\'' +
                ", card_discount=" + cardDiscount +
                '}';
    }
}
