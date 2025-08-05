package br.edu.iff.ccc.gerenciadorapp.controller.restapi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "api/v1")
public class RestApiMainController{
    @GetMapping()
    public ResponseEntity<String> getApiHome(){
        return ResponseEntity.ok().header("content-type", "application/json")
            .body("{\"status\":\"Em implementação\"}");
    }
}