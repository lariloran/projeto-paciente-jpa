package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}

	public boolean atualizar(Paciente entity) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		} 
	}

	public boolean remover(long id) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			Paciente entity = em.find(Paciente.class, id);
			em.remove(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		} 
	}

	public Paciente buscarID(int id) {
		try {
			em = JPAUtil.getEntityManager();
			Paciente entity = em.find(Paciente.class, id);
			return entity;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Paciente> buscarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<Paciente> query = em.createQuery("SELECT obj FROM Paciente obj", Paciente.class);
			List<Paciente> Pacientes = query.getResultList();
			return Pacientes;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return null;
		} 
	}
}
