package com.quanh.Identity_service.repository;

import com.quanh.Identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //Phải đặt đúng tên - spring sẽ tạo proxy class imple interface này trong runtime
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
