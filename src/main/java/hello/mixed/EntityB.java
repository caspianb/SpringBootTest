package hello.mixed;

import hello.base.BaseDocument;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("entities")
@TypeAlias("entityB")
public class EntityB extends BaseDocument {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
