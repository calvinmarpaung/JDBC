package springboot.controller;

import springboot.model.Book;
import springboot.service.BookRepo;
import springboot.util.CustomErrorType;
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

//        if (books.isEmpty()) {
//            return new ResponseEntity(books, HttpStatus.NOT_FOUND);
//        }
        ResponseEntity responseEntity = new ResponseEntity(books, HttpStatus.OK);
        return responseEntity;
    }

    // -------------------Retrieve Single book------------------------------------------

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getBook(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        logger.info("Fetching Book with id {}", id);
        Book book = bookRepo.findById(id);
//        if (book == null) {
//            logger.error("Book with id {} not found.", id);
//            return new ResponseEntity<>(new CustomErrorType("Book with id " + id  + " not found"), HttpStatus.NOT_FOUND);
//        }

        ResponseEntity responseEntity = new ResponseEntity(book, HttpStatus.OK);
        return responseEntity;
    }

    // -------------------Create a Book-------------------------------------------

    // Save to DB - Insert to Database
    @RequestMapping(value = "/book/", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity createBook(@RequestBody Book book) throws SQLException, ClassNotFoundException {
        logger.info("Creating Book : {}", book);

//        if (bookService.isBookExist(book)) {
//            logger.error("Unable to create. A Book with name {} already exist", book.getName());
//            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Book with name " +
//                    book.getName() + " already exist."), HttpStatus.CONFLICT);
//        }
        bookRepo.save(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // ------------------- Update a Book ------------------------------------------------
    // Save to DB - Insert to Database
    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) throws SQLException, ClassNotFoundException {
        logger.info("Updating Book with id {}", id);

        Book currentBook = bookRepo.findById(id);

//        if (currentBook == null) {
//            logger.error("Unable to update. Book with id {} not found.", id);
//            return new ResponseEntity<>(new CustomErrorType("Unable to update. Book with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
//        }

        currentBook.setName(book.getName());

        currentBook.setPrice(book.getPrice());

        bookRepo.update(currentBook);
        return new ResponseEntity<>(currentBook, HttpStatus.OK);
    }

    // ------------------- Delete a Book-----------------------------------------

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        logger.info("Fetching & Deleting Book with id {}", id);

        Book book = bookRepo.findById(id);
        if (book == null) {
            logger.error("Unable to delete. Book with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Book with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        bookRepo.deleteBookById(id);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Books-----------------------------

    @RequestMapping(value = "/book/", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteAllBooks() throws SQLException, ClassNotFoundException {
        logger.info("Deleting All Books");

        bookRepo.deleteAll();
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

}
