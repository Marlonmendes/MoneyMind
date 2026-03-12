package com.moneymind.user.domain.repository;

import com.moneymind.user.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<Users, UUID>, UserRepository {
}
