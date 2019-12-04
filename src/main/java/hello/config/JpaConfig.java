package hello.config;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.AttributeConverter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    public static final String GENERATOR_NAME = "uuid_generator";
    public static final String ID_GENERATOR = "hello.config.JpaConfig$UuidGenerator";

    @Bean
    public TokenStore tokenStore() {
        return new TokenStore();
    }

    public static class UuidGenerator implements IdentifierGenerator {
        @Override
        public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }
    }

    public static class JpaJsonConverter<T> implements AttributeConverter<T, String> {

        private static final ObjectMapper objectMapper = new ObjectMapper();
        private final Class<T> classType;

        public JpaJsonConverter(Class<T> classType) {
            this.classType = classType;
        }

        @Override
        public String convertToDatabaseColumn(T attribute) {
            try {
                return objectMapper.writeValueAsString(attribute);
            }
            catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public T convertToEntityAttribute(String dbData) {
            try {
                return objectMapper.readValue(dbData, classType);
            }
            catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class TokenStore {
        private String tenant;

        public String getTenant() {
            return tenant;
        }

        public void setTenant(String tenant) {
            this.tenant = tenant;
        }
    }

}
