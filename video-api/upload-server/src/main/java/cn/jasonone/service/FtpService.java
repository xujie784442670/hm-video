package cn.jasonone.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author xujie
 * @version 1.0.0
 */
public interface FtpService {
    /**
     * 上传文件
     * @param storeDirectory 上传文件的目录
     * @param fileName  上传的文件名
     * @param is 上传的文件流
     */
    boolean uploadFile(String storeDirectory,String fileName, InputStream is);

    /**
     * 切换工作目录
     *
     * @param directory 目录
     * @param create    如果目录不存在,是否创建
     * @return
     */
    boolean changeWorkingDirectory(String directory, boolean create);

    boolean checkFileExists(String directory,String fileName);
}
