/**
 * 
 */
package com.example.library.services;

import java.util.List;

import com.example.library.entities.LibraryBranch;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public interface LibraryBranchService {

	LibraryBranch addLibraryBranch(LibraryBranch libraryBranch);
	
	LibraryBranch findLibraryBranchById(Long id);
	
	List<LibraryBranch> findAllLibraryBranches();
	
	LibraryBranch updateLibraryBranch(Long id, LibraryBranch libraryBranch);
	
	LibraryBranch deleteLibraryBranchById(Long id);
}
