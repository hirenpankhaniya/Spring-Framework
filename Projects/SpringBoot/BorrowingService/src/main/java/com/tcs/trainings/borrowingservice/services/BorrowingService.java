/**
 * 
 */
package com.tcs.trainings.borrowingservice.services;

import java.util.List;

import com.tcs.trainings.borrowingservice.entities.Borrowing;
import com.tcs.trainings.borrowingservice.models.Book;
import com.tcs.trainings.borrowingservice.models.Borrower;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface BorrowingService {
	
	Borrowing findBorrowingById(Long id);

	Borrowing findBorrowingByBookId(Long bookId);
	
	List<Borrowing> findAllBorrowings();
	
	Borrowing borrowBook(Long bookId, Long borrowerId);
	
	Borrowing returnBook(Long bookId);
	
	Borrowing deleteBorrowingById(Long id);
	
	Book findBookByBookId(Long bookId);
	
	Borrower findBorrowerByBorrowerId(Long borrowerId);
	
	void updateBookStatus(Long bookId, Book book);
	
	void updateBorrower(Long borrowerId, Borrower borrower);
}
