package cn.jasonone.mapper;

import cn.jasonone.bean.Video;
import cn.jasonone.model.VideoQuery;
import cn.jasonone.vo.VideoManageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频表的数据库操作Mapper
 *
 * @author 黎凡
 * @version 1.0.0
 * @since 1.0.0
 */
public interface VideoMapper {
    /**
     * 通过主键删除视频
     *
     * @param id 视频id
     * @return 受影响行数
     * @author 黎凡
     * @since 1.0.0
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加视频
     *
     * @param video 视频信息
     * @return 结果
     */
    int insert(Video video);

    /**
     * 通过视频id查询视频
     *
     * @param id 视频id
     * @return 视频信息
     */
    Video selectByPrimaryKey(Long id);

    /**
     * 通过用户id查询视频id
     */
    Long selectVideoByAccountId(Long accountId);
    /**
     * 通过视频发布者查找集合
     *
     * @param id
     * @return 视频集合
     */
    List<Video> selectByAccountId(Long id);

    /**
     * 查询所有视频
     *
     * @param videoQuery 视频查询对象
     * @return 视频集合
     */
    List<Video> findAll(VideoQuery videoQuery);

    /**
     * 根据主键更新视频信息
     *
     * @param video 视频信息
     * @return 结果
     */
    int updateByPrimaryKey(Video video);

    /**
     * 根据主键id根据条件修改视频信息
     *
     * @param video 视频对象
     * @return 受影响行数
     */
    int updateByPrimaryKeySelective(Video video);

    /**
     * 根据主键逻辑删除视频
     *
     * @param videoId
     * @return
     */
    int logicalDelete(@Param("id") Long videoId);

    /**
     * 根据视频类型id查找视频
     *
     * @param typeId 类型id
     * @return
     */
    List<Video> findByTypeId(@Param("typeId") Long typeId);

    /**
     * 获得所有视频的详细信息
     *
     * @return 视频详细信息
     */
    List<Video> findDetailInfo();

    /**
     * 根据条件从数据库中查询前后端交互的信息
     *
     * @return 前后端交互对象
     */
    List<VideoManageVo> findVideoManageVo(VideoManageVo videoManageVo);

    /**
     * 根据视频id查询视频inxi
     * @param videoId 视频id
     */
    Video findById(Long videoId);

    boolean updateTitle(Video video);

    int logicalDel(Long id);
}
