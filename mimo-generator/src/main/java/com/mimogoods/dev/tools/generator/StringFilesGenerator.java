package com.mimogoods.dev.tools.generator;

import com.mimogoods.dev.tools.commons.utils.Util;
import com.mimogoods.dev.tools.generator.context.GeneratorContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class StringFilesGenerator {
    private StringFilesGenerator() {
    }

    public static void generate(GeneratorContext generatorContext) throws IOException {
        writeFile(generatorContext.getErrorMessages(), generatorContext.getStringsFileOut());
    }

    private static void writeFile(Map<String, Pair<String, String>> errorMessages, String stringsFileOut) throws IOException {
        File csvOutputFile = new File(stringsFileOut);
        csvOutputFile.delete();
        csvOutputFile.getParentFile().mkdirs();
        csvOutputFile.createNewFile();
        final String fileName = StringUtils.removeEnd(csvOutputFile.getName(), ".strings");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println(String.join(",", "8859_1", StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
            errorMessages.entrySet().stream()
                    .map(errorMessage -> convertToCSV(errorMessage, fileName))
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static String convertToCSV(Map.Entry<String, Pair<String, String>> errorMessage, String documentSectionName) {
        final Pair<String, String> messageCommentPair = errorMessage.getValue();
        return Util.convertToCSV(documentSectionName, errorMessage.getKey(), messageCommentPair.getLeft(),
                messageCommentPair.getLeft(), messageCommentPair.getRight());
    }

}

