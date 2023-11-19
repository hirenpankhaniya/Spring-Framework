/**
 * 
 */
package com.tcs.trainings.borrowerservice.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.trainings.borrowerservice.entities.Borrower;
import com.tcs.trainings.borrowerservice.repositories.BorrowerRepository;
import com.tcs.trainings.borrowerservice.services.BorrowerService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class BorrowerServiceImpl implements BorrowerService {
	
	private final BorrowerRepository borrowerRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	@Autowired
	public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
		this.borrowerRepository = borrowerRepository;
	}

	@Override
	public Borrower addBorrower(Borrower borrower) {
		try {
			if(borrower != null) {
				return borrowerRepository.save(borrower);
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while adding Borrower: ", e);
		}
		return null;
	}

	@Override
	public Borrower findBorrowerById(Long id) {
		try {
			if(id != null && id > 0) {
				Optional<Borrower> optional = borrowerRepository.findById(id);
				if(!optional.isEmpty()) {
					return optional.get();
				}				
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while finding Borrower by Id: ", e);
		}
		return null;
	}

	@Override
	public List<Borrower> findAllBorrowers() {
		try {
			return borrowerRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Borrowers: ", e);
		}
		return new ArrayList<>();
	}

	@Override
	public Borrower updateBorrower(Long id, Borrower borrower) {
		try {
			if(id != null && id > 0 && borrower != null) {
				Borrower borrowerFromDB = findBorrowerById(id);
				if(borrowerFromDB != null) {
					borrowerFromDB.setName(borrower.getName());
					borrowerFromDB.setBorrowings(borrower.getBorrowings());
					return borrowerRepository.save(borrowerFromDB);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while updating Borrower: ", e);
		}
		return null;
	}

	@Override
	public Borrower deleteBorrowerById(Long id) {
		try {
			Borrower borrowerFromDB = findBorrowerById(id);
			if(borrowerFromDB != null) {
				borrowerRepository.deleteById(id);
				return borrowerFromDB;
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while deleting Borrower by Id: ", e);
		}
		return null;
	}

}
