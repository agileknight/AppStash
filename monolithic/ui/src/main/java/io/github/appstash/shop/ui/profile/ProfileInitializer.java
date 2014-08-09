package io.github.appstash.shop.ui.profile;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * ProfileInitializer initialize the default profile when no profile was set
 *
 * @author zutherb
 */
public class ProfileInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileInitializer.class);

    @Override
    public void initialize(ConfigurableWebApplicationContext configurableWebApplicationContext) {
        ConfigurableEnvironment environment = configurableWebApplicationContext.getEnvironment();
        if (!hasActiveProfile(environment)) {
            environment.setActiveProfiles("default");
        }
        LOGGER.info("Active profiles are {}", StringUtils.join(environment.getActiveProfiles(), ","));
        LOGGER.info("Database: {}", environment.getProperty("mongo.db"));
        LOGGER.info("Host: {}", environment.getProperty("mongo.host"));
        LOGGER.info("Port: {}", environment.getProperty("mongo.port"));
    }

    private boolean hasActiveProfile(ConfigurableEnvironment environment) {
        return environment.getActiveProfiles().length > 0;
    }
}