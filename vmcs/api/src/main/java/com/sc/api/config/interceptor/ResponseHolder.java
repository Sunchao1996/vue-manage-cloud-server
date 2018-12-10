package com.sc.api.config.interceptor;

import com.sc.util.json.JsonResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 孔垂云 on 2017/9/18.
 */
@Component
public class ResponseHolder {
    private static ThreadLocal<Object> model = new ThreadLocal<>();

    public void clean(){
        model.remove();
    }

    public JsonResult getModel(){
        Object o = model.get();
        if(o == null){
            this.setModel(new JsonResult());
            o = this.getObject();
        }
        if(o != null && o instanceof JsonResult){
            return (JsonResult) o;
        }else {
            return null;
        }
    }

    public Object getObject(){
        return model.get();
    }

    public void setModel(Object o){
        model.set(o);
    }

    @SuppressWarnings("unchecked")
	public JsonResult put(String key, Object value){
        JsonResult responseModel = this.getModel();
        Object data = responseModel.getData();
        if(data == null || !(data instanceof Map)){
            data = new HashMap<String,Object>();
            responseModel.setData(data);
        }
        Map<String,Object> map = (Map<String,Object>) data;
        map.put(key,value);
        return responseModel;
    }

    public JsonResult setData(Object data){
        JsonResult responseModel = this.getModel();
        responseModel.setData(data);
        return responseModel;
    }

}
