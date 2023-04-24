package com.mimogoods.dev.tools.files;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public class RenameFileInOrder {

    public static void main(String[] args) {
        File dir = new File("/Users/I068378/Desktop/Camera");
        Collection<File> files = FileUtils.listFiles(dir, null, true);
        Iterator<File> ifiles = files.iterator();
        int i = 1;
        while (ifiles.hasNext()) {
            File file = ifiles.next();
            if (file.getName().lastIndexOf(".jpg") != -1) {
                String name = String.format("%05d", i) + file.getName().substring(file.getName().indexOf("."));
                file.renameTo(new File("/Users/I068378/Desktop/Camera/" + name));
                i++;
            }
        }
    }
}
