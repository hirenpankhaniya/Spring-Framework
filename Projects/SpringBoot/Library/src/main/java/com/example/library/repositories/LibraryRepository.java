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
public interface LibraryRepository extends JpaRepository<Book, Long> {
/*
	List<Book> findBooksByAuthorId(Long id);
	
	List<Book> findBooksByGenreId(Long id);
	
	List<Book> findBooksByLibraryBranchId(Long id);
	
	List<Book> findBooksByBorrowerId(Long id);
*/	
	/*
	 * @Query("SELECT new Tutorial(t.title) from Tutorial t WHERE t.id = ?1")
	Tutorial findTutorialTitlesById(long id);
	
	@Query("SELECT b FROM Author b WHERE b.name LIKE %?1%")
    List<Author> search(String author);
    
	 */
}
