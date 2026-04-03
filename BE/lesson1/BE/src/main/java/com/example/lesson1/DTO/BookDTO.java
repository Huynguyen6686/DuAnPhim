package com.example.lesson1.DTO;

public class BookDTO {
    private String id;
    private String category;
    private String name;
    private String author;
    private Double price;

    public BookDTO() {
    }

    public BookDTO(String id, String category, String name, String author, Double price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
