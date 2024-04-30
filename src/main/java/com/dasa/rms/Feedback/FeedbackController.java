package com.dasa.rms.Feedback;

import com.dasa.rms.Feedback.Feedback;
import com.dasa.rms.Feedback.FeedbackController;
import com.dasa.rms.configurations.ResponseEntityResult;
import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Feedback")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Feedback", description = "Feedback APIs")
public class FeedbackController {
    private final SL4J sl4J;
    private final ResponseEntityResult responseEntityResult;
    private final ResponseObject responseObject; // Generic Response Object
    private final FeedbackService feedbackService;

    @PostMapping("/addNewFeedback")
    private ResponseEntity<?> addNewFeedback(@Valid @RequestBody CreateFeedbackRequest createFeedbackRequest, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, FeedbackController.class, "addNewFeedback", "API-> " + endpoint);
            return responseEntityResult.responseEntity(feedbackService.addNew(httpServletRequest, responseObject, createFeedbackRequest));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, FeedbackController.class, "addNewFeedback", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }


    @PutMapping("/updateFeedback")
    private ResponseEntity<?> updateFeedback(@Valid @RequestBody Feedback feedback, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, FeedbackController.class, "updateFeedback", "API-> " + endpoint);
            return responseEntityResult.responseEntity(feedbackService.update(httpServletRequest, responseObject, feedback));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, FeedbackController.class, "updateFeedback", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, FeedbackController.class, "deleteById", "API-> " + endpoint);
            return responseEntityResult.responseEntity(feedbackService.delete(httpServletRequest, responseObject, id));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, FeedbackController.class, "deleteById", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

}
