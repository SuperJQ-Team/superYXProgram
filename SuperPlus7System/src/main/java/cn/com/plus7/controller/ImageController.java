package cn.com.plus7.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@Slf4j
@RequestMapping("/img")
public class ImageController {

    static String IMG_PATH = "E:\\springmvc-img-data\\img-super-puls7-team\\";

    @RequestMapping("/{fileName}")
    public void showPicture(@PathVariable("fileName") String fileName,
                            HttpServletResponse response) {

        File imgFile = new File(IMG_PATH + fileName );
        if (imgFile.exists()) {
            responseFile(response, imgFile);
        }
        else{
            File img404 = new File(IMG_PATH + "404.jpg");
            responseFile(response, img404);
        }
    }

    private void responseFile(HttpServletResponse response, File imgFile) {
        try (InputStream is = new FileInputStream(imgFile);
             OutputStream os = response.getOutputStream();) {
            byte[] buffer = new byte[1024]; // 图片文件流缓存池
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
