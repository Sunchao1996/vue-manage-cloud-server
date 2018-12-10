package com.sc.util.doc;

import com.sc.util.file.FileUtil;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;

/**
 * 请求xml和报告的接口
 * Created by 孔垂云 on 2017/8/15.
 */
public class DocUtil {

    /**
     * 解析返回的xml值
     *
     * @param xml
     * @return
     */
    public static HashMap<String, HashMap<String, String>> dealXml(String xml) {
//        String str = FileUtil.readFile("D:\\ivalue.xml");
        HashMap<String, HashMap<String, String>> hashMap = new HashMap<>();//定义解析的xml结构，外层的key为line节点的id值，内层hash的key为data的id，value为data的value
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(xml)));
            Element root = doc.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            NodeList nodeList = root.getElementsByTagName("line");
            for (int k = 0; k < nodeList.getLength(); k++) {
                Element element = (Element) nodeList.item(k);
                String id = element.getAttribute("value");
                NodeList nodeDatas = element.getElementsByTagName("data");
                HashMap<String, String> hashData = new HashMap<>();
                for (int j = 0; j < nodeDatas.getLength(); j++) {
                    Element data = (Element) nodeDatas.item(j);
                    hashData.put(data.getAttribute("id"), data.getAttribute("value"));
                }
                hashMap.put(id, hashData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /**
     * 根据line的id和data的id取对应的值
     *
     * @param hashMap 解析xml返回的值
     * @param line    第几行
     * @param param   参数名
     * @return
     */
    public static String getXmlData(HashMap<String, HashMap<String, String>> hashMap, String line, String param) {
        try {
            return hashMap.get(line).get(param);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        String str = FileUtil.readFile("D:\\ivalue.xml");
    }

}
