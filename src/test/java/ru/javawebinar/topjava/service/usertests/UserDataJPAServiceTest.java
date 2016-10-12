package ru.javawebinar.topjava.service.usertests;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by nicolas on 12.10.2016.
 */
@ActiveProfiles("datajpa")
public class UserDataJPAServiceTest extends AbstractUserServiceTestClass {
    public UserDataJPAServiceTest() {
        super(LoggerFactory.getLogger(UserDataJPAServiceTest.class));
    }
}
