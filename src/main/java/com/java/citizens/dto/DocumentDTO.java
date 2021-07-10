package com.java.citizens.dto;

import com.java.citizens.entity.Doctype;
import com.java.citizens.entity.Document;
import com.java.citizens.service.Converter;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentDTO {

    private Long id;

    @NotBlank(message = "Обязательное поле")
    @Size(max = 64)
    private String number;

    private Doctype doctype;

    @NotBlank(message = "Обязательное поле")
    @Size(max = 128)
    private String giver;

    @NotBlank(message = "Обязательное поле")
    private String issue;

    @NotBlank(message = "Обязательное поле")
    private String expiration;

    private String doctypeName;

    private String login;

    public static DocumentDTO from(Document document) {
        return builder()
                .id(document.getId())
                .number(document.getNumber())
                .doctype(document.getDoctype())
                .giver(document.getGiver())
                .issue(Converter.convertDateToString(document.getIssue()))
                .expiration(Converter.convertDateToString(document.getExpiration()))
                .build();
    }
}
