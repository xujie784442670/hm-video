package cn.jasonone.model.upload;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文件上传结果信息
 * @author xujie
 * @version 1.0.0
 */
@Data
@Accessors(chain = true)
public class UploadInfo {
    /**
     * 存储路径
     */
    private String path;
    /**
     * 访问路径
     */
    private String url;
    /**
     * 文件签名
     */
    private String sign;
}
