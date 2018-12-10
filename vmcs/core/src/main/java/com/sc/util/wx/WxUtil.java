package com.sc.util.wx;

import com.sc.core.spring.SpringContextHolder;
import com.sc.util.date.DateUtil;
import com.sc.util.http.HttpUtil;
import com.sc.util.json.JsonUtil;
import com.sc.util.log.LogUtil;
import com.sc.util.redis.RedisKey;
import com.sc.util.string.StringUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by 孔垂云 on 2017/7/31.
 */
public class WxUtil {
    //获取用户基本信息url
    private static String getUserInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?";
    //获取access_token的url
    private static String getAccess_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    //获取JSApi接口的url
    private static String getJsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    //获取校验用户的url
    public static String wx_authorize_url = "https://open.weixin.qq.com/connect/oauth2/authorize";
    //获取刷新token的url
    public static String wx_refresh_token_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    //下载文件url
    private static String wx_download_url = "http://file.api.weixin.qq.com/cgi-bin/media/get";
    //	private static PubConfig pubConfig = (PubConfig) SpringContextHolder.getBean("pubConfig");
    //发送消息
    private static String send_msg_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    //创建菜单url
    private static String wx_create_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    // 素材上传(POST)
    private static final String upload_media_url = "https://api.weixin.qq.com/cgi-bin/media/upload";

    /**
     * 根据openid获取用户基本信息，包括nickname和头像
     *
     * @param appid
     * @param openid
     * @return
     */
    public static String[] getWxuserBasic(String openid, String appid, String appsecret) {
        String access_token = getAccessToken(appid, appsecret);
        String url = getUserInfo_url + "access_token=" + access_token + "&openid=" + openid;
        String getJson = HttpUtil.getJsonFromUrl(url);
        @SuppressWarnings("unchecked")
        HashMap<String, String> hashMap = JsonUtil.toObject(getJson, HashMap.class);
        String[] str = new String[3];
        try {
            if (hashMap != null && hashMap.containsKey("nickname")) {
                str[0] = hashMap.get("nickname");
                str[1] = hashMap.get("headimgurl");
                str[2] = hashMap.get("province");
            }
        } catch (Exception e) {
            LogUtil.infoHttp("获取失败");
            LogUtil.infoHttp(getJson);
        }
        return str;
    }

    /**
     * 获取access_token
     *
     * @return
     */
    public synchronized static String getAccessToken(String appid, String appsecret) {
        //首先从redis里面获取，如果没有，则重新生成，放到redis里面
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getBean("redisUtil");
        //获取valueOperations并设置泛型
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        String accessToken = valueOperations.get(RedisKey.ACCESS_TOKEN + "-" + appid);
        if (StringUtil.isNullOrEmpty(accessToken)) {
            String url = getAccess_token_url + "&appid=" + appid + "&secret=" + appsecret;
            String getJson = HttpUtil.getJsonFromUrl(url);
            HashMap<String, String> hashMap = JsonUtil.toObject(getJson, HashMap.class);
            if (hashMap != null && hashMap.containsKey("access_token")) {
                accessToken = hashMap.get("access_token");
            } else {
                accessToken = "";
            }
            //重新生成accesstoken
            valueOperations.set(RedisKey.ACCESS_TOKEN + "-" + appid, accessToken, 7000);//一小时过期一次
        }
        return accessToken;
    }


    /**
     * 获取jsapi_ticket
     *
     * @return
     */
    public synchronized static String getJsapi_ticket(String appid, String appsecret) {
        return "";
    }

    /**
     * 初始化jspapiticket
     *
     * @param appid
     * @param appsecret
     */
    public synchronized static void initJsapi_ticket(String appid, String appsecret) {

    }

