package com.java.citizens.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "documents")
public class Document extends AbstractEntity {

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String number;

    @Column
    @Enumerated(EnumType.STRING)
    private Doctype doctype;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 128)
    private String giver;

    @NotNull(message = "Обязательное поле")
    @Column
    private Date issue;

    @NotNull(message = "Обязательное поле")
    @Column
    private Date expiration;
}
