/**
 * 
 */
package com.example.library.entities;

/**
 * 
 * @author Hiren Pankhaniya
 * 
 */
public enum BookLanguage {

	ENGLISH {
        @Override
        public String toString() {
            return "English";
        }
    },
	FRENCH {
        @Override
        public String toString() {
            return "French";
        }
    },
	SPANISH {
        @Override
        public String toString() {
            return "Spanish";
        }
    };
}
