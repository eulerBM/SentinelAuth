package com.example.authen.service;

import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteUserService {

    @Autowired
    private UsersRepository repository;

    public ResponseEntity<Void> DeleteUserService(Long id){

        Optional<UsersModel> findUserId = repository.findById(id);

        if (findUserId.isEmpty()){

            return ResponseEntity.notFound().build();

        }

        else {

            repository.deleteById(id);

            return ResponseEntity.noContent().build();

        }
    }
}
