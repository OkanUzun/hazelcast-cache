package com.okan.hazelcastdemo.mapStore;

import com.hazelcast.core.MapStore;
import com.okan.hazelcastdemo.domain.Fruit;
import com.okan.hazelcastdemo.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class FruitMapStore implements MapStore<String, Fruit> {

    private final FruitRepository fruitRepository;

    @Autowired
    public FruitMapStore(final FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public void store(final String key, final Fruit value) {
        fruitRepository.save(value);
    }

    @Override
    public void storeAll(final Map<String, Fruit> map) {
        for (final Map.Entry<String, Fruit> fruitEntry : map.entrySet()) {
            this.store(fruitEntry.getKey(), fruitEntry.getValue());
        }
    }

    @Override
    public void delete(final String key) {
        fruitRepository.deleteFruitByName(key);
    }

    @Override
    public void deleteAll(final Collection<String> keys) {
        for (String key : keys) {
            this.delete(key);
        }
    }

    @Override
    public Fruit load(final String key) {
        return fruitRepository.findFruitByName(key);
    }

    @Override
    public Map<String, Fruit> loadAll(final Collection<String> keys) {
        final Map<String, Fruit> fruits = new HashMap<>();
        for (final String key : keys) {
            fruits.put(key, load(key));
        }

        return fruits;
    }

    @Override
    public Set<String> loadAllKeys() {
        return fruitRepository.findAllFruits();
    }
}
