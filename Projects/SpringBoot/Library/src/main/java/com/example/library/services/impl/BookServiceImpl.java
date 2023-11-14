/**
 * 
 */
package com.example.library.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entities.Book;
import com.example.library.repositories.BookRepository;
import com.example.library.services.BookService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());

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
					bookFromDB.setSubTitle(book.getSubTitle());
					bookFromDB.setFormat(book.getFormat());
					bookFromDB.setLanguage(book.getLanguage());
					bookFromDB.setAuthors(book.getAuthors());
					bookFromDB.setBorrower(book.getBorrower());
					bookFromDB.setGenres(book.getGenres());
					bookFromDB.setLibraryBranch(book.getLibraryBranch());
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
	
}
