/**
 * 
 */
package com.example.library.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entities.Genre;
import com.example.library.repositories.GenreRepository;
import com.example.library.services.GenreService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class GenreServiceImpl implements GenreService {
	
	private final GenreRepository genreRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	@Autowired
	public GenreServiceImpl(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Override
	public Genre addGenre(Genre genre) {
		try {
			if(genre != null) {
				return genreRepository.save(genre);
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while adding Genre: ", e);
		}
		return null;
	}

	@Override
	public Genre findGenreById(Long id) {
		try {
			if(id != null && id > 0) {
				Optional<Genre> optional = genreRepository.findById(id);
				if(!optional.isEmpty()) {
					return optional.get();
				}				
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while finding Genre by Id: ", e);
		}
		return null;
	}

	@Override
	public List<Genre> findAllGenres() {
		try {
			return genreRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Genres: ", e);
		}
		return new ArrayList<>();
	}

	@Override
	public Genre updateGenre(Long id, Genre genre) {
		try {
			if(id != null && id > 0 && genre != null) {
				Genre genreFromDB = findGenreById(id);
				if(genreFromDB != null) {
					genreFromDB.setName(genre.getName());
					genreFromDB.setBooks(genre.getBooks());
					return genreRepository.save(genreFromDB);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while updating Genre: ", e);
		}
		return null;
	}

	@Override
	public Genre deleteGenreById(Long id) {
		try {
			Genre genreFromDB = findGenreById(id);
			if(genreFromDB != null) {
				genreRepository.deleteById(id);
				return genreFromDB;
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while deleting Genre by Id: ", e);
		}
		return null;
	}

}
