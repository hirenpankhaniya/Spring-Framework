/**
 * 
 */
package com.example.simplelibrary.entities;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public enum BookStatus {

	AVAILABLE {
        @Override
        public String toString() {
            return "Available";
        }
    },
	BORROWED {
        @Override
        public String toString() {
            return "Borrowed";
        }
    };
}
