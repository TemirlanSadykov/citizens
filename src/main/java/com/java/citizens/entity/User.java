package com.java.citizens.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "users")
public class User extends AbstractEntity {

    @NotBlank(message = "Обязательное поле")
    @Column(length = 128)
    private String name;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 14)
    private String inn;

    @NotNull(message = "Обязательное поле")
    @Column
    private Date birth;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 128)
    private String place;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String login;

    @NotBlank(message = "Обязательное поле")
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    @Column(length = 64)
    private String password;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    @JoinColumn(name = "document_id")
    private Document document;
}
