package com.workintech.burger.demo.dao;

import com.workintech.burger.demo.entity.BreadType;
import com.workintech.burger.demo.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BurgerDaoImpl implements BurgerDao {

    private EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Optional<Burger> findById(int id) {
        return Optional.ofNullable(entityManager.find(Burger.class, id));
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT e FROM Burger e", Burger.class);
        return query.getResultList();
    }

    @Override
    public Set<Burger> findByPrice(double price) {
        //Aldığı price değerinden daha büyük olan Burgerleri pricelarına göre büyükten küçüğe dogru listeler.
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC", Burger.class);
        query.setParameter("price", price);
        return query.getResultList().stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Burger> findByBreadType(BreadType breadType) {
        //Bu parametreye eşit olan breadType tipindeki Burgerleri isimlerine göre alfabetik sırada küçükten büyüğe doğru sıralar
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType= :breadType ORDER BY b.name", Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList().stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Burger> findByContent(String searchingContent) {
        // Bir adet String değeri alır ve bu değeri contents tablosunda içeren tüm burgerleri döner.
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE " +
                "b.contents like CONCAT('%', :searchingContent ,'%') ORDER BY b.name", Burger.class);
        query.setParameter("searchingContent", searchingContent);
        return query.getResultList().stream().collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(int id) {
        Optional<Burger> burger = findById(id);
        if (burger.isPresent()) {
            entityManager.remove(burger);
        }
        return burger.get();
    }
}
