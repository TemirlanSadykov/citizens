package com.java.citizens.repository;

import com.java.citizens.entity.QUser;
import com.java.citizens.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepo extends ExCustomRepository<User, QUser, Long> {

    Optional<User> findByLogin(String login);

    @Override
    default void customize(QuerydslBindings querydslBindings, QUser qUser) {
        querydslBindings.bind(qUser.name).first(((stringPath, s) -> qUser.name.containsIgnoreCase(s)));
    }
}
