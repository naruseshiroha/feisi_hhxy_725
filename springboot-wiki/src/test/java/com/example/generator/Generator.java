package com.example.generator;

import java.sql.SQLException;
import java.util.Collections;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Generator {

        /**
         * 执行初始化数据库脚本
         */
        // public static void before() throws SQLException {
        // Connection conn = DATA_SOURCE_CONFIG.build().getConn();
        // InputStream inputStream =
        // H2CodeGeneratorTest.class.getResourceAsStream("/sql/init.sql");
        // ScriptRunner scriptRunner = new ScriptRunner(conn);
        // scriptRunner.setAutoCommit(true);
        // scriptRunner.runScript(new InputStreamReader(inputStream));
        // conn.close();
        // }

        /**
         * 数据源配置
         */
        private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(
                        "jdbc:mysql://127.0.0.1:3306/1908rg?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true",
                        "root",
                        "root");

        /**
         * 执行 run
         */
        public static void main(String[] args) throws SQLException {
                // before();

                FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                                .globalConfig((scanner, builder) -> builder
                                                .author(scanner.apply("请输入作者名称"))
                                                .outputDir("E:/demo/src/main/java")
                                                .commentDate("yyyy-MM-dd")
                                                .fileOverride())
                                .packageConfig((scanner, builder) -> builder
                                                .parent(scanner.apply("请输入包名")) // 设置父包名
                                                // .moduleName(scanner.apply("请输入模块名")) // 设置父包模块名

                                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                                                System.getProperty("user.dir")
                                                                                + "/src/main/resources/mapper")))
                                .strategyConfig((scanner, builder) -> builder
                                                .addInclude(scanner.apply("请输入表名多个表名用,隔开").split(","))
                                                .addTablePrefix("t_", "c_"))
                                .strategyConfig((builder) -> builder.entityBuilder().enableLombok())
                                .templateEngine(new FreemarkerTemplateEngine())
                                .execute();

                // FastAutoGenerator.create("url", "username", "password")
                // .globalConfig(builder -> {
                // builder.author("baomidou") // 设置作者
                // .enableSwagger() // 开启 swagger 模式
                // .fileOverride() // 覆盖已生成文件
                // .outputDir("D://"); // 指定输出目录
                // })
                // .packageConfig(builder -> {
                // builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
                // .moduleName("system") // 设置父包模块名
                // .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); //
                // 设置mapperXml生成路径
                // })
                // .strategyConfig(builder -> {
                // builder.addInclude("t_simple") // 设置需要生成的表名
                // .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                // })
                // .templateEngine(new FreemarkerTemplateEngine()) //
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                // .execute();

                // 数据库配置(DataSourceConfig)
                // new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/mybatis-plus",
                // "root", "123456")
                // .dbQuery(new MySqlQuery())
                // .schema("mybatis-plus")
                // .typeConvert(new MySqlTypeConvert())
                // .keyWordsHandler(new MySqlKeyWordsHandler())
                // .build();

                // 全局配置(GlobalConfig)
                // new GlobalConfig.Builder()
                // .fileOverride()
                // .outputDir("/opt/baomidou")
                // .author("baomidou")
                // .enableKotlin()
                // .enableSwagger()
                // .dateType(DateType.TIME_PACK)
                // .commentDate("yyyy-MM-dd")
                // .build();

                // 包配置(PackageConfig)
                // new PackageConfig.Builder()
                // .parent("com.baomidou.mybatisplus.samples.generator")
                // .moduleName("sys")
                // .entity("po")
                // .service("service")
                // .serviceImpl("service.impl")
                // .mapper("mapper")
                // .xml("mapper.xml")
                // .controller("controller")
                // .other("other")
                // .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://"))
                // .build();

                // 模板配置(TemplateConfig)
                // new TemplateConfig.Builder()
                // .disable(TemplateType.ENTITY)
                // .entity("/templates/entity.java")
                // .service("/templates/service.java")
                // .serviceImpl("/templates/serviceImpl.java")
                // .mapper("/templates/mapper.java")
                // .mapperXml("/templates/mapper.xml")
                // .controller("/templates/controller.java")
                // .build();

                // 注入配置(InjectionConfig)
                // new InjectionConfig.Builder()
                // .beforeOutputFile((tableInfo, objectMap) -> {
                // System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: "
                // + objectMap.size());
                // })
                // .customMap(Collections.singletonMap("test", "baomidou"))
                // .customFile(Collections.singletonMap("test.txt", "/templates/test.vm"))
                // .build();

                // 策略配置(StrategyConfig)
                // new StrategyConfig.Builder()
                // .enableCapitalMode()
                // .enableSkipView()
                // .disableSqlFilter()
                // .likeTable(new LikeTable("USER"))
                // .addInclude("t_simple")
                // .addTablePrefix("t_", "c_")
                // .addFieldSuffix("_flag")
                // .build();

                // Entity 策略配置
                // new StrategyConfig.Builder()
                // .entityBuilder()
                // .superClass(BaseEntity.class)
                // .disableSerialVersionUID()
                // .enableChainModel()
                // .enableLombok()
                // .enableRemoveIsPrefix()
                // .enableTableFieldAnnotation()
                // .enableActiveRecord()
                // .versionColumnName("version")
                // .versionPropertyName("version")
                // .logicDeleteColumnName("deleted")
                // .logicDeletePropertyName("deleteFlag")
                // .naming(NamingStrategy.no_change)
                // .columnNaming(NamingStrategy.underline_to_camel)
                // .addSuperEntityColumns("id", "created_by", "created_time", "updated_by",
                // "updated_time")
                // .addIgnoreColumns("age")
                // .addTableFills(new Column("create_time", FieldFill.INSERT))
                // .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                // .idType(IdType.AUTO)
                // .formatFileName("%sEntity")
                // .build();

                // Controller 策略配置
                // new StrategyConfig.Builder()
                // .controllerBuilder()
                // .superClass(BaseController.class)
                // .enableHyphenStyle()
                // .enableRestStyle()
                // .formatFileName("%sAction")
                // .build();

                // Service 策略配置
                // new StrategyConfig.Builder()
                // .serviceBuilder()
                // .superServiceClass(BaseService.class)
                // .superServiceImplClass(BaseServiceImpl.class)
                // .formatServiceFileName("%sService")
                // .formatServiceImplFileName("%sServiceImp")
                // .build();

                // Mapper 策略配置
                // new StrategyConfig.Builder()
                // .mapperBuilder()
                // .superClass(BaseMapper.class)
                // .enableMapperAnnotation()
                // .enableBaseResultMap()
                // .enableBaseColumnList()
                // .cache(MyMapperCache.class)
                // .formatMapperFileName("%sDao")
                // .formatXmlFileName("%sXml")
                // .build();
        }
}
