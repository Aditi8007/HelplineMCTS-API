package com.iemr.mcts.configure;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.iemr.mcts.utils.config.ConfigProperties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "secondaryEntityManagerFactory",
  transactionManagerRef = "secondaryTransactionManager",
  basePackages = { "com.iemr.mcts.secondary.repository.*", "com.iemr.mcts.secondary.data.*" }
)
public class SecondaryDBConfig {
	@Bean(name = "secondaryDataSource")
	  @ConfigurationProperties(prefix = "secondary.datasource")
	  public DataSource dataSource() {
		PoolConfiguration p = new PoolProperties();
		  p.setMaxActive(20);
		  p.setMaxIdle(8);
		  p.setMinIdle(3);
		  p.setInitialSize(5);
		  p.setMaxWait(10000);
		  p.setMinEvictableIdleTimeMillis(15000);
		  p.setRemoveAbandoned(true);
		  p.setLogAbandoned(true);
		  p.setRemoveAbandonedTimeout(600);
		  p.setTestOnBorrow(true);
		  p.setValidationQuery("SELECT 1");
		  org.apache.tomcat.jdbc.pool.DataSource datasource = new org.apache.tomcat.jdbc.pool.DataSource();
		  datasource.setPoolProperties(p);
		  StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setAlgorithm("PBEWithMD5AndDES");

			encryptor.setPassword("dev-env-secret");

			datasource.setUsername(encryptor.decrypt(ConfigProperties.getPropertyByName("encDbUserNameSec")));
			datasource.setPassword(encryptor.decrypt(ConfigProperties.getPropertyByName("encDbPassSec")));
			
	    return datasource;
	  }
	  
	  @Bean(name = "secondaryEntityManagerFactory")
	  public LocalContainerEntityManagerFactoryBean 
	  barEntityManagerFactory(
	    EntityManagerFactoryBuilder builder,
	    @Qualifier("secondaryDataSource") DataSource dataSource
	  ) {
	    return
	      builder
	        .dataSource(dataSource)
	        .packages("com.iemr.mcts.secondary.data.report", "com.iemr.mcts.secondary.data.*")
	        .persistenceUnit("db_reporting")
	        .build();
	  }
	  @Bean(name = "secondaryTransactionManager")
	  public PlatformTransactionManager barTransactionManager(
	    @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory
	    secondaryEntityManagerFactory
	  ) {
	    return new JpaTransactionManager(secondaryEntityManagerFactory);
	  }
}
