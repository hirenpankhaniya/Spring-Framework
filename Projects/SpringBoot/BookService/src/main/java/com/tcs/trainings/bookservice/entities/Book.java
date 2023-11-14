/**
 * 
 */
package com.tcs.trainings.bookservice.entities;

import java.util.Set;

import com.tcs.trainings.bookservice.models.Author;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "isbn", nullable = false)
	private String isbn;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private BookStatus status;
	
	@ElementCollection(targetClass = Long.class)
	private Set<Long> authorIds;
	
	@Column(name = "borrowingId", nullable = true)
	private Long borrowingId;
	
	@Transient
	private Set<Author> authors;

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
