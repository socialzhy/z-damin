package com.z.admin.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.z.admin.util.DataUtils;
import org.springframework.util.ObjectUtils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CodeGenerator {


    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (DataUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/z_admin?characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true";
        FastAutoGenerator.create(url, "root", "root")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride(); // 覆盖已生成文件
//                            .outputDir("/src/main/java/com/z/admin/"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
                            .moduleName("system"); // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/src/main/java/com/z/admin/")); // 设置mapperXml生成路径
                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("t_simple") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


//
//
//
//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator().getConfig();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");//设置代码生成路径
//        gc.setFileOverride(true);//是否覆盖以前文件
//        gc.setOpen(false);//是否打开生成目录
//        gc.setAuthor("yixin");//设置项目作者名称
//        gc.setIdType(IdType.AUTO);//设置主键策略
//        gc.setBaseResultMap(true);//生成基本ResultMap
//        gc.setBaseColumnList(true);//生成基本ColumnList
//        gc.setServiceName("%sService");//去掉服务默认前缀
//        gc.setDateType(DateType.ONLY_DATE);//设置时间类型
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig builder = new DataSourceConfig.Builder().build();
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus_db?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("123456");
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.yixin");
//        pc.setMapper("mapper");
//        pc.setXml("mapper.xml");
//        pc.setEntity("pojo");
//        pc.setService("service");
//        pc.setServiceImpl("service.impl");
//        pc.setController("controller");
//        mpg.setPackageInfo(pc);
//
//        // 策略配置
//        StrategyConfig sc = new StrategyConfig();
//        sc.setNaming(NamingStrategy.underline_to_camel);
//        sc.setColumnNaming(NamingStrategy.underline_to_camel);
//        sc.setEntityLombokModel(true);//自动lombok
//        sc.setRestControllerStyle(true);
//        sc.setControllerMappingHyphenStyle(true);
//
//        sc.setLogicDeleteFieldName("deleted");//设置逻辑删除
//
//        //设置自动填充配置
//        TableFill gmt_create = new TableFill("create_time", FieldFill.INSERT);
//        TableFill gmt_modified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
//        ArrayList<TableFill> tableFills=new ArrayList<>();
//        tableFills.add(gmt_create);
//        tableFills.add(gmt_modified);
//        sc.setTableFillList(tableFills);
//
//        //乐观锁
//        sc.setVersionFieldName("version");
//        sc.setRestControllerStyle(true);//驼峰命名
//
//
//
//        //  sc.setTablePrefix("tbl_"); 设置表名前缀
//        sc.setInclude(scanner("表名，多个英文逗号分割").split(","));
//        mpg.setStrategy(sc);
//
//        // 生成代码
//        mpg.execute();
//    }

}