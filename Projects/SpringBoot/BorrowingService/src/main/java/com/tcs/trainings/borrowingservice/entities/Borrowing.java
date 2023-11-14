/**
 * 
 */
package com.tcs.trainings.borrowingservice.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "borrowings")
public class Borrowing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "bookid", nullable = false)
	private Long bookId;
	
	@Column(name = "borrowerid", nullable = false)
	private Long borrowerId;
	
	@Column(name = "borrowing_date", nullable = false)
	private Date borrowingDate;
	
	@Column(name = "return_date", nullable = true)
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
	public void setBookId(Long bookId) {
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
