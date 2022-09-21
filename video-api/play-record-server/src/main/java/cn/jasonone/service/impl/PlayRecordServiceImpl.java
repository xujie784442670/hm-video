package cn.jasonone.service.impl;

import cn.jasonone.bean.PlayRecord;
import cn.jasonone.mapper.PlayRecordMapper;
import cn.jasonone.service.PlayRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 播放记录表服务接口实现类
 */
@Service
public class PlayRecordServiceImpl implements PlayRecordService {
    @Resource
    private PlayRecordMapper playRecordMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return playRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PlayRecord record) {
        return playRecordMapper.insert(record);
    }

    @Override
    public int update(PlayRecord record) {
        return playRecordMapper.update(record);
    }

    @Override
    public int insertSelective(PlayRecord record) {
        return playRecordMapper.insertSelective(record);
    }

    @Override
    public PlayRecord selectByPrimaryKey(Long id) {
        return playRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PlayRecord record) {
        return playRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PlayRecord record) {
        return playRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public int findOne(PlayRecord record) {
        return playRecordMapper.findOne(record);
    }

    @Override
    public List<PlayRecord> findAll(PlayRecord record) {
        return playRecordMapper.findAll(record);
    }

    @Override
    public int getViews(Long id) {
        return playRecordMapper.getViews(id);
    }


}
