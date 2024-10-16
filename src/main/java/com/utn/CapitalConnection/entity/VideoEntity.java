package com.utn.CapitalConnection.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "video")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String link_image;

    @ManyToMany
    @JoinTable(
            name = "videos_x_entrepreneurship",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "entrepreneurship_id"))
    private List<EntrepreneurshipEntity> entrepreneurships;

    public VideoEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getLinkImage() {
        return link_image;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurships() {
        return entrepreneurships;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLink_image(String link_image) {
        this.link_image = link_image;
    }

    public void setEntrepreneurships(List<EntrepreneurshipEntity> entrepreneurships) {
        this.entrepreneurships = entrepreneurships;
    }
}