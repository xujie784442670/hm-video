package cn.jasonone.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 从前端接收的视频对象
 *
 * @author 杨洪义
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class VideoManageVo {
    /**
     * 视频id
     */
    private Long id;

    /**
     * 视频标题;用于查询和接收从数据库中返回的信息
     */
    private String title;

    /**
     * 视频类型id;用于查询
     */
    private String typeId;

    /**
     * 视频状态;用于查询
     */
    private Integer state;

    /**
     * 视频发布者;用于查询和接收从数据库中返回的信息
     */
    private String nickName;

    /**
     * 视频创建时间范围;用于查询
     */
    private Date[] createTimes;

    /**
     * 视频类型;用于接收从数据库中返回的信息
     */
    private String videoType;

    /**
     * 视频封面地址;用于接收从数据库中返回的信息
     */
    private String coverUrl;

    /**
     * 视频创建时间;用于接收从数据库中返回的信息
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 视频内容地址;用于接收从数据库中返回的信息
     */
    private String videoUrl;

    /**
     * 视频状态;用于接收从数据库中返回的信息
     */
    private Integer status;

    /**
     * 逻辑删除;用于接收从数据库中返回的信息
     */
    private Integer logicDelete;

    /**
     * 当前页码;用于控制分页
     */
    private Integer page;

    /**
     * 每页数据量;用于控制分页
     */
    private Integer pageSize;
}
