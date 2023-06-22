package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceException;
import javax.persistence.NoResultException;

import classes.Medico;

public class MedicoDAO {

    private EntityManager em;

    public MedicoDAO(EntityManager em) {
        this.em = em;
    }

    public boolean salvar(Medico entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar o médico.", e);
        }
    }

    public boolean atualizar(Medico entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar o médico.", e);
        }
    }

    public boolean remover(long id) {
        try {
            em.getTransaction().begin();
            Medico entity = em.find(Medico.class, id);
            if (entity == null) {
                throw new RuntimeException("Médico não encontrado com o ID: " + id);
            }
            em.remove(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover o médico.", e);
        }
    }

    public Medico buscarID(long id) {
        try {
            Medico entity = em.find(Medico.class, id);
            return entity;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar o médico pelo ID: " + id, e);
        }
    }

    public Medico buscarCrm(String crm) {
        try {
            TypedQuery<Medico> query = em.createQuery(
                "SELECT obj FROM Medico obj WHERE obj.crm = :crm",
                Medico.class
            );
            query.setParameter("crm", crm);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar o médico pelo CRM: " + crm, e);
        }
    }

    public List<Medico> buscarTodos() {
        try {
            TypedQuery<Medico> query = em.createQuery("SELECT obj FROM Medico obj", Medico.class);
            List<Medico> medicos = query.getResultList();
            return medicos;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar todos os médicos.", e);
        }
    }
}
