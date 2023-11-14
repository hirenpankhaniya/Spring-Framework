/**
 * 
 */
package com.tcs.trainings.authorservice.models;

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
