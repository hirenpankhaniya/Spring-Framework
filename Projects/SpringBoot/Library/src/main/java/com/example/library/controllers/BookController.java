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

import com.example.library.entities.Book;
import com.example.library.services.BookService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private BookService bookService;

	/**
	 * 
	 */
	@Autowired
	public BookController(BookService bookService) {
		
		this.bookService = bookService;
	}

	@GetMapping()
	public ResponseEntity<List<Book>> getAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooks());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id) {
		
		Book bookReturn = bookService.findBookById(id);
		if(bookReturn != null) {
			return ResponseEntity.ok(bookReturn);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping()
	public ResponseEntity<Book> create(@RequestBody Book book) {
		
		Book bookReturn = bookService.addBook(book);
		if(bookReturn != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(bookReturn);
		} else {
			return ResponseEntity.noContent().build();
		} 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
		
		Book bookReturn = bookService.updateBook(id, book);
		if(bookReturn != null) {
			return ResponseEntity.ok(bookReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> delete(@PathVariable Long id) {
		
		Book bookReturn = bookService.deleteBookById(id);
		if(bookReturn != null) {
			return ResponseEntity.ok(bookReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
