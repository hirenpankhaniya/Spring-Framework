/**
 * 
 */
package com.tcs.trainings.borrowerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.trainings.borrowerservice.entities.Borrower;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

}
