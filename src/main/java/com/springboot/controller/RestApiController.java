package com.springboot.controller;

import com.springboot.model.Book;
import com.springboot.service.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")//resources
public class RestApiController{

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    BookRepo bookRepo; //Service which will do all data retrieval/manipulation work


    // -------------------Retrieve All Books--------------------------------------------

    @RequestMapping(value = "/book/", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<Book>> listAllBooks() throws SQLException, ClassNotFoundException {
        List<Book> books = bookRepo.findAll();


        ResponseEntity responseEntity = new ResponseEntity(books, HttpStatus.OK);
        return responseEntity;
    }

    // -------------------Retrieve Single book------------------------------------------

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getBook(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        logger.info("Fetching Book with id {}", id);
        Book book = bookRepo.findById(id);


        ResponseEntity responseEntity = new ResponseEntity(book, HttpStatus.OK);
        return responseEntity;
    }

    // -------------------Create a Book-------------------------------------------

    // Save to DB - Insert to Database
    @RequestMapping(value = "/book/", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity createBook(@RequestBody Book book) throws SQLException, ClassNotFoundException {
        logger.info("Creating Book :", book);

        bookRepo.save(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // ------------------- Update book Price ------------------------------------------------
    // Save to DB - Insert to Database
    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) throws SQLException, ClassNotFoundException {
        logger.info("Updating Book with id {}", id);

        Book currentBook = bookRepo.findById(id);
        currentBook.setPrice(book.getPrice());

        bookRepo.updatePrice(currentBook);
        return new ResponseEntity<>(currentBook, HttpStatus.OK);
    }

    // ------------------- Delete a Book-----------------------------------------

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        logger.info("Fetching & Deleting Book with id {}", id);
        bookRepo.deleteById(id);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Books-----------------------------

    @RequestMapping(value = "/delete/all", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteTable() throws SQLException, ClassNotFoundException {
        bookRepo.dropTable();
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }
}
