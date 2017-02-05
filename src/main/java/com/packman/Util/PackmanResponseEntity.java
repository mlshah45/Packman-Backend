package com.packman.Util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by sujaysudheendra on 5/4/16.
 */
public class PackmanResponseEntity extends ResponseEntity {

    public PackmanResponseEntity(SuccessResponse response) {
        super(response, HttpStatus.OK);
    }
}
