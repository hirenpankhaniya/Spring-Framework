/**
 * 
 */
package com.example.simplelibrary.services.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplelibrary.entities.Book;
import com.example.simplelibrary.entities.BookStatus;
import com.example.simplelibrary.entities.Borrower;
import com.example.simplelibrary.entities.Borrowing;
import com.example.simplelibrary.repositories.BorrowingRepository;
import com.example.simplelibrary.services.BookService;
import com.example.simplelibrary.services.BorrowerService;
import com.example.simplelibrary.services.BorrowingService;


/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class BorrowingServiceImpl implements BorrowingService {
	
	private final BorrowingRepository borrowingRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	private BookService bookService;
	
	//@Autowired
	private BorrowerService borrowerService;

	/**
	 * 
	 */
	@Autowired
	public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BookService bookService, BorrowerService borrowerService) {
		this.borrowingRepository = borrowingRepository;
		this.bookService = bookService;
		this.borrowerService = borrowerService;
	}
	
	@Override
	public Borrowing findBorrowingById(Long id) {
		try {
			if(id != null && id > 0) {
				Optional<Borrowing> optional = borrowingRepository.findById(id);
				if(!optional.isEmpty()) {
					return optional.get();
				}				
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while finding Borrowing by Id: ", e);
		}
		return null;
	}

	@Override
	public Borrowing findBorrowingByBookId(Long bookId) {
		try {
			if(bookId != null && bookId > 0) {
				return borrowingRepository.findBorrowingByBookId(bookId);				
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while finding Borrowing by Id: ", e);
		}
		return null;
	}
	
	@Override
	public List<Borrowing> findAllBorrowings() {
		try {
			return borrowingRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Borrowings: ", e);
		}
		return new ArrayList<>();
	}

	@Override
	public Borrowing deleteBorrowingById(Long id) {
		try {
			Borrowing borrowingFromDB = findBorrowingById(id);
			if(borrowingFromDB != null) {
				if(borrowingFromDB.getReturnDate() != null) {
					Book bookFromDB = bookService.findBookById(borrowingFromDB.getBook().getId());
					if(BookStatus.AVAILABLE.equals(bookFromDB.getStatus())) {
						borrowingRepository.deleteById(id);
						return borrowingFromDB;
					}
				}
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while deleting Borrowing by Id: ", e);
		}
		return null;
	}

	@Override
	public Borrowing borrowBook(Long bookId, Long borrowerId) {
		try {
			if(bookId != null && bookId > 0 && borrowerId != null && borrowerId > 0) {
				Book bookFromDB = bookService.findBookById(bookId);
				Borrower borrowerFromDB = borrowerService.findBorrowerById(borrowerId);
				if(bookFromDB != null && borrowerFromDB != null) {
					Borrowing borrowing = new Borrowing();
					borrowing.setBook(bookFromDB);
					borrowing.setBorrower(borrowerFromDB);
					borrowing.setBorrowingDate(Date.valueOf(LocalDate.now()));
					borrowing.setReturnDate(null);
					
					Borrowing borrowingReturn = borrowingRepository.save(borrowing);
					
					bookFromDB.setStatus(BookStatus.BORROWED);
					bookService.updateBook(bookFromDB.getId(), bookFromDB);
					
					return borrowingReturn;
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while borrowing a book: ", e);
		}
		return null;
	}

	@Override
	public Borrowing returnBook(Long bookId) {
		try {
			if(bookId != null && bookId > 0) {
				
				Book bookFromDB = bookService.findBookById(bookId);
				if(bookFromDB != null && BookStatus.BORROWED.equals(bookFromDB.getStatus())) {
					
					Borrowing borrowingFromDB = findBorrowingByBookId(bookId);
					if(borrowingFromDB != null) {
						borrowingFromDB.setReturnDate(Date.valueOf(LocalDate.now()));
						Borrowing borrowingReturn = borrowingRepository.save(borrowingFromDB);
						
						bookFromDB.setStatus(BookStatus.AVAILABLE);
						bookService.updateBook(bookFromDB.getId(), bookFromDB);
						
						return borrowingReturn;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while borrowing a book: ", e);
		}
		return null;
	}

}
