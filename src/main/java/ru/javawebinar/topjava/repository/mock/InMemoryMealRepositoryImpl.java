package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

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

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        MealUserRelation.addRelation(meal.getId(), userId);
        return repository.put(meal.getId(), meal);
    }

    @Override
    public Meal remove(int id, int userId) {
        if (MealUserRelation.getUserIdByMeaIdl(repository.get(id).getId()) == userId) {
            MealUserRelation.removeRelation(id, MealUserRelation.getUserIdByMeaIdl(id));
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
}

