package com.sc.core.pub;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 全局参数配置，用于在后端获取参数
 *
 */
@Component
@RefreshScope
@ConfigurationProperties
public class PubConfig {

    /**
     * 图片显示服务器地址
     */
    private String imageServer;

    /**
     * 图片路径
     */
    private String imageUploadPath;

    /**
     * 网站title
     */
    private String webTitle;

    public String getImageServer() {
        return imageServer;
    }

    public PubConfig setImageServer(String imageServer) {
        this.imageServer = imageServer;
        return this;
    }

    public String getImageUploadPath() {
        return imageUploadPath;
    }

    public PubConfig setImageUploadPath(String imageUploadPath) {
        this.imageUploadPath = imageUploadPath;
        return this;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public PubConfig setWebTitle(String webTitle) {
        this.webTitle = webTitle;
        return this;
    }

    @Override
    public String toString() {
        return "PubConfig{" +
                "imageServer='" + imageServer + '\'' +
                ", imageUploadPath='" + imageUploadPath + '\'' +
                ", webTitle='" + webTitle + '\'' +
                '}';
    }
}
