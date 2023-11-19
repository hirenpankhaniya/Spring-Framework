/**
 * 
 */
package com.tcs.trainings.borrowingservice.services.impl;

import java.sql.Date;
import java.time.LocalDate;
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

import com.tcs.trainings.borrowingservice.entities.Borrowing;
import com.tcs.trainings.borrowingservice.models.Book;
import com.tcs.trainings.borrowingservice.models.BookStatus;
import com.tcs.trainings.borrowingservice.models.Borrower;
import com.tcs.trainings.borrowingservice.repositories.BorrowingRepository;
import com.tcs.trainings.borrowingservice.services.BorrowingService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class BorrowingServiceImpl implements BorrowingService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final BorrowingRepository borrowingRepository;
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${book.service.url}")
	private String bookServiceURL;
	
	@Value("${borrower.service.url}")
	private String borrowerServiceURL;
	
	/**
	 * 
	 */
	@Autowired
	public BorrowingServiceImpl(BorrowingRepository borrowingRepository) {
		this.borrowingRepository = borrowingRepository;
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
			logger.error("Exception occurred while finding Borrowing by Id: " + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Borrowing findBorrowingByBookId(Long bookId) {
		try {
			if(bookId != null && bookId > 0) {
				
				List<Borrowing> borrowings = borrowingRepository.findBorrowingByBookId(bookId);
				if(borrowings != null && !borrowings.isEmpty()) {
				
					for(Borrowing borrowing : borrowings) {
						if(borrowing.getReturnDate() == null) {
							return borrowing;
						}
					}
				} 				
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while finding Borrowing by BookId: " + e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public List<Borrowing> findAllBorrowings() {
		try {
			return borrowingRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Borrowings: " + e.getMessage(), e);
		}
		return new ArrayList<>();
	}

	@Override
	public Borrowing deleteBorrowingById(Long id) {
		try {
			Borrowing borrowingFromDB = findBorrowingById(id);
			if(borrowingFromDB != null) {
				if(borrowingFromDB.getReturnDate() != null) {
					Book bookFromBookService = findBookByBookId(borrowingFromDB.getBookId());
					if(BookStatus.AVAILABLE.equals(bookFromBookService.getStatus())) {
						borrowingRepository.deleteById(id);
						return borrowingFromDB;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while deleting Borrowing by Id: " + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Borrowing borrowBook(Long bookId, Long borrowerId) {
		try {
			if(bookId != null && bookId > 0 && borrowerId != null && borrowerId > 0) {
				
				Book book = findBookByBookId(bookId);
				
				Borrower borrower = findBorrowerByBorrowerId(borrowerId);
				
				if(book != null && BookStatus.AVAILABLE.equals(book.getStatus()) 
						&& borrower != null) {
					logger.info("Book is valid, Book is available and Borrower is valid.");
					Borrowing borrowing = new Borrowing();
					borrowing.setBookId(bookId);
					borrowing.setBorrowerId(borrowerId);
					borrowing.setBorrowingDate(Date.valueOf(LocalDate.now()));
					borrowing.setReturnDate(null);
					
					Borrowing borrowingReturn = borrowingRepository.save(borrowing);
					Long borrowingId = borrowingReturn.getId();
					logger.info("Book with BookId: " + bookId + " borrowed (BorrowingId: " + borrowingId + ") by Borrower with BorrowerId: " + borrowerId);
					
					//Updating BookStatus = BORROWED for Book using BookService
					book.setStatus(BookStatus.BORROWED);
					book.setBorrowingId(borrowingId);
					
					logger.info("Authors from book: " + book.getAuthorIds());
					
					updateBookStatus(bookId, book);
					
					//Updating Borrowings for Borrower using BorrowerService
					Set<Long> borrowings = borrower.getBorrowings();
					borrowings.add(borrowingId);
					borrower.setBorrowings(borrowings);
					updateBorrower(borrowerId, borrower);
					
					return borrowingReturn;
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while borrowing a book: " + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Borrowing returnBook(Long bookId) {
		try {
			if(bookId != null && bookId > 0) {
				
				Book book = findBookByBookId(bookId);
				if(book != null && BookStatus.BORROWED.equals(book.getStatus())) {
					
					Borrowing borrowingFromDB = findBorrowingByBookId(bookId);
					if(borrowingFromDB != null) {
						
						borrowingFromDB.setReturnDate(Date.valueOf(LocalDate.now()));
						Borrowing borrowingReturn = borrowingRepository.save(borrowingFromDB);
						
						//Updating BookStatus = AVAILABLE for Book using BookService
						book.setStatus(BookStatus.AVAILABLE);
						book.setBorrowingId(null);
						updateBookStatus(bookId, book);
						
						//Updating Borrowings for Borrower using BorrowerService
						Long borrowerId = borrowingReturn.getBorrowerId();
						Borrower borrower = findBorrowerByBorrowerId(borrowerId);
						Set<Long> borrowings = borrower.getBorrowings();
						borrowings.remove(borrowingReturn.getId());
						borrower.setBorrowings(borrowings);
						updateBorrower(borrowerId, borrower);
						
						return borrowingReturn;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while borrowing a book: " + e.getMessage(), e);
		}
		return null;
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
	public void updateBookStatus(Long bookId, Book book) {
		try {
			restTemplate.put(bookServiceURL + "/" + bookId, book);
		} catch (Exception e) {
			logger.error("Exception occurred while updating BookStatus: " + e.getMessage(), e);
		}
	}

	@Override
	public void updateBorrower(Long borrowerId, Borrower borrower) {
		try {
			restTemplate.put(borrowerServiceURL + "/" + borrowerId, borrower);
		} catch (Exception e) {
			logger.error("Exception occurred while updating Borrower: " + e.getMessage(), e);
		}		
	}

}
