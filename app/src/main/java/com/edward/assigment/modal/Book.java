package com.edward.assigment.modal;

public class Book {
    private String title,description,author,imgUrl;
    private int pages,review;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getDrawableResource() {
        return drawableResource;
    }

    public void setDrawableResource(int drawableResource) {
        this.drawableResource = drawableResource;
    }

    private float rating;
    private int drawableResource;
    public Book(int drawableResource) {
        this.drawableResource = drawableResource;
    }

    public Book(String title, String description, String author, int pages, int review, float rating, int drawableResource) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.pages = pages;
        this.review = review;
        this.rating = rating;
        this.drawableResource = drawableResource;
    }
}
