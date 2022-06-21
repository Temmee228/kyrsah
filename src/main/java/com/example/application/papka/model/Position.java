package com.example.application.papka.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name = "positions")
@Data
public class Position implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int zp;

    private float premium;

    public Position() {

    }

    public Position(Long id, String name, String description, int zp, float premium) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.zp = zp;
        this.premium = premium;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getZp() {
        return zp;
    }

    public float getPremium() {
        return premium;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setZp(int zp) {
        this.zp = zp;
    }

    public void setPremium(float premium) {
        this.premium = premium;
    }

    public float GetAllZp() {
        return zp + (zp * premium);
    }
    @Override
    public String getAuthority() {
        return name;
    }
}