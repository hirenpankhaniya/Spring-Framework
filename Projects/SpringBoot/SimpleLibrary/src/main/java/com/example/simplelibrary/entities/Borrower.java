/**
 * 
 */
package com.example.simplelibrary.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "borrowers")
public class Borrower {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Borrowing> borrowings;

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
	 * @return the borrowings
	 */
	public Set<Borrowing> getBorrowings() {
		return borrowings;
	}

	/**
	 * @param borrowings the borrowings to set
	 */
	public void setBorrowings(Set<Borrowing> borrowings) {
		this.borrowings = borrowings;
	}
	
}
