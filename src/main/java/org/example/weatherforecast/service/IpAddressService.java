package org.example.weatherforecast.service;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.util.HttpUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class IpAddressService implements OptionalPipeline<String, HttpServletRequest> {
    public String find(HttpServletRequest request) {
        return HttpUtils.getIpFromRequest(request);
    }
}
