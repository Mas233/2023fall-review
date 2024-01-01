package com.thoughtmechanix.licenses.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.thoughtmechanix.licenses.clients.OrganizationRestTemplateClient;
import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.LicenseRepository;
import com.thoughtmechanix.licenses.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

//@DefaultProperties(
//        commandProperties = {
//                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
//        }
//)
@Service
public class LicenseService {
    private static final Logger logger = LoggerFactory.getLogger(LicenseService.class);
    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    ServiceConfig config;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;


    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Organization org = getOrganization(organizationId);

        return license
                .withOrganizationName(org.getName())
                .withContactName(org.getContactName())
                .withContactEmail(org.getContactEmail())
                .withContactPhone(org.getContactPhone())
                .withComment(config.getExampleProperty());
    }

    @HystrixCommand
    private Organization getOrganization(String organizationId) {
//        if (true) {
//            throw new RuntimeException("-------");
//        }
//        上面的异常导致前端报以下异常：
//        {
//            "timestamp": 1618738393140,
//                "status": 500,
//                "error": "Internal Server Error",
//                "exception": "java.lang.RuntimeException",
//                "message": "-------",
//                "path": "/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a/licenses/f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a"
//        }
        return organizationRestClient.getOrganization(organizationId);
    }

    private void randomlyRunLong() {
        Random rand = new Random();

        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        if (randomNum != 3) sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @HystrixCommand(//fallbackMethod = "buildFallbackLicenseList",
//            threadPoolKey = "licenseByOrgThreadPool",
//            threadPoolProperties =
//                    {@HystrixProperty(name = "coreSize", value = "30"),
//                            @HystrixProperty(name = "maxQueueSize", value = "10")},
//            commandProperties = {
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
//                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000"),
//                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")}
    )
    public List<License> getLicensesByOrg(String organizationId) {
        logger.debug("LicenseService.getLicensesByOrg  Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        randomlyRunLong();

        return licenseRepository.findByOrganizationId(organizationId);
    }
    //没有指定fallbackMethod报的错如下：
//    {
//        "timestamp": 1618738250577,
//            "status": 500,
//            "error": "Internal Server Error",
//            "exception": "com.netflix.hystrix.exception.HystrixRuntimeException",
//            "message": "getLicensesByOrg timed-out and fallback failed.",
//            "path": "/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a/licenses/"
//    }

    private List<License> buildFallbackLicenseList(String organizationId) {
        List<License> fallbackList = new ArrayList<>();
        License license = new License()
                .withId("0000000-00-00000")
                .withOrganizationId(organizationId)
                .withProductName("Sorry no licensing information currently available");

        fallbackList.add(license);
        return fallbackList;
    }

    public void saveLicense(License license) {
        license.withId(UUID.randomUUID().toString());

        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.delete(license.getLicenseId());
    }

}
