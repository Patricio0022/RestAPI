package org.rest.exceptions;

    public class RequiredObjectIsNull extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public RequiredObjectIsNull() {
            super("Required object is null");
        }

        public RequiredObjectIsNull(String message) {
            super(message);
        }




}
