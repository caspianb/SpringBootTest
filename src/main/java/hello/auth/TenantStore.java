package hello.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.util.WebUtils;

public class TenantStore {

    private String tenant;

    public String getTenant() {
        return tenant;
    }

    public void clear() {
        tenant = null;
    }

    public void fromRequest(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "auth");
        this.tenant = cookie != null ? cookie.getName() : "anon";
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
