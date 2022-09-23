package cn.jasonone.service.impl;

import cn.jasonone.bean.Video;
import cn.jasonone.mapper.VideoMapper;
import cn.jasonone.model.VideoQuery;
import cn.jasonone.service.VideoService;
import cn.jasonone.vo.VideoManageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.0.0
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    VideoMapper videoMapper;

    @Override
    public List<Video> findAllVideos(VideoQuery videoQuery) {
        return videoMapper.findAll(videoQuery);
    }

    @Override
    public List<Video> findByTypeId(Long typeId) {
        return videoMapper.findByTypeId(typeId);
    }

    @Override
    public List<Video> selectByAccountId(Long id) {
        return videoMapper.selectByAccountId(id);
    }

    @Override
    public int insertVideo(Video video) {
        return videoMapper.insert(video);
    }

    @Override
    public List<Video> findDeatilVideos() {
        return videoMapper.findDetailInfo();
    }

    @Override
    public List<VideoManageVo> findVideoManageVo(VideoManageVo videoManageVo) {
        return videoMapper.findVideoManageVo(videoManageVo);
    }

    @Override
    public Video findById(Long videoId) {
        return videoMapper.findById(videoId);
    }

    @Override
    public int logicDeleteById(Long id) {
        return videoMapper.logicalDelete(id);
    }

    @Override
    public int updateById(Video video) {
        return videoMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    public List<Video> findVideosByAccountId(Long id) {
        return videoMapper.selectByAccountId(id);
    }

    @Override
    public boolean updateTitle(Video video) {
        return videoMapper.updateTitle(video);
    }

    @Override
    public int logicalDel(Long id) {
        return videoMapper.logicalDel(id);
    }


}
