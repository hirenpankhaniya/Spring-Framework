/**
 * 
 */
package com.tcs.trainings.borrowingservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.trainings.borrowingservice.entities.Borrowing;
import com.tcs.trainings.borrowingservice.services.BorrowingService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {
	
	private BorrowingService borrowingService;

	/**
	 * 
	 */
	@Autowired
	public BorrowingController(BorrowingService borrowingService) {
		
		this.borrowingService = borrowingService;
	}
	
	@GetMapping()
	public ResponseEntity<List<Borrowing>> getAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(borrowingService.findAllBorrowings());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Borrowing> getById(@PathVariable Long id) {
		
		Borrowing borrowingReturn = borrowingService.findBorrowingById(id);
		if(borrowingReturn != null) {
			return ResponseEntity.ok(borrowingReturn);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("/book/{bookId}/borrower/{borrowerId}")
	public ResponseEntity<Borrowing> borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
		
		Borrowing borrowingReturn = borrowingService.borrowBook(bookId, borrowerId);
		if(borrowingReturn != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(borrowingReturn);
		} else {
			return ResponseEntity.noContent().build();
		} 
	}
	
	@PutMapping("/book/{bookId}")
	public ResponseEntity<Borrowing> returnBook(@PathVariable Long bookId) {
		
		Borrowing borrowingReturn = borrowingService.returnBook(bookId);
		if(borrowingReturn != null) {
			return ResponseEntity.ok(borrowingReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Borrowing> delete(@PathVariable Long id) {
		
		Borrowing borrowingReturn = borrowingService.deleteBorrowingById(id);
		if(borrowingReturn != null) {
			return ResponseEntity.ok(borrowingReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
