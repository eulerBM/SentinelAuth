package com.example.authen.users;

import com.example.authen.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersModel, Long> {
}
