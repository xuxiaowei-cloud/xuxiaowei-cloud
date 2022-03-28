package cloud.xuxiaowei.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * 阿里云 OSS Maven 上传插件
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class OssMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}/oss", required = true)
    private File workDirectory;

    @Parameter(defaultValue = "${project.build.directory}", required = true)
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException {
        File f = outputDirectory;

        if (!f.exists()) {
            boolean mkdirs = f.mkdirs();
        }

    }

}
