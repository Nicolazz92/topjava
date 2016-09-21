package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static class MealUserRelation {
        private static Map<Integer, Integer> mealUserRelation = new ConcurrentHashMap();

        static Integer getUserIdByMeaIdl(int mealId) {
            return mealUserRelation.get(mealId);
        }

        static void addRelation(int mealId, int userId) {
            mealUserRelation.put(mealId, userId);
        }

        static void removeRelation(int mealId) {
            mealUserRelation.remove(mealId);
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        MealUserRelation.addRelation(meal.getId(), userId);
        return repository.put(meal.getId(), meal);
    }

    @Override
    public Meal update(Meal meal, int userId) {
        if (MealUserRelation.getUserIdByMeaIdl(repository.get(meal.getId()).getId()) == userId) {
            MealUserRelation.addRelation(meal.getId(), userId);
            return repository.put(meal.getId(), meal);
        } else {
            return null;
        }
    }

    @Override
    public Meal remove(int id, int userId) {
        if (repository.get(id) != null && MealUserRelation.getUserIdByMeaIdl(repository.get(id).getId()) == userId) {
            MealUserRelation.removeRelation(id);
            return repository.remove(id);
        } else {
            return null;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        return MealUserRelation.getUserIdByMeaIdl(id) == userId ?
                repository.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().stream()
                .filter(meal -> MealUserRelation.getUserIdByMeaIdl(meal.getId()) == userId)
                .sorted((o1, o2) -> -o1.getDateTime().compareTo(o2.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        return getAll(userId).stream()
                .filter(meal -> meal.getDate().isAfter(startDate) && meal.getDate().isBefore(endDate))
                .collect(Collectors.toList());
    }
}

