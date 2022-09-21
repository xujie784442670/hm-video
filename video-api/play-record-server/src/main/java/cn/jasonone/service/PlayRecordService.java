package cn.jasonone.service;


import cn.jasonone.bean.PlayRecord;

import java.util.List;

/**
 * 播放记录表服务接口
 * @author 86176
 */
public interface PlayRecordService {
    int deleteByPrimaryKey(Long id);

    /**
     * 添加播放记录
     * @param record 播放记录
     * @return 结果
     */
    int insert(PlayRecord record);

    /**
     * 修改播放记录
     * @param record 播放记录
     * @return 结果
     */
    int update(PlayRecord record);
    int insertSelective(PlayRecord record);

    PlayRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayRecord record);

    int updateByPrimaryKey(PlayRecord record);

    /**
     * 查找已播放时长
     * @param record 播放记录
     * @return 播放时长
     */
    int findOne(PlayRecord record);

    List<PlayRecord> findAll(PlayRecord record);

    int getViews(Long id);
}
