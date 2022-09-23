package cn.jasonone.service;

import cn.jasonone.bean.Video;
import cn.jasonone.model.VideoQuery;
import cn.jasonone.vo.VideoManageVo;

import java.util.List;

/**
 * 视频表服务接口
 * @version 1.0.0
 */
public interface VideoService {
    /**
     * 查询所有视频
     * @param videoQuery 视频查询对象
     * @return 视频集合
     */
    List<Video> findAllVideos(VideoQuery videoQuery);

    /**
     * 根据视频类型id 查找该类型的所有视频
     * @param typeId 查询条件
     * @return 视频集合
     */
    List<Video> findByTypeId(Long typeId);
    /**
     * 通过视频发布者查找集合
     * @param id
     * @return 视频集合
     */
    List<Video> selectByAccountId(Long id);
    /**
     * 新增视频
     * @param video 视频信息
     * @return 受影响行数
     */
    int insertVideo(Video video);

    /**
     * 查询视频详细信息结合
     * @return 视频详细信息
     */
    List<Video> findDeatilVideos();


    /**
     * 根据条件从数据库中查询前后端交互的信息
     * @return 前后端交互对象
     */
    List<VideoManageVo> findVideoManageVo(VideoManageVo videoManageVo);

    /**
     * 根据id查找视频
     * @params videoId 视频id
     */
    Video findById(Long videoId);
    /**
     * 通过主键逻辑删除视频
     *
     * @param id 主键
     * @return 受影响行数
     */
    int logicDeleteById(Long id);


    /**
     * 通过主键修改视频信息
     *
     * @param video 视频对象
     * @return 受影响行数
     */
    int updateById(Video video);

    List<Video> findVideosByAccountId(Long id);

    boolean updateTitle(Video video);

    int logicalDel(Long id);
}
