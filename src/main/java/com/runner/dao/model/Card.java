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
    private String card_name;

    @Column(name = "card_discount")
    private Double card_discount;


    public Long getId() {
        return id;
    }

    public String getCard_name() {
        return card_name;
    }

    public Double getCard_discount() {
        return card_discount;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", card_name='" + card_name + '\'' +
                ", card_discount=" + card_discount +
                '}';
    }
}
