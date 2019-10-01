package hello.composite;

import hello.base.BaseDocument;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Composite extends BaseDocument {

    @Indexed
    private CompositeId compositeId;

    private String name;

    public CompositeId getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(CompositeId compositeId) {
        this.compositeId = compositeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static class CompositeId {
        private String id;
        private String tenant;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTenant() {
            return tenant;
        }

        public void setTenant(String tenant) {
            this.tenant = tenant;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
