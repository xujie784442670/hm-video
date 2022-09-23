package cn.jasonone.mapper;

import cn.jasonone.bean.Likes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 针对表【likes(点赞表)】的数据库操作
* @author
* @author 点赞表数据库操作mapper
* @description 针对表【likes(点赞表)】的数据库操作Mapper
*/
public interface LikesMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Likes record);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(Long id);

    /**
     * 通过视频id查询对象集合
     * @param id
     * @return 点赞集合
     */
    List<Likes> selectByVideoId(@Param("id") Long id);
    /**
     * 通过评论id查询对象集合
     * @param id
     * @return 点赞集合
     */
    List<Likes> selectByCommentId(@Param("id") Long id);

    /**
     * 通过视频id查找点赞数
     * @param vid
     * @return
     */
    int selectByVideoIdInt(@Param("Vid") Long vid);
    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);

    /**
     * 添加视频点赞
     * @param likes 点赞信息
     * @return 受影响行数
     */
    int addVideoLike(Likes likes);

    /**
     * 添加评论点赞
     * @param likes 点赞信息
     * @return 受影响行数
     */
    int addCommentLike(Likes likes);

    /**
     * 删除视频点赞记录
     * @param likes 点赞信息
     * @return 受影响函数
     */
    int delVideoLike(Likes likes);
    /**
     * 删除评论点赞记录
     * @param likes 点赞信息
     * @return 受影响函数
     */
    int delCommentLike(Likes likes);

    /**
     * 检验该视频是否已被点赞
     * @param likes 点赞信息
     * @return
     */
    int findLike1(Likes likes);

    /**
     * 检验该评论是否已被点赞
     * @param likes
     * @return
     */
    Object findLike2(Likes likes);

    /**
     * 查询视频点赞数
     * @param id
     * @return
     */
    int countSelectVideoid(@Param("id") Long id);
}
