/**
 * 
 */
package com.example.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entities.Borrower;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

}
