/**
 * 
 */
package com.example.library.entities;

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
    },
	NOT_IN_SERVICE {
        @Override
        public String toString() {
            return "Not In Service";
        }
    };
}
