/**
 * 
 */
package com.tcs.trainings.borrowingservice.services.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.trainings.borrowingservice.entities.Borrowing;
import com.tcs.trainings.borrowingservice.repositories.BorrowingRepository;
import com.tcs.trainings.borrowingservice.services.BorrowingService;

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
	//private BookService bookService;
	
	//@Autowired
	//private BorrowerService borrowerService;

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
		return null;
	}

	@Override
	public Borrowing borrowBook(Long bookId, Long borrowerId) {
		return null;
	}

	@Override
	public Borrowing returnBook(Long bookId) {
		return null;
	}

}
