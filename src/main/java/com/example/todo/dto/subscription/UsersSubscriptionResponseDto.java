package com.example.todo.dto.subscription;

import com.example.todo.domain.entity.UsersSubscription;
import com.example.todo.domain.entity.enums.SubscriptionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class UsersSubscriptionResponseDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private SubscriptionStatus subscriptionStatus;
    private String username;
    private String subscriptionName;
    private String subscriptionDescription;
    private String merchantUid;
    private BigDecimal SubscriptionPrice;

    public static UsersSubscriptionResponseDto fromEntity(UsersSubscription entity){
        UsersSubscriptionResponseDto dto = new UsersSubscriptionResponseDto();
        dto.setId(entity.getId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setSubscriptionStatus(entity.getSubscriptionStatus());
        dto.setUsername(entity.getUsers().getUsername());
        dto.setSubscriptionName(entity.getSubscription().getName());
        dto.setSubscriptionDescription(entity.getSubscription().getDescription());
        dto.setMerchantUid(entity.getMerchantUid());
        dto.setSubscriptionPrice(entity.getSubscriptionPrice());
        return dto;
    }


}
