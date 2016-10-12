package ru.javawebinar.topjava.service.usertests;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by nicolas on 12.10.2016.
 */
@ActiveProfiles("jdbc")
public class UserJDBCServiceTest extends AbstractUserServiceTestClass {
    public UserJDBCServiceTest() {
        super(LoggerFactory.getLogger(UserJDBCServiceTest.class));
    }
}
