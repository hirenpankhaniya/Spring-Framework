/**
 * 
 */
package com.example.library.services;

import java.util.List;

import com.example.library.entities.Borrower;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface BorrowerService {

	Borrower addBorrower(Borrower borrower);
	
	Borrower findBorrowerById(Long id);
	
	List<Borrower> findAllBorrowers();
	
	Borrower updateBorrower(Long id, Borrower borrower);
	
	Borrower deleteBorrowerById(Long id);
}
