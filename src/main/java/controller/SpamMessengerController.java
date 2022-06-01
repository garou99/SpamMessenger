package controller;

import dto.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import service.SpamMessengerService;

public class SpamMessengerController {

    @Autowired
    SpamMessengerService spamMessengerService;

    @PostMapping(value = "/sendMessage")
    public ResponseEntity<String> getStatus(@RequestBody Details details) {

        String response = spamMessengerService.getMessageResponse(details);

        if (response.equals("Error")) {
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }
}
