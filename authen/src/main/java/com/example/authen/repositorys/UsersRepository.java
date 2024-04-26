package com.example.authen.repositorys;

import com.example.authen.model.UsersModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel, Long>{

    Optional<UsersModel> findByEmail(String email);

    Optional<UsersModel> findByUsername(String username);

}
