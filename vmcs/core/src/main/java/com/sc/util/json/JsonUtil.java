package com.sc.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON工具类，进行对象转string和string转对象
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 把对象转成json串
     *
     * @param obj 对象，可以是VO、List、HashMap等等
     * @return 返回生成的json值
     */
    public static String toStr(Object obj) {
        String json_str = "";
        try {
            json_str = objectMapper.writer().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json_str;
    }

    /**
     * json转对象
     *
     * @param jsonStr   json字符串
     * @param valueType 要转成的对象类型，采用泛型的方式
     * @return
     */
    @Valid
    public static <T> T toObject(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象
     *
     * @param json      json字符串
     * @param valueType 要转成的对象类型，采用泛型的方式
     * @return
     */
    @Valid
    public static <T> T parseJsonAndValid(String json, Class<T> valueType) {
        T t = null;
        try {
            t = objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * 生成操作后的json串，{success:false,msgText:'删除失败'}
     *
     * @param b   是否
     * @param msg 提示消息
     * @return
     */
    public static String createOperaStr(boolean b, String msg) {
        return "{\"success\":" + b + ",\"msgText\":\"" + msg + "\"}";
    }


    /**
     * 生成datatable的分页json
     *
     * @param pageIndex
     * @param rows
     * @param data
     * @return
     */
    public static String createDataTablePageJson(int pageIndex, int rows, String data) {
        String json = "{ \"draw\": " + pageIndex + "," + "\"total\": " + rows + ",\"data\": " + data + "}";
        return json;
    }

    /**
     * 将json串转为jsonNode
     *
     * @param jsonString
     * @return
     * @throws IOException
     */
    public static JsonNode toJsonNode(String jsonString) throws IOException {
        return objectMapper.readTree(jsonString);
    }

    /**
     * 直接读取json串里面某个节点的值
     *
     * @param json
     * @param nodeStr
     * @return
     */
    public static String getNode(String json, String nodeStr) {
        String str = "";
        try {
            str = objectMapper.readTree(json).get(nodeStr).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 返回json的jsonNode
     *
     * @param json
     * @return
     */
    public static JsonNode getNode(String json) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    /**
     * 把json转成map
     *
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map toMap(String jsonStr) {
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        Map map = new HashMap();
        try {
            map = objectMapper.readValue(jsonStr, Map.class); //json转换成map
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 反序列化生成对象
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    public static void main(String[] args) {
        String str = "{\"interface\":{\"globalInfo\":{\"interfaceCode\":\"DFXJ1001\",\"responseCode\":\"1\",\"appId\":\"0\",\"requestTime\":\"2017-10-21 16:49:37:873\",\"requestCode\":\"DZFPQZ\",\"interfaceId\":\"\",\"dataExchangeId\":\"DZFPQZDFXJ10012017-10-21198389913\"},\"returnStateInfo\":{\"returnCode\":\"0000\",\"returnMessage\":\"成功\"},\"Data\":{\"dataDescription\":{\"zipCode\":\"0\"},\"content\":\"eyJGUFFRTFNIIjoiVEVTVDIwMTcxMDIxMTY1MDU2MDEiLCJGUF9ETSI6IjA1MDAwMzUyMTMzMyIsIkZQX0hNIjoiMTAxNjExODUiLCJLUFJRIjoiMjAxNzEwMjExNjUwMjEiLCJKWU0iOiIxMDAwNTczOTk0OTkwMDk0NzY5MSIsIlBERl9VUkwiOiJodHRwOi8vZGV2LmZhcGlhby5jb206MTkwODAvZHpmcC13eC9wZGYvZG93bmxvYWQ/cmVxdWVzdD1lNXVoZjhXRVRJT01nYWEyY0NVTXRsMTZkUEUwMjBNNEJ6TTZRN0M2bnJqejI0MDc5NFZ3bVoxSy5zV0FlcFYuZXMwRG1oRXdwdHRKTE96NXBmank3d19fJTVFY2pJaGZJaEVKRyIsIlNQX1VSTCI6Imh0dHA6Ly90ZXN0d3guZmFwaWFvLmNvbS9mcHQtd2VjaGF0L3d4YWRkY2FyZC5kbz9jb2RlPSUyQmFVcyUyRmlEZDRsYlNJRUZNM1ZraUJqbW9reUFoV1VMbVpvc0pRMGVmMyUyQmRHQzJCR0k3b3NHQWgzUzlKbGUxdjVqM0dOaWxrMDZHQkklMEFnTVNkem80TkJBJTNEJTNEIn0=\"}}}";
        String interfaceJson = JsonUtil.getNode(str, "interface");
        String resultJson = JsonUtil.getNode(interfaceJson, "returnStateInfo");
        String dataJson = JsonUtil.getNode(interfaceJson, "Data");
        System.out.println(dataJson);
        Map<String, String> map = JsonUtil.toMap(dataJson);
        System.out.println(map.get("content"));
    }
}
