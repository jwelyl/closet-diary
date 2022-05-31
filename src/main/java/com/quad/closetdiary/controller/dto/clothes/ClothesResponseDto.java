package com.quad.closetdiary.controller.dto.clothes;

import com.quad.closetdiary.domain.clothes.Clothes;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ClothesResponseDto {
    private Long id;
    private String name;
    private String category;
    private String brand;
    private LocalDate purchaseDate;
    private Integer price;
    private String originPicName;
    private String picPath;

    public ClothesResponseDto(Clothes entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.category = entity.getCategory();
        this.brand = entity.getBrand();
        this.purchaseDate = entity.getPurchaseDate();
        this.price = entity.getPrice();
        this.originPicName = entity.getOriginPicName();
        this.picPath = entity.getPicPath();
    }
}
