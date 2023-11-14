/**
 * 
 */
package com.example.library.services;

import java.util.List;

import com.example.library.entities.Author;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface AuthorService {

	Author addAuthor(Author author);
	
	Author findAuthorById(Long id);
	
	List<Author> findAllAuthors();
	
	Author updateAuthor(Long id, Author author);
	
	Author deleteAuthorById(Long id);	
}
