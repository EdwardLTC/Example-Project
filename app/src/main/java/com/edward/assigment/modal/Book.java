package com.edward.assigment.modal;

public class Book {
    private int id, idCategory;
    private String title;
    private String description;
    private String author;
    private String imgUrl;
    private float rating;
    private int drawableResource, review;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


    public int getReview() {
        return review;
    }

    public void setReview(int review) {this.review = review;}

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getDrawableResource() {
        return drawableResource;
    }

    public void setDrawableResource(int drawableResource) {this.drawableResource = drawableResource;}

    public Book(int drawableResource) {
        this.drawableResource = drawableResource;
    }


    public Book(String title, String description, String author, int id, int review, float rating, int drawableResource) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.id = id;
        this.review = review;
        this.rating = rating;
        this.drawableResource = drawableResource;
    }

    public Book(int id,int idCategory,String title, String author,int drawableResource, String description,float rating){
        this.id =id;
        this.idCategory = idCategory;
        this.title = title;
        this.author =author;
        this.drawableResource =drawableResource;
        this.description =description;
        this.rating = rating;
    }

    public Book(int idCategory,String title, String author,int drawableResource, String description,float rating){
        this.idCategory = idCategory;
        this.title = title;
        this.author =author;
        this.drawableResource =drawableResource;
        this.description =description;
        this.rating = rating;
    }

    public Book(String title, String description, String author, String imgUrl, int id, int idCategory, float rating, int drawableResource, int review) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.imgUrl = imgUrl;
        this.id = id;
        this.idCategory = idCategory;
        this.rating = rating;
        this.drawableResource = drawableResource;
        this.review = review;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", id='" + id + '\'' +
                ", idCategory='" + idCategory + '\'' +
                ", rating=" + rating +
                ", drawableResource=" + drawableResource +
                ", review=" + review +
                '}';
    }
}
