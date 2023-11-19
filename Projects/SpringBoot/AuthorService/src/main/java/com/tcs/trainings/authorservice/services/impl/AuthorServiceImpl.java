/**
 * 
 */
package com.tcs.trainings.authorservice.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.trainings.authorservice.entities.Author;
import com.tcs.trainings.authorservice.models.Book;
import com.tcs.trainings.authorservice.repositories.AuthorRepository;
import com.tcs.trainings.authorservice.services.AuthorService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class AuthorServiceImpl implements AuthorService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final AuthorRepository authorRepository;
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${book.service.url}")
	private String bookServiceURL;

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
					
					Set<Long> bookIds = author.getBookIds();
					Set<Long> validatedBookIds = new HashSet<>(); 
					
					if(bookIds != null && !bookIds.isEmpty()) {
						
						for(Long bookId : bookIds) {
							
							Book book = findBookByBookId(bookId);
							if(book != null) {
								validatedBookIds.add(book.getId());
							}
						}
					}
					authorFromDB.setBookIds(validatedBookIds);
					
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
		Set<Book> books = new HashSet<>();
		try {
			if(authorId != null && authorId > 0) {
				Author authorFromDB = findAuthorById(authorId);
				if(authorFromDB != null) {
					Set<Long> bookIds = authorFromDB.getBookIds();
					for(Long bookId : bookIds) {
						Book book = findBookByBookId(bookId);
						books.add(book);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Books by Author: ", e);
		}
		return books;
	}
	
	@Override
	public Book findBookByBookId(Long bookId) {
		try {
			return restTemplate.getForEntity(bookServiceURL + "/" + bookId, Book.class).getBody();
		} catch (Exception e) {
			logger.error("Exception occurred while getting Book by Book Id: " + e.getMessage(), e);
		}
		return null;
	}

}
