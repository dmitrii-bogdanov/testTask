package bogdanov.dbinit.configs;

import bogdanov.dbinit.database.entities.CopiedNameEntity;
import bogdanov.dbinit.database.entities.NameEntity;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:table.properties")
public class ExternalPhysicalNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {

    @Value("${originalTableName}")
    private String originalTableName;

    @Value("${copiedTableName}")
    private String copiedTableName;

    private static final String ORIGINAL_TABLE_ENTITY_NAME = NameEntity.class.getSimpleName();
    private static final String COPIED_TABLE_ENTITY_NAME = CopiedNameEntity.class.getSimpleName();

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        if (name.getText().equalsIgnoreCase(ORIGINAL_TABLE_ENTITY_NAME)) {
            return Identifier.toIdentifier(originalTableName);
        }
        if (name.getText().equalsIgnoreCase(COPIED_TABLE_ENTITY_NAME)) {
            return Identifier.toIdentifier(copiedTableName);
        }
        return super.toPhysicalTableName(name, jdbcEnvironment);
    }

}
