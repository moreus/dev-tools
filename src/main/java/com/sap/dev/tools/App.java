package com.sap.dev.tools;

import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

public class App {
    public static void main(String[] args) {
        File dir = new File("/Users/I068378/Documents/07_private/【2020春】3-4年级");
        Collection<File> files = FileUtils.listFiles(dir, null, true);
        files.stream().forEach(x -> {
            String fileName = x.getAbsolutePath().replace("", "");
                    x.renameTo(new File(fileName));
        });
    }
}
