package com.java.citizens.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "roles")
public class Role extends AbstractEntity {

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String name;

}
