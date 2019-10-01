package hello.simple;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SimpleRepository extends MongoRepository<Simple, String> {
}
