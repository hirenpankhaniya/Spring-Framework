/**
 * 
 */
package com.tcs.trainings.borrowingservice.repositories;

import java.util.List;

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

	List<Borrowing> findBorrowingByBookId(Long bookId);
}
