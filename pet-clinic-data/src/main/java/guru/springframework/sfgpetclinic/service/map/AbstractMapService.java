package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import lombok.extern.log4j.Log4j2;

import java.util.*;

@Log4j2
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    private Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object could not be null");
        }
        return object;
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    private Long getNextId() {
        Long increment = map.isEmpty() ? 1L : Collections.max(map.keySet()) + 1L;
        log.info("Row increment: {}", increment);
        return increment;
    }
}
