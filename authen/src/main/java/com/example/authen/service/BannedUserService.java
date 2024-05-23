package com.example.authen.service;

import com.example.authen.dto.BannedUserDTO;
import com.example.authen.entity.StatusAccount;
import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.BannedUserRequestDTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BannedUserService {

    @Autowired
    private UsersRepository repository;

    public ResponseEntity<BannedUserDTO> BannedUserService(@RequestBody BannedUserRequestDTP data){

            Optional<UsersModel> user = repository.findByUsername(data.username());

            if (user.isEmpty()){

                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }

            UsersModel userModel = user.get();
            StatusAccount userStatus = userModel.getStatusAccount();

            userStatus.setAccountStatus(StatusAccount.ChoiceStatus.valueOf(data.accountStatus()));
            userStatus.setTime_banned(LocalDateTime.now());
            userStatus.setReason(data.reason());

            repository.save(userModel);

            BannedUserDTO BannedResponseDTO = new BannedUserDTO(userModel);

            return ResponseEntity.status(HttpStatus.OK).body(BannedResponseDTO);
            
    }
}
