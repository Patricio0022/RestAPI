package org.rest.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullExceptionTest extends RuntimeException{

@Serial
private static final long serialVersionUID = 1L;

public RequiredObjectIsNullExceptionTest(){
    super("its is not allowed to persist a null object");
}

public RequiredObjectIsNullExceptionTest(String ex){
    super(ex);
}

}
