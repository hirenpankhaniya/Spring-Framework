/**
 * 
 */
package com.example.simplelibrary.services;

import java.util.List;

import com.example.simplelibrary.entities.Borrowing;

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
}
