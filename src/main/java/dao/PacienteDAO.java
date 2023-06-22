package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceException;
import javax.persistence.NoResultException;

import classes.Paciente;

public class PacienteDAO {

    private EntityManager em;

    public PacienteDAO(EntityManager em) {
        this.em = em;
    }

    public boolean salvar(Paciente entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar o paciente.", e);
        }
    }

    public boolean atualizar(Paciente entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar o paciente.", e);
        }
    }

    public boolean remover(long id) {
        try {
            em.getTransaction().begin();
            Paciente entity = em.find(Paciente.class, id);
            if (entity == null) {
                throw new RuntimeException("Paciente não encontrado com o ID: " + id);
            }
            em.remove(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover o paciente.", e);
        }
    }

    public Paciente buscarID(long id) {
        try {
            Paciente entity = em.find(Paciente.class, id);
            return entity;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar o paciente pelo ID: " + id, e);
        }
    }

    public Paciente buscarCpf(String cpf) {
        try {
            TypedQuery<Paciente> query = em.createQuery(
                "SELECT obj FROM Paciente obj WHERE obj.cpf = :cpf",
                Paciente.class
            );
            query.setParameter("cpf", cpf);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar o paciente pelo CPF: " + cpf, e);
        }
    }

    public List<Paciente> buscarTodos() {
        try {
            TypedQuery<Paciente> query = em.createQuery("SELECT obj FROM Paciente obj", Paciente.class);
            List<Paciente> pacientes = query.getResultList();
            return pacientes;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao buscar todos os pacientes.", e);
        }
    }
}