    /**
     * 获取微信基本配置信息
     *
     * @return
     */
    public static WxConfig getWxConfig2(String url, String appid, String appsecret) {
        WxConfig wxConfig = new WxConfig();
        wxConfig.setAppid(appid);//appid
        wxConfig.setTimestamp(Sha1Util.getTimeStamp());//timestamp
        wxConfig.setNonceStr(getNonceStr());
        SortedMap<String, String> signParams = new TreeMap<>();
        signParams.put("jsapi_ticket", getJsapi_ticket(appid, appsecret));
        signParams.put("noncestr", wxConfig.getNonceStr());//
        signParams.put("timestamp", wxConfig.getTimestamp());//
        signParams.put("url", url);
        wxConfig.setJsapi_ticket(signParams.get("jsapi_ticket"));
        wxConfig.setSignature(Sha1Util.createSHA1Sign(signParams));
        return wxConfig;
    }

    /**
     * 获取微信基本配置信息
     *
     * @return
     */
    public static WxConfig getWxConfig(String url, String appid, String appsecret, String timeStamp, String nonceStr) {
        WxConfig wxConfig = new WxConfig();
        wxConfig.setAppid(appid);//appid
        wxConfig.setTimestamp(timeStamp);//timestamp
        wxConfig.setNonceStr(nonceStr);
        SortedMap<String, String> signParams = new TreeMap<>();
        //		"jsapi_ticket=" + (string)Session["ticketzj"] + "&noncestr=" + nonceStr +        "&timestamp=" + timespanstr + "&url=" + url
        signParams.put("jsapi_ticket", getJsapi_ticket(appid, appsecret));
        signParams.put("noncestr", wxConfig.getNonceStr());//
        signParams.put("timestamp", wxConfig.getTimestamp());//
        signParams.put("url", url);
        wxConfig.setJsapi_ticket(signParams.get("jsapi_ticket"));
        //		OpsLogDao opsLogDao = SpringContextHolder.getBean("opsLogDao");
        //		opsLogDao.addLog("签名 参数：" + signParams.toString());
        wxConfig.setSignature(Sha1Util.createSHA1Sign(signParams));
        return wxConfig;
    }


    /**
     * 生成分享的二维码
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String createWxtgTicket(String cust_code, String appid, String appsecret) {
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + getAccessToken(appid, appsecret);
        String jsonParam = "{\"action_name\": \"QR_LIMIT_STR_SCENE\",\"action_info\": {\"scene\": {\"scene_str\": \"" + cust_code + "\"}}}";
        String getJson = HttpUtil.getJsonFromUrlPost(url, jsonParam);
        HashMap<String, String> hashMap = JsonUtil.toObject(getJson, HashMap.class);
        String ticket = "";
        if (hashMap.containsKey("ticket"))
            ticket = hashMap.get("ticket");
        System.out.println(hashMap.get("ticket"));
        return ticket;
    }

    /**
     * 根据用户的code获取返回的access_token
     * {"access_token":"OezXcEiiBSKSxW0eoylIeH_DOnD3GOXrxENfxbQ0EGJLfTsrLt6CgaqF6cRYJd-feUPdU0wshbLZqxPSSkS6InJMA9ug_HcYtxShr43gIOgVw10fp2-Ury03mLIyBZNvDzD4qnzESVJw0sCwvrM8Jg","expires_in":7200,"refresh_token":"OezXcEiiBSKSxW0eoylIeH_DOnD3GOXrxENfxbQ0EGJLfTsrLt6CgaqF6cRYJd-f7vO0nJgEmPhbqwEkkm5vm24R2OzbTAZLXxE2HSFJwVVcCTMBv1OGxBwspnQg3sK754u6740TVGEvfsjqLpYJng","openid":"oeFknt-qVCX9YLmfAnK2oSfr49Io","scope":"snsapi_userinfo"}
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String[] getUserAccessTokenByCode(String code, String appid, String appsecret) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        String getJson = HttpUtil.getJsonFromUrl(url);
        HashMap<String, String> hashMap = JsonUtil.toObject(getJson, HashMap.class);
        String token[] = new String[2];//二维数组，第一维是access_token,第二维是refress_token;
        if (hashMap.containsKey("access_token")) {
            token[0] = hashMap.get("access_token");
            token[1] = hashMap.get("refresh_token");
        }
        return token;
    }

    /**
     * 获取openid
     * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx1d2f36767d7e5b55&grant_type=refresh_token&refresh_token=OezXcEiiBSKSxW0eoylIeH_DOnD3GOXrxENfxbQ0EGJLfTsrLt6CgaqF6cRYJd-fMPDIZNsdIF40dQ2RTSjwYTf8isKEuhh2f3_9e2R_sjwomgnzuNSKphJpve_8s7S7KL_6A64V_goinOYHFmisgA
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getOpenid(String refresh_token, String appid) {
        String url = wx_refresh_token_url + "?appid=" + appid + "&grant_type=refresh_token&refresh_token=" + refresh_token + "";
        String getJson = HttpUtil.getJsonFromUrl(url);
        HashMap<String, String> hashMap = JsonUtil.toObject(getJson, HashMap.class);
        String openid = "";
        if (hashMap.containsKey("openid")) {
            openid = hashMap.get("openid");
        }
        return openid;
    }

    /**
     * 发送消息
     *
     * @param openid
     * @param text
     * @return
     */
    public static void sendMsg(String openid, String text, String appid, String appsecret) {
        String url = send_msg_url + "?access_token=" + getAccessToken(appid, appsecret);
        String jsonParam = "{\"touser\":\"" + openid + "\",\"msgtype\":\"text\", \"text\":{\"content\":\"" + text + "\"}}";
        HttpUtil.getJsonFromUrlPost(url, jsonParam);
    }

