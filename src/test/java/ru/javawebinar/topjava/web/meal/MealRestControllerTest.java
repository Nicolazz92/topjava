package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.web.meal.MealRestController.REST_URL;

/**
 * Created by nicolas on 28.10.2016.
 */
public class MealRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1)));
    }

    @Test
    public void testDelete() throws Exception {
        int beforeSize = mealService.getAll(AuthorizedUser.id()).size();
        TestUtil.print(
                mockMvc.perform(delete(REST_URL + MEAL1_ID))
                        .andExpect(status().isOk())
        );
        if (mealService.getAll(AuthorizedUser.id()).size() + 1 != beforeSize) {
            throw new Exception();
        }
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(MealsUtil.getWithExceeded(MEALS, AuthorizedUser.getCaloriesPerDay())))
        );
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = MealTestData.getUpdated();

        TestUtil.print(mockMvc.perform(put(REST_URL + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))));

        if (!mealService.get(updated.getId(), AuthorizedUser.id()).equals(updated)) {
            throw new Exception();
        }
    }

    @Test
    public void testCreate() throws Exception {
        Meal created = MealTestData.getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))).andExpect(status().isCreated());

        Meal returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        List<Meal> newMeals = new ArrayList<>();
        newMeals.add(created);
        newMeals.addAll(MEALS);
        MATCHER.assertCollectionEquals(newMeals, mealService.getAll(AuthorizedUser.id()));
    }

    @Test
    public void testBetween() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "/between" + "?startDateTime=2015-05-30T11:00:00&endDateTime=2015-05-31T15:00:00"))
                .andExpect(status().isOk())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(
                        MealsUtil.createWithExceed(MEAL5, true),
                        MealsUtil.createWithExceed(MEAL2, false)
                ))
        );
    }

}