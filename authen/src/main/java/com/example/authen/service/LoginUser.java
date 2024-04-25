package com.example.authen.service;

import com.example.authen.model.UsersModel;
import org.springframework.stereotype.Service;

@Service
public class LoginUser {


    public boolean UserBanned (UsersModel usersModel){

        String status = usersModel.getStatusAccount().getAccountStatus().toString();

        if (status.equals("suspenso") || status.equals("banido")){
            return true;
        } else  {
            return false;
        }


    }
}
