package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceException;

import classes.Prontuario;
import classes.Situacao;

public class ProntuarioDAO {

    private EntityManager em;

    public ProntuarioDAO(EntityManager em) {
        this.em = em;
    }

    public boolean salvar(Prontuario entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar o prontuário.", e);
        }
    }

    public boolean atualizar(Prontuario entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar o prontuário.", e);
        }
    }

    public boolean remover(long id) {
        try {
            em.getTransaction().begin();
            Prontuario entity = em.find(Prontuario.class, id);
            if (entity == null) {
                throw new RuntimeException("Prontuário não encontrado com o ID: " + id);
            }
            em.remove(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover o prontuário.", e);
        }
    }

    public Prontuario buscarID(long id) {
        try {
            Prontuario entity = em.find(Prontuario.class, id);
            return entity;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar o prontuário pelo ID: " + id, e);
        }
    }

    public List<Prontuario> buscarSituacao(Situacao situacao) {
        try {
            TypedQuery<Prontuario> query = em.createQuery(
                "SELECT obj FROM Prontuario obj WHERE obj.situacao = :situacao",
                Prontuario.class
            );
            query.setParameter("situacao", situacao);
            return query.getResultList();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar os prontuários pela situação: " + situacao, e);
        }
    }

    public List<Prontuario> buscarTodos() {
        try {
            TypedQuery<Prontuario> query = em.createQuery("SELECT obj FROM Prontuario obj", Prontuario.class);
            List<Prontuario> prontuarios = query.getResultList();
            return prontuarios;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar todos os prontuários.", e);
        }
    }
}
