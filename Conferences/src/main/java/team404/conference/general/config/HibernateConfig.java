package team404.conference.general.config;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Value("${spring.jpa.database-platform}")
    private String hibernateDialect;

    @Bean(name = "sessionFactory")
    @DependsOn("liquibase")
    public LocalSessionFactoryBean createLocalSessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setConfigLocation(new ClassPathResource("database/hibernate.cfg.xml"));
        sessionFactoryBean.setDataSource(dataSource);

        sessionFactoryBean.getHibernateProperties().put(
                "hibernate.dialect",
                hibernateDialect);

        return sessionFactoryBean;
    }

    @Bean(name = "liquibase")
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:database/liquibase-changelog.xml");
        return springLiquibase;
    }

    @Bean(name = "hibernateTemplate")
    public HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory);
        return hibernateTemplate;
    }
}
