package com.utn.CapitalConnection.entity;

import com.utn.CapitalConnection.types.TypeAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private InvestorEntity investor;

    @OneToOne(mappedBy = "address")
    private EntrepreneurEntity entrepreneur;

    @NotBlank(message = "Street is required")
    @Column(nullable = false)
    private String street;

    @Column
    private String number;

    @Column
    private String other;

    @NotBlank(message = "Locality is required")
    @Column(nullable = false)
    private String locality;

    @NotBlank(message = "Province is required")
    @Column(nullable = false)
    private String province;

    @NotNull(message = "Address type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeAddress type;

    public AddressEntity() {}

    public AddressEntity(InvestorEntity investor, EntrepreneurEntity entrepreneur, String street, String number, String other, String locality, String province, TypeAddress type) {
        this.investor = investor;
        this.entrepreneur = entrepreneur;
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

    public InvestorEntity getInvestor() {
        return investor;
    }

    public EntrepreneurEntity getEntrepreneur() {
        return entrepreneur;
    }

    public @NotBlank String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getOther() {
        return other;
    }

    public @NotBlank String getLocality() {
        return locality;
    }

    public @NotBlank String getProvince() {
        return province;
    }

    public @NotNull TypeAddress getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvestor(InvestorEntity investor) {
        this.investor = investor;
    }

    public void setEntrepreneur(EntrepreneurEntity entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public void setStreet(@NotBlank(message = "Street is required") String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setLocality(@NotBlank(message = "Locality is required") String locality) {
        this.locality = locality;
    }

    public void setProvince(@NotBlank(message = "Province is required") String province) {
        this.province = province;
    }

    public void setType(@NotNull(message = "Type is required") TypeAddress type) {
        this.type = type;
    }
}
