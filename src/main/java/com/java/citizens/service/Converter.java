package com.java.citizens.service;

import com.google.common.collect.Lists;
import com.java.citizens.dto.UserDTO;
import com.java.citizens.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Converter {

    public static Page<UserDTO> pageConverterForUsers(Iterable<User> userIterable, Pageable pageable) {
        List<User> users = Lists.newArrayList(userIterable);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), users.size());
        final Page<User> page = new PageImpl<>(users.subList(start, end), pageable, users.size());
        return page.map(UserDTO::from);
    }

    public static Date convertStringToDate(String date) {
        if (date.equals("")) {
            return new Date();
        } else {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

}
