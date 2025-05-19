package com.example.tcomplex;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "floor_space")
public class FloorSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Double area;
    private Status status;
    private Integer floor;
    private Type type;
    private Long price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    public enum Status {
        TRONG, HA_TANG, DAY_DU
    }

    public enum Type {
        VAN_PHONG_CHIA_SE, VAN_PHONG_TRON_GOI
    }

    // Các hàm get và set
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Double getArea() { return area; }
    public void setArea(Double area) { this.area = area; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Integer getFloor() { return floor; }
    public void setFloor(Integer floor) { this.floor = floor; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public Long getPrice() { return price; }
    public void setPrice(Long price) { this.price = price; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}