package com.lzy.utils;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static String uploadImg(MultipartFile file) throws IOException {
        // 上传地址
        ApplicationHome applicationHome = new ApplicationHome(ImageUtils.class);
        String path = applicationHome.getSource().getParentFile().getParentFile().getParentFile().getPath()
                + "\\SuperMarket-web\\src\\main\\webapp\\img";
        // 获取图片名
        String fileName = file.getOriginalFilename();
        // 根据时间戳生成新的文件名
        String imageName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        // 封装上传文件位置的全路径
        File targetFile  = new File(path,imageName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        Thumbnails.of(targetFile).forceSize(200,210).toFile(targetFile);


        return imageName;
    }


}
