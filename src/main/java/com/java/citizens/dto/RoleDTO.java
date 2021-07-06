package com.java.citizens.dto;

import com.java.citizens.entity.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDTO{

    private Long id;

    @NotBlank(message = "Обязательное поле")
    @Size(max = 64)
    private String name;

    public static RoleDTO from(Role role) {
        return builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
