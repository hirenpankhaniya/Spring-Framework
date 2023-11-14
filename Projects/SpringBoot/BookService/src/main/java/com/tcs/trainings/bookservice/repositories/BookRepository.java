/**
 * 
 */
package com.tcs.trainings.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.trainings.bookservice.entities.Book;


/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
}
