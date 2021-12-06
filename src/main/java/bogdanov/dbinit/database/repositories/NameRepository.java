package bogdanov.dbinit.database.repositories;

import bogdanov.dbinit.database.entities.NameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends CrudRepository<NameEntity, Long> {
}
