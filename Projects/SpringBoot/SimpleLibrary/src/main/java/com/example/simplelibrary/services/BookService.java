/**
 * 
 */
package com.example.simplelibrary.services;

import java.util.List;
import java.util.Set;

import com.example.simplelibrary.entities.Book;


/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface BookService {

	Book addBook(Book book);
	
	Book findBookById(Long id);
	
	List<Book> findAllBooks();
	
	Set<Book> findAllBooksByAuthorId(Long authorId);
	
	Set<Book> findAllBooksByBorrowerId(Long borrowerId);
	
	Book updateBook(Long id, Book book);
	
	Book deleteBookById(Long id);
}
