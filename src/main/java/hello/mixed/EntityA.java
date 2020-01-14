package hello.mixed;

import hello.base.BaseDocument;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("entities")
@TypeAlias("entityA")
public class EntityA extends BaseDocument {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
