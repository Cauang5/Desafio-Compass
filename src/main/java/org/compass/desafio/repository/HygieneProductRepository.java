package org.compass.desafio.repository;

import jakarta.persistence.*;
import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Food;
import org.compass.desafio.model.HygieneProduct;
import org.compass.desafio.model.Shelter;


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

    public List<HygieneProduct> findByDistributionCenter(DistributionCenter distributionCenter) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<HygieneProduct> query = em.createQuery("SELECT c FROM HygieneProduct  c WHERE c.distributionCenter = :distributionCenter", HygieneProduct.class);
            query.setParameter("distributionCenter", distributionCenter);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<HygieneProduct> findByShelter(Shelter shelter) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<HygieneProduct> query = em.createQuery("SELECT c FROM HygieneProduct c WHERE c.shelter = :shelter", HygieneProduct.class);
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

}
