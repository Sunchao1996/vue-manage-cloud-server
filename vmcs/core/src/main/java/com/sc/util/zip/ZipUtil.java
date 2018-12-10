package com.sc.util.zip;

import com.sc.util.file.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩打包下载
 * Created by 孔垂云 on 2017/9/30.
 */
public class ZipUtil {

    /**
     * 压缩文件至一个文件包
     *
     * @param listZipFiles 要压缩的文件列表，里面是文件路径及文件名拼接起来
     * @param zipPath      压缩文件存放路径
     * @param zipFileName 压缩文件名
     * @return 临时生成的压缩包路径
     */
    public static String zipFiles(List<String> listZipFiles, String zipPath, String zipFileName) {
        String createRandomDir = zipPath + File.separator + zipFileName;//生成一个临时文件夹，用完后删除
        File zipPathDir = new File(createRandomDir);
        if (!zipPathDir.exists()) {//如果压缩文件存放路径不存在，先创建文件夹
            zipPathDir.mkdirs();
        }
        //把列表的文件拷贝至压缩文件夹
        for (String str : listZipFiles) {
            File file = new File(str);
            FileUtil.copyFile(str, zipPathDir + File.separator + file.getName());
        }
        zipDirectory(zipFileName, zipFileName, zipPath);
//        zip(zipPathDir, new File(destZipFile));
//        FileUtil.deleteFile(zipPathDir);
        return createRandomDir + ".zip";
    }

    /**
     * 删除打包pdf报告生成的临时文件夹和对应zip文件
     * @param zipPath
     */
    public static void deleteZipFile(String zipPath) {
        FileUtil.delete(zipPath);
        FileUtil.delete(zipPath + ".zip");
    }


    /**
     * 压缩文件夹
     *
     * @param directory
     * @param zipFileName
     * @param baseDir
     */
    public static void zipDirectory(String directory, String zipFileName, String baseDir) {
        // 要被压缩的文件夹
        String fileName1 = baseDir + File.separator + directory;
        File file = new File(fileName1);
        File zipFile = new File(baseDir + File.separator + zipFileName + ".zip");
        InputStream input = null;
        ZipOutputStream zipOut = null;
        try {
            zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            // zip的名称为
            zipOut.setComment(file.getName());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; ++i) {
                    input = new FileInputStream(files[i]);
                    zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + files[i].getName()));
                    int temp = 0;
                    while ((temp = input.read()) != -1) {
                        zipOut.write(temp);
                    }
                    input.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            {
                try {
                    if (input != null)
                        input.close();
                    if (zipOut != null)
                        zipOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();//定义文件列表
        list.add("D:\\upload\\pdf\\emp.pdf");
        list.add("D:\\upload\\pdf\\iValue.pdf");
       ZipUtil.zipFiles(list, "D:\\upload\\pdf", "测试名称");
    }

}
