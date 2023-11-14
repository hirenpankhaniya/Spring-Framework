/**
 * 
 */
package com.example.simplelibrary.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplelibrary.entities.Book;
import com.example.simplelibrary.entities.BookStatus;
import com.example.simplelibrary.repositories.BookRepository;
import com.example.simplelibrary.services.AuthorService;
import com.example.simplelibrary.services.BookService;
import com.example.simplelibrary.services.BorrowerService;


/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	private AuthorService authorService;
	
	//@Autowired
	private BorrowerService borrowerService;

	/**
	 * 
	 */
	@Autowired
	public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, BorrowerService borrowerService) {
		this.bookRepository = bookRepository;
		this.authorService = authorService;
		this.borrowerService = borrowerService;
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
					bookFromDB.setAuthors(book.getAuthors());
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
		return authorService.findAllBooksByAuthorId(authorId);
	}

	@Override
	public Set<Book> findAllBooksByBorrowerId(Long borrowerId) {
		return borrowerService.findAllBooksByBorrowerId(borrowerId);
	}
	
}
