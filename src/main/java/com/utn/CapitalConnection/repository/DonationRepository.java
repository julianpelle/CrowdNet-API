package com.utn.CapitalConnection.repository;

import com.utn.CapitalConnection.entity.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {

    @Query("SELECT d FROM DonationEntity d WHERE d.entrepreneurship.id = :entrepreneurshipId")
    List<DonationEntity> findByEntrepreneurshipId(@Param("entrepreneurshipId") Long entrepreneurshipId);

    @Query("SELECT d FROM DonationEntity d WHERE d.investor.id = :investorId")
    List<DonationEntity> findByInvestorId(@Param("investorId") Long investorId);

    @Query("SELECT d FROM DonationEntity d WHERE d.date BETWEEN :startDate AND :endDate")
    List<DonationEntity> findByDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT d FROM DonationEntity d ORDER BY d.date DESC")
    Optional<DonationEntity> findTopByOrderByDateDesc();


}
