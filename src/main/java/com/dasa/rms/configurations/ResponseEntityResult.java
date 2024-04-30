package com.dasa.rms.configurations;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Data
@Component
public class ResponseEntityResult {

    public ResponseEntity<?> responseEntity(ResponseObject responseObject) {
        switch (responseObject.getCode()) {
            case "200":
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON).body(responseObject);
            case "400":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON).body(responseObject);
            case "401":
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .contentType(MediaType.APPLICATION_JSON).body(responseObject);
            case "500":
                responseObject.setStatus(false);
                responseObject.setMessage("Please Contact Help Center");
                responseObject.setData(null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON).body(responseObject);
            default:
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setMessage("Please Contact Help Center");
                responseObject.setData(null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON).body(responseObject);
        }
    }


}
