package com.sc.util.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 文件下载工具类
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class DownloadUtil {

    /**
     * 文件下载方法
     *
     * @param response
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName, String encode) {
        response.setContentType("text/html;charset=" + encode);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String downLoadPath = filePath;
        try {
            long fileLength = new File(downLoadPath).length();
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {// win10 ie edge 浏览器 和其他系统的ie
                response.setHeader("Content-Disposition", "attachment;" + "filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {//firefox、chrome、safari、opera
                response.setHeader("Content-Disposition", "attachment;" +
                        "filename=" + new String(fileName.getBytes("UTF8"), "ISO8859-1"));
            }
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 根据网络地址下载文件到本地
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws Exception
     */

    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(30 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName );
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        System.out.println("info:" + url + " download success");
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 文件下载
     *
     * @param response
     * @param filePath
     * @param fileName
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
        DownloadUtil.download(request, response, filePath, fileName, "UTF-8");
    }
}
