package ru.javawebinar.topjava.service.usertests;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by nicolas on 12.10.2016.
 */
@ActiveProfiles("jpa")
public class UserJPAServiceTest extends AbstractUserServiceTestClass {
    public UserJPAServiceTest() {
        super(LoggerFactory.getLogger(UserJPAServiceTest.class));
    }
}
