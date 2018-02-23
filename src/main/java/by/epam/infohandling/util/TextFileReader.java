package by.epam.infohandling.util;

import by.epam.infohandling.exceptions.IncorrectFileException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {

    public String readTextFromFile(String filePath) throws IncorrectFileException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect file path.");
        }
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader textReader = new BufferedReader(fileReader)) {
            StringBuilder text = new StringBuilder();

            while (textReader.ready()) {
                String line = textReader.readLine();
                text.append(line);
            }

            return text.toString();
        } catch (IOException exception) {
            throw new IncorrectFileException("Incorrect file.", exception);
        }

    }

}
