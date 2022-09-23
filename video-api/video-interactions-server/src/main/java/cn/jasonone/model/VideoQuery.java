package cn.jasonone.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频表查询对象
 */
@Data
public class VideoQuery implements Serializable {
    /**
     * 视频id
     */
    private Long id;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频类型id
     */
    private Long typeId;

    /**
     * 视频创建时间
     */
    private Date[] createTime;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 视频封面地址
     */
    private String coverUrl;

    /**
     * 视频播放量
     */
    private Long views;

    /**
     * 视频点赞量
     */
    private Long likes;

    /**
     * 视频发布者id
     */
    private Long accountId;

    /**
     * 视频状态:0-正常,1-禁用
     */
    private Integer status;

    /**
     * 逻辑删除:0-未删除,1-已删除
     */
    private Integer logicalDelete;

    private static final long serialVersionUID = 1L;
}