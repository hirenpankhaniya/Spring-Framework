/**
 * 
 */
package com.example.simplelibrary.services;

import java.util.List;
import java.util.Set;

import com.example.simplelibrary.entities.Book;
import com.example.simplelibrary.entities.Borrower;


/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface BorrowerService {

	Borrower addBorrower(Borrower borrower);
	
	Borrower findBorrowerById(Long id);
	
	Borrower findBorrowerByName(String borrowerName);
	
	List<Borrower> findAllBorrowers();
	
	Set<Book> findAllBooksByBorrowerId(Long borrowerId);
	
	Borrower updateBorrower(Long id, Borrower borrower);
	
	Borrower deleteBorrowerById(Long id);
}
