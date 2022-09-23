package cn.jasonone.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.jasonone.bean.Account;
import cn.jasonone.bean.Likes;
import cn.jasonone.bean.Video;
import cn.jasonone.model.HttpResult;
import cn.jasonone.service.AccountService;
import cn.jasonone.service.LikesService;
import cn.jasonone.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/likes")
public class LikesController {

    @Resource
    private LikesService likesService;
    @Resource
    private VideoService videoService;
    @Resource
    private AccountService accountService;
    @GetMapping("")
    public HttpResult accountLikes(Long id) {
        Long totalNumberOfLikes = 0L;
        Long accountId;
        if (id == null) {//没传值，默认获取自己的点赞总数
            SaSession session = StpUtil.getSession();
            String username = (String) session.get("username");
            Account account = accountService.selectAllByUsername(username);
            accountId = account.getId();
        } else {
            accountId = id;
        }
        //用户拥有的视频集合
        List<Video> videos = videoService.selectByAccountId(accountId);

        for (Video video : videos) {
            List<Likes> likes = likesService.selectByVideoId(video.getId());
            totalNumberOfLikes += likes.size();
        }
        return HttpResult.ok(totalNumberOfLikes);
    }

    @PostMapping("/findVideoLike")
    public Map<String, Object> findLike(@RequestBody Likes likes) {
        int result = likesService.findVideoLike(likes);
        Map<String, Object> rs = new HashMap<>();
        if (result != 0) {
            rs.put("code", 0);
        } else {
            rs.put("code", 1);
            rs.put("msg", "未找到");
        }
        return rs;
    }

    @PostMapping("/findCommentLike")
    public Map<String, Object> findCommentLike(@RequestBody Likes likes) {
        Object result = likesService.findCommentLike(likes);
        Map<String, Object> rs = new HashMap<>();
        if (result != null) {
            rs.put("code", "0");
        } else {
            rs.put("code", "1");
            rs.put("msg", "未找到");
        }
        return rs;
    }

}
