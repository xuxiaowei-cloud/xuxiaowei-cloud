package cloud.xuxiaowei.plugins.rar;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.maven.archiver.MavenArchiveConfiguration;
import org.apache.maven.archiver.MavenArchiver;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.archiver.Archiver;
import org.codehaus.plexus.archiver.jar.JarArchiver;

import java.io.File;
import java.util.List;

/**
 * Maven 打包插件
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Getter
@Setter
@Mojo(name = "rar", defaultPhase = LifecyclePhase.PACKAGE)
public class RarMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}/rar", required = true)
    private File workDirectory;

    @Parameter(defaultValue = "${project.build.directory}", required = true)
    private File outputDirectory;

    @Parameter(required = true)
    private List<Resource> resources;

    /**
     * 是否生成 maven 描述
     */
    @Parameter(defaultValue = "true")
    private boolean addMavenDescriptor;

    @SneakyThrows
    @Override
    public void execute() {

        log.info("开始执行 rar 打包");

        // 复制文件
        for (Resource resource : resources) {
            String directory = resource.getDirectory();
            FileUtils.copyDirectoryToDirectory(new File(directory), workDirectory);
        }

        // 执行打包
        // 以下内容参考了：org.apache.maven.plugins:maven-rar-plugin
        File rarFile = getRarFile(outputDirectory, finalName, classifier);
        try {

            MavenArchiver archiver = new MavenArchiver();
            archiver.setArchiver(jarArchiver);
            archiver.setOutputFile(rarFile);

            archiver.getArchiver().addDirectory(workDirectory);

            archive.setAddMavenDescriptor(addMavenDescriptor);

            archiver.createArchive(session, project, archive);
        } catch (Exception e) {
            log.error("打包 RAR 报错", e);
            throw new MojoExecutionException("打包 RAR 报错", e);
        }

    }

    /////////////////////////// 以下内容参考了：org.apache.maven.plugins:maven-rar-plugin

    @Parameter
    private MavenArchiveConfiguration archive = new MavenArchiveConfiguration();
    @Parameter(alias = "rarName", defaultValue = "${project.build.finalName}", required = true)
    private String finalName;
    @Parameter(property = "maven.rar.classifier", defaultValue = "")
    private String classifier;
    @Component(role = Archiver.class, hint = "jar")
    private JarArchiver jarArchiver;
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;
    @Parameter(defaultValue = "${session}", required = true, readonly = true)
    protected MavenSession session;

    protected static File getRarFile(File basedir, String finalName, String classifier) {
        if (classifier == null) {
            classifier = "";
        } else if (classifier.trim().length() > 0 && !classifier.startsWith("-")) {
            classifier = "-" + classifier;
        }
        return new File(basedir, finalName + classifier + ".rar");
    }

}
