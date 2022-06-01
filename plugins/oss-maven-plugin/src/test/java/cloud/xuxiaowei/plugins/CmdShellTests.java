package cloud.xuxiaowei.plugins;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 执行 cmd、shell 命令 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class CmdShellTests {

    @Test
    void run() {

        String cmd = "ping -t 127.0.0.1";

        Runtime runtime = Runtime.getRuntime();

        Process process;
        try {
            process = runtime.exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String line;
        try (InputStream inputStream = process.getInputStream(); InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("GBK")); BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                log.info(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
