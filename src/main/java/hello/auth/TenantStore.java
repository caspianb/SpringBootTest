package hello.auth;

import javax.servlet.http.HttpServletRequest;

public class TenantStore {

    private String tenant;

    public String getTenant() {
        return tenant;
    }

    public void clear() {
        tenant = null;
    }

    public void fromRequest(HttpServletRequest request) {
        this.tenant = "John Doe";
    }

    @Override
    public String toString() {
        return "TenantStore@" + System.identityHashCode(this) + " tenant=" + tenant;
    }
}
