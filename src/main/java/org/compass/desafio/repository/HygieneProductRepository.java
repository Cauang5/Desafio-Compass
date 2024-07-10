package org.compass.desafio.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.compass.desafio.model.HygieneProduct;


import java.util.List;

public class HygieneProductRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("desafio compass");

    public HygieneProduct save(HygieneProduct hygieneProduct) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(hygieneProduct);
            transaction.commit();
            return hygieneProduct;
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

    public HygieneProduct update(HygieneProduct hygieneProduct) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            HygieneProduct updatedClothing = em.merge(hygieneProduct);
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

    public HygieneProduct findById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(HygieneProduct.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<HygieneProduct> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("from HygieneProduct", HygieneProduct.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
            HygieneProduct hygieneProduct = em.find(HygieneProduct.class, id);
            if (hygieneProduct != null) {
                em.remove(hygieneProduct);
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

    public int getTotalQuantity() {
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
    }
}
