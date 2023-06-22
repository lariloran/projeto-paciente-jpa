package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceException;
import javax.persistence.NoResultException;

import classes.Pessoa;

public class PessoaDAO {

    private EntityManager em;

    public PessoaDAO(EntityManager em) {
        this.em = em;
    }

    public boolean salvar(Pessoa entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar a pessoa.", e);
        }
    }

    public boolean atualizar(Pessoa entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar a pessoa.", e);
        }
    }

    public boolean remover(long id) {
        try {
            em.getTransaction().begin();
            Pessoa entity = em.find(Pessoa.class, id);
            if (entity == null) {
                throw new RuntimeException("Pessoa não encontrada com o ID: " + id);
            }
            em.remove(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover a pessoa.", e);
        }
    }

    public Pessoa buscarID(long id) {
        try {
            Pessoa entity = em.find(Pessoa.class, id);
            return entity;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar a pessoa pelo ID: " + id, e);
        }
    }

    public Pessoa buscarNome(String nome) {
        try {
            TypedQuery<Pessoa> query = em.createQuery(
                "SELECT obj FROM Pessoa obj WHERE obj.nome = :nome",
                Pessoa.class
            );
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar a pessoa pelo nome: " + nome, e);
        }
    }

    public List<Pessoa> buscarTodos() {
        try {
            TypedQuery<Pessoa> query = em.createQuery("SELECT obj FROM Pessoa obj", Pessoa.class);
            List<Pessoa> pessoas = query.getResultList();
            return pessoas;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar todas as pessoas.", e);
        }
    }
}
