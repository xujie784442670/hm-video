package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 收藏表
 * @author
 * @TableName collection
 */
@Data
public class Collections implements Serializable {
    /**
     * 收藏id
     */
    private Long id;

    /**
     * 收藏者id
     */
    private Long accountId;

    /**
     * 视频id
     */
    private Long videoId;

    /**
     * 视频信息
     */
    private Video video;

    private static final long serialVersionUID = 1L;
}