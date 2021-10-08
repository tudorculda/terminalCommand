package com.harulmures.runcommand.controller;

import com.harulmures.runcommand.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("run")
public class CommandCOntroller {
    @Autowired
    private CommandService commandService;

    @GetMapping("vlc")
    public void runWithParameters(@RequestParam("file") String file){
        commandService.runVlc(file);
    }
}
