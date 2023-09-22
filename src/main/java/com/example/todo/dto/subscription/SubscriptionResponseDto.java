package com.example.todo.dto.subscription;


import com.example.todo.domain.entity.Subscription;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SubscriptionResponseDto {
    private Long id;
    private String name;
    private Integer maxMember;
    private BigDecimal price;
    private String description;
    private Boolean status;

    public static SubscriptionResponseDto fromEntity(Subscription entity){
        SubscriptionResponseDto dto = new SubscriptionResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setMaxMember(entity.getMaxMember());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
