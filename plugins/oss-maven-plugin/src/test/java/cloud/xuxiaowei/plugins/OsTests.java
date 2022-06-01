package cloud.xuxiaowei.plugins;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * 系统 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class OsTests {

    @Test
    void name() {
        String name = System.getProperties().getProperty("os.name");
        log.info(name);
    }

    @Test
    void arch() {
        String arch = System.getProperty("os.arch");
        log.info(arch);
    }

    @Test
    void separator() {
        String separator = File.separator;
        log.info(separator);
    }

}
