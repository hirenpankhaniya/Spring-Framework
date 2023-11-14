/**
 * 
 */
package com.example.library.entities;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public enum BookFormat {

	HARDCOVER {
        @Override
        public String toString() {
            return "Hardcover";
        }
    },
	PAPERBACK {
        @Override
        public String toString() {
            return "Paperback";
        }
    },
	KINDLE {
        @Override
        public String toString() {
            return "Kindle";
        }
    },
	AUDIOBOOK {
        @Override
        public String toString() {
            return "Audiobook";
        }
    },
	SPIRAL {
        @Override
        public String toString() {
            return "Spiral Bound";
        }
    };
}
