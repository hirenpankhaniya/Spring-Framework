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

import com.example.library.entities.Genre;
import com.example.library.services.GenreService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@RestController
@RequestMapping("/api/genres")
public class GenreController {
	
	private GenreService genreService;

	/**
	 * 
	 */
	@Autowired
	public GenreController(GenreService genreService) {
		
		this.genreService = genreService;
	}

	@GetMapping()
	public ResponseEntity<List<Genre>> getAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(genreService.findAllGenres());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Genre> getById(@PathVariable Long id) {
		
		Genre genreReturn = genreService.findGenreById(id);
		if(genreReturn != null) {
			return ResponseEntity.ok(genreReturn);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping()
	public ResponseEntity<Genre> create(@RequestBody Genre genre) {
		
		Genre genreReturn = genreService.addGenre(genre);
		if(genreReturn != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(genreReturn);
		} else {
			return ResponseEntity.noContent().build();
		} 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Genre> update(@PathVariable Long id, @RequestBody Genre genre) {
		
		Genre genreReturn = genreService.updateGenre(id, genre);
		if(genreReturn != null) {
			return ResponseEntity.ok(genreReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Genre> delete(@PathVariable Long id) {
		
		Genre genreReturn = genreService.deleteGenreById(id);
		if(genreReturn != null) {
			return ResponseEntity.ok(genreReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
