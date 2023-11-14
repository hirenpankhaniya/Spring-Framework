/**
 * 
 */
package com.example.simplelibrary.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(
			name = "books_authors", 
			joinColumns = {@JoinColumn(name = "bookid")}, 
			inverseJoinColumns = {@JoinColumn(name = "authorid")}
	)
	private Set<Author> authors = new HashSet<>();
	
	@OneToOne(mappedBy = "book", cascade= CascadeType.ALL)
	@JsonIgnore
	private Borrowing borrowing;

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
	public Set<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
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
	public Borrowing getBorrowing() {
		return borrowing;
	}

	/**
	 * @param borrowing the borrowing to set
	 */
	public void setBorrowing(Borrowing borrowing) {
		this.borrowing = borrowing;
	}

}
