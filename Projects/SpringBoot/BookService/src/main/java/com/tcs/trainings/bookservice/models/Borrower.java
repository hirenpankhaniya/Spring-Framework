/**
 * 
 */
package com.tcs.trainings.bookservice.models;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Hiren Pankhaniya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrower {

	private Long id;
	private String name;
	private Set<Long> borrowingIds;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the borrowingIds
	 */
	public Set<Long> getBorrowings() {
		return borrowingIds;
	}

	/**
	 * @param borrowings the borrowingIds to set
	 */
	public void setBorrowings(Set<Long> borrowingIds) {
		this.borrowingIds = borrowingIds;
	}
	
}
