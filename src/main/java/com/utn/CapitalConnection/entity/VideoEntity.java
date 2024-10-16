package com.utn.CapitalConnection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "video")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Video link cannot be blank")
    @Size(max = 255, message = "Video link must not exceed 255 characters")
    @Column(name = "linkVideo", nullable = false)
    private String linkVideo;

    @NotNull(message = "Entrepreneurships list cannot be null")
    @ManyToMany
    @JoinTable(
            name = "videos_x_entrepreneurship",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "entrepreneurship_id"))
    private List<EntrepreneurshipEntity> entrepreneurships;

    public VideoEntity() {}

    public Long getId() {
        return id;
    }

    public String getLinkImage() {
        return linkVideo;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurships() {
        return entrepreneurships;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLinkVideo(@NotBlank String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public void setEntrepreneurships(@NotNull List<EntrepreneurshipEntity> entrepreneurships) {
        this.entrepreneurships = entrepreneurships;
    }
}
