/**
 * 
 */
package com.tcs.trainings.bookservice.services;

import java.util.List;
import java.util.Set;

import com.tcs.trainings.bookservice.entities.Book;
import com.tcs.trainings.bookservice.models.Author;
import com.tcs.trainings.bookservice.models.Borrower;
import com.tcs.trainings.bookservice.models.Borrowing;

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
	
	Set<Book> findAllBorrowedBooksByBorrowerId(Long borrowerId);
	
	Author getAuthorByAuthorId(Long authorId);
	
	Book updateBook(Long id, Book book);
	
	Book deleteBookById(Long id);
	
	Borrower findBorrowerByBorrowerId(Long borrowerId);
	
	Borrowing findBorrowingByBorrowingId(Long borrowingId);
}
