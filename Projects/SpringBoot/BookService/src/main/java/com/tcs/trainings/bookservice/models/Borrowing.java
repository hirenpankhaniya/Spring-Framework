/**
 * 
 */
package com.tcs.trainings.bookservice.models;

import java.sql.Date;

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
public class Borrowing {

	private Long id;
	private Long bookId;
	private Long borrowerId;
	private Date borrowingDate;
	private Date returnDate;

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
	 * @return the bookId
	 */
	public Long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBook(Long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the borrowerId
	 */
	public Long getBorrowerId() {
		return borrowerId;
	}

	/**
	 * @param borrowerId the borrowerId to set
	 */
	public void setBorrowerId(Long borrowerId) {
		this.borrowerId = borrowerId;
	}

	/**
	 * @return the borrowingDate
	 */
	public Date getBorrowingDate() {
		return borrowingDate;
	}

	/**
	 * @param borrowingDate the borrowingDate to set
	 */
	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
