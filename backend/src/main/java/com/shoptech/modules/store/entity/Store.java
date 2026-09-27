package com.shoptech.modules.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sellerProfileId;
    private String name;
    private String slug;
    private String logo;
    private String description;
    private String status;
    private String pickupContactName;
    private String pickupPhone;
    private Long provinceId;
    private String provinceName;
    private Long districtId;
    private String districtName;
    private String wardCode;
    private String wardName;
    private String addressLine;
    private Instant createdAt;
    private Instant updatedAt;
}
