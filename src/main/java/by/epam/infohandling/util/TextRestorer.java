package by.epam.infohandling.util;

import by.epam.infohandling.text.composite.TextComponent;
import org.apache.log4j.Logger;

public class TextRestorer {

    private static final Logger LOGGER = Logger.getLogger(TextRestorer.class);

    public void restoreText(TextComponent text) {
        if (text == null) {
            throw new IllegalArgumentException("Incorrect text detected.");
        }
        LOGGER.info("Start restoring text from components.");
        String result = text.getContent();

        LOGGER.info(String.format("Text was restored successfully:\n%s", result));
    }

}
