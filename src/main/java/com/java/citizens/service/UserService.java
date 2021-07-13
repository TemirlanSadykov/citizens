package com.java.citizens.service;

import com.java.citizens.dto.RoleDTO;
import com.java.citizens.dto.UserDTO;
import com.java.citizens.entity.*;
import com.java.citizens.repository.RoleRepo;
import com.java.citizens.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;

    public UserDTO getOne(String login) {
        if (userRepo.findOne(QUser.user.login.eq(login)).isEmpty()){
            return null;
        }
        return UserDTO.from(userRepo.findOne(QUser.user.login.eq(login)).get());
    }

    public UserDTO getOneById(Long id) {
        return UserDTO.from(userRepo.findOne(QUser.user.id.eq(id)).get());
    }

    public List<RoleDTO> getRoles() {
        List<Role> roles = new ArrayList<Role>();
        roles = roleRepo.findAll();

        List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
        roles.forEach(obj -> {
            roleDTOS.add(RoleDTO.from(obj));
        });
        return roleDTOS;
    }

    public void createUser(UserDTO userDTO) {

        User user = User.builder()
                .name(userDTO.getName())
                .inn(userDTO.getInn())
                .birth(Converter.convertStringToDate(userDTO.getBirth()))
                .place(userDTO.getPlace())
                .login(userDTO.getLogin())
                .password(encoder.encode(userDTO.getPassword()))
                .role(roleRepo.findOne(QRole.role.id.eq(Long.parseLong(userDTO.getRoleId()))).get())
                .document(null)
                .build();

        userRepo.save(user);
    }

    public Page<UserDTO> getAllUsers(String role, Pageable pageable) {
        return Converter.pageConverterForUsers(userRepo.findAll(QUser.user.role.name.eq(role)), pageable);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public void editUserInfo(UserDTO userDTO, Long id) throws IllegalArgumentException {
        User user = userRepo.findById(id).get();
        user.setName(userDTO.getName());
        user.setInn(userDTO.getInn());
        user.setBirth(Converter.convertStringToDate(userDTO.getBirth()));
        user.setPlace(userDTO.getPlace());
        user.setLogin(userDTO.getLogin());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        userRepo.save(user);
    }

    public Page<UserDTO> searchUser(String name, String birth, String number, String role, Pageable pageable) {
        QUser qUser = QUser.user;
        if (name.equals("")) {
            if (number.equals("")){
                if(birth.equals("")){
                    return Converter.pageConverterForUsers(userRepo.findAll(QUser.user.role.name.eq(role)), pageable);
                }
                return Converter.pageConverterForUsers(userRepo.findAll(qUser.role.name.eq(role).andAnyOf(qUser.birth.eq(Converter.convertStringToDate(birth)), qUser.name.eq(name), qUser.document.number.eq(number))), pageable);
            }
            return Converter.pageConverterForUsers(userRepo.findAll(qUser.role.name.eq(role).andAnyOf(qUser.birth.eq(Converter.convertStringToDate(birth)), qUser.name.eq(name), qUser.document.number.eq(number))), pageable);
        }
        return Converter.pageConverterForUsers(userRepo.findAll(qUser.role.name.eq(role).andAnyOf(qUser.birth.eq(Converter.convertStringToDate(birth)), qUser.name.eq(name), qUser.document.number.eq(number))), pageable);
    }
}
