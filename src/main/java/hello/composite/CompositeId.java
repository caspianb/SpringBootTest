package hello.composite;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CompositeId {
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
