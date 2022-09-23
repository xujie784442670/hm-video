package cn.jasonone.controller;

import cn.jasonone.bean.VideoComment;
import cn.jasonone.model.HttpResult;
import cn.jasonone.service.VideoCommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘柏宏
 */
@RestController
public class CommentsController {
    @Resource
    private VideoCommentService videoCommentService;

    /**
     * 根据视频id查找所有评论
     * @param videoId
     * @return HttpResult.ok(rs)
     */
    @GetMapping("/list1")
    public HttpResult getComments1(Long videoId){
        List<VideoComment> videoComments = videoCommentService.findAllByVideoId1(videoId);
        Map<String,Object> rs=new HashMap<>();
        rs.put("data",videoComments);
        return  HttpResult.ok(rs);
    }

    /**
     * 添加评论
     * @param videoComment
     * @return HttpResult
     */
    @PostMapping("/insert")
    public HttpResult insertComment(@RequestBody VideoComment videoComment){
        System.out.println(videoComment);
        Map<String,Object> rs=new HashMap<>();
        if (videoCommentService.insert(videoComment)==1){
            rs.put("code",0);
            rs.put("msg","评论成功");
        };
        return HttpResult.ok(rs);
    }

    /**
     * 删除评论
     * @param commentId
     * @return  HttpResult
     */
    @GetMapping("/delComment")
    public HttpResult delComment(Long commentId){
        System.out.println(commentId);
        Map<String,Object> rs=new HashMap<>();
        videoCommentService.deleteByPrimaryKey(commentId);
        rs.put("code",0);
        rs.put("msg","删除评论成功");
        return HttpResult.ok(rs);
    }
}
