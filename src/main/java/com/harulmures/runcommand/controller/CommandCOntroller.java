package com.harulmures.runcommand.controller;

import com.harulmures.runcommand.service.CommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("run")
@Slf4j
public class CommandCOntroller {
    @Autowired
    private CommandService commandService;

    @GetMapping("video")
    public ResponseEntity<String> runWithParameters(@RequestParam("file") String file){
        try{

        commandService.runVideo(file);
        return new ResponseEntity<>("Comanda a fost lansata", HttpStatus.OK);
        } catch(Exception ex){
            log.error(ex.getMessage(), ex);

            return new ResponseEntity<>("Ceva nu a mers bine, vorbeste cu Tudor, noteaza timpul la care a aparut acest mesaj (" + LocalDateTime.now().toString() + "):" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("video/stop")
    public ResponseEntity<String> stopAllVideos(){
        try{
            commandService.terminatePreviousProcesses();
            return new ResponseEntity<>("Comanda a fost lansata", HttpStatus.OK);
        } catch(Exception ex){
            log.error(ex.getMessage(), ex);

            return new ResponseEntity<>("Ceva nu a mers bine, vorbeste cu Tudor, noteaza timpul la care a aparut acest mesaj (" + LocalDateTime.now().toString() + "):" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
