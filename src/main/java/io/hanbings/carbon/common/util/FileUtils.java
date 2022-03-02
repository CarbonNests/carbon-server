package io.hanbings.carbon.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileUtils {
    public static List<File> getFiles(String path) {
        List<File> files = new ArrayList<>();
        FileUtils.addFile(new File(path), files);
        return files;
    }

    private static void addFile(File file, List<File> files) {
        // Todo: 什么时候回来修缮下 改成流处理
        if (file.isFile()) {
            files.add(file);
        } else {
            File[] fs = file.listFiles();
            if (fs != null) {
                for (File f : fs) {
                    addFile(f, files);
                }
            }
        }
    }

}
