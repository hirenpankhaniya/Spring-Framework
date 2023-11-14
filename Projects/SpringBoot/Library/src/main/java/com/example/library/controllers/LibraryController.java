/**
 * 
 */
package com.example.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entities.Book;
import com.example.library.services.LibraryService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@RestController
@RequestMapping("/api/books")
public class LibraryController {
	
	//private LibraryService libraryService;

	/**
	 * 
	 *//*
	@Autowired
	public LibraryController(LibraryService libraryService) {
		
		this.libraryService = libraryService;
	}

	@GetMapping("/genre/{id}")
	public ResponseEntity<List<Book>> getBooksByGenreId(@PathVariable Long id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(libraryService.findBooksByGenreId(id));
	}
	
	@GetMapping("/author/{id}")
	public ResponseEntity<List<Book>> getBooksByAuthorId(@PathVariable Long id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(libraryService.findBooksByAuthorId(id));
	}
	
	@GetMapping("/library-branch/{id}")
	public ResponseEntity<List<Book>> getBooksByLibraryBranchId(@PathVariable Long id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(libraryService.findBooksByLibraryBranchId(id));
	}
	
	@GetMapping("/borrower/{id}")
	public ResponseEntity<List<Book>> getBooksByBorrowerId(@PathVariable Long id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(libraryService.findBooksByBorrowerId(id));
	}*/
}
