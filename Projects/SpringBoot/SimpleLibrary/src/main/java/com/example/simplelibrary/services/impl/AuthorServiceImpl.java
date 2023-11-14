/**
 * 
 */
package com.example.simplelibrary.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplelibrary.entities.Author;
import com.example.simplelibrary.entities.Book;
import com.example.simplelibrary.repositories.AuthorRepository;
import com.example.simplelibrary.services.AuthorService;


/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class AuthorServiceImpl implements AuthorService {
	
	private final AuthorRepository authorRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	@Autowired
	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public Author addAuthor(Author author) {
		try {
			if(author != null) {
				return authorRepository.save(author);
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while adding Author: ", e);
		}
		return null;
	}

	@Override
	public Author findAuthorById(Long id) {
		try {
			if(id != null && id > 0) {
				Optional<Author> optional = authorRepository.findById(id);
				if(!optional.isEmpty()) {
					return optional.get();
				}				
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while finding Author by Id: ", e);
		}
		return null;
	}

	@Override
	public List<Author> findAllAuthors() {
		try {
			return authorRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Authors: ", e);
		}
		return new ArrayList<>();
	}

	@Override
	public Author updateAuthor(Long id, Author author) {
		try {
			if(id != null && id > 0 && author != null) {
				Author authorFromDB = findAuthorById(id);
				if(authorFromDB != null) {
					authorFromDB.setName(author.getName());
					authorFromDB.setBooks(author.getBooks());
					return authorRepository.save(authorFromDB);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while updating Author: ", e);
		}
		return null;
	}

	@Override
	public Author deleteAuthorById(Long id) {
		try {
			Author authorFromDB = findAuthorById(id);
			if(authorFromDB != null) {
				authorRepository.deleteById(id);
				return authorFromDB;
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while deleting Author by Id: ", e);
		}
		return null;
	}

	@Override
	public Set<Book> findAllBooksByAuthorId(Long authorId) {
		try {
			if(authorId != null && authorId > 0) {
				Author authorFromDB = findAuthorById(authorId);
				if(authorFromDB != null) {
					return authorFromDB.getBooks();
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Books by Author: ", e);
		}
		return new HashSet<>();
	}

}
