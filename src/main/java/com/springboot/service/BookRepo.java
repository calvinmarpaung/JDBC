package com.springboot.service;

import com.springboot.model.Book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookRepo")
public interface BookRepo {
    String name = null;

    int save(Book book);

    int updatePrice(Book book);

    int deleteById(Long id);

    List<Book> findAll();

    Book findById(Long id);


    int dropTable();
}




