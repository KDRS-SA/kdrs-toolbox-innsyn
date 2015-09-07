package no.arkivlab.innsyn;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import no.arkivlab.innsyn.export.ArkivstrukturHandler;
import no.arkivlab.innsyn.export.ExportManager;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@PropertySource("classpath:config.properties")
public class WebConfig {

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect ";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";


	@Value("${db.address}")
	private String dbAddress;
	
	@Value("${db.port}")
	private String dbPort;

	@Value("${db.name}")
	private String dbName;
	
	@Value("${db.username}")
	private String dbUsername;
	
	@Value("${db.password}")
	private String dbPassword;
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + dbAddress + ":" + dbPort + "/" + dbName);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "no.arkivlab.innsyn.models.n5" });
	
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());

		return em;
	}
	
	@Bean(name = "hibernateProperties")
	public Properties hibernateProperties() {

		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
				"org.hibernate.dialect.MySQLDialect");
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, "true");
		//TODO: Probably want this to be validate only!!
		properties.put("hibernate.hbm2ddl.auto", "update"); 
		 	
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		
		return transactionManager;
	}

	@Bean
	public SessionFactory sessionFactory() {
		return ((HibernateEntityManagerFactory) entityManagerFactory().getObject()).getSessionFactory();
	}
	
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public ExportManager exportManager() {
		ExportManager exportManager = null;
		
			exportManager = new ExportManager();
		
		return exportManager;
	}

	@Bean
	public ArkivstrukturHandler arkivstrukturHandler() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException, FactoryConfigurationError {
		return new ArkivstrukturHandler();
	}
	
	

}
