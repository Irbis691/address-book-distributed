package com.epam.addressbook.history;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class HousingClient {

    private final Map<Long, HousingInfo> housingCache = new ConcurrentHashMap<>();
    private final RestTemplate restTemplate;
    private final String endpoint;

    public HousingClient(RestTemplate restTemplate, String registrationServerEndpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = registrationServerEndpoint;
    }

    @HystrixCommand(fallbackMethod = "getHousingFromCache")
    public HousingInfo getHousing(long housingId) {
        HousingInfo housingInfo = restTemplate.getForObject(endpoint + "/housings/" + housingId, HousingInfo.class);

        housingCache.put(housingId, housingInfo);

        return housingInfo;
    }

    public HousingInfo getHousingFromCache(long projectId) {
        log.info("Getting project with id {} from cache", projectId);
        return housingCache.get(projectId);
    }
}