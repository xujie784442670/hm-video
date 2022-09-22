package cn.jasonone.mapper;


import cn.jasonone.bean.VideoComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频评论表数据库操作
* @author 25403
* @description 针对表【video_comment(视频评论表)】的数据库操作Mapper
* @createDate 2022-08-28 08:36:07
* @Entity level3.project.bean.VideoComment
*/
public interface VideoCommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(VideoComment record);

    int insertSelective(VideoComment record);

    VideoComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoComment record);

    int updateByPrimaryKey(VideoComment record);

    List<VideoComment> findAllByVideoId(@Param("videoId") Long videoId);

    VideoComment findCommentByPid(@Param("pid") Long pid);

    /**
     * 通过视频id查找视频评论数
     * @param vid
     * @return
     */
    int selectByVideoIdInt(@Param("Vid") Long vid);

    List<VideoComment> findAllByVideoId1(Long videoId);
}
