/**
 * 
 */
package com.tcs.trainings.authorservice.models;

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
public class Book {

	private Long id;
	private String title;
	private String isbn;
	private BookStatus status;
	private Set<Long> authorIds;
	private Long borrowingId;
	
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the status
	 */
	public BookStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(BookStatus status) {
		this.status = status;
	}
	
	/**
	 * @return the authors
	 */
	public Set<Long> getAuthorIds() {
		return authorIds;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthorIds(Set<Long> authorIds) {
		this.authorIds = authorIds;
	}
	
	/**
	 * @return the borrowing
	 */
	public Long getBorrowingId() {
		return borrowingId;
	}

	/**
	 * @param borrowing the borrowing to set
	 */
	public void setBorrowingId(Long borrowingId) {
		this.borrowingId = borrowingId;
	}

}
