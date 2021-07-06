package com.java.citizens.dto;

import com.java.citizens.entity.Document;
import com.java.citizens.entity.Role;
import com.java.citizens.entity.User;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {

    private Long id;

    @NotBlank(message = "Обязательное поле")
    @Size(max = 128)
    private String name;

    @NotBlank(message = "Обязательное поле")
    @Max(value = 32)
    private int inn;

    @NotBlank(message = "Обязательное поле")
    private String birth;

    @NotBlank(message = "Обязательное поле")
    @Size(max = 128)
    private String place;

    @NotBlank(message = "Обязательное поле")
    @Size(max = 64)
    private String login;

    @NotBlank(message = "Обязательное поле")
    @Size(min = 8, max = 64, message = "Пароль должен содержать минимум 8 символов, максимум 64")
    private String password;

    private RoleDTO roleDTO;
    private Document document;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .inn(user.getInn())
                .birth(user.getBirth().toString())
                .place(user.getPlace())
                .login(user.getLogin())
                .password(user.getPassword())
                .roleDTO(RoleDTO.from(user.getRole()))
                .document(user.getDocument())
                .build();
    }
}
