/**
 * 
 */
package com.example.simplelibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simplelibrary.entities.Book;


/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
}
