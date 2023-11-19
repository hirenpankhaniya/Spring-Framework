/**
 * 
 */
package com.tcs.trainings.bookservice.services.impl;

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

import com.tcs.trainings.bookservice.entities.Book;
import com.tcs.trainings.bookservice.entities.BookStatus;
import com.tcs.trainings.bookservice.models.Author;
import com.tcs.trainings.bookservice.models.Borrower;
import com.tcs.trainings.bookservice.models.Borrowing;
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
	
	@Value("${borrower.service.url}")
	private String borrowerServiceURL;
	
	@Value("${borrowing.service.url}")
	private String borrowingServiceURL;
	
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
			logger.error("Exception occurred while adding Book: " + e.getMessage(), e);
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
			logger.error("Exception occurred while finding Book by Id: " + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<Book> findAllBooks() {
		try {
			return bookRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Books: " + e.getMessage(), e);
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
					bookFromDB.setStatus(((book.getStatus() == null) ? BookStatus.AVAILABLE : book.getStatus()));
					
					Set<Long> authors = massageAndUpdateAuthors(book.getAuthorIds(), bookFromDB.getId());
					
					bookFromDB.setAuthorIds(authors);
					bookFromDB.setBorrowingId(book.getBorrowingId());
					
					return bookRepository.save(bookFromDB);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while updating Book: " + e.getMessage(), e);
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
			logger.error("Exception occurred while deleting Book by Id: " + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Set<Book> findAllBooksByAuthorId(Long authorId) {
		Set<Book> books = new HashSet<>();
		try {
			if(authorId != null && authorId > 0) {
				Author author = getAuthorByAuthorId(authorId);
				if(author != null) {
					Set<Long> bookIds = author.getBookIds();
					for(Long bookId : bookIds) {
						books.add(findBookById(bookId));
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Books by Author Id: " + e.getMessage(), e);
		}
		return books;
	}

	@Override
	public Set<Book> findAllBorrowedBooksByBorrowerId(Long borrowerId) {
		Set<Book> borrowedBooks = new HashSet<>();
		try {
			if(borrowerId != null && borrowerId > 0) {
				Borrower borrower = findBorrowerByBorrowerId(borrowerId);
				if(borrower != null) {
					Set<Long> borrowingIds = borrower.getBorrowings();
					for (Long borrowingId : borrowingIds) {
						Borrowing borrowing = findBorrowingByBorrowingId(borrowingId);
						Long bookId = borrowing.getBookId();
						Book book = findBookById(bookId);
						borrowedBooks.add(book);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while finding all borrowed Books by Borrower: " + e.getMessage(), e);
		}
		return borrowedBooks;
	}

	@Override
	public Author getAuthorByAuthorId(Long authorId) {
		try {
			return restTemplate.getForEntity(authorServiceURL + "/" + authorId, Author.class).getBody();
		} catch (Exception e) {
			logger.error("Exception occurred while getting Author by Author Id: " + e.getMessage(), e);
		}
		return null;
	}
	
	private Set<Long> massageAndUpdateAuthors(Set<Long> authors, Long bookId) {
		
		Set<Long> validatedAuthors = new HashSet<>();
		
		logger.info("Authors from arguments: " + authors);
		
		for(Long authorId : authors) {
			
			Author authorFromAuthorService = getAuthorByAuthorId(authorId);
			
			logger.info("Author from service: " + authorFromAuthorService);
			
			if(authorFromAuthorService != null) {
				
				Set<Long> books = authorFromAuthorService.getBookIds();
				books.add(bookId);
				authorFromAuthorService.setBookIds(books);
				
				restTemplate.put(authorServiceURL + "/" + authorId, authorFromAuthorService);
				
				validatedAuthors.add(authorId);
			}
		}
		
		logger.info("Authors validated: " + validatedAuthors);
		
		return validatedAuthors;
	}

	@Override
	public Borrower findBorrowerByBorrowerId(Long borrowerId) {
		try {
			return restTemplate.getForEntity(borrowerServiceURL + "/" + borrowerId, Borrower.class).getBody();
		} catch (Exception e) {
			logger.error("Exception occurred while getting Borrower by Borrower Id: " + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Borrowing findBorrowingByBorrowingId(Long borrowingId) {
		try {
			return restTemplate.getForEntity(borrowingServiceURL + "/" + borrowingId, Borrowing.class).getBody();
		} catch (Exception e) {
			logger.error("Exception occurred while getting Borrowing by Borrowing Id: " + e.getMessage(), e);
		}
		return null;
	}
	
}
