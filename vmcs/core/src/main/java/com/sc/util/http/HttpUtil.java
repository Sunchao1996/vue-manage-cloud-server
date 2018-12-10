package com.sc.util.http;

import com.sc.util.log.LogUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 孔垂云 on 2017/7/31.
 */
public class HttpUtil {

    /**
     * 发送http请求， 参数是json串，获取返回值，返回值是json
     *
     * @param url
     * @param jsonParam
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getJsonFromUrlPost(String url, String jsonParam) {
        LogUtil.infoHttp("请求url：" + url + ";请求参数：" + jsonParam);
        String getJson = "";
        HttpClient httpclient = null;
        HttpPost httpPost = null;
        try {
            httpclient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response = httpclient.execute(httpPost);
            response.getStatusLine().getStatusCode();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String str = "";
            while ((str = rd.readLine()) != null) {
                getJson += str;
            }
            LogUtil.infoHttp("返回值：" + getJson);
        } catch (Exception e) {
            LogUtil.infoHttp(e.getMessage());
        } finally {
            httpPost.abort();
            httpPost.releaseConnection();
        }
        return getJson;
    }

    /**
     * 根据url请求服务器，获取json
     *
     * @param url
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getJsonFromUrl(String url) {
        LogUtil.infoHttp("请求url：" + url);
        String getJson = "";
        HttpClient httpclient = null;
        HttpGet httpgets = null;
        try {
            httpclient = new DefaultHttpClient();
            httpgets = new HttpGet(url);
            httpgets.addHeader("Content-Type", "text/html;charset=UTF-8");
            HttpResponse response = httpclient.execute(httpgets);
            response.getStatusLine().getStatusCode();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String str = "";
            while ((str = rd.readLine()) != null) {
                getJson += str;
            }
            LogUtil.infoHttp("返回值：" + getJson);
        } catch (Exception e) {
            LogUtil.infoHttp(e.getMessage());
            LogUtil.infoHttp(url);
        } finally {
            httpgets.abort();
            httpgets.releaseConnection();
        }
        return getJson;
    }

    /**
     * 根据请求，获取map
     *
     * @param request
     * @return
     */
    public static Map<String, String> parseXml(HttpServletRequest request) {
        // 解析结果存储在HashMap
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            for (Element e : elementList)
                map.put(e.getName(), e.getText());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.infoHttp("解析xml报错：" + e.getMessage());
            LogUtil.infoHttp("解析xml报错：" + inputStream.toString());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = null;
        }
        return map;
    }


    /**
     * 解析返回的xml
     *
     * @param protocolXML
     * @return
     */
    public static HashMap<String, String> parseXml(String protocolXML) {
        protocolXML = protocolXML.replaceAll("\\\n", "");
        System.out.println(protocolXML);
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(protocolXML)));
            org.w3c.dom.Element root = doc.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            if (nodes != null) {
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node book = nodes.item(i);
                    hashMap.put(book.getNodeName(), book.getFirstChild().getNodeValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /**
     * 提交xml的http请求
     *
     * @param urlStr
     * @param xml
     * @return
     */
    public static String postXml(String urlStr, String xml) {
        LogUtil.infoHttp("请求url：" + urlStr + ";请求参数：" + xml);
        String ret = "";
        OutputStreamWriter out = null;
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
//            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            con.setRequestProperty("charset", "utf-8");
            out = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            out.write(xml);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
                ret += line;
            }
            con = null;
            LogUtil.infoHttp("返回结果：" + ret);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(ret);
        return ret;
    }

    /**
     * 提交xml的http请求,设置超时时间为5分钟
     *
     * @param urlStr
     * @param xml
     * @return
     */
    public static String doPostXml(String urlStr, String xml) {
        LogUtil.infoHttp("请求url：" + urlStr + ";请求参数：" + xml);
        String ret = "";
        OutputStreamWriter out = null;
        try {
            ///设置超时时间
            int connectionTimeout = 5 * 60 * 1000;
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(connectionTimeout);//设置连接主机超时
            con.setReadTimeout(connectionTimeout);//从主机读取数据超时
            con.setDoOutput(true);
//            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            con.setRequestProperty("charset", "utf-8");
            out = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            out.write(xml);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
                ret += line;
            }
            con = null;
            LogUtil.infoHttp("返回结果：" + ret);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(ret);
        return ret;
    }

    /**
     * 发送json请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        LogUtil.infoHttp(url);
        LogUtil.infoHttp(json);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            ///设置超时时间
            int connectionTimeout = 10 * 60 * 1000;
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setSocketTimeout(connectionTimeout)
                    .setConnectTimeout(connectionTimeout)
                    .setConnectionRequestTimeout(connectionTimeout)
                    .setStaleConnectionCheckEnabled(true)
                    .build();
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .build();
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(defaultRequestConfig);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            LogUtil.infoHttp(resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 加入header
     *
     * @param url
     * @param json
     * @param header
     * @return
     */
    public static String doJpushPostJson(String url, String json, String header) {
        // 创建Httpclient对象
        LogUtil.infoHttp(url);
        LogUtil.infoHttp(json);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            ///设置超时时间
            int connectionTimeout = 10 * 1000;
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setSocketTimeout(connectionTimeout)
                    .setConnectTimeout(connectionTimeout)
                    .setConnectionRequestTimeout(connectionTimeout)
                    .setStaleConnectionCheckEnabled(true)
                    .build();
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .build();
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(defaultRequestConfig);
            httpPost.setHeader("Authorization", header);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            LogUtil.infoHttp(resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


    /**
     * 请求报告Json，设置超时时间为10分钟
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPostDocJson(String url, String json) {
        // 创建Httpclient对象
        LogUtil.infoHttp("【报告请求Url】:" + url);
        LogUtil.infoHttp("【报告请求参数】:" + json);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            ///设置超时时间
            int connectionTimeout = 10 * 60 * 1000;
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setSocketTimeout(connectionTimeout)
                    .setConnectTimeout(connectionTimeout)
                    .setConnectionRequestTimeout(connectionTimeout)
                    .setStaleConnectionCheckEnabled(true)
                    .build();
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .build();
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(defaultRequestConfig);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            LogUtil.infoHttp("【报告请求结果】:" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 请求xml的Json，设置超时时间为5分钟
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPostXmlJson(String url, String json) {
        // 创建Httpclient对象
        LogUtil.infoHttp(url);
        LogUtil.infoHttp(json);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            ///设置超时时间
            int connectionTimeout = 5 * 60 * 1000;
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setSocketTimeout(connectionTimeout)
                    .setConnectTimeout(connectionTimeout)
                    .setConnectionRequestTimeout(connectionTimeout)
                    .setStaleConnectionCheckEnabled(true)
                    .build();
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .build();
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(defaultRequestConfig);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            LogUtil.infoHttp(resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 请求pdf报告Json，设置超时时间为10分钟
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPostPdfJson(String url, String json) {
        // 创建Httpclient对象
        LogUtil.infoHttp(url);
        LogUtil.infoHttp(json);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            ///设置超时时间
            int connectionTimeout = 10 * 60 * 1000;
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setSocketTimeout(connectionTimeout)
                    .setConnectTimeout(connectionTimeout)
                    .setConnectionRequestTimeout(connectionTimeout)
                    .setStaleConnectionCheckEnabled(true)
                    .build();
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .build();
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(defaultRequestConfig);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            LogUtil.infoHttp(resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null) response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static void main(String[] args) {
        System.out.println(HttpUtil.doPostJson("http://localhost:8080/api/checkLogin", "{\"username\":\"13810280825\",\"password\":\"123456\"}"));
    }
}
