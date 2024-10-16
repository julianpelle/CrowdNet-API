package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.EntrepreneurshipEntity;
import com.utn.CapitalConnection.exception.NoEntrepreneurshipsFoundException;
import com.utn.CapitalConnection.repository.EntrepreneurshipRepository;
import com.utn.CapitalConnection.types.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepreneurshipService {

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    public List<EntrepreneurshipEntity> getAllEntrepreneurships() {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findAll();
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found.");
        }
        return entrepreneurships;
    }

    public EntrepreneurshipEntity getEntrepreneurshipByName(@NotBlank String name) {
        Optional<EntrepreneurshipEntity> entrepreneurship = entrepreneurshipRepository.findByName(name);
        if (entrepreneurship.isPresent()) {
            return entrepreneurship.get();
        } else {
            throw new NoEntrepreneurshipsFoundException("Entrepreneurship not found with name: " + name);
        }
    }

    public List<EntrepreneurshipEntity> getEntrepreneurshipsByNameContaining(@NotBlank String name) {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findByNameContaining(name);
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found containing: " + name);
        }
        return entrepreneurships;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurshipsByCategory(@NotNull Category category) {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findByCategory(category);
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found in category: " + category);
        }
        return entrepreneurships;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurshipsByMinimumGoal(@Positive float minGoal) {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findByMinimumGoal(minGoal);
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found with a minimum goal of: " + minGoal);
        }
        return entrepreneurships;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurshipsByMaximumGoal(@Positive float maxGoal) {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findByMaximumGoal(maxGoal);
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found with a maximum goal of: " + maxGoal);
        }
        return entrepreneurships;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurshipsByGoalRange(Float minGoal, Float maxGoal) {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findByGoalRange(minGoal, maxGoal);
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found in the specified goal range.");
        }
        return entrepreneurships;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurshipsByEntrepreneurId(@NotNull Long entrepreneurId) {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findByEntrepreneurId(entrepreneurId);
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found for entrepreneur ID: " + entrepreneurId);
        }
        return entrepreneurships;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurshipsByDescriptionContaining(@NotBlank String description) {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findByDescriptionContaining(description);
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found containing: " + description);
        }
        return entrepreneurships;
    }
}
