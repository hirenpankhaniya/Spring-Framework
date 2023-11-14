/**
 * 
 */
package com.example.library.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entities.Book;
import com.example.library.repositories.LibraryRepository;
import com.example.library.services.LibraryService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class LibraryServiceImpl implements LibraryService {
/*	
	private final LibraryRepository libraryRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());
*/
	/**
	 * 
	 *//*
	@Autowired
	public LibraryServiceImpl(LibraryRepository libraryRepository) {
		
		this.libraryRepository = libraryRepository;
	}

	@Override
	public List<Book> findBooksByGenreId(Long id) {
		/*
		try {
			if(id != null && id > 0) {
				return libraryRepository.findById(id);
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while finding Books by Genre Id: ", e);
		}*//*
		return null;
	}

	@Override
	public List<Book> findBooksByAuthorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findBooksByLibraryBranchId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findBooksByBorrowerId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
