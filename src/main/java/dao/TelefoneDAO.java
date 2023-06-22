package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceException;

import classes.Telefone;

public class TelefoneDAO {

    private EntityManager em;

    public TelefoneDAO(EntityManager em) {
        this.em = em;
    }

    public boolean salvar(Telefone entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar o telefone.", e);
        }
    }

    public boolean atualizar(Telefone entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar o telefone.", e);
        }
    }

    public boolean remover(long id) {
        try {
            em.getTransaction().begin();
            Telefone entity = em.find(Telefone.class, id);
            if (entity == null) {
                throw new RuntimeException("Telefone não encontrado com o ID: " + id);
            }
            em.remove(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover o telefone.", e);
        }
    }

    public Telefone buscarID(long id) {
        try {
            Telefone entity = em.find(Telefone.class, id);
            return entity;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar o telefone pelo ID: " + id, e);
        }
    }

    public List<Telefone> buscarDDD(int ddd) {
        try {
            TypedQuery<Telefone> query = em.createQuery(
                "SELECT obj FROM Telefone obj WHERE obj.ddd = :ddd",
                Telefone.class
            );
            query.setParameter("ddd", ddd);
            return query.getResultList();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar os telefones pelo DDD: " + ddd, e);
        }
    }

    public List<Telefone> buscarTodos() {
        try {
            TypedQuery<Telefone> query = em.createQuery("SELECT obj FROM Telefone obj", Telefone.class);
            List<Telefone> telefones = query.getResultList();
            return telefones;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar todos os telefones.", e);
        }
    }
}
