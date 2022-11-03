package org.example.weatherforecast.util;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpUtilsTest {

    private static final String IP_ADDRESS = "127.0.0.1";
    private static final String X_FORWARDED_FOR_HEADER = "X-Forwarded-For";
    private static final String HTTP_X_FORWARDED_FOR_HEADER = "HTTP_X_FORWARDED_FOR";
    private static final String HTTP_FORWARDED_FOR_HEADER = "HTTP_FORWARDED_FOR";

    @Test
    public void shouldFindIpAddress1 () {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(X_FORWARDED_FOR_HEADER, IP_ADDRESS);
        String ipAddress = HttpUtils.getIpFromRequest(request);
        assertEquals(IP_ADDRESS, ipAddress);
    }

    @Test
    public void shouldFindIpAddress2 () {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HTTP_FORWARDED_FOR_HEADER, IP_ADDRESS);
        String ipAddress = HttpUtils.getIpFromRequest(request);
        assertEquals(IP_ADDRESS, ipAddress);
    }

    @Test
    public void shouldFindIpAddress3 () {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HTTP_X_FORWARDED_FOR_HEADER, IP_ADDRESS);
        String ipAddress = HttpUtils.getIpFromRequest(request);
        assertEquals(IP_ADDRESS, ipAddress);
    }
}
