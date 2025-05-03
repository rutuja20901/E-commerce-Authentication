package com.practice.ecommerceAuth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.ecommerceAuth.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

}
