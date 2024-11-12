package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.dto.EntrepreneurshipRequest;
import com.utn.CapitalConnection.entity.EntrepreneurshipEntity;
import com.utn.CapitalConnection.entity.ReviewEntity;
import com.utn.CapitalConnection.exception.NoEntrepreneurshipsFoundException;
import com.utn.CapitalConnection.exception.ResourceNotFoundException;
import com.utn.CapitalConnection.model.Entrepreneurship;
import com.utn.CapitalConnection.model.Review;
import com.utn.CapitalConnection.repository.EntrepreneurshipRepository;
import com.utn.CapitalConnection.repository.ReviewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntrepreneurshipService {

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;



    public EntrepreneurshipEntity getEntrepreneurshipById(Long id) {
        return entrepreneurshipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrepreneurship not found with id: " + id));
    }

    // Método para obtener emprendimientos con filtrado
    public Page<Entrepreneurship> findEntrepreneurship(String name, Float stars, BigDecimal goal, Pageable pageable) {
        Page<EntrepreneurshipEntity> entity;

        // Filtrado
        if (name != null && goal != null) {
            entity = entrepreneurshipRepository.findByNameAndGoal(name, goal, pageable);
        }  else if (name != null) {
            entity = entrepreneurshipRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (goal != null) {
            entity = entrepreneurshipRepository.findByGoal(goal, pageable);
        } else {
            entity = entrepreneurshipRepository.findAll(pageable);
        }
        return entity.map(this::entityToEntrepreneurship);
    }

    private Entrepreneurship entityToEntrepreneurship(EntrepreneurshipEntity entity) {
        Entrepreneurship entrepreneurship = new Entrepreneurship();

        entrepreneurship.setId(entity.getId());
        entrepreneurship.setName(entity.getName());
        entrepreneurship.setDescription(entity.getDescription());
        entrepreneurship.setGoal(entity.getGoal());
        entrepreneurship.setCategory(entity.getCategory());
        entrepreneurship.setImages(entity.getImages());
        entrepreneurship.setVideos(entity.getVideos());
        entrepreneurship.setActivated(entity.isActivated());

        return entrepreneurship;
    }


    public Page<EntrepreneurshipEntity> getAllEntrepreneurships(Pageable pageable) {
        return entrepreneurshipRepository.findAll(pageable);
    }

    public Page<EntrepreneurshipEntity> getActiveEntrepreneurships(Pageable pageable) {
        return entrepreneurshipRepository.findByIsActivatedTrue(pageable);
    }

    // GET by exact name
    public EntrepreneurshipEntity getEntrepreneurshipByName(@NotBlank String name) {
        return entrepreneurshipRepository.findByName(name)
                .orElseThrow(() -> new NoEntrepreneurshipsFoundException("Entrepreneurship not found with name: " + name));
    }

    // POST create a new entrepreneurship
    public EntrepreneurshipEntity createEntrepreneurship(EntrepreneurshipEntity entrepreneurship) {
        System.out.println(entrepreneurship);
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Transactional
    public void deactivateEntrepreneurship(Long id) {
        EntrepreneurshipEntity entrepreneurship = entrepreneurshipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrepreneurship not found with id: " + id));
        entrepreneurship.setIsActivated(false);  // Cambiar setisActivated por setIsActivated
// Desactivamos el emprendimiento
        entrepreneurshipRepository.save(entrepreneurship); // Guardamos la entidad actualizada
    }

    // PUT update an entrepreneurship by ID
    @Transactional
    public EntrepreneurshipEntity updateEntrepreneurship(@NotNull Long id, EntrepreneurshipEntity updatedEntrepreneurship) {
        EntrepreneurshipEntity entrepreneurship = entrepreneurshipRepository.findById(id)
                .orElseThrow(() -> new NoEntrepreneurshipsFoundException("Entrepreneurship not found with id: " + id));

        entrepreneurship.setName(updatedEntrepreneurship.getName());
        entrepreneurship.setCategory(updatedEntrepreneurship.getCategory());
        entrepreneurship.setGoal(updatedEntrepreneurship.getGoal());
        entrepreneurship.setDescription(updatedEntrepreneurship.getDescription());

        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Operation(summary = "Get ntrepreneurships by user creator",
            description = "Returns a list of entrepreneurships that match a specific star rating.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneurship found")
    })
    public List<EntrepreneurshipEntity> getEntrepreneurshipByUserId(@Parameter(description = "Star rating to search for")
                                                 @Positive String id) {
        return entrepreneurshipRepository.findByUserId(id);
    }

    // PATCH partially update an entrepreneurship
    @Transactional
    public EntrepreneurshipEntity partiallyUpdateEntrepreneurship(@NotNull Long id, String name, BigDecimal goal) {
        EntrepreneurshipEntity entrepreneurship = entrepreneurshipRepository.findById(id)
                .orElseThrow(() -> new NoEntrepreneurshipsFoundException("Entrepreneurship not found with id: " + id));

        if (name != null) {
            entrepreneurship.setName(name);
        }
        if (goal != null) {
            entrepreneurship.setGoal(goal);
        }

        return entrepreneurshipRepository.save(entrepreneurship);
    }

    // DELETE an entrepreneurship by ID
    public void deleteEntrepreneurship(@NotNull Long id) {
        if (!entrepreneurshipRepository.existsById(id)) {
            throw new NoEntrepreneurshipsFoundException("Entrepreneurship not found with id: " + id);
        }
        entrepreneurshipRepository.deleteById(id);
    }
    public EntrepreneurshipEntity patchEntrepreneurship(Long id, EntrepreneurshipRequest patchData) {
        EntrepreneurshipEntity entrepreneurship = entrepreneurshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneurship not found"));

        if (patchData.getName() != null) {
            entrepreneurship.setName(patchData.getName());
        }

        if (patchData.getDescription() != null) {
            entrepreneurship.setDescription(patchData.getDescription());
        }

        if (patchData.getGoal() > 0) {
            entrepreneurship.setGoal(BigDecimal.valueOf(patchData.getGoal()));
        }

        if (patchData.getCategory() != null) {
            entrepreneurship.setCategory(patchData.getCategory());
        }

        if (patchData.getImages() != null) {
            entrepreneurship.setImages(patchData.getImages());
        }

        if (patchData.getVideos() != null) {
            entrepreneurship.setVideos(patchData.getVideos());
        }

        if (patchData.getCollected() > 0) {
            entrepreneurship.setCollected(BigDecimal.valueOf(patchData.getCollected()));
        }

        return entrepreneurshipRepository.save(entrepreneurship);
    }

}
