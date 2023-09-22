package com.example.todo.domain.repository;

import com.example.todo.domain.entity.UsersSubscription;
import com.example.todo.domain.entity.enums.SubscriptionStatus;
import com.example.todo.domain.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsersSubscriptionRepository extends JpaRepository<UsersSubscription, Long> {
    List<UsersSubscription> findAllByUsers(User user);
    Page<UsersSubscription> findAllByUsers(User user, Pageable pageable);
    Optional<UsersSubscription> findByUsersAndSubscriptionStatus(User user, SubscriptionStatus status);
    Boolean existsByUsersAndSubscriptionStatus(User user, SubscriptionStatus status);
    Optional<UsersSubscription> findByMerchantUid(String merchantUid);
    List<UsersSubscription> findAllByEndDateBeforeAndSubscriptionStatus(LocalDate localDate, SubscriptionStatus status);

    @Query("select us " +
            "from UsersSubscription us " +
            "where us.subscriptionStatus = 'ACTIVE' " +
            "and us.id between 2 and 10000" +
            "and us.endDate =:week")
    List<UsersSubscription> customFindAll(@Param("week") LocalDate week);
//    @Query("select us " +
//            "from UsersSubscription us " +
//            "where us.subscriptionStatus = 'ACTIVE' " +
//            "and us.id = 2 or us.id = 3")
//    List<UsersSubscription> customFindAll();
}
