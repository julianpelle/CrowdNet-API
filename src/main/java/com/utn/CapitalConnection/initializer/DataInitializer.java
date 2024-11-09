package com.utn.CapitalConnection.initializer;

import com.utn.CapitalConnection.entity.EntrepreneurshipEntity;
import com.utn.CapitalConnection.entity.ReviewEntity;
import com.utn.CapitalConnection.repository.EntrepreneurshipRepository;
import com.utn.CapitalConnection.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private EntrepreneurshipRepository entrepreneurshipRepository;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Crear algunas instancias de emprendimientos
//        EntrepreneurshipEntity entrepreneurship1 = new EntrepreneurshipEntity();
//        entrepreneurship1.setName("Proyecto A");
//        entrepreneurship1.setDescription("Descripción del Proyecto A");
//        entrepreneurship1.setCategory(Category.ART);
//        entrepreneurship1.setGoal(BigDecimal.valueOf(100000));
//        EntrepreneurshipEntity entrepreneurship2 = new EntrepreneurshipEntity();
//        entrepreneurship2.setName("Proyecto B");
//        entrepreneurship2.setDescription("Descripción del Proyecto B");
//        entrepreneurship2.setCategory(Category.TECHNOLOGY);
//        entrepreneurship2.setGoal(BigDecimal.valueOf(500000));
//        // Crear algunas instancias de reseñas
//        ReviewEntity review1 = new ReviewEntity();
//        review1.setStars(4.5f);
//        review1.setReviewText("Excelente proyecto!");
//
//        ReviewEntity review2 = new ReviewEntity();
//        review2.setStars(3.0f);
//        review2.setReviewText("Mejorable.");
//
//        // Inicializar las listas de emprendimientos en las reseñas
//        if (review1.getEntrepreneurships() == null) {
//            review1.setEntrepreneurships(new ArrayList<>());
//        }
//        if (review2.getEntrepreneurships() == null) {
//            review2.setEntrepreneurships(new ArrayList<>());
//        }
//
//        // Establecer relaciones
//        review1.getEntrepreneurships().add(entrepreneurship1);
//        review2.getEntrepreneurships().add(entrepreneurship1);
//        review2.getEntrepreneurships().add(entrepreneurship2);
//
//        // Persistir las entidades
//        entrepreneurshipRepository.save(entrepreneurship1);
//        entrepreneurshipRepository.save(entrepreneurship2);
//        reviewRepository.save(review1);
//        reviewRepository.save(review2);
//
//        // Opcional: imprimir en consola para verificar
//        System.out.println("Datos inicializados:");
//        System.out.println("Emprendimiento 1: " + entrepreneurship1.getName());
//        System.out.println("Reseña 1: " + review1.getReviewText() + " - Stars: " + review1.getStars());
//        System.out.println("Reseña 2: " + review2.getReviewText() + " - Stars: " + review2.getStars());
//    }
//}
