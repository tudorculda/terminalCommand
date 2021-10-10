package com.harulmures.runcommand.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommandService {

    @Value("${video.command}")
    private String vlcCommand;

    @Value("${video.close-previous}")
    private Boolean cleanVideoBeforeStart;

    @Value("${video.video-folder:.}")
    private String videoFolder;

    private List<Process> openedVideoProcesses = new ArrayList<>();

    public void runVideo(String fileName) throws IOException {
        if (cleanVideoBeforeStart) {
            terminatePreviousProcesses();
        }

        File file = new File(videoFolder, fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(MessageFormat.format("Fisierul {0} nu a fost gasit in folderul {1}", fileName, videoFolder));
        }
        String command = MessageFormat.format(vlcCommand, file.getAbsolutePath());
        log.info("Se pregateste pentru executie comanda={}", command);
        Process process = Runtime.getRuntime().exec(command);
        log.info("Am lansat comanda:{}    --> process id:{}", command, process.pid());
        openedVideoProcesses.add(process);


    }

    public void terminatePreviousProcesses() {
        openedVideoProcesses.forEach(this::killProcess);
        openedVideoProcesses.clear();
    }

    private void killProcess(Process prc) {
        log.info("Se pregateste pentru terminare procesul={}", prc.info().toString());
        prc.destroy();
    }
}
