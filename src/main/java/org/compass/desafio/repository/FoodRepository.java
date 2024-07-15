package org.compass.desafio.repository;

import jakarta.persistence.*;
import org.compass.desafio.model.Clothing;
import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Food;
import org.compass.desafio.model.Shelter;

import java.util.List;

public class FoodRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("desafio compass");

    public Food save(Food food) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(food);
            transaction.commit();
            return food;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Food update(Food food) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            Food updatedClothing = em.merge(food);
            transaction.commit();
            return updatedClothing;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Food findById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(Food.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Food> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("from Food", Food.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Food> findByDistributionCenter(DistributionCenter distributionCenter) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Food> query = em.createQuery("SELECT c FROM Food c WHERE c.distributionCenter = :distributionCenter", Food.class);
            query.setParameter("distributionCenter", distributionCenter);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Food> findByShelter(Shelter shelter) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Food> query = em.createQuery("SELECT c FROM Food c WHERE c.shelter = :shelter", Food.class);
            query.setParameter("shelter", shelter);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public boolean delete(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Food food = em.find(Food.class, id);
            if (food != null) {
                em.remove(food);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    /*public int getTotalQuantity() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            Long totalQuantity = em.createQuery("SELECT SUM(c.quantity) FROM Clothing c", Long.class).getSingleResult();
            return totalQuantity != null ? totalQuantity.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            em.close();
        }
    }*/
}
