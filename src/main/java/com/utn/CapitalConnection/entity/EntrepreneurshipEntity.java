package com.utn.CapitalConnection.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
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

    @Column(nullable = true) // Permite que sea null
    private String id_user;

    @NotBlank(message = "Name must not be blank")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Column(nullable = false)
    private String description;

    @Positive(message = "Goal must be a positive number")
    @Column(nullable = false)
    private BigDecimal goal;

    @Column(nullable = false)
    private String category;

    @ElementCollection
    @CollectionTable(name = "entrepreneurship_images", joinColumns = @JoinColumn(name = "entrepreneurship_id"))
    @Column(name = "link_image")
    private List<String> images;

    @ElementCollection
    @CollectionTable(name = "entrepreneurship_videos", joinColumns = @JoinColumn(name = "entrepreneurship_id"))
    @Column(name = "link_video")
    private List<String> videos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "entrepreneurship_id")
    private List<ReviewEntity> reviews = new ArrayList<>();



    public void addReview(ReviewEntity review) {
        if (review.getEntrepreneurships() == null) {
            review.setEntrepreneurships(new ArrayList<>());
        }
        review.getEntrepreneurships().add(this);
    }


    public EntrepreneurshipEntity() {
    }

    public EntrepreneurshipEntity(String name,String id_user, ArrayList<String> images, String description, ArrayList<String> videos, BigDecimal goal, String category, List<ReviewEntity> reviews) {
        this.name = name;
        this.id_user = id_user;
        this.images = images;
        this.description = description;
        this.videos = videos;
        this.goal = goal;
        this.category = category;
        this.reviews = reviews;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getVideos() {
        return videos;
    }

    public BigDecimal getGoal() {
        return goal;
    }

    public String getCategory() {
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

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public void setGoal(BigDecimal goal) {
        this.goal = goal;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }


}
