package hello.simple;

import hello.base.BaseDocument;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Simple extends BaseDocument {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
