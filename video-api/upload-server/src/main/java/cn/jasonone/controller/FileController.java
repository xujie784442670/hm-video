package cn.jasonone.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.io.file.PathUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.digest.MD5;
import cn.jasonone.config.FtpProperties;
import cn.jasonone.model.HttpResult;
import cn.jasonone.model.upload.UploadInfo;
import cn.jasonone.service.FtpService;
import cn.jasonone.util.Asserts;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * @author xujie
 * @version 1.0.0
 */
@Validated
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FtpService ftpService;
    @Resource
    private FtpProperties ftpProperties;

    @PostMapping("/upload")
    @SaCheckPermission("file:upload")
    public HttpResult<UploadInfo> uploadInfo(@NotNull(message = "待上传的文件不能为空") @RequestParam("file") MultipartFile file, @NotBlank(message = "存储目录不能为空") String storeDir) {
        MD5 md5 = new MD5();
        try {
            String sign = md5.digestHex(file.getInputStream());
            String fileName = sign + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if (!ftpService.checkFileExists(storeDir, fileName)) {
                Asserts.isTrue(ftpService.uploadFile(storeDir, fileName, file.getInputStream()), 10700, "上传失败");
            }
            UploadInfo uploadInfo = new UploadInfo();
            uploadInfo.setPath(Paths.get(storeDir).resolve(fileName).toString());
            uploadInfo.setSign(sign);
            String url = URLUtil.completeUrl(ftpProperties.getResourceHost(), uploadInfo.getPath());
            uploadInfo.setUrl(url);
            return HttpResult.ok(uploadInfo);
        } catch (IOException e) {
            Asserts.throwException(10700, "上传失败",e);
        }
        return null;
    }

}
