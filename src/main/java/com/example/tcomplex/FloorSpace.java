package com.example.tcomplex;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class FloorSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã mặt bằng không được để trống")
    @Pattern(regexp = "[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}", message = "Mã mặt bằng phải có định dạng XXX-XX-XX-XX")
    private String code;

    @NotNull(message = "Diện tích không được để trống")
    @Min(value = 20, message = "Diện tích phải lớn hơn hoặc bằng 20")
    private Double area;

    @NotNull(message = "Trạng thái không được để trống")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "Tầng không được để trống")
    @Min(value = 1, message = "Tầng phải từ 1 trở lên")
    @Max(value = 15, message = "Tầng phải nhỏ hơn hoặc bằng 15")
    private Integer floor;

    @NotNull(message = "Loại văn phòng không được để trống")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull(message = "Giá cho thuê không được để trống")
    @Min(value = 1000000, message = "Giá cho thuê phải lớn hơn hoặc bằng 1,000,000")
    private Double price;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    private LocalDate endDate;

    private String description;

    // Enum cho Status
    public enum Status {
        AVAILABLE("Có sẵn"),
        OCCUPIED("Đã sử dụng"),
        MAINTENANCE("Đang bảo trì");

        private final String displayName;

        Status(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // Enum cho Type
    public enum Type {
        PRIVATE("Văn phòng riêng"),
        SHARED("Văn phòng chia sẻ"),
        VIRTUAL("Văn phòng ảo");

        private final String displayName;

        Type(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}