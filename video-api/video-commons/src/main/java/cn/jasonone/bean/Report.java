package cn.jasonone.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 举报表
 * @TableName report
 */
@Data
public class Report implements Serializable {
    /**
     * 举报id
     */
    private Long id;

    /**
     * 举报者id
     */
    private Long accountId;
    /**
     * 举报者昵称
     */
    private String nickName;
    /**
     * 被举报视频id
     */
    private Long videoId;
    /**
     * 被举报视频url
     */
    private String videoUrl;
    /**
     * 被举报视频封面url
     */
    private String coverUrl;
    /**
     * 举报内容
     */
    private String content;

    /**
     * 举报时间
     */
    private Date createTime;

    /**
     * 举报状态:0-审核中,1-已通过,2-未通过
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}