/**
 * 
 */
package com.example.library.services;

import java.util.List;

import com.example.library.entities.Genre;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface GenreService {

	Genre addGenre(Genre genre);
	
	Genre findGenreById(Long id);
	
	List<Genre> findAllGenres();
	
	Genre updateGenre(Long id, Genre genre);
	
	Genre deleteGenreById(Long id);
}
