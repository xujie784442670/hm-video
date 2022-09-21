package cn.jasonone.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 播放记录表
 * @TableName play_record
 */
@Data
public class PlayRecord implements Serializable {
    /**
     * 播放记录id
     */
    private Long id;

    /**
     * 播放记录用户id
     */
    private Long accountId;

    /**
     * 播放记录视频id
     */
    private Long videoId;

    /**
     *视频播放时长
     */
    private int playTime;
    /**
     * 视频信息
     */
    private Video video;
    /**
     * 播放时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}