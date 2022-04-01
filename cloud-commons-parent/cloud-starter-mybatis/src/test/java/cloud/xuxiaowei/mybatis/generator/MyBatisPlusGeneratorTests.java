package cloud.xuxiaowei.mybatis.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * MyBatis Plus Generator
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class MyBatisPlusGeneratorTests {

    public static void main(String[] args) {

        DataSourceConfig.Builder dataSourceConfig =
                new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/xuxiaowei_cloud", "root", "root")
                        .dbQuery(new MySqlQuery())
                        .schema("users")
                        .typeConvert(new MySqlTypeConvert())
                        .keyWordsHandler(new MySqlKeyWordsHandler());

        String userDir = System.getProperty("user.dir");
        String fileSeparator = System.getProperty("file.separator");
        List<Module> moduleList = Arrays.asList(
                new Module("authorization-server", "cloud.xuxiaowei.authorizationserver"),
                new Module("cloud-commons-parent" + fileSeparator + "cloud-starter-core", "cloud.xuxiaowei.core"),
                new Module("resource-server", "cloud.xuxiaowei.resourceserver"),
                new Module("gateway", "cloud.xuxiaowei.gateway")
        );

        System.out.println("项目文件夹：" + userDir);

        System.out.println("模块列表：");
        for (int i = 0; i < moduleList.size(); i++) {
            Module module = moduleList.get(i);
            System.out.println("序号：" + i + "：" + module.getModuleFolder() + "：" + module.getPackageName());
        }

        int moduleNumber = scannerInt("请输入模块名序号？");
        Module module = moduleList.get(moduleNumber);
        String moduleFolder = module.getModuleFolder();
        String packageName = module.getPackageName();

        String[] packageNameSplit = packageName.split("\\.");
        String xmlFolder = packageNameSplit[packageNameSplit.length - 1];

        String mainFolder = userDir + fileSeparator + moduleFolder + fileSeparator + "src" + fileSeparator + "main";

        String javaDir = mainFolder + fileSeparator + "java";
        String xmlDir = mainFolder + fileSeparator + "resources" + fileSeparator + "mapper" + fileSeparator + xmlFolder;

        System.out.println("java 输出目录：" + javaDir);
        System.out.println("xml 输出目录：" + xmlDir);

        FastAutoGenerator.create(dataSourceConfig)
                // 全局配置
                .globalConfig((scanner, builder) -> {
                    builder.author(scanner.apply("请输入作者名称？"));

                    // 禁止打开输出目录
                    builder.disableOpenDir();
                    // 输出目录
                    builder.outputDir(javaDir);
                })
                // 包配置
                .packageConfig((scanner, builder) -> {
                    builder.parent(packageName);

                    builder.pathInfo(Collections.singletonMap(OutputFile.xml, xmlDir));
                })
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder()
                        // 开启生成@RestController控制器
                        .enableRestStyle()
                        // 开启驼峰转连字符
                        .enableHyphenStyle()
                        .mapperBuilder()
                        // 开启baseResultMap
                        .enableBaseResultMap()
                        .entityBuilder()
                        // 开启lombok模型
                        .enableLombok()
                        .addTableFills(
                                new Column("create_date", FieldFill.INSERT),
                                new Column("update_date", FieldFill.UPDATE),
                                new Column("create_username", FieldFill.INSERT),
                                new Column("update_username", FieldFill.UPDATE)
                        ).logicDeleteColumnName("deleted")
                        .build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();
    }

    @Data
    @AllArgsConstructor
    private static class Module {

        private String moduleFolder;

        private String packageName;

    }

    /**
     * 读取控制台内容
     */
    private static int scannerInt(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            return scanner.nextInt();
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 处理 all 情况
     *
     * @param tables 表
     * @return 返回表名
     */
    private static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
