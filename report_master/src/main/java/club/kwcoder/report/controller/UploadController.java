package club.kwcoder.report.controller;

import club.kwcoder.report.model.bean.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultBean<Boolean> upload(MultipartFile file) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            assert file != null;
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream("/home/zhinushannan/Desktop/data-insert.csv");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBean.error("上传失败，请稍后重试！", false);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultBean.ok("上传成功！", true);
    }

}
