package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import classes.Prontuario;

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
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}

	public boolean atualizar(Prontuario entity) {
		try {
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
			em.getTransaction().begin();
			Prontuario entity = em.find(Prontuario.class, id);
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

	public Prontuario buscarID(long id) {
		try {
			Prontuario entity = em.find(Prontuario.class, id);
			return entity;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Prontuario> buscarTodos() {
		try {
			TypedQuery<Prontuario> query = em.createQuery("SELECT obj FROM Prontuario obj", Prontuario.class);
			List<Prontuario> Prontuarios = query.getResultList();
			return Prontuarios;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return null;
		} 
	}
}
