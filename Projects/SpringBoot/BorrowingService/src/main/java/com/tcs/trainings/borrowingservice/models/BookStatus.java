/**
 * 
 */
package com.tcs.trainings.borrowingservice.models;

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
