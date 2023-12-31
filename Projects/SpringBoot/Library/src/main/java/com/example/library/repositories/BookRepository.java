/**
 * 
 */
package com.example.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entities.Book;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
