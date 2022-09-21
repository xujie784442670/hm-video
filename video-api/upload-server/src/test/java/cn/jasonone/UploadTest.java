package cn.jasonone;

import cn.jasonone.service.FtpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author xujie
 * @version 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UploadTest {
    @Resource
    private FtpService ftpService;

    @Test
    public void testUploadFile(){
//        ftpService.uploadFile("a/b/c/d",null);
    }
}
