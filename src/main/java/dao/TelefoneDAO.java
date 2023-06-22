package dao;

import util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}

	public boolean atualizar(Telefone entity) {
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
			Telefone entity = em.find(Telefone.class, id);
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

	public Telefone buscarID(long id) {
		try {
			Telefone entity = em.find(Telefone.class, id);
			return entity;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Telefone> buscarTodos() {
		try {
			TypedQuery<Telefone> query = em.createQuery("SELECT obj FROM Telefone obj", Telefone.class);
			List<Telefone> Telefones = query.getResultList();
			return Telefones;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return null;
		} 
	}
}
