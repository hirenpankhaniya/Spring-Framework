/**
 * 
 */
package com.example.simplelibrary.services;

import java.util.List;
import java.util.Set;

import com.example.simplelibrary.entities.Author;
import com.example.simplelibrary.entities.Book;


/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface AuthorService {

	Author addAuthor(Author author);
	
	Author findAuthorById(Long id);
	
	List<Author> findAllAuthors();
	
	Set<Book> findAllBooksByAuthorId(Long authorId);
	
	Author updateAuthor(Long id, Author author);
	
	Author deleteAuthorById(Long id);
}
