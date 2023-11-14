/**
 * 
 */
package com.tcs.trainings.authorservice.services;

import java.util.List;
import java.util.Set;

import com.tcs.trainings.authorservice.entities.Author;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface AuthorService {

	Author addAuthor(Author author);
	
	Author findAuthorById(Long id);
	
	List<Author> findAllAuthors();
	
	Set<Long> findAllBooksByAuthorId(Long authorId);
	
	Author updateAuthor(Long id, Author author);
	
	Author deleteAuthorById(Long id);
}
