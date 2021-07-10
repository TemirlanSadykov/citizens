package com.java.citizens.repository;

import com.java.citizens.entity.Document;
import com.java.citizens.entity.QDocument;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.transaction.Transactional;

@Transactional
public interface DocumentRepo extends ExCustomRepository<Document, QDocument, Long> {
    @Override
    default void customize(QuerydslBindings querydslBindings, QDocument qDocument) {
        querydslBindings.bind(qDocument.number).first(((stringPath, s) -> qDocument.number.containsIgnoreCase(s)));
    }
}
