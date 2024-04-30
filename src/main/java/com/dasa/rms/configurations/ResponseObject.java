package com.dasa.rms.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data @Component
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {
    private String code;     // Return Code for decision making
    private boolean status;  // Return Status for decision making
    private String message;  //Return Message if required
    private Object data;    // Return Response Object


    public String responseObjectMethod() {
        return "API Response{" +
                "code='" + code + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String responseObjectlevelCheck() {
        return "levelCheck Response{" +
                "code='" + code + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public ResponseObject success(String code, Object object){
        return new ResponseObject(code, true, null, object);
    }

    public ResponseObject failure(String code, String message){
        return new ResponseObject(code, false, message, null);
    }
}
