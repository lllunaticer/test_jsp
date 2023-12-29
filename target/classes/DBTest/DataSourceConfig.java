package DBTest;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-03-21
 */
public class DataSourceConfig {
    public DataSource dataSourceLocalHost() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/gifshow?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    public DataSource dataSourceStaging() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://bjfk-staging-d41.yz02:15686/gifshow?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("test_rw");
        dataSource.setPassword("54rltyi5BCdcm06wu22A0brvvzU5uDgB");
        return dataSource;
    }

    public DataSource dataSourceMusicStagingV3() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://bjfk-staging-d42.yz02:15938/gifshow?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("test_rw");
        dataSource.setPassword("54rltyi5BCdcm06wu22A0brvvzU5uDgB");
        return dataSource;
    }

    public DataSource dataSourceCreatorStaging() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://bjfk-staging-d27.yz02:15896/gifshow?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("test_rw");
        dataSource.setPassword("54rltyi5BCdcm06wu22A0brvvzU5uDgB");
        return dataSource;
    }

    public NamedParameterJdbcTemplate creatorStaging() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://bjfk-staging-d27.yz02:15896/gifshow?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("test_rw");
        dataSource.setPassword("54rltyi5BCdcm06wu22A0brvvzU5uDgB");
        return new NamedParameterJdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate namedParameterJdbcTemplateMusicStagingV3() {
        return new NamedParameterJdbcTemplate(dataSourceMusicStagingV3());
    }

    public NamedParameterJdbcTemplate namedParameterJdbcTemplateCreatorStaging() {
        return new NamedParameterJdbcTemplate(dataSourceCreatorStaging());
    }


    public NamedParameterJdbcTemplate namedParameterJdbcTemplateLocalhost() {
        return new NamedParameterJdbcTemplate(dataSourceLocalHost());
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplateStaging() {
        return new NamedParameterJdbcTemplate(dataSourceStaging());
    }
}
