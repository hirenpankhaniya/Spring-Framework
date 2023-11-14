/**
 * 
 */
package com.example.library.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	@Column(name = "subtitle", nullable = false)
	private String subTitle;
	@Column(name = "format", nullable = false)
	private BookFormat format;
	@Column(name = "language", nullable = false)
	private BookLanguage language;
	
	@ManyToMany()
	@JoinTable(
			name = "books_authors", 
			joinColumns = {@JoinColumn(name = "bookid")}, 
			inverseJoinColumns = {@JoinColumn(name = "authorid")}
	)
	private Set<Author> authors = new HashSet<>();
	
	@ManyToMany()
	@JoinTable(
			name = "books_genres", 
			joinColumns = {@JoinColumn(name = "bookid")}, 
			inverseJoinColumns = {@JoinColumn(name = "genreid")}
	)
	private Set<Genre> genres = new HashSet<>();
	
	@ManyToOne()
	@JoinTable(
			name = "books_borrowers", 
			joinColumns = {@JoinColumn(name = "bookid")}, 
			inverseJoinColumns = {@JoinColumn(name = "borrowerid")}
	)
	private Borrower borrower;
	
	@ManyToOne()
	@JoinTable(
			name = "books_library_branches", 
			joinColumns = {@JoinColumn(name = "bookid")}, 
			inverseJoinColumns = {@JoinColumn(name = "branchid")}
	)
	private LibraryBranch libraryBranch;

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
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return the format
	 */
	public BookFormat getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(BookFormat format) {
		this.format = format;
	}

	/**
	 * @return the language
	 */
	public BookLanguage getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(BookLanguage language) {
		this.language = language;
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
	 * @return the genres
	 */
	public Set<Genre> getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	/**
	 * @return the libraryBranch
	 */
	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}

	/**
	 * @param libraryBranch the libraryBranch to set
	 */
	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}
}
