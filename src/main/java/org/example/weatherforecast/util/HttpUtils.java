package org.example.weatherforecast.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@UtilityClass
public class HttpUtils {
    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_FORWARDED_FOR"
    };

    public static String getIpFromRequest(HttpServletRequest request) {
        for (String header: IP_HEADERS) {
            log.debug("Searching for header: {}", header);
            String value = request.getHeader(header);
            if (value == null || value.isEmpty()) {
                continue;
            }
            String[] parts = value.split("\\s*,\\s*");
            return parts[0];
        }
        return request.getRemoteAddr();
    }
}
