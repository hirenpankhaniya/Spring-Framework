/**
 * 
 */
package com.example.library.services;

import java.util.List;

import com.example.library.entities.Book;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface BookService {

	Book addBook(Book book);
	
	Book findBookById(Long id);
	
	List<Book> findAllBooks();
	
	Book updateBook(Long id, Book book);
	
	Book deleteBookById(Long id);
}
