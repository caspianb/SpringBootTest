package hello.test;

import java.util.List;

public interface TestRepositoryCustom {

    List<Test> findMatching(String like);
}
