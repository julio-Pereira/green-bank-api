package com.br.greenbank.repository.user;

import com.br.greenbank.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Modifying
    @Query("UPDATE User u SET u.fullName = :userFullName WHERE u.userId = :id")
    void updateUserName(@Param("id") UUID id, @Param("userFullName") String userFullName);

    @Modifying
    @Query("UPDATE User u SET u.email = :userEmail WHERE u.userId = :id")
    void updateUserEmail(@Param("id") UUID id, @Param("userEmail") String userEmail);

    @Modifying
    @Query("UPDATE User u SET u.fullName = :userFullName, u.email = :userEmail, u.password = :userPassword WHERE u.userId = :id")
    void updateUser(@Param("id") UUID id, @Param("userFullName") String userFullName, @Param("userEmail") String userEmail, @Param("userPassword") String userPassword);

    @Modifying
    @Query("UPDATE User u SET u.password = :userPassword WHERE u.userId = :id")
    void updateUserPassword(@Param("id") UUID id, @Param("userPassword") String userPassword);

    boolean existsByEmail(String email);

}
