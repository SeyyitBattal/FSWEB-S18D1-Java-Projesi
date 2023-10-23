package com.workintech.burger.demo.dao;

import com.workintech.burger.demo.entity.BreadType;
import com.workintech.burger.demo.entity.Burger;
import com.workintech.burger.demo.entity.Contents;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BurgerDao {
    Burger save(Burger burger);

    Optional<Burger> findById(int id);

    List<Burger> findAll();

    Set<Burger> findByPrice(double price);

    Set<Burger> findByBreadType(BreadType breadType);

    Set<Burger> findByContent(String searchingContent);

    Burger update(Burger burger);

    Burger remove(int id);

}
