package cn.jasonone.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 视频评论表
 * @TableName video_comment
 */
@Data
public class VideoComment implements Serializable {
    /**
     * 视频评论id
     */
    private Long id;

    /**
     * 视频id
     */
    private Long videoId;

    /**
     * 评论者id
     */
    private Long accountId;
    /**
     * 评论人昵称
     */
    private String nickName;
    /**
     * 评论者头像
     */
    private String avatar;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 逻辑删除:0-未删除,1-已删除
     */
    private Integer logicalDelete;

    /**
     * 父评论id
     */
    private Long pid;

    /**
     * 子评论
     */
    private List<VideoComment> sons;
    /**
     * 点在次数
     */
    private long likes;

    private static final long serialVersionUID = 1L;
}