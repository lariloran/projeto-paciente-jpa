package testes;

import util.JPAUtil;
import java.util.*;
import javax.persistence.*;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import classes.*;

public class TestesMapeamentos {
	   public static void main(String[] args) {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	        	/*Médico e Consulta Testes
	        	List<Consulta> consultas = new ArrayList();
	        	consultas.add(new Consulta(new Date(), new Date()));
	        	consultas.add(new Consulta(new Date(), new Date()));
	        	
		        Medico medico = new Medico("123", "Oftalmo");
		        medico.setConsultas(consultas);
		        for (Consulta consulta : consultas) {
					consulta.setMedico(medico);
				}
		        
		        em.getTransaction().begin();
		        em.persist(medico);
		        System.out.println("Medico salvo com sucesso!");
		        em.getTransaction().commit();*/
	        	
	        	
	          
	        	List<Consulta> consultas = new ArrayList();
	        	consultas.add(new Consulta(new Date(), new Date()));
	        	consultas.add(new Consulta(new Date(), new Date())); 
	        	

	        	List<Consulta> consultas2 = new ArrayList();
	        	consultas2.add(new Consulta(new Date(), new Date()));
	        	consultas2.add(new Consulta(new Date(), new Date()));
	        	
	        	 Medico medico = new Medico("123", "Oftalmo");
			        medico.getConsultas().addAll(consultas);
			        medico.getConsultas().addAll(consultas2);
			        
			        for (Consulta consulta : consultas) {
						consulta.setMedico(medico);
					}
			        
			        for (Consulta consulta : consultas2) {
									consulta.setMedico(medico);
								}
	        	List<String> emails = new ArrayList();
	        	emails.add("abc@gmail");
	        	emails.add("abc2@gmail");
	        	
	        	List<String> emails2 = new ArrayList();
	        	emails2.add("arererer@gmail");
	        	emails2.add("acvdccd@gmail");
	        	
	        	
	        	List<Telefone> telefone = new ArrayList();
	        	telefone.add(new Telefone(51,30225526));
	        	telefone.add(new Telefone(51,25894178));
	        	
	        	
	        	List<Telefone> telefone2 = new ArrayList();
	        	telefone2.add(new Telefone(55,63958741));
	        	telefone2.add(new Telefone(55,15424836));
	        	
		        Paciente paciente = new Paciente("Doctor", "043", new Date(), emails,consultas);
		        paciente.setNome("Alberto Rodrigues");
		        paciente.setEndereco("Rua Pereira de Souza 223");
		        paciente.setTelefones(telefone);
		        
		        Paciente paciente2 = new Paciente("Unimed", "896", new Date(), emails2,consultas2);
		        paciente2.setNome("Gabriely Rodrigues");
		        paciente2.setEndereco("Rua Tenente Alpion de Souza 115");
		        paciente2.setTelefones(telefone2);
		        
		        for (Consulta consulta : consultas) {
					consulta.setPaciente(paciente);
				}
		        
		        for (Consulta consulta : consultas2) {
							consulta.setPaciente(paciente2);
						}
			
	
		        Prontuario prontuario = new Prontuario(new Date(),"Cirurgia Geral", paciente,Situacao.HOSPITALIZADO);

		        Prontuario prontuario2 = new Prontuario(new Date(),"Apendicite", paciente2,Situacao.NORMAL);
		
		        
		        em.getTransaction().begin();
		        em.persist(medico);
		        em.persist(paciente);
		        em.persist(prontuario);
		        em.persist(paciente2);
		        em.persist(prontuario2);
		        System.out.println("Medico e Paciente salvos com sucesso!");
		        em.getTransaction().commit();
		        
	        }catch(RuntimeException e) {
	        	 if (em.getTransaction().isActive()) {
	                 em.getTransaction().rollback();
	             } 
	         } finally {
	             em.close();
	             JPAUtil.close();
	         }
	    }
}
