package com.victor.springapi.services;

import com.victor.springapi.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    public static UserSS authenticated(){
        try {
            //Obtem usuario atual logado, se nao estiver logado gera exception no cast
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e){
            return null;
        }
    }
}
