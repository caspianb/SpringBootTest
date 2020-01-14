package hello.mixed;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EntityARepository extends MongoRepository<EntityA, String> {

    // This is required for mixed collections to filter properly
    // Base interface repository methods DO NOT filter out on _class field
    @Query(value = "{tenant: ?0, _class: 'entityA'}")
    List<EntityA> findAllByTenant(String tenant);
}
