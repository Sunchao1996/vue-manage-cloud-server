package com.sc.api.config.aop;

import com.sc.util.code.EnumReturnCode;
import com.sc.util.code.GlobalCode;
import com.sc.util.exception.DealException;
import com.sc.util.json.JsonResult;
import com.sc.util.json.JsonUtil;
import com.sc.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 该类主要解决以下问题
 * 2、处理controller层的异常，返回状态为500
 * 3、处理请求的参数异常，返回状态为400
 * <p>
 * Created by 孔垂云 on 2017/8/20.
 */
@ControllerAdvice
public class ApiControllerAdvice extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger("controllerLog");


    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 系统异常处理，比如：404,500
     *
     * @param request
     * @param response
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @Order(0)
    public String defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        if (e instanceof AccessDeniedException) {
            throw e;
        }
        logger.error("系统异常", e);
        logException(request, e);
        JsonResult jsonResult = new JsonResult(EnumReturnCode.FAIL_ERROR);
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return JsonUtil.toStr(jsonResult);
    }


    /**
     * 拦截@Valid请求参数验证不通过的异常
     *
     * @param exception 验证不通过的异常
     * @return 执行结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Order(0)
    public JsonResult handler(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException exception) {
        logger.error("请求的参数不正确", exception);
        logException(request, exception);
        String validation_message;
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult != null && bindingResult.getFieldError() != null) {
            validation_message = bindingResult.getFieldError().getDefaultMessage();
        } else {
            validation_message = exception.getMessage();
        }
        logger.error("参数错误信息：" + validation_message);
        return new JsonResult(EnumReturnCode.FAIL_ARGS);
    }

    /**
     * 拦截参数解析不正常
     *
     * @param exception 验证不通过的异常
     * @return 执行结果
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @Order(0)
    public JsonResult handlerHttpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response, HttpMessageNotReadableException exception) {
        logger.info("请求的参数不正确", exception);
        logException(request, exception);
        return new JsonResult(EnumReturnCode.FAIL_ERROR);
    }

    /**
     * 取得header的所有属性
     *
     * @param headers
     * @param request
     * @return
     */
    private String getHeaderValue(Enumeration<String> headers, HttpServletRequest request) {
        String str = "";
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            str += header + "=" + request.getHeader(header) + "&";
        }
        return str;
    }

    /**
     * 获取request的body的字符串
     *
     * @param br
     * @return
     */
    private String getBodyString(BufferedReader br) {
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return str;
    }

    /**
     * 打印所有异常信息
     *
     * @param request
     */
    private void logException(HttpServletRequest request, Exception e) {
        try {
            logger.error("Request Path：" + request.getServletPath());
            logger.error("Request Params：" + request.getParameterMap().toString());
            logger.error("Request Header:" + getHeaderValue(request.getHeaderNames(), request));
            logger.error("Request Body:" + StringUtil.getBodyString(request.getReader()));
            logger.error("Exception Message:" + e.getMessage());
            logger.error("Exception StackTrace:" + Arrays.toString(e.getStackTrace()));
        } catch (Exception e1) {
        }
    }
}