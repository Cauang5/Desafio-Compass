package org.compass.desafio.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.compass.desafio.model.Clothing;
import org.compass.desafio.model.DistributionCenter;

import java.util.List;

public class ClothingRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("desafio compass");

    public Clothing save(Clothing clothing) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        DistributionCenter dc = clothing.getDistributionCenter();

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(clothing);
            transaction.commit();
            return clothing;
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

    public Clothing update(Clothing clothing) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            Clothing updatedClothing = em.merge(clothing);
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

    public Clothing findById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(Clothing.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Clothing> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("from Clothing", Clothing.class).getResultList();
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
            Clothing clothing = em.find(Clothing.class, id);
            if (clothing != null) {
                em.remove(clothing);
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
