package com.utn.CapitalConnection.entity;

import com.utn.CapitalConnection.types.Category;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entrepreneurships")
public class EntrepreneurshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "entrepreneurships", fetch = FetchType.LAZY)
    private List<PictureEntity> pictures = new ArrayList<>();

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "entrepreneurships", fetch = FetchType.LAZY)
    private List<VideoEntity> videos = new ArrayList<>();

    @Column(nullable = false)
    private float goal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "entrepreneurships", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "entrepreneurship")
    private List<DonationEntity> donations;


    @ManyToMany(mappedBy = "entrepreneurships", fetch = FetchType.LAZY)
    private List<EntrepreneurEntity> entrepreneurs = new ArrayList<>(); // Cambiar a EntrepreneurEntity

    // Constructor, getters y setters

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
