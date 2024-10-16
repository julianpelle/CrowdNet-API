package com.utn.CapitalConnection.model;

import com.utn.CapitalConnection.types.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Entrepreneurship {

private Long id;
private String name;
private ArrayList<String> pictures;
private String description;
private ArrayList<String> videos;
private float goal;
private Category category;
private List<Review> reviewList;

    public Entrepreneurship() {
    }

    public Entrepreneurship(String name, String description, float goal, Category category) {
        this.name = name;
        this.pictures = new ArrayList<>();
        this.description = description;
        this.videos = new ArrayList<>();
        this.goal = goal;
        this.category = category;
    }

    public Entrepreneurship(Long id, String name, String description, float goal, Category category) {
        this.id = id;
        this.name = name;
        this.pictures = new ArrayList<>();
        this.description = description;
        this.videos = new ArrayList<>();
        this.goal = goal;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<String> videos) {
        this.videos = videos;
    }

    public float getGoal() {
        return goal;
    }

    public void setGoal(float goal) {
        this.goal = goal;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public void addVideo(String hrefVideo){
        this.pictures.add(hrefVideo);
    }
    public void addPicture(String hrefPicture){
        this.videos.add(hrefPicture);
    }
    public void addReview(Review review){
        this.reviewList.add(review);

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entrepreneurship that)) return false;
        return Float.compare(that.goal, goal) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(pictures, that.pictures) && Objects.equals(description, that.description) && Objects.equals(videos, that.videos) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pictures, description, videos, goal, category);
    }
}
