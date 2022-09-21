package cn.jasonone.mapper;

import cn.jasonone.bean.PlayRecord;

import java.util.List;

/**
 * 播放记录表数据库操作mapper
* @author
* @description 针对表【play_record(播放记录表)】的数据库操作Mapper
* @createDate 2022-08-24 10:18:51
* @Entity level3.project.bean.PlayRecord
*/
public interface PlayRecordMapper {

    int deleteByPrimaryKey(Long id);

    /**
     * 新增播放记录
     * @param record 播放记录信息
     * @return 结果
     */
    int insert(PlayRecord record);

    int insertSelective(PlayRecord record);

    PlayRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayRecord record);

    int updateByPrimaryKey(PlayRecord record);

    /**
     * 修改播放记录
     * @param record
     * @return
     */
    int update(PlayRecord record);

    /**
     * 查找已播放时长
     * @param record 播放记录
     * @return 播放时长
     */
    int findOne(PlayRecord record);

    List<PlayRecord> findAll(PlayRecord record);

    int getViews(Long id);
}
