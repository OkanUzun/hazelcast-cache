package com.okan.hazelcastdemo.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.okan.hazelcastdemo.domain.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class FruitRestService {

    private IMap<String, Fruit> fruitMap;

    @Autowired
    public FruitRestService(final HazelcastInstance hazelcastInstance){
        this.fruitMap = hazelcastInstance.getMap("fruitMap");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fruit/{fruitName}")
    public Fruit getFruit(@PathVariable(value = "fruitName") final String fruitName) {
        return fruitMap.get(fruitName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fruit")
    public Collection<Fruit> getFruits() {
        return fruitMap.values();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/fruit/{fruitName}")
    public void updateFruit(@PathVariable(value = "fruitName") final String fruitName, final Fruit fruit) {
        fruit.setName(fruitName);
        fruitMap.put(fruitName, fruit);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/fruit/{fruitName}")
    public void deleteFruit(@PathVariable(value = "fruitName") final String fruitName) {
        fruitMap.remove(fruitName);
    }

}
