package com.java.citizens.service;

import com.java.citizens.dto.DocumentDTO;
import com.java.citizens.entity.*;
import com.java.citizens.repository.DocumentRepo;
import com.java.citizens.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepo documentRepo;
    private final UserRepo userRepo;

    public List<DocumentDTO> getAll() {
        List<Document> documents = new ArrayList<Document>();
        documents = documentRepo.findAll();

        List<DocumentDTO> documentDTO = new ArrayList<DocumentDTO>();
        documents.forEach(obj -> {
            documentDTO.add(DocumentDTO.from(obj));
        });
        return documentDTO;
    }

    public List<Doctype> getDocumentType() {
        List<Doctype> doctypes = new ArrayList<>();
        Collections.addAll(doctypes, Doctype.values());
        return doctypes;
    }

    public ModelAndView createDocument(DocumentDTO documentDTO, RedirectAttributes attributes) {

        if (documentRepo.findOne(QDocument.document.number.eq(documentDTO.getNumber())).isEmpty()) {
            Document document = Document.builder()
                    .number(documentDTO.getNumber())
                    .doctype(Doctype.valueOf(documentDTO.getDoctypeName()))
                    .giver(documentDTO.getGiver())
                    .issue(Converter.convertStringToDate(documentDTO.getIssue()))
                    .expiration(Converter.convertStringToDate(documentDTO.getExpiration()))
                    .build();

            documentRepo.save(document);

            User user = userRepo.findOne(QUser.user.login.eq(documentDTO.getLogin())).get();
            user.setDocument(document);
            userRepo.save(user);
            return new ModelAndView("redirect:/default");
        } else {
            attributes.addFlashAttribute("error", "Данный документ уже существует");
            return new ModelAndView("redirect:/info/document");
        }

    }

    public void editUserDocument(DocumentDTO documentDTO, Long id) throws IllegalArgumentException {
        Document document = documentRepo.findById(id).get();
        document.setNumber(documentDTO.getNumber());
        document.setDoctype(Doctype.valueOf(documentDTO.getDoctypeName()));
        document.setGiver(documentDTO.getGiver());
        document.setIssue(Converter.convertStringToDate(documentDTO.getIssue()));
        document.setExpiration(Converter.convertStringToDate(documentDTO.getExpiration()));
        documentRepo.save(document);
    }
}
