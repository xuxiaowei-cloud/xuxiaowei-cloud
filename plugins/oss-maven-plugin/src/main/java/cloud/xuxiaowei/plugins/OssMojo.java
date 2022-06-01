package cloud.xuxiaowei.plugins;

import cloud.xuxiaowei.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * 阿里云 OSS Maven 上传插件
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Mojo(name = "oss", defaultPhase = LifecyclePhase.PACKAGE)
public class OssMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}/oss", required = true)
    private File workDirectory;

    @Parameter(defaultValue = "${project.build.directory}", required = true)
    private File outputDirectory;

    @Override
    public void execute() {

        if (outputDirectory.exists()) {
            log.info("阿里云 OSS 输出目录 已存在");
        } else {
            boolean mkdirs = outputDirectory.mkdirs();
            log.info("阿里云 OSS 输出目录 创建结果：{}", mkdirs);
        }

        if (workDirectory.exists()) {
            log.info("阿里云 OSS 工作目录 已存在");
        } else {
            boolean mkdirs = workDirectory.mkdirs();
            log.info("阿里云 OSS 工作目录 创建结果：{}", mkdirs);
        }

        String name = System.getProperties().getProperty("os.name");
        String arch = System.getProperty("os.arch");
        String separator = File.separator;
        log.info("操作系统：{}，位数：{}，系统分隔符：{}", name, arch, separator);

        final String win = "win";
        final String lin = "lin";

        boolean isWin = name.toLowerCase().startsWith(win);
        boolean isLin = name.toLowerCase().startsWith(lin);
        boolean is64 = arch.contains("64");

        String url;
        if (isWin) {
            if (is64) {
                url = "https://gosspublic.alicdn.com/ossutil/1.7.13/ossutil64.zip";
            } else {
                url = "https://gosspublic.alicdn.com/ossutil/1.7.13/ossutil32.zip";
            }
        } else if (isLin) {
            if (is64) {
                url = "https://gosspublic.alicdn.com/ossutil/1.7.13/ossutil64";
            } else {
                url = "https://gosspublic.alicdn.com/ossutil/1.7.13/ossutil32";
            }
        } else {
            if (is64) {
                url = "https://gosspublic.alicdn.com/ossutil/1.7.13/ossutilmac64";
            } else {
                url = "https://gosspublic.alicdn.com/ossutil/1.7.7/ossutilmac32";
                log.warn("ossutil 1.7.7 以后的版本是基于 Golang 1.16 版本编译，不支持通过 macOS x86 32bit 操作系统使用 ossutil");
            }
        }

        String[] urlSplit = url.split("/");
        String filename = urlSplit[urlSplit.length - 1];
        String workDirectoryPath = workDirectory.getPath();
        String ossutilPath = workDirectoryPath + File.separator + filename;

        log.info("ossutil 下载 URL：{}，路径：{}", url, ossutilPath);

        FileUtils.downloadFile(url, ossutilPath);
        log.info("ossutil 文件下载完成");

        final String zip = ".zip";

        String executableFile;

        if (filename.endsWith(zip)) {
            log.info("{} 开始解压......", ossutilPath);
            FileUtils.unzip(ossutilPath, workDirectoryPath);
            log.info("{} 解压完成", ossutilPath);

            executableFile = workDirectoryPath + filename.substring(0, filename.length() - 4);
        } else {
            executableFile = ossutilPath;
        }

        log.info("可执行文件路径：{}", executableFile);

    }

}
