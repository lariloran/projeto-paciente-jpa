package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import classes.Consulta;

public class ConsultaDAO {

    private EntityManager em;

    public ConsultaDAO(EntityManager em) {
        this.em = em;
    }

    public boolean salvar(Consulta entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar a consulta.", e);
        } 
    }

    public boolean atualizar(Consulta entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar a consulta.", e);
        } 
    }

    public boolean remover(long id) {
        try {
            em.getTransaction().begin();
            Consulta entity = em.find(Consulta.class, id);
            if (entity == null) {
                throw new RuntimeException("Consulta não encontrada com o ID: " + id);
            }
            em.remove(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover a consulta.", e);
        } 
    }

    public Consulta buscarID(long id) {
        try {
            Consulta entity = em.find(Consulta.class, id);
            return entity;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar a consulta pelo ID: " + id, e);
        } 
    }

    public List<Consulta> buscarConsultaCrmMedico(String crm) {
        try {
            TypedQuery<Consulta> query = em.createQuery(
                "SELECT obj FROM Consulta obj INNER JOIN Medico m WHERE m.crm = :crm",
                Consulta.class
            );
            query.setParameter("crm", crm);
            List<Consulta> consultas = query.getResultList();
            return consultas;
        } catch (NoResultException e) {
            return null; 
        } catch (NonUniqueResultException e) {
            throw new RuntimeException("Mais de uma consulta encontrada para o CRM do médico: " + crm, e);
        } catch (PersistenceException e) {
            throw new RuntimeException("Erro ao buscar consultas pelo CRM do médico: " + crm, e);
        }
    }

    public List<Consulta> buscarTodos() {
        try {
            TypedQuery<Consulta> query = em.createQuery("SELECT obj FROM Consulta obj", Consulta.class);
            List<Consulta> consultas = query.getResultList();
            return consultas;
        } catch (PersistenceException e) {
            throw new RuntimeException("Erro ao buscar todas as consultas.", e);
        } 
    }
}
