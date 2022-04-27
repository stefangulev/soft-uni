package com.example.packetproblemspring;

import com.example.packetproblemspring.service.DeserializerService;
import com.example.packetproblemspring.service.SerializerService;
import com.example.packetproblemspring.service.UtilService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.NoSuchFileException;
import java.util.Scanner;

@Component
public class CommandRunner implements CommandLineRunner {
    private final Scanner scan;
    private final SerializerService serializerService;
    private final DeserializerService deserializerService;
    private final UtilService utilService;

    public CommandRunner(SerializerService serializerService, DeserializerService deserializerService, UtilService utilService) {
        this.scan = new Scanner(System.in);
        this.serializerService = serializerService;
        this.deserializerService = deserializerService;
        this.utilService = utilService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to PacketProblem. Valid commands start with '-i' or '-o', followed by a whitespace character. After that, please make sure to include a file name that contains letters or numbers, along with the .txt extension.");
        System.out.println("Enter command to start program:");
        String[] userInput = scan.nextLine().split("\\s+");
        String command = userInput[0];
        String fileName = userInput[1];

        if(!utilService.fileInputIsValid(fileName)){
            System.out.println("Invalid file name! Please make sure to include a file name that contains letters or numbers, along with the .txt extension.");
            return;
        }

        switch (command) {
            case "-o":
                System.out.println("Enter string to be serialized (can't be blank) :");
                String input = scan.nextLine();
                serializerService.serialize(input, fileName);
                break;
            case "-i":
                try {
                    deserializerService.deserialize(fileName);
                } catch (NoSuchFileException ex) {
                    System.out.println("The file you are trying to read from does not exist!");
                }
                break;
            default:
                System.out.println("Invalid command! Valid commands include '-i' ,'-o', followed by a whitespace character");
        }
    }
}
