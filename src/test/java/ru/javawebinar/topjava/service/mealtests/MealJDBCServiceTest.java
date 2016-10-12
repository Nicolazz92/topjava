package ru.javawebinar.topjava.service.mealtests;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by nicolas on 12.10.2016.
 */
@ActiveProfiles("jdbc")
public class MealJDBCServiceTest extends AbstractMealServiceTestClass {
    public MealJDBCServiceTest() {
        super(LoggerFactory.getLogger(MealJDBCServiceTest.class));
    }
}
