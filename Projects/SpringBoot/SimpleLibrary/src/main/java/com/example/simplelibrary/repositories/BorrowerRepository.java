/**
 * 
 */
package com.example.simplelibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simplelibrary.entities.Borrower;


/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

	Borrower findBorrowerByName(String borrowerName);
}
