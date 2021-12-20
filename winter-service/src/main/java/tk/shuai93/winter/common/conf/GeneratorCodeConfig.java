package tk.shuai93.winter.common.conf;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneratorCodeConfig {
    public static void main(String[] args) {
        // reference: https://mp.baomidou.com/guide/generator-new.html#%E6%95%B0%E6%8D%AE%E5%BA%93%E9%85%8D%E7%BD%AE-datasourceconfig
        // 默认使用 generateCode 配置见代码，生成文件见 system 文件夹
        generateCode();
        // 交互式的方式生成 tmp 文件夹后复制到项目
        // generateCodeInteractive();
    }

    private static void generateCode() {
        String outDir = System.getProperty("user.dir") + "/src/main/java/";
        FastAutoGenerator.create(
                        "jdbc:mysql://srd-mariadb102-01-qcsh-test.nioint.com/musesapi_sit?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8",
                        "musesapi_sit_rw",
                        "V574H54KL84xm1ZA4nt"
                )
                .globalConfig(builder -> {
                    builder.author("shuai.yang1") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.nio.dd.sdi.muses") // 设置父包名
                            .moduleName("music") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, outDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    // "t_music_genre", "t_music_tag", "t_music", "t_music_info", "t_music_album", "t_playlist", "t_weekly", "t_appreciate", "t_original", "t_operate_config"
                    builder.addInclude(List.of("t_music_category" )).addTablePrefix("t_"); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    public static void generateCodeInteractive(){
        var config = new DataSourceConfig.
                Builder("jdbc:mysql://srd-mariadb102-01-qcsh-test.nioint.com/musesapi_sit?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8",
                "musesapi_sit_rw",
                "V574H54KL84xm1ZA4nt"
        );

        FastAutoGenerator.create(config)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())

                 */
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
