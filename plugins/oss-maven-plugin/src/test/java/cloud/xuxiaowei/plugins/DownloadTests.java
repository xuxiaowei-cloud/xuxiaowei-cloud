package cloud.xuxiaowei.plugins;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class DownloadTests {

    @Test
    void file() {

        String spec = "https://gosspublic.alicdn.com/ossutil/1.7.13/ossutil64.zip";
        String name = "D:/ossutil64.zip";

        URL url;
        try {
            url = new URL(spec);
        } catch (MalformedURLException e) {
            log.error("创建下载 URL：{} 异常：", spec, e);
            throw new RuntimeException(e);
        }

        URLConnection urlConnection;
        try {
            urlConnection = url.openConnection();
        } catch (IOException e) {
            log.error("打开 URL：{} 链接异常：", spec, e);
            throw new RuntimeException(e);
        }

        InputStream inputStream;
        try {
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            log.error("获取 URL：{} 输入流异常：", spec, e);
            throw new RuntimeException(e);
        }

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(name);
        } catch (FileNotFoundException e) {
            log.error("URL：{} 文件：{} 输出流异常：", spec, name, e);
            throw new RuntimeException(e);
        }

        byte[] buffer = new byte[1204];
        int byteSum = 0;
        int byteRead;
        while (true) {
            try {
                if ((byteRead = inputStream.read(buffer)) == -1) break;
            } catch (IOException e) {
                log.error("URL：{} 文件：{} 读取异常：", spec, name, e);
                throw new RuntimeException(e);
            }
            byteSum += byteRead;
            try {
                fileOutputStream.write(buffer, 0, byteRead);
            } catch (IOException e) {
                log.error("URL：{} 文件：{} 写入异常：", spec, name, e);
                throw new RuntimeException(e);
            }
        }

        log.info("URL：{} 文件：{} 大小：{}", spec, name, byteSum);

    }

}
