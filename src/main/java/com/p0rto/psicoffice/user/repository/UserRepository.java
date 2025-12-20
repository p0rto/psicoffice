package com.p0rto.psicoffice.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p0rto.psicoffice.user.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    Optional<User> findByCpf(String cpf);

    boolean existsByCpfOrEmail(String cpf, String email);

}