    /**
     * 发送图片消息
     * @param openid
     * @param text
     * @return
     */
   /* @Async
    public static void sendImgMsg(String openid, WechatSpokeman wechatSpokeman, String appid, String appsecret) {
        //先判断当前时间和生成分享二维码的时间是否大于72小时，如果大于，则重新生成，否则直接发送图片至微信客户端
        String media_id = wechatSpokeman.getShare_qrcode();
        if (StringUtil.isNullOrEmpty(media_id) || wechatSpokeman.getShare_qrcode_date() == null || DateUtil.getHourDifference(new Date(), wechatSpokeman.getShare_qrcode_date()) > 71) {
            //	重新生成
            OpsShareqrService opsShareqrService = (OpsShareqrService) SpringContextHolder.getBean("opsShareqrService");
            media_id = opsShareqrService.createShareQr(wechatSpokeman.getShop_id(), wechatSpokeman.getCust_code());
        }
        String url = send_msg_url + "?access_token=" + getAccessToken(appid, appsecret);
        String jsonParam = "{\"touser\":\"" + openid + "\",\"msgtype\":\"image\", \"image\":{\"media_id\":\"" + media_id + "\"}}";
        HttpUtil.getJsonFromUrlPost(url, jsonParam);
    }*/

    /**
     * 下载文件
     * 以http方式发送请求,并将请求响应内容输出到文件
     *
     * @return 返回响应的存储到文件
     */
    //	@Async
    public static void downloadMedia(String mediaId, String storePath, String localName, String appid, String appsecret) {
        String imgDownloadUrl = wx_download_url + "?access_token=" + getAccessToken(appid, appsecret) + "&media_id=" + mediaId;
        LogUtil.infoHttp("请求url：" + imgDownloadUrl);
        File file = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        try {
            URL url = new URL(imgDownloadUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            inputStream = conn.getInputStream();
            if (inputStream != null) {
                file = new File(storePath + localName);
            }
            //写入到文件
            fileOut = new FileOutputStream(file);
            if (fileOut != null) {
                int c = inputStream.read();
                while (c != -1) {
                    fileOut.write(c);
                    c = inputStream.read();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.infoHttp(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                inputStream.close();
                fileOut.close();
            } catch (IOException execption) {
                execption.printStackTrace();
            }
        }
    }

    /**
     * 创建菜单,1成功，0失败
     *
     * @param access_token
     * @param jsonMenu
     * @return
     */
    public static int createMenu(String access_token, String jsonMenu) {
        String ret = HttpUtil.getJsonFromUrlPost(wx_create_menu_url + access_token, jsonMenu);
        HashMap<String, String> hashMap = JsonUtil.toObject(ret, HashMap.class);
        if (hashMap.get("errmsg").toString().equals("ok"))
            return 1;
        else
            return 0;
    }

    /**
     * 生成菜单json
     *
     * @param appid
     * @param shop_id
     * @return
     */
    public static String createMenu(String appid, int shop_id) {
        StringBuilder sb = new StringBuilder();
        sb.append("	{");
        sb.append("\"button\": [");
        sb.append("{");
        sb.append("\"type\": \"view\",");
        sb.append("\"name\": \"百乐思\",");
        sb.append("\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2Fbls.mendcar.com.cn%2Fwfx%2Fwechat%2Fuser%2Findex.htm%3Fshop_id="
                + shop_id + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect\"");
        sb.append("},");

        sb.append(" {\"name\": \"服务中心\", \"sub_button\": [");
        sb.append("{");
        sb.append("\"type\": \"view\",");
        sb.append("\"name\": \"服务预约\",");
        sb.append("\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2Fwx.mendcar.com.cn%2Frpwx%2Fmtn%2Fappointment%2Flist.htm%3Fshop_id="
                + shop_id + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect\"");
        sb.append("},{");
        sb.append("\"type\": \"view\",");
        sb.append("	\"name\": \"服务追踪\",");
        sb.append("	\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2Fwx.mendcar.com.cn%2Frpwx%2Fmtn%2FserviceTrack%2Flist.htm%3Fshop_id="
                + shop_id + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect\"");
        sb.append("	}]},");
        String url = StringUtil.encodeUrl("http://bls.mendcar.com.cn/wfx/wechat/user/index.htm?shop_id=" + shop_id);
        sb.append("{");
        sb.append("\"type\": \"view\",");
        sb.append("\"name\": \"用户中心\",");
        sb.append("\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect\"");
        sb.append("	} ]");
        sb.append("	}");
        return sb.toString();
    }

    public static void main(String[] args) {
        //		WxUtil wxUtil = new WxUtil();
        //		System.out.println(wxUtil.sendImgMsg("oCVGJuHlH3DcvA3g6EVI_hJJaYJQ", media_id, appid, appsecret);
    }


    /**
     * 获取微信基本配置信息
     *
     * @return
     */
    public static WxConfig getWxConfig(String url, String appid, String appsecret) {
        WxConfig wxConfig = new WxConfig();
        wxConfig.setAppid(appid);//appid
        wxConfig.setTimestamp(Sha1Util.getTimeStamp());//timestamp
        wxConfig.setNonceStr(getNonceStr());
        SortedMap<String, String> signParams = new TreeMap<>();
        signParams.put("jsapi_ticket", getJsapi_ticket(appid, appsecret));
        signParams.put("noncestr", wxConfig.getNonceStr());//
        signParams.put("timestamp", wxConfig.getTimestamp());//
        signParams.put("url", url);
        wxConfig.setJsapi_ticket(signParams.get("jsapi_ticket"));
        wxConfig.setSignature(Sha1Util.createSHA1Sign(signParams));
        return wxConfig;
    }

    /**
     * 获取微信基本配置信息
     *
     * @return
     */
    public static String createSignature(String url,String nonce_str, String appid, String appsecret) {
        WxConfig wxConfig = new WxConfig();
        wxConfig.setAppid(appid);//appid
        wxConfig.setTimestamp(Sha1Util.getTimeStamp());//timestamp
        wxConfig.setNonceStr(nonce_str);
        SortedMap<String, String> signParams = new TreeMap<>();
        signParams.put("jsapi_ticket", getJsapi_ticket(appid, appsecret));
        signParams.put("noncestr", wxConfig.getNonceStr());//
        signParams.put("timestamp", wxConfig.getTimestamp());//
        signParams.put("url", url);
        return Sha1Util.createSHA1Sign(signParams);
    }

    /**
     * 获取随机字符串，微信用到
     *
     * @return
     */
    public static String getNonceStr() {
        // 随机数
        String currTime = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        return strTime + strRandom;
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }


}
