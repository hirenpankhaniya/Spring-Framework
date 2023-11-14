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

import com.example.library.entities.LibraryBranch;
import com.example.library.repositories.LibraryBranchRepository;
import com.example.library.services.LibraryBranchService;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Service
public class LibraryBranchServiceImpl implements LibraryBranchService {
	
	private final LibraryBranchRepository libraryBranchRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	@Autowired
	public LibraryBranchServiceImpl(LibraryBranchRepository libraryBranchRepository) {
		this.libraryBranchRepository = libraryBranchRepository;
	}

	@Override
	public LibraryBranch addLibraryBranch(LibraryBranch libraryBranch) {
		try {
			if(libraryBranch != null) {
				return libraryBranchRepository.save(libraryBranch);
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while adding Library Branch: ", e);
		}
		return null;
	}

	@Override
	public LibraryBranch findLibraryBranchById(Long id) {
		try {
			if(id != null && id > 0) {
				Optional<LibraryBranch> optional = libraryBranchRepository.findById(id);
				if(!optional.isEmpty()) {
					return optional.get();
				}				
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while finding Library Branch by Id: ", e);
		}
		return null;
	}

	@Override
	public List<LibraryBranch> findAllLibraryBranches() {
		try {
			return libraryBranchRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while finding all Library Branches: ", e);
		}
		return new ArrayList<>();
	}

	@Override
	public LibraryBranch updateLibraryBranch(Long id, LibraryBranch libraryBranch) {
		try {
			if(id != null && id > 0 && libraryBranch != null) {
				LibraryBranch libraryBranchFromDB = findLibraryBranchById(libraryBranch.getId());
				if(libraryBranchFromDB != null) {
					libraryBranchFromDB.setName(libraryBranch.getName());
					libraryBranchFromDB.setBooks(libraryBranch.getBooks());
					return libraryBranchRepository.save(libraryBranchFromDB);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while updating Library Branch: ", e);
		}
		return null;
	}

	@Override
	public LibraryBranch deleteLibraryBranchById(Long id) {
		try {
			LibraryBranch libraryBranchFromDB = findLibraryBranchById(id);
			if(libraryBranchFromDB != null) {
				libraryBranchRepository.deleteById(id);
				return libraryBranchFromDB;
			}			
		} catch (Exception e) {
			logger.error("Exception occurred while deleting Library Branch by Id: ", e);
		}
		return null;
	}

}
