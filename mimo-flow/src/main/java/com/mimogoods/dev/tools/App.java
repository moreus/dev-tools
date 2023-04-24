package com.mimogoods.dev.tools;

import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

public class App {
    public static void main(String[] args) {
        File dir = new File("/Users/I068378/Documents/01 幼小【2020 秋】");
        Collection<File> files = FileUtils.listFiles(dir, null, true);
        files.stream().forEach(x -> {
            String fileName = x.getAbsolutePath().replace("【CC下载站：www.52sk3.com】", "");
                    x.renameTo(new File(fileName));
        });
    }
}
