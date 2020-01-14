package hello.mixed;

import java.util.List;

import org.springframework.core.annotation.AliasFor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntityBRepository extends MongoRepository<EntityB, String> {

    @AliasFor("entityA")
    List<EntityB> findAllByTenant(String tenant);

}
