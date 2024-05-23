package com.example.authen.repositorys;

import com.example.authen.entity.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UsersModel, Long>{

    Optional<UsersModel> findByEmail(String email);

    Optional<UsersModel> findByUsername(String username);

    Optional<UsersModel> findByIdPublic(UUID idPublic);


}
