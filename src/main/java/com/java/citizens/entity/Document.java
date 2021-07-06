package com.java.citizens.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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

    @NotBlank(message = "Обязательное поле")
    private Doctype doctype;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 128)
    private String giver;

    @NotBlank(message = "Обязательное поле")
    @Column
    private LocalDateTime issue;

    @NotBlank(message = "Обязательное поле")
    @Column
    private LocalDateTime expiration;
}
