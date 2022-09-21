package cn.jasonone.service.impl;

import cn.jasonone.config.FtpProperties;
import cn.jasonone.service.FtpService;
import cn.jasonone.util.Asserts;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xujie
 * @version 1.0.0
 */
@Service
public class FtpServiceImpl implements FtpService {
    @Resource
    private FTPClient client;
    @Resource
    private FtpProperties properties;

    /**
     * 连接服务器
     *
     * @throws IOException
     */
    private void connect() {
        if (!client.isConnected() || !client.isAvailable()) {
            try {
                client.connect(properties.getHost(), properties.getPort());
                client.login(properties.getUsername(), properties.getPassword());
            } catch (IOException e) {
                Asserts.throwException(10701, "FTP服务器连接失败",e);
            }
        }
    }

    /**
     * 检查服务器上指定目录是否存在
     *
     * @param directory
     * @return
     */
    @Override
    public boolean changeWorkingDirectory(String directory,boolean create) {
        try {
            connect();
            if(client.changeWorkingDirectory(directory)){
                return true;
            }
            if(create){
                String[] dirs = directory.split("/");
                String tempPath = "";
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    tempPath += "/" + dir;
                    if (!changeWorkingDirectory(tempPath,false)) {
                        Asserts.isTrue(client.makeDirectory(tempPath), 10702, "创建目录失败");
                        client.changeWorkingDirectory(tempPath);
                    }
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean uploadFile(String storeDirectory,String fileName, InputStream is) {
        connect();
        changeWorkingDirectory(storeDirectory,true);
        // 上传文件
        try {
            return client.storeFile(fileName, is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean checkFileExists(String directory, String fileName) {
        connect();
        try {
            InputStream inputStream = client.retrieveFileStream(directory + "/" + fileName);
            int code = client.getReplyCode();
            if(inputStream != null){
                inputStream.close();
                return FTPReply.isPositivePreliminary(code)||FTPReply.isPositiveCompletion(code);
            }
            return FTPReply.isNegativePermanent(client.getReplyCode());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.completePendingCommand();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
