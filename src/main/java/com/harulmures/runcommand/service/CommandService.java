package com.harulmures.runcommand.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.List;

@Service
@Slf4j
public class CommandService {

    @Value("${vlc.command}")
    private String vlcCommand;

    @Value("${vlc.close-previous}")
    private Boolean cleanVlcBeforeStart;

    @Value("${vlc.video-folder:.}")
    private String vlcFolder;

    private List<String> openedVlcPids;

    public void runVlc(String file) throws FileNotFoundException {

        String command = MessageFormat.format(vlcCommand, vlcFolder + File.separator + file);
        log.info("Will run the following command: {}", command);
        throw new IllegalArgumentException("nu am gasit fisierul");
    }
}
