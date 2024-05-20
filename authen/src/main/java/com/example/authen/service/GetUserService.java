package com.example.authen.service;

import com.example.authen.dto.InfosUserDTO;
import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserService {

    @Autowired
    private UsersRepository repository;

    public ResponseEntity<InfosUserDTO> InfosUserService(Long id) {

        Optional<UsersModel> user = repository.findById(id);

        if (user.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } else {

            UsersModel getUser = user.get();

            InfosUserDTO InfosUserDTOResponse = new InfosUserDTO(getUser);

            return ResponseEntity.status(HttpStatus.OK).body(InfosUserDTOResponse);

        }
    }


    public boolean UserBanned (UsersModel usersModel){

        String status = usersModel.getStatusAccount().getAccountStatus().toString();

        if (status.equals("suspenso") || status.equals("banido")){

            return true;

        } else  {

            return false;

        }


    }
}
