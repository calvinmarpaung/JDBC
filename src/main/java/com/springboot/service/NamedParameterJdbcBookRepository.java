package com.springboot.service;

import com.springboot.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class NamedParameterJdbcBookRepository implements BookRepo{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Empty Constructor
    public NamedParameterJdbcBookRepository() {
    }

    public int save(Book book) {
        return jdbcTemplate.update(
                "insert into books ( name, price) values(?,?)",
                book.getName(), book.getPrice());
    }

    public int updatePrice(Book book) {
        return namedParameterJdbcTemplate.update(
                "update books set price = :price where id = :id",
                new BeanPropertySqlParameterSource(book));
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete from books where id = ?",
                id);
    }

    public List<Book> findAll() {
        return jdbcTemplate.query(
                "select * from books",
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        )
        );
    }

    public Book findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from books where id = :id",
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        )
        );
    }

    public int dropTable(){
        return jdbcTemplate.update(
                "truncate table books"
        );
    }



}
