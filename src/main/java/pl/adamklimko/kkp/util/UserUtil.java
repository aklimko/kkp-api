package pl.adamklimko.kkp.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static String getUsernameFromContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
