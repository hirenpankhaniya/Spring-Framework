/**
 * 
 */
package com.example.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entities.Genre;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
