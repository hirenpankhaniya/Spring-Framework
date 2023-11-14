/**
 * 
 */
package com.tcs.trainings.authorservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.trainings.authorservice.entities.Author;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
}
