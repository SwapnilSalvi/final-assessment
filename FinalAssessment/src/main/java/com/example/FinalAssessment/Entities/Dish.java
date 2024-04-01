package com.example.FinalAssessment.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String tag;

    @Column(name = "prep_time", nullable = false)
    private String prepTime;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public Dish() {
    }

    public Dish(long id, String name, Double price, String ingredients, String tag, String prepTime, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.tag = tag;
        this.prepTime = prepTime;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ingredients='" + ingredients + '\'' +
                ", tag='" + tag + '\'' +
                ", prepTime='" + prepTime + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
