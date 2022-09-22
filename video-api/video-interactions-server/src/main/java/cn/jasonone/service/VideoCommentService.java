package cn.jasonone.service;


import cn.jasonone.bean.VideoComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频评论表服务接口
 * @author 刘柏宏
 */
public interface VideoCommentService {
    int deleteByPrimaryKey(Long id);

    int insert(VideoComment record);

    int insertSelective(VideoComment record);

    VideoComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoComment record);

    int updateByPrimaryKey(VideoComment record);

    List<VideoComment> findAllByVideoId1(@Param("id")Long id);
}
