package com.z.admin.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.z.admin.entity.po.base.CommonPo;

import java.sql.Types;
import java.util.Collections;

/**
 * @author zhy
 * @description
 */
public class xx {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/z_admin?characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true";
        String username = "root";
        String password = "root";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("zhy") // 设置作者
                            .outputDir(System.getProperty("user.dir")+"/src/main/java") // 指定输出目录
                            .disableOpenDir();
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            if (typeCode == Types.BIGINT) {
                                // 自定义类型转换
                                return DbColumnType.LONG;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.z.admin")
                                .entity("entity.po")
                                .mapper("dao")
                                .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mybatis")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.entityBuilder()
                                //todo 子类会包含父类属性，待处理
                                .superClass(CommonPo.class)
                                .disableSerialVersionUID()
                                .enableLombok()
//                        builder.addInclude("t_simple") // 设置需要生成的表名
//                                .addTablePrefix("t_", "c_") // 设置过滤表前缀
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
