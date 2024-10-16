package com.utn.CapitalConnection.entity;

import com.utn.CapitalConnection.types.Category;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "entrepreneurships")
public class EntrepreneurshipEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be blank")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Column(nullable = false)
    private String description;

    @Positive(message = "Goal must be a positive number")
    @Column(nullable = false)
    private float goal;

    @NotNull(message = "Category must not be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "entrepreneurships", fetch = FetchType.LAZY)
    private List<PictureEntity> pictures = new ArrayList<>();


    @ManyToMany(mappedBy = "entrepreneurships", fetch = FetchType.LAZY)
    private List<VideoEntity> videos = new ArrayList<>();


    @ManyToMany(mappedBy = "entrepreneurships", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "entrepreneurship")
    private List<DonationEntity> donations;


    @ManyToMany(mappedBy = "entrepreneurships", fetch = FetchType.LAZY)
    private List<EntrepreneurEntity> entrepreneurs = new ArrayList<>();


    public List<EntrepreneurEntity> getEntrepreneurs() {
        return entrepreneurs;
    }

    public void setEntrepreneurs(List<EntrepreneurEntity> entrepreneurs) {
        this.entrepreneurs = entrepreneurs;
    }

    public EntrepreneurshipEntity() {
    }

    public EntrepreneurshipEntity(String name, List<PictureEntity> pictures, String description, List<VideoEntity> videos, float goal, Category category, List<ReviewEntity> reviews) {
        this.name = name;
        this.pictures = pictures;
        this.description = description;
        this.videos = videos;
        this.goal = goal;
        this.category = category;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public String getDescription() {
        return description;
    }

    public List<VideoEntity> getVideos() {
        return videos;
    }

    public float getGoal() {
        return goal;
    }

    public Category getCategory() {
        return category;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVideos(List<VideoEntity> videos) {
        this.videos = videos;
    }

    public void setGoal(float goal) {
        this.goal = goal;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public void setDonations(List<DonationEntity> donations) {
        this.donations = donations;
    }
}
