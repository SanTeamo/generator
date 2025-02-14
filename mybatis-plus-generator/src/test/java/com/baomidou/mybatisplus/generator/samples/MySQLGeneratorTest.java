package com.baomidou.mybatisplus.generator.samples;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * MySQL 代码生成
 *
 * @author lanjerry
 * @since 3.5.3
 */
public class MySQLGeneratorTest extends BaseGeneratorTest {

    @Test
    public void testSimple() {
        // 输出路径
        final String outputDir = System.getProperty("user.dir") + "\\src\\main\\java";
        // 包路径
        final String packagePath = "cn.santeamo";
        // 模块名称 TODO 改成自己的模块名称
        final String moduleName = "module";
        // 模块路径
        final String modulePath = outputDir + "\\" + packagePath.replace(".", "\\") + "\\" + moduleName;
        final String mapperPath = modulePath + "\\mapper";
        final String dtoPath = modulePath + "\\dto";
        final String voPath = modulePath + "\\vo";
        // TODO 改成要生成文件的表名，英文逗号分隔
        final String tableNameStr = "t_table";
        FastAutoGenerator
            .create(
                // TODO 修改数据库连接信息
                "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=Asia/Shanghai&useSSL=false&allowMultiQueries=true",
                "root", "root")
            .globalConfig(builder -> {
                // TODO 改成自己的名称
                builder.author("author") // 设置作者
                    .enableSwagger() // 开启 swagger 模式
                    .outputDir(outputDir); // 指定输出目录
            })
            .packageConfig(builder -> {
                // 设置父包名
                builder.parent(packagePath)
                    // 设置父包模块名
                    .moduleName(moduleName)
                    // 设置mapperXml生成路径
                    .pathInfo(Collections.singletonMap(OutputFile.xml, mapperPath));
            })
            .strategyConfig(builder -> {
                // 设置需要生成的表名，支持多个表，使用英文逗号分隔
                builder.addInclude(tableNameStr)
                    // 设置过滤表前缀
                    .addTablePrefix("t_");
                builder.entityBuilder()
                    .enableLombok()
                    .enableTableFieldAnnotation()
                    .logicDeleteColumnName("deleted")
                    .addTableFills(
                        new Column("create_time", FieldFill.INSERT),
                        new Column("update_time", FieldFill.UPDATE)
                    );
                builder.controllerBuilder()
                    .enableRestStyle()
                    .enableHyphenStyle()
                ;
                builder.mapperBuilder()
                    .enableBaseColumnList()
                    .enableBaseResultMap()
                ;
            })
            .injectionConfig(builder ->
                builder.customFile(file ->
                        file.fileName("SearchDto.java")
                            .templatePath("/templates/dto.search.java.vm")
                            .filePath(dtoPath)
                    )
                    .customFile(file ->
                        file.fileName("Vo.java")
                            .templatePath("/templates/vo.java.vm")
                            .enableFileOverride()
                            .filePath(voPath)
                    )
                    .build())
            .templateConfig(TemplateConfig.Builder::build)
            .templateEngine(new VelocityTemplateEngine())
            .execute();
    }
}
