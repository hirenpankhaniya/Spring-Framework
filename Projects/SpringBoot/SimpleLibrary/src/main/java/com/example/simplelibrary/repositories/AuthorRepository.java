/**
 * 
 */
package com.example.simplelibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simplelibrary.entities.Author;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
}
