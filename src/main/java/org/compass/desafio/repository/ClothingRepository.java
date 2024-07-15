package org.compass.desafio.repository;

import jakarta.persistence.*;
import org.compass.desafio.model.Clothing;
import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Shelter;

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
            return em.createQuery("from clothing_item", Clothing.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Clothing> findByDistributionCenter(DistributionCenter distributionCenter) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Clothing> query = em.createQuery("SELECT c FROM Clothing c WHERE c.distributionCenter = :distributionCenter", Clothing.class);
            query.setParameter("distributionCenter", distributionCenter);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Clothing> findByShelter(Shelter shelter) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Clothing> query = em.createQuery("SELECT c FROM Clothing c WHERE c.shelter = :shelter", Clothing.class);
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
}
