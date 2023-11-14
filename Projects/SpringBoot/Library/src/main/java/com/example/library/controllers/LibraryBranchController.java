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

import com.example.library.entities.LibraryBranch;
import com.example.library.services.LibraryBranchService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@RestController
@RequestMapping("/api/library-branches")
public class LibraryBranchController {
	
	private LibraryBranchService libraryBranchService;

	/**
	 * 
	 */
	@Autowired
	public LibraryBranchController(LibraryBranchService libraryBranchService) {
		
		this.libraryBranchService = libraryBranchService;
	}

	@GetMapping()
	public ResponseEntity<List<LibraryBranch>> getAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(libraryBranchService.findAllLibraryBranches());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LibraryBranch> getById(@PathVariable Long id) {
		
		LibraryBranch libraryBranchReturn = libraryBranchService.findLibraryBranchById(id);
		if(libraryBranchReturn != null) {
			return ResponseEntity.ok(libraryBranchReturn);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping()
	public ResponseEntity<LibraryBranch> create(@RequestBody LibraryBranch libraryBranch) {
		
		LibraryBranch libraryBranchReturn = libraryBranchService.addLibraryBranch(libraryBranch);
		if(libraryBranchReturn != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(libraryBranchReturn);
		} else {
			return ResponseEntity.noContent().build();
		} 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LibraryBranch> update(@PathVariable Long id, @RequestBody LibraryBranch libraryBranch) {
		
		LibraryBranch libraryBranchReturn = libraryBranchService.updateLibraryBranch(id, libraryBranch);
		if(libraryBranchReturn != null) {
			return ResponseEntity.ok(libraryBranchReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LibraryBranch> delete(@PathVariable Long id) {
		
		LibraryBranch libraryBranchReturn = libraryBranchService.deleteLibraryBranchById(id);
		if(libraryBranchReturn != null) {
			return ResponseEntity.ok(libraryBranchReturn);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
