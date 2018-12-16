package com.cl.dataSource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@Configuration // 注册到springboot容器中
@MapperScan(basePackages = "com.cl.source02.mapper", sqlSessionFactoryRef = "source2SqlSessionFactory")
public class DataSource2Config {
	
	
	@Bean(name = "source2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.source2")
	public DataSource sourceDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "source2SqlSessionFactory")
	public SqlSessionFactory sourceSqlSessionFactory(@Qualifier("source2DataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// bean.setMapperLocations(
		// new
		// PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test2/*.xml"));
		return bean.getObject();
	}
	
	@Bean(name = "source2TransactionManager")
	public DataSourceTransactionManager sourceTransactionManager(@Qualifier("source2DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "source2SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate sourceSqlSessionTemplate(
			@Qualifier("source2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}


}
