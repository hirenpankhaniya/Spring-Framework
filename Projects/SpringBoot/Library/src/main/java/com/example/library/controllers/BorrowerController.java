/**
 * 
 */
package com.example.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entities.Borrower;
import com.example.library.services.BorrowerService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
	
	private BorrowerService borrowerService;

	/**
	 * 
	 */
	@Autowired
	public BorrowerController(BorrowerService borrowerService) {
		
		this.borrowerService = borrowerService;
	}
	
	@GetMapping()
	public ResponseEntity<List<Borrower>> getAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(borrowerService.findAllBorrowers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Borrower> getById(@PathVariable Long id) {
		
		Borrower borrowerReturn = borrowerService.findBorrowerById(id);
		if(borrowerReturn != null) {
			return ResponseEntity.ok(borrowerReturn);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping()
	public ResponseEntity<Borrower> create(@RequestBody Borrower borrower) {
		
		Borrower borrowerReturn = borrowerService.addBorrower(borrower);
		if(borrowerReturn != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(borrowerReturn);
		} else {
			return ResponseEntity.noContent().build();
		} 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Borrower> update(@PathVariable Long id, @RequestBody Borrower borrower) {
		
		Borrower borrowerReturn = borrowerService.updateBorrower(id, borrower);
		if(borrowerReturn != null) {
			return ResponseEntity.ok(borrowerReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Borrower> delete(@PathVariable Long id) {
		
		Borrower borrowerReturn = borrowerService.deleteBorrowerById(id);
		if(borrowerReturn != null) {
			return ResponseEntity.ok(borrowerReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
