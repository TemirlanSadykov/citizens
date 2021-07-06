package com.java.citizens.service;

import com.java.citizens.dto.UserDTO;
import com.java.citizens.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public Page<UserDTO> findAll(Pageable pageable){
        return userRepo.findAll(pageable).map(UserDTO::from);
    }


}
