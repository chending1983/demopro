package cn.isc.iscWebDemo.configutation;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * data source config
 * 
 * @author cd
 * @date 2021/01/06
 */
@Configuration
@MapperScan(basePackages = "cn.isc.iscWebDemo.dao", sqlSessionTemplateRef  = "sqlSessionTemplate")
public class DataSourceConfiguration {
    
    @DependsOn({"transactionManager"})
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource datasource(Environment env) {
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // use annotation mapper sql
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath**:mappers/*.xml"));
        return bean.getObject();
    }


}
