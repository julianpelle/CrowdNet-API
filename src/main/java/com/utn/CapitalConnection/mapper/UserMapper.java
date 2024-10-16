package com.utn.CapitalConnection.mapper;

import com.utn.CapitalConnection.entity.AddressEntity;
import com.utn.CapitalConnection.entity.InvestorEntity;
import com.utn.CapitalConnection.entity.EntrepreneurEntity;
import com.utn.CapitalConnection.entity.UserEntity;
import com.utn.CapitalConnection.model.Address;
import com.utn.CapitalConnection.model.User;
import com.utn.CapitalConnection.model.Investor;
import com.utn.CapitalConnection.model.Entrepreneur;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class UserMapper {

    public User entityToUser(UserEntity userEntity) {
        if (userEntity instanceof InvestorEntity) {
            return mapInvestor((InvestorEntity) userEntity);
        } else if (userEntity instanceof EntrepreneurEntity) {
            return mapEntrepreneur((EntrepreneurEntity) userEntity);
        }
        return null; // Consider handling unexpected cases more gracefully
    }

    private Investor mapInvestor(InvestorEntity investorEntity) {
        AddressEntity addressEntity = investorEntity.getAddress();
        Address address = new Address(
                addressEntity.getId(),
                addressEntity.getStreet(),
                addressEntity.getNumber(),
                addressEntity.getOther(),
                addressEntity.getLocality(),
                addressEntity.getProvince(),
                addressEntity.getType()
        );
        return new Investor(
                investorEntity.getId(),
                investorEntity.getName(),
                investorEntity.getSurname(),
                investorEntity.getEmail(),
                Date.valueOf(investorEntity.getDateOfBirth()),
                investorEntity.getWallet(),
                investorEntity.getYearsOfExperience(),
                investorEntity.getCategory(),
                address,
                investorEntity.getPortfolioValue()
        );
    }

    private Entrepreneur mapEntrepreneur(EntrepreneurEntity entrepreneurEntity) {
        AddressEntity addressEntity = entrepreneurEntity.getAddress();
        Address address = new Address(
                addressEntity.getId(),
                addressEntity.getStreet(),
                addressEntity.getNumber(),
                addressEntity.getOther(),
                addressEntity.getLocality(),
                addressEntity.getProvince(),
                addressEntity.getType()
        );
        return new Entrepreneur(
                entrepreneurEntity.getId(),
                entrepreneurEntity.getName(),
                entrepreneurEntity.getSurname(),
                entrepreneurEntity.getEmail(),
                Date.valueOf(entrepreneurEntity.getDateOfBirth()),
                entrepreneurEntity.getWallet(),
                entrepreneurEntity.getYearsOfExperience(),
                entrepreneurEntity.getCategory(),
                address,
                entrepreneurEntity.getSuccessRate(),
                entrepreneurEntity.getCbu()
        );
    }
}
