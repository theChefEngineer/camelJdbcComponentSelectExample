package com.javarticles.camel;

public class Article {
    private long id;
    private String name;
    private String author;
    private String category;
    private String tags;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public String toString() {
        return "id:" + id + ":" + "name:" + name + ":" + "author:" + author + ":" + "category:" + category;
    }
    
}
