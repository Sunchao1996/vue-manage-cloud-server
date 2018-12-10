package com.sc.util.config;

import org.springframework.stereotype.Component;

/**
 * 微信和支付宝相关配置
 * Created by 孔垂云 on 2017/8/9.
 */
@Component
public class PayConfig {

    //微信相关
    private String wx_id;//原始id
    private String wx_name;//微信号
    private String wx_code;//微信号
    private String wx_appid;//appid
    private String wx_appsecret;//appsecret
    private String wx_token;//token
    private String wx_mch_id;//商户号
    private String wx_mch_key;//商户key
    private String wx_mch_certificate;//证书地址
    private String wx_server_ip;//服务端ip
    private String wx_qrcode_notify_url;//二维码扫码回调url

    //支付宝相关
    private String alipay_app_id;// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private String alipay_seller_id;//
    private String alipay_merchant_private_key;// 商户私钥
    private String alipay_public_key;//支付宝公钥
    private String alipay_notify_url;//服务器异步通知页面路径
    private String alipay_return_url;//页面跳转同步通知页面路径
    private String alipay_sign_type;//签名方式
    private String alipay_charset;//字符编码格式
    private String alipay_gatewayUrl;//支付宝网关
    private String alipay_log_path;//支付宝网关

    private String union_encoding; //字符集编码，可以使用UTF-8,GBK两种方式
    private String union_version;//版本号，全渠道默认值
    private String union_signMethod;//签名方法
    private String union_txnType;//交易类型 ，01：消费
    private String union_txnSubType;//交易子类型， 01：自助消费
    private String union_bizType;//业务类型，B2C网关支付，手机wap支付
    private String union_channelType;//渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机
    private String union_merId;//商户号码
    private String union_accessType;//接入类型，0：直连商户
    private String union_currencyCode; //交易币种
    private String union_frontUrl;//异步通知参
    private String union_backUrl;//后台通知
    private String union_requestFrontUrl;//请求银联的前台地址
    private String union_certId;//签名证书序列号
    private String union_signCertPwd;// 签名证书密码
    private String union_signCertPath;//签名证书路径
    private String union_signCertType;//签名证书类型


    private String invoice_key_store_file;//发票秘钥
    private String invoice_trust_store_file;//发票通信证书


    public String getInvoice_key_store_file() {
        return invoice_key_store_file;
    }

    public void setInvoice_key_store_file(String invoice_key_store_file) {
        this.invoice_key_store_file = invoice_key_store_file;
    }

    public String getInvoice_trust_store_file() {
        return invoice_trust_store_file;
    }

    public void setInvoice_trust_store_file(String invoice_trust_store_file) {
        this.invoice_trust_store_file = invoice_trust_store_file;
    }

    public String getUnion_signCertPwd() {
        return union_signCertPwd;
    }

    public void setUnion_signCertPwd(String union_signCertPwd) {
        this.union_signCertPwd = union_signCertPwd;
    }

    public String getUnion_signCertPath() {
        return union_signCertPath;
    }

    public void setUnion_signCertPath(String union_signCertPath) {
        this.union_signCertPath = union_signCertPath;
    }

    public String getUnion_signCertType() {
        return union_signCertType;
    }

    public void setUnion_signCertType(String union_signCertType) {
        this.union_signCertType = union_signCertType;
    }

    public String getUnion_certId() {
        return union_certId;
    }

    public void setUnion_certId(String union_certId) {
        this.union_certId = union_certId;
    }

    public String getWx_id() {
        return wx_id;
    }

    public void setWx_id(String wx_id) {
        this.wx_id = wx_id;
    }

    public String getWx_name() {
        return wx_name;
    }

    public void setWx_name(String wx_name) {
        this.wx_name = wx_name;
    }

    public String getWx_code() {
        return wx_code;
    }

    public void setWx_code(String wx_code) {
        this.wx_code = wx_code;
    }

    public String getWx_appid() {
        return wx_appid;
    }

    public void setWx_appid(String wx_appid) {
        this.wx_appid = wx_appid;
    }

    public String getWx_appsecret() {
        return wx_appsecret;
    }

    public void setWx_appsecret(String wx_appsecret) {
        this.wx_appsecret = wx_appsecret;
    }

    public String getWx_token() {
        return wx_token;
    }

    public void setWx_token(String wx_token) {
        this.wx_token = wx_token;
    }

    public String getWx_mch_id() {
        return wx_mch_id;
    }

    public void setWx_mch_id(String wx_mch_id) {
        this.wx_mch_id = wx_mch_id;
    }

    public String getWx_mch_key() {
        return wx_mch_key;
    }

    public void setWx_mch_key(String wx_mch_key) {
        this.wx_mch_key = wx_mch_key;
    }

    public String getWx_mch_certificate() {
        return wx_mch_certificate;
    }

    public void setWx_mch_certificate(String wx_mch_certificate) {
        this.wx_mch_certificate = wx_mch_certificate;
    }

