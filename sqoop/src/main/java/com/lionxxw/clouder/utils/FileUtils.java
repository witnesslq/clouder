package com.lionxxw.clouder.utils;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作工具类
 * Package com.lionxxw.clouder.utils
 * Project clouder
 * Company www.baofoo.com
 * Author wangjian@baofoo.com
 * Created on 2016/12/6 15:21
 * version 1.0.0
 */
public class FileUtils {

    /**
     * 创建文件
     *
     * @param path
     * @throws IOException
     */
    public static void createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        System.out.println(path + " create success!");
    }
}