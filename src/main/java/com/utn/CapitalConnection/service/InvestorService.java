package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.InvestorEntity;
import com.utn.CapitalConnection.exception.InvestorNonExistingException;
import com.utn.CapitalConnection.exception.InvalidPortfolioValueException;
import com.utn.CapitalConnection.repository.InvestorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InvestorService extends UserService<InvestorEntity> {

    private final InvestorRepository investorRepository;

    @Autowired
    public InvestorService(InvestorRepository investorRepository) {
        super(investorRepository);
        this.investorRepository = investorRepository;
    }

    public List<InvestorEntity> findAllUsers() {
        return investorRepository.findAll();
    }

    public InvestorEntity findUserById(Long id) throws InvestorNonExistingException {
        return investorRepository.findById(id)
                .orElseThrow(() -> new InvestorNonExistingException("Investor not found with ID: " + id));
    }

    public InvestorEntity saveUser(@Valid InvestorEntity investor) {
        try {
            return investorRepository.save(investor);
        } catch (Exception e) {
            throw new RuntimeException("Error saving investor: " + e.getMessage());
        }
    }

    public void deleteUser(Long id) throws InvestorNonExistingException {
        if (!investorRepository.existsById(id)) {
            throw new InvestorNonExistingException("Investor not found with ID: " + id);
        }
        investorRepository.deleteById(id);
    }

    public List<InvestorEntity> findInvestorsByPortfolioValue(BigDecimal minPortfolioValue) {
        if (minPortfolioValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPortfolioValueException("Number cant be negative");
        }
        return investorRepository.findInvestorsByPortfolioValue(minPortfolioValue);
    }

    public List<InvestorEntity> findInvestorsByExperience(int minYearsOfExperience) {
        return investorRepository.findInvestorsByExperience(minYearsOfExperience);
    }

    public List<InvestorEntity> findInvestorsByPortfolioValueRange(BigDecimal minPortfolioValue, BigDecimal maxPortfolioValue) {
        if (minPortfolioValue.compareTo(BigDecimal.ZERO) < 0 || maxPortfolioValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPortfolioValueException("Numbers cant be negatives");
        }
        return investorRepository.findInvestorsByPortfolioRange(minPortfolioValue, maxPortfolioValue);
    }
}
