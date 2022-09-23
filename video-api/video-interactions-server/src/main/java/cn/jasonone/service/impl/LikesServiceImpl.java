package cn.jasonone.service.impl;

import cn.jasonone.bean.Likes;
import cn.jasonone.mapper.LikesMapper;
import cn.jasonone.service.LikesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 点赞表服务接口实现类
 */
@Service
public class LikesServiceImpl implements LikesService {
    @Resource
    private LikesMapper likesMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return likesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Likes record) {
        return likesMapper.insert(record);
    }

    @Override
    public int insertSelective(Likes record) {
        return likesMapper.insertSelective(record);
    }

    @Override
    public Likes selectByPrimaryKey(Long id) {
        return likesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Likes record) {
        return likesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Likes record) {
        return likesMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Likes> selectByVideoId(Long id) {
        return likesMapper.selectByVideoId(id);
    }

    @Override
    public List<Likes> selectByCommentId(Long id) {
        return likesMapper.selectByCommentId(id);
    }

    @Override
    public int addVideoLike(Likes likes) {
        return likesMapper.addVideoLike(likes);
    }

    @Override
    public int addCommentLike(Likes likes) {
        return likesMapper.addCommentLike(likes);
    }

    @Override
    public int delVideoLike(Likes likes) {
        return likesMapper.delVideoLike(likes);
    }

    @Override
    public int delCommentLike(Likes likes) {
        return likesMapper.delCommentLike(likes);
    }

    @Override
    public int findVideoLike(Likes likes) {
        return likesMapper.findLike1(likes);
    }

    @Override
    public Object findCommentLike(Likes likes) {
        return likesMapper.findLike2(likes);
    }

    @Override
    public int countSelectVideoid(Long id) {
        return likesMapper.countSelectVideoid(id);
    }


}
