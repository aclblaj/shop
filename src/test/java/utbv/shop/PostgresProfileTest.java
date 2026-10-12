package utbv.shop;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostgresProfileTest {

    @Test
    void testPostgresProperties() throws IOException {
        Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application-postgres.properties"));
        assertEquals("org.postgresql.Driver", props.getProperty("spring.datasource.driver-class-name"));
        assertEquals("org.hibernate.dialect.PostgreSQLDialect", props.getProperty("spring.jpa.database-platform"));
        assertTrue(props.getProperty("spring.datasource.url").contains("jdbc:postgresql://"));
    }

    @Test
    void testPostgresqlProperties() throws IOException {
        Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application-postgresql.properties"));
        assertEquals("org.postgresql.Driver", props.getProperty("spring.datasource.driver-class-name"));
        assertEquals("org.hibernate.dialect.PostgreSQLDialect", props.getProperty("spring.jpa.database-platform"));
        assertTrue(props.getProperty("spring.datasource.url").contains("jdbc:postgresql://"));
    }
}
