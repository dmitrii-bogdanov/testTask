package bogdanov.dbinit.database.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@PropertySource("classpath:table.properties")
public class NameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "timestamp", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;

    public NameEntity(String name) {
        this.name = name;
    }

}
