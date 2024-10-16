package com.utn.CapitalConnection.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pictures")
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link_image")
    private String linkImage;

    @ManyToMany
    @JoinTable(
            name = "pictures_x_user",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "entrepreneurship_id"))
    private List<EntrepreneurshipEntity> entrepreneurships;

    public PictureEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurships() {
        return entrepreneurships;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public void setEntrepreneurships(List<EntrepreneurshipEntity> entrepreneurships) {
        this.entrepreneurships = entrepreneurships;
    }
}
