package com.moneymind.user.domain.repository;

import com.moneymind.user.domain.model.Users;
import com.moneymind.user.domain.valueObject.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository {
    Users save(Users user);
    Optional<Users> findByEmail(Email email);
    Page<Users> findAll(Pageable pageable);
}
