package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 视频类型表
 * @TableName video_type
 */
@Data
public class VideoType implements Serializable {
    /**
     * 视频类型id
     */
    private Long id;

    /**
     * 视频类型
     */
    private String type;

    private static final long serialVersionUID = 1L;
}