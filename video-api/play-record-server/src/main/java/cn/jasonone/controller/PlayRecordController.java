package cn.jasonone.controller;

import cn.jasonone.bean.PlayRecord;
import cn.jasonone.model.HttpResult;
import cn.jasonone.service.PlayRecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 视频播放记录控制层
 * @author 刘柏宏
 */
@RestController
@RequestMapping("/record")
public class PlayRecordController {
    @Resource
    private PlayRecordService playRecordService;

    @PostMapping("/findOne")
    public HttpResult findOne(@RequestBody PlayRecord record){
        Map<String,Object> rs=new HashMap<>();
        int playTime = playRecordService.findOne(record);
        if (playTime > 0){
            rs.put("code",0);
            rs.put("playTime",playTime);
        }
        else {
            rs.put("code",0);
            rs.put("playTime",0);
        }
        return HttpResult.ok(rs);
    }
}
