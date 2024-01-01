package spittr.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import spittr.db.SpitterRepository;
import spittr.db.SpittleRepository;
import spittr.db.jdbc.JdbcSpitterRepository;
import spittr.db.jdbc.JdbcSpittleRepository;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
//    @Bean
//    public JndiObjectFactoryBean dataSource0() {
//        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName("jdbc/person");
//        jndiObjectFactoryBean.setResourceRef(true);
//        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
//        return jndiObjectFactoryBean;
//    }

    @Profile("ut")
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:spittr/db/jdbc/schema.sql", "classpath:spittr/db/jdbc/test-data.sql")
                .build();
    }

    @Profile("qa")
    @Bean
    public DataSource dataSource2() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/exampledb6");
        ds.setUsername("root");
        ds.setPassword("exampledb20");
        return ds;
    }

    @Profile("prod")
    @Bean
    public DataSource dataSource3() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/exampledb6");
        ds.setUsername("root");
        ds.setPassword("exampledb20");
        ds.setInitialSize(5);
        ds.setMaxActive(10);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcSpitterRepository(jdbcTemplate);
    }

    @Bean
    public SpittleRepository spittleRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcSpittleRepository(jdbcTemplate);
    }

    @Bean
    public NamedParameterJdbcTemplate namedJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
