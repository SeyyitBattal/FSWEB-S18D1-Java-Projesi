package com.workintech.burger.demo.controller;

import com.workintech.burger.demo.dao.BurgerDao;
import com.workintech.burger.demo.entity.BreadType;
import com.workintech.burger.demo.entity.Burger;
import com.workintech.burger.demo.exceptions.BurgerExcetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/burgers")
public class BurgerController {

    private BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping("/")
    public List<Burger> allBurgers() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger find(@PathVariable int id) {
        Optional<Burger> burger = burgerDao.findById(id);
        if (burger.isPresent()) {
            return burger.get();
        }
        throw new BurgerExcetion("Burger with given id is not exist: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public Burger save(@RequestBody Burger burger) {
        return burgerDao.save(burger);
    }

    @PutMapping("/")
    public Burger update(@RequestBody Burger burger) {
        return burgerDao.update(burger);
    }

    @GetMapping("/findByPrice/{price}")
    public Set<Burger> findByLastName(@PathVariable double price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/findByBreadType/{breadType}")
    public Set<Burger> findByBreadType(@PathVariable String breadType) {
        BreadType breadTypeEnum = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(breadTypeEnum);
    }

    @GetMapping("/findByContent/{contents}")
    public Set<Burger> findByContent(@PathVariable String contents) {
        return burgerDao.findByContent(contents);
    }

    @DeleteMapping("/{id}")
    public Burger delete(@PathVariable int id) {
        return burgerDao.remove(id);
    }


}
