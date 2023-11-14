/**
 * 
 */
package com.tcs.trainings.bookservice.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.trainings.bookservice.entities.Book;
import com.tcs.trainings.bookservice.entities.BookStatus;
import com.tcs.trainings.bookservice.models.Author;
import com.tcs.trainings.bookservice.repositories.BookRepository;
import com.tcs.trainings.bookservice.services.BookService;



/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class BookServiceImpl implements BookService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final BookRepository bookRepository;	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${author.service.url}")
	private String authorServiceURL;
	
	/**
	 * 
	 */
	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book addBook(Book book) {
		try {
			if(book != null) {
				book.setStatus(BookStatus.AVAILABLE);
				return bookRepository.save(book);
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while adding Book: ", e);
		}
		return null;
	}

	@Override
	public Book findBookById(Long id) {
		try {
			if(id != null && id > 0) {
				Optional<Book> optional = bookRepository.findById(id);
				if(!optional.isEmpty()) {
					return optional.get();
				}				
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while finding Book by Id: ", e);
		}
		return null;
	}

	@Override
	public List<Book> findAllBooks() {
		try {
			return bookRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Books: ", e);
		}
		return new ArrayList<>();
	}

	@Override
	public Book updateBook(Long id, Book book) {
		try {
			if(id != null && id > 0 && book != null) {
				Book bookFromDB = findBookById(id);
				if(bookFromDB != null) {
					bookFromDB.setTitle(book.getTitle());
					bookFromDB.setIsbn(book.getIsbn());
					bookFromDB.setAuthorIds(book.getAuthorIds());
					return bookRepository.save(bookFromDB);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while updating Book: ", e);
		}
		return null;
	}

	@Override
	public Book deleteBookById(Long id) {
		try {
			Book bookFromDB = findBookById(id);
			if(bookFromDB != null) {
				bookRepository.deleteById(id);
				return bookFromDB;
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while deleting Book by Id: ", e);
		}
		return null;
	}

	@Override
	public Set<Book> findAllBooksByAuthorId(Long authorId) {
		return null;//authorService.findAllBooksByAuthorId(authorId);
	}

	@Override
	public Set<Book> findAllBooksByBorrowerId(Long borrowerId) {
		return null;//borrowerService.findAllBooksByBorrowerId(borrowerId);
	}

	@Override
	public Author getAuthorByAuthorId(Long authorId) {
		try {
			if(authorId != null && authorId > 0) {
				//ResponseEntity<Author> responseEntity = restTemplate.getForEntity(authorServiceURL + "/" + authorId, Author.class);
				logger.info("Response Entity: " + restTemplate.getForEntity(authorServiceURL + "/" + authorId, Author.class));				
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while getting Author by Author Id: ", e);
		}
		return null;
	}
	
}
