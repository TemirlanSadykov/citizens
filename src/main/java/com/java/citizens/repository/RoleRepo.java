package com.java.citizens.repository;

import com.java.citizens.entity.QRole;
import com.java.citizens.entity.QUser;
import com.java.citizens.entity.Role;
import com.java.citizens.entity.User;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface RoleRepo extends ExCustomRepository<Role, QRole, Long> {
    @Override
    default void customize(QuerydslBindings querydslBindings, QRole qRole) {
        querydslBindings.bind(qRole.name).first(((stringPath, s) -> qRole.name.containsIgnoreCase(s)));
    }
}
