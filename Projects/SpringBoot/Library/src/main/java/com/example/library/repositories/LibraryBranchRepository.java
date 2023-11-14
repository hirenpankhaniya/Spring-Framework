/**
 * 
 */
package com.example.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entities.LibraryBranch;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Repository
public interface LibraryBranchRepository extends JpaRepository<LibraryBranch, Long> {

}
