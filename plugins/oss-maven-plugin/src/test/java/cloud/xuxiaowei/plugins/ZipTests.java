package cloud.xuxiaowei.plugins;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * ZIP 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
public class ZipTests {

    @Test
    void zipTests() {

        unzip("D:\\IdeaProjects\\xuxiaowei-cloud\\ui\\target\\ossossutil32.zip", "D:\\IdeaProjects\\xuxiaowei-cloud\\ui\\target");

    }

    /**
     * 解压 ZIP
     *
     * @param filePath 压缩文件夹路径
     * @param zipDir   解压后的路径
     */
    public static void unzip(String filePath, String zipDir) {

        try (ZipFile zipfile = new ZipFile(filePath)) {
            Enumeration<? extends ZipEntry> dir = zipfile.entries();

            String name;
            ZipEntry entry;

            while (dir.hasMoreElements()) {
                entry = dir.nextElement();

                if (entry.isDirectory()) {
                    name = entry.getName();
                    name = name.substring(0, name.length() - 1);
                    File fileObject = new File(zipDir + File.separator + name);
                    boolean mkdir = fileObject.mkdir();
                }
            }

            int buffer = 1024;

            Enumeration<? extends ZipEntry> e = zipfile.entries();
            while (e.hasMoreElements()) {
                entry = e.nextElement();
                if (entry.isDirectory()) {

                } else {
                    try (BufferedInputStream bufferedInputStream = new BufferedInputStream(zipfile.getInputStream(entry));
                         FileOutputStream fileOutputStream = new FileOutputStream(zipDir + File.separator + entry.getName());
                         BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, buffer)) {

                        int count;
                        byte[] dataByte = new byte[buffer];

                        while ((count = bufferedInputStream.read(dataByte, 0, buffer)) != -1) {
                            bufferedOutputStream.write(dataByte, 0, count);
                        }

                    } catch (FileNotFoundException ex) {
                        log.error("不存在：{}", filePath, ex);
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        log.error("：{}", filePath, ex);
                        throw new RuntimeException(ex);
                    }
                }
            }

        } catch (IOException e) {
            log.error("创建压缩文件异常：{}", filePath, e);
            throw new RuntimeException(e);
        }

    }

}
