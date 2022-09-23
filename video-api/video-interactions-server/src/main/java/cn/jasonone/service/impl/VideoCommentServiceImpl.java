package cn.jasonone.service.impl;

import cn.jasonone.bean.VideoComment;
import cn.jasonone.mapper.VideoCommentMapper;
import cn.jasonone.service.VideoCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频评论表服务接口实现类
 */
@Service
public class VideoCommentServiceImpl implements VideoCommentService {
    @Resource
    private VideoCommentMapper videoCommentMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return videoCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VideoComment record) {
        return videoCommentMapper.insert(record);
    }

    @Override
    public int insertSelective(VideoComment record) {
        return videoCommentMapper.insertSelective(record);
    }

    @Override
    public VideoComment selectByPrimaryKey(Long id) {
        return videoCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(VideoComment record) {
        return videoCommentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(VideoComment record) {
        return videoCommentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<VideoComment> findAllByVideoId1(Long id) {
        return videoCommentMapper.findAllByVideoId1(id);
    }
}
