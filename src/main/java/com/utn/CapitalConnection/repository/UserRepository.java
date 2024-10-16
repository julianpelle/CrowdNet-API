package com.utn.CapitalConnection.repository;


import com.utn.CapitalConnection.entity.UserEntity;

import com.utn.CapitalConnection.types.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@NoRepositoryBean
public interface UserRepository<T extends UserEntity> extends JpaRepository<T, Long> {


    @Query("SELECT u FROM #{#entityName} u WHERE " +
            "(:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:minExperience IS NULL OR u.yearsOfExperience >= :minExperience) " +
            "AND (:maxExperience IS NULL OR u.yearsOfExperience <= :maxExperience)")
    List<T> findByNameOrExperience(@Param("name") String name,
                                     @Param("minExperience") Integer minExperience,
                                     @Param("maxExperience") Integer maxExperience);

    @Query("SELECT u FROM #{#entityName} u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<T> findByEmail(@Param("email") String email);


    @Query("SELECT u FROM #{#entityName} u WHERE " +
            "(:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:surname IS NULL OR LOWER(u.surname) LIKE LOWER(CONCAT('%', :surname, '%')))")
    List<T> findByNameOrSurname(@Param("name") String name, @Param("surname") String surname);


    @Query("SELECT u FROM #{#entityName} u WHERE u.category = :category")
    List<T> findByCategory(@Param("category") Category category);

    @Query("SELECT u FROM #{#entityName} u WHERE " +
            "(:minExperience IS NULL OR u.yearsOfExperience >= :minExperience) " +
            "AND (:maxExperience IS NULL OR u.yearsOfExperience <= :maxExperience)")
    List<T> findByYearsOfExperienceBetween(@Param("minExperience") Integer minExperience,
                                           @Param("maxExperience") Integer maxExperience);

    @Query("SELECT u FROM #{#entityName} u WHERE u.registrationDate >= :registrationDate")
    List<T> findByRegistrationDateAfter(@Param("registrationDate") LocalDateTime registrationDate);

    @Query("SELECT u FROM #{#entityName} u WHERE " +
            "(:minWallet IS NULL OR u.wallet >= :minWallet) " +
            "AND (:maxWallet IS NULL OR u.wallet <= :maxWallet)")
    List<T> findByWalletBetween(@Param("minWallet") BigDecimal minWallet,
                                @Param("maxWallet") BigDecimal maxWallet);

    @Query("SELECT COUNT(u) FROM #{#entityName} u WHERE u.category = :category")
    long countByCategory(@Param("category") Category category);

    @Query("SELECT u FROM #{#entityName} u WHERE " +
            "(:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:surname IS NULL OR LOWER(u.surname) LIKE LOWER(CONCAT('%', :surname, '%'))) " +
            "AND (:minExperience IS NULL OR u.yearsOfExperience >= :minExperience) " +
            "AND (:maxExperience IS NULL OR u.yearsOfExperience <= :maxExperience)")
    List<T> findByNameSurnameAndExperience(@Param("name") String name,
                                           @Param("surname") String surname,
                                           @Param("minExperience") Integer minExperience,
                                           @Param("maxExperience") Integer maxExperience);

    @Query("SELECT u FROM #{#entityName} u WHERE u.dateOfBirth > :dateOfBirth")
    List<T> findByDateOfBirthAfter(@Param("dateOfBirth") LocalDate dateOfBirth);

    @Query("SELECT u FROM #{#entityName} u WHERE u.dateOfBirth < :dateOfBirth")
    List<T> findByDateOfBirthBefore(@Param("dateOfBirth") LocalDate dateOfBirth);


}
