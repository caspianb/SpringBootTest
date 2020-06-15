package hello.test;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class TestRepositoryCustomImpl implements TestRepositoryCustom {

    private final MongoOperations mongoOperations;

    public TestRepositoryCustomImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<Test> findMatching(String like) {
        Criteria criteria = Criteria.where("id").regex(like, "i");
        Query query = Query.query(criteria);
        return mongoOperations.find(query, Test.class);
    }
}
