package com.example.todoserver;

import org.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

@CrossOrigin
@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public ResponseEntity<JSONObject> helloWorld() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "Hello World");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
    }

}
