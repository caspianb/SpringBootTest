package hello.composite;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompositeRepository extends MongoRepository<Composite, Composite.CompositeId> {
}
