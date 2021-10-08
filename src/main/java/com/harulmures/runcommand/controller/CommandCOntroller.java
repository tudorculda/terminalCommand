package com.harulmures.runcommand.controller;

import com.harulmures.runcommand.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("run")
public class CommandCOntroller {
    @Autowired
    private CommandService commandService;

    @GetMapping("vlc")
    public ResponseEntity<String> runWithParameters(@RequestParam("file") String file){
        try{

        commandService.runVlc(file);
        return new ResponseEntity<>("Comanda a fost lansata", HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>("Ceva nu a mers bine, vorbeste cu Tudor, noteaza timpul la care a aparut acest mesaj (" + LocalDateTime.now().toString() + "):" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
