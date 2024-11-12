    package com.utn.CapitalConnection.entity;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Positive;

    @Entity
    @Table(name = "reviews")
    public class ReviewEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull(message = "ID User must not be null")
        @Column(nullable = false)
        private String idUser;

        @NotBlank(message = "Name must not be blank")
        @Column(nullable = false, length = 50)
        private String username;

        @NotNull(message = "Stars must not be null")
        @Positive(message = "Stars must be positive")
        @Column(nullable = false)
        private float stars;

        @NotBlank(message = "Review text must not be blank")
        @Column(nullable = false, length = 500)
        private String reviewText;


        @ManyToOne
        @JoinColumn(name = "entrepreneurship_id", referencedColumnName = "id", nullable = false)
        @JsonBackReference
        private EntrepreneurshipEntity entrepreneurship;

        // Constructor vacío para JPA
        public ReviewEntity() {}

        // Constructor completo
        public ReviewEntity(String idUser, float stars, String reviewText, String username, EntrepreneurshipEntity entrepreneurship) {
            this.idUser = idUser;
            this.stars = stars;
            this.reviewText = reviewText;
            this.username = username;
            this.entrepreneurship = entrepreneurship;
        }

        // Getters y setters
        public Long getIdReview() {
            return id;
        }

        public void setIdReview(Long idReview) {
            this.id = idReview;
        }

        public String getIdUser() {
            return idUser;
        }

        public void setIdUser(String idUser) {
            this.idUser = idUser;
        }

        public float getStars() {
            return stars;
        }

        public void setStars(float stars) {
            this.stars = stars;
        }

        public String getReviewText() {
            return reviewText;
        }

        public void setReviewText(String reviewText) {
            this.reviewText = reviewText;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public EntrepreneurshipEntity getEntrepreneurship() {
            return entrepreneurship;
        }

        public void setEntrepreneurship(EntrepreneurshipEntity entrepreneurship) {
            this.entrepreneurship = entrepreneurship;
        }
    }