package ru.javawebinar.topjava.service.mealtests;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by nicolas on 12.10.2016.
 */
@ActiveProfiles("jpa")
public class MealJPAServiceTest extends AbstractMealServiceTestClass {
    public MealJPAServiceTest() {
        super(LoggerFactory.getLogger(MealJPAServiceTest.class));
    }
}
