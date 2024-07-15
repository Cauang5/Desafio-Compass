package org.compass.desafio.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.compass.desafio.model.DistributionCenter;

import java.util.List;

public class DistributionCenterRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("desafio compass");


    public DistributionCenter save(DistributionCenter distributionCenter) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(distributionCenter);
            transaction.commit();
            return distributionCenter;
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

    public DistributionCenter update(DistributionCenter distributionCenter) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            DistributionCenter updatedDistributionCenter = em.merge(distributionCenter);
            transaction.commit();
            return updatedDistributionCenter;
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

    public DistributionCenter findById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(DistributionCenter.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<DistributionCenter> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("from DistributionCenter", DistributionCenter.class).getResultList();
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
            DistributionCenter distributionCenter = em.find(DistributionCenter.class, id);
            if (distributionCenter != null) {
                em.remove(distributionCenter);
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

    public boolean findByEntity(DistributionCenter distributionCenter){
        EntityManager em = entityManagerFactory.createEntityManager();

        return em.createQuery("""
                    SELECT COUNT(dc) FROM DistributionCenter dc
                    WHERE dc.name = :name
                    AND dc.cep = :cep
                """, Long.class)
                .setParameter("name", distributionCenter.getName())
                .setParameter("cep", distributionCenter.getCep())
                .getSingleResult() >= 1L;
    }




}