    public String getWx_server_ip() {
        return wx_server_ip;
    }

    public void setWx_server_ip(String wx_server_ip) {
        this.wx_server_ip = wx_server_ip;
    }

    public String getWx_qrcode_notify_url() {
        return wx_qrcode_notify_url;
    }

    public void setWx_qrcode_notify_url(String wx_qrcode_notify_url) {
        this.wx_qrcode_notify_url = wx_qrcode_notify_url;
    }

    public String getAlipay_app_id() {
        return alipay_app_id;
    }

    public void setAlipay_app_id(String alipay_app_id) {
        this.alipay_app_id = alipay_app_id;
    }

    public String getAlipay_seller_id() {
        return alipay_seller_id;
    }

    public void setAlipay_seller_id(String alipay_seller_id) {
        this.alipay_seller_id = alipay_seller_id;
    }

    public String getAlipay_merchant_private_key() {
        return alipay_merchant_private_key;
    }

    public void setAlipay_merchant_private_key(String alipay_merchant_private_key) {
        this.alipay_merchant_private_key = alipay_merchant_private_key;
    }

    public String getAlipay_public_key() {
        return alipay_public_key;
    }

    public void setAlipay_public_key(String alipay_public_key) {
        this.alipay_public_key = alipay_public_key;
    }

    public String getAlipay_notify_url() {
        return alipay_notify_url;
    }

    public void setAlipay_notify_url(String alipay_notify_url) {
        this.alipay_notify_url = alipay_notify_url;
    }

    public String getAlipay_return_url() {
        return alipay_return_url;
    }

    public void setAlipay_return_url(String alipay_return_url) {
        this.alipay_return_url = alipay_return_url;
    }

    public String getAlipay_sign_type() {
        return alipay_sign_type;
    }

    public void setAlipay_sign_type(String alipay_sign_type) {
        this.alipay_sign_type = alipay_sign_type;
    }

    public String getAlipay_charset() {
        return alipay_charset;
    }

    public void setAlipay_charset(String alipay_charset) {
        this.alipay_charset = alipay_charset;
    }

    public String getAlipay_gatewayUrl() {
        return alipay_gatewayUrl;
    }

    public void setAlipay_gatewayUrl(String alipay_gatewayUrl) {
        this.alipay_gatewayUrl = alipay_gatewayUrl;
    }

    public String getAlipay_log_path() {
        return alipay_log_path;
    }

    public void setAlipay_log_path(String alipay_log_path) {
        this.alipay_log_path = alipay_log_path;
    }

    public String getUnion_encoding() {
        return union_encoding;
    }

    public void setUnion_encoding(String union_encoding) {
        this.union_encoding = union_encoding;
    }

    public String getUnion_version() {
        return union_version;
    }

    public void setUnion_version(String union_version) {
        this.union_version = union_version;
    }

    public String getUnion_signMethod() {
        return union_signMethod;
    }

    public void setUnion_signMethod(String union_signMethod) {
        this.union_signMethod = union_signMethod;
    }

    public String getUnion_txnType() {
        return union_txnType;
    }

    public void setUnion_txnType(String union_txnType) {
        this.union_txnType = union_txnType;
    }

    public String getUnion_txnSubType() {
        return union_txnSubType;
    }

    public void setUnion_txnSubType(String union_txnSubType) {
        this.union_txnSubType = union_txnSubType;
    }

    public String getUnion_bizType() {
        return union_bizType;
    }

    public void setUnion_bizType(String union_bizType) {
        this.union_bizType = union_bizType;
    }

    public String getUnion_channelType() {
        return union_channelType;
    }

    public void setUnion_channelType(String union_channelType) {
        this.union_channelType = union_channelType;
    }

    public String getUnion_merId() {
        return union_merId;
    }

    public void setUnion_merId(String union_merId) {
        this.union_merId = union_merId;
    }

    public String getUnion_accessType() {
        return union_accessType;
    }

    public void setUnion_accessType(String union_accessType) {
        this.union_accessType = union_accessType;
    }

    public String getUnion_currencyCode() {
        return union_currencyCode;
    }

    public void setUnion_currencyCode(String union_currencyCode) {
        this.union_currencyCode = union_currencyCode;
    }

    public String getUnion_frontUrl() {
        return union_frontUrl;
    }

    public void setUnion_frontUrl(String union_frontUrl) {
        this.union_frontUrl = union_frontUrl;
    }

    public String getUnion_backUrl() {
        return union_backUrl;
    }

    public void setUnion_backUrl(String union_backUrl) {
        this.union_backUrl = union_backUrl;
    }

    public String getUnion_requestFrontUrl() {
        return union_requestFrontUrl;
    }

    public void setUnion_requestFrontUrl(String union_requestFrontUrl) {
        this.union_requestFrontUrl = union_requestFrontUrl;
    }
}
