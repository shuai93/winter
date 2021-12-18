package tk.shuai93.winter.common.enums.conf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;



/**
 * 自动生成mybatisplus的相关代码
 */
public class GeneratorCodeConfig {

    public static void main(String[] args) {

        String packageName = "com.github.shuai93.winter";
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(false, packageName, "user");
    }

    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/winter";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true) // 【实体】是否为lombok模型（默认 false)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("shuai93")
                .setOutputDir(System.getProperty("user.dir") + "/winter-service/src/main/java/")
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }

}