package cn.jasonone.config;

import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

/**
 * @author xujie
 * @version 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties("hm-upload.ftp")
public class FtpProperties {
    /**
     * 字符集编码
     */
    private Charset encoding=Charset.forName("UTF-8");
    /**
     * FTP服务器地址
     */
    private String host;
    /**
     * FTP服务器端口
     */
    private int port=21;
    /**
     * FTP服务器账号
     */
    private String username="anonymous";
    /**
     * FTP服务器密码
     */
    private String password;
    /**
     * 静态资源服务器地址
     */
    private String resourceHost;
    /**
     * 默认文件传输类型
     */
    private FileTypeEnum fileType=FileTypeEnum.ASCII_FILE_TYPE;
}
