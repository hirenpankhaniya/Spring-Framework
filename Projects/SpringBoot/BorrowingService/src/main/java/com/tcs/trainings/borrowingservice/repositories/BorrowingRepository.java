/**
 * 
 */
package com.tcs.trainings.borrowingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.trainings.borrowingservice.entities.Borrowing;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

	Borrowing findBorrowingByBookId(Long bookId);
}
