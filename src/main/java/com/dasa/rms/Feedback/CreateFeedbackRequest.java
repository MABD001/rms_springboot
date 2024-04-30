package com.dasa.rms.Feedback;

import lombok.Data;

@Data
public class CreateFeedbackRequest {
    private String feedback;
    private long customerId;
    private long reservation;

}
