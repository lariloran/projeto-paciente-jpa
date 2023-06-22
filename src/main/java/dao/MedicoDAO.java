package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}  
	}

	public boolean atualizar(Medico entity) {
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
			Medico entity = em.find(Medico.class, id);
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

	public Medico buscarID(long id) {
		try {
			Medico entity = em.find(Medico.class, id);
			return entity;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Medico> buscarTodos() {
		try {
			TypedQuery<Medico> query = em.createQuery("SELECT obj FROM Medico obj", Medico.class);
			List<Medico> Medicos = query.getResultList();
			return Medicos;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return null;
		} 
	}
}
