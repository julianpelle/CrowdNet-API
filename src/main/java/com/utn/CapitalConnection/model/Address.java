package com.utn.CapitalConnection.model;

import com.utn.CapitalConnection.entity.EntrepreneurEntity;
import com.utn.CapitalConnection.entity.InvestorEntity;
import com.utn.CapitalConnection.types.TypeAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

public class Address {

    private Long id;

    private String street;

    private String number;

    private String other;

    private String locality;

    private String province;

    private TypeAddress type;

    public Address() {

    }

    public Address(String street, String number, String other, String locality, String province, TypeAddress type) {
        this.street = street;
        this.number = number;
        this.other = other;
        this.locality = locality;
        this.province = province;
        this.type = type;
    }

    public Address(Long id, String street, String number, String other, String locality, String province, TypeAddress type) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.other = other;
        this.locality = locality;
        this.province = province;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public TypeAddress getType() {
        return type;
    }

    public void setType(TypeAddress type) {
        this.type = type;
    }
}
