package com.example.authen.repositorys;

import com.example.authen.entity.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel, Long>{

    Optional<UsersModel> findByEmail(String email);

    Optional<UsersModel> findByUsername(String username);

}
