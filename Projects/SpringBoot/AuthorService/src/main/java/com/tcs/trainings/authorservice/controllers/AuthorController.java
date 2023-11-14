/**
 * 
 */
package com.tcs.trainings.authorservice.controllers;

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

import com.tcs.trainings.authorservice.entities.Author;
import com.tcs.trainings.authorservice.services.AuthorService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
	
	private AuthorService authorService;

	/**
	 * 
	 */
	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@GetMapping()
	public ResponseEntity<List<Author>> getAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(authorService.findAllAuthors());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> getById(@PathVariable Long id) {
		
		Author authorReturn = authorService.findAuthorById(id);
		if(authorReturn != null) {
			return ResponseEntity.ok(authorReturn);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping()
	public ResponseEntity<Author> create(@RequestBody Author author) {
		
		Author authorReturn = authorService.addAuthor(author);
		if(authorReturn != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(authorReturn);
		} else {
			return ResponseEntity.noContent().build();
		} 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author author) {
		
		Author authorReturn = authorService.updateAuthor(id, author);
		if(authorReturn != null) {
			return ResponseEntity.ok(authorReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Author> delete(@PathVariable Long id) {
		
		Author authorReturn = authorService.deleteAuthorById(id);
		if(authorReturn != null) {
			return ResponseEntity.ok(authorReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
