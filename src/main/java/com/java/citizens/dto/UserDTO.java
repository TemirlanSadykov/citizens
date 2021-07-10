package com.java.citizens.dto;

import com.java.citizens.entity.User;
import com.java.citizens.service.Converter;
import lombok.*;

import javax.validation.constraints.*;

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
    @Size(max = 14, min = 14, message = "Требуется ввести 14 цифр")
    private String inn;

    @NotNull(message = "Обязательное поле")
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

    private String roleId;

    private RoleDTO roleDTO;
    private DocumentDTO documentDTO;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .inn(user.getInn())
                .birth(Converter.convertDateToString(user.getBirth()))
                .place(user.getPlace())
                .login(user.getLogin())
                .password(user.getPassword())
                .roleDTO(RoleDTO.from(user.getRole()))
                .documentDTO(DocumentDTO.from(user.getDocument()))
                .build();
    }
}
