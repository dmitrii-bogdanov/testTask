package bogdanov.dbinit.services.implementations;

import bogdanov.dbinit.database.entities.NameEntity;
import bogdanov.dbinit.database.repositories.NameRepository;
import bogdanov.dbinit.services.interfaces.NameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class NameServiceImpl implements NameService {

    private final NameRepository nameRepository;

    private final String NAME_PREFIX = "NAME_";

    @Override
    public boolean generateAndSave(int numberOfRecords, int bufferSize) {
        int i = 0;
        int offset = 0;
        for (; i < (numberOfRecords / bufferSize); i++, offset += bufferSize) {
            saveAll(generate(bufferSize, (1 + offset)));
        }
        if (numberOfRecords > i * bufferSize) {
            saveAll(generate(numberOfRecords - offset, (1 + offset)));
        }

        nameRepository.findAll().forEach(entity -> log.info(entity.toString()));
        return true;
    }

    private List<NameEntity> generate(int size, int startingIndex) {

        List<String> names = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            names.add(NAME_PREFIX + (i + startingIndex));
        }

        return names.stream().map(name -> new NameEntity(name)).toList();
    }

    private void saveAll(List<NameEntity> entities) {
        nameRepository.saveAll(entities);
    }
}
