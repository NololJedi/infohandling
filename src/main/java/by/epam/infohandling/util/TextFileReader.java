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
        try (FileReader fileReader = new FileReader(filePath)) {
            StringBuilder text = new StringBuilder();

            while (fileReader.ready()) {
                char symbol = (char)fileReader.read();
                text.append(symbol);
            }

            String result = text.toString();
            result = result.replaceAll("\r","");
            return result;
        } catch (IOException exception) {
            throw new IncorrectFileException("Incorrect file.", exception);
        }

    }

}
