package springboot.service;

import springboot.model.Book;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service("bookRepo")
public interface BookRepo {
    String name = null;
    int count();

    int save(Book book);

    int update(Book book);

    int deleteById(Long id);

    List<Book> findAll();

    List<Book> findByNameAndPrice(String name, BigDecimal price);

    Book findById(Long id);

    String findNameById(Long id);

    int[] batchInsert(List<Book> books);

    int[][] batchInsert(List<Book> books, int batchSize);

    int[] batchUpdate(List<Book> books);

    int[][] batchUpdate(List<Book> books, int batchSize);

//    void saveImage(Long bookId, File image);

//    List<Map<String, InputStream>> findImageByBookId(Long bookId);

    void updateBook(Book currentBook);

    void deleteBookById(long id);

    void deleteAll();
}




