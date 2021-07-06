package com.java.citizens.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

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
    @Column(length = 32)
    private Integer inn;

    @NotBlank(message = "Обязательное поле")
    @Column
    private LocalDateTime birth;

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
