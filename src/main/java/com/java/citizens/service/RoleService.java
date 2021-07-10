package com.java.citizens.service;

import com.java.citizens.dto.RoleDTO;
import com.java.citizens.entity.Role;
import com.java.citizens.repository.RoleRepo;
import com.java.citizens.repository.UserRepo;
import com.java.citizens.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    public List<RoleDTO> getAll() {
        List<Role> roles = new ArrayList<Role>();
        roles = roleRepo.findAll();

        List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
        roles.forEach(obj -> {
            rolesDTO.add(RoleDTO.from(obj));
        });
        return rolesDTO;
    }

    public String giveRoles(Principal principal) {
        String login = principal.getName();
        for (int i = 0; i < getAll().size(); i++) {
            if (userRepo.existsByLoginAndRoleId(login, getAll().get(i).getId())) {
                return Constants.REDIRECT_LIST().get(i);
            }
        }
        return "/";
    }

}
