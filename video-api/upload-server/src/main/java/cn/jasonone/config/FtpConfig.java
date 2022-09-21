package cn.jasonone.config;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author xujie
 * @version 1.0.0
 */
@Configuration
public class FtpConfig {

    @Bean
    public FTPClient ftpClient(FtpProperties properties) throws IOException {
        FTPClient client = new FTPClient();
        client.connect(properties.getHost(),properties.getPort());
        client.login(properties.getUsername(),properties.getPassword());
        client.setFileType(properties.getFileType().getType());
        client.setCharset(properties.getEncoding());
        int replyCode = client.getReplyCode();
        if(!FTPReply.isPositiveCompletion(replyCode)){
            client.abort();
            client.disconnect();
            throw new IOException("FTP服务器连接失败");
        }
        return client;
    }
}
