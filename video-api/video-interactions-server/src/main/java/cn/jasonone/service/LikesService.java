package cn.jasonone.service;

import cn.jasonone.bean.Likes;

import java.util.List;

/**
 * 点赞表服务接口
 */
public interface LikesService {
    int deleteByPrimaryKey(Long id);

    int insert(Likes record);

    int insertSelective(Likes record);



    Likes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);

    /**
     * 通过视频id查询对象集合
     * @param id
     * @return 点赞集合
     */
    List<Likes> selectByVideoId(Long id);
    /**
     * 通过评论id查询对象集合
     * @param id
     * @return 点赞集合
     */
    List<Likes> selectByCommentId(Long id);

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

    int delVideoLike(Likes likes);
    int delCommentLike(Likes likes);

    int findVideoLike(Likes likes);
    Object findCommentLike(Likes likes);

    /**
     * 查询视频点赞数
     * @param id
     * @return
     */
    int countSelectVideoid(Long id);
}
