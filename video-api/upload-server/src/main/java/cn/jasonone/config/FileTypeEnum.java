package cn.jasonone.config;

import org.apache.commons.net.ftp.FTPClient;

/**
 * @author xujie
 * @version 1.0.0
 */
public enum FileTypeEnum {
    BINARY_FILE_TYPE(FTPClient.BINARY_FILE_TYPE),
    ASCII_FILE_TYPE(FTPClient.ASCII_FILE_TYPE);

    private int type;

    FileTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
