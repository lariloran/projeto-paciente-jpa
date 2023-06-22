package testes;

import java.util.*;

import javax.persistence.EntityManager;

import classes.*;
import dao.*;
import util.JPAUtil;

public class TestesMapeamentos {
	   public static void main(String[] args) {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {  
		        MedicoDAO medicoDao = new MedicoDAO(em);
		        PacienteDAO pacienteDao = new PacienteDAO(em);
		        ProntuarioDAO prontuarioDao = new ProntuarioDAO(em);
		        
	        	List<Consulta> consultas = new ArrayList<>();
	        	consultas.add(new Consulta(new Date(), new Date()));
	        	consultas.add(new Consulta(new Date(), new Date())); 
	        	

	        	List<Consulta> consultas2 = new ArrayList<>();
	        	consultas2.add(new Consulta(new Date(), new Date()));
	        	consultas2.add(new Consulta(new Date(), new Date()));
	        	
	        	 Medico medico = new Medico("123", "Oftalmo");
			        if(medicoDao.salvar(medico))
			        	System.out.println("Medico salvo com sucesso!");
//			       
			 
	        	List<String> emails = new ArrayList<>();
	        	emails.add("abc@gmail");
	        	emails.add("abc2@gmail");
	        	
	        	List<Telefone> telefone = new ArrayList<>();
	        	telefone.add(new Telefone(51,30225526));
	        	telefone.add(new Telefone(51,25894178));
	        	
		        Paciente paciente = new Paciente("Doctor", "043", new Date(), emails,consultas);
		        paciente.setNome("Alberto Rodrigues");
		        paciente.setEndereco("Rua Pereira de Souza 223");
		        paciente.setTelefones(telefone);
		        
		        if(pacienteDao.salvar(paciente))
		        	System.out.println("Paciente 1 salvo com sucesso!");
		        
		        Prontuario prontuario = new Prontuario(new Date(),"Cirurgia Geral", paciente,Situacao.HOSPITALIZADO);

		        if(prontuarioDao.salvar(prontuario))
		        	System.out.println("Prontuario salvo com sucesso!");
		        
		        for (Consulta consulta : consultas) {
		            consulta.setPaciente(paciente);
		            consulta.setMedico(medico);
		        }
		        /* ******************************************/
		        
	        	List<String> emails2 = new ArrayList<>();
	        	emails2.add("arererer@gmail");
	        	emails2.add("acvdccd@gmail");

	        	List<Telefone> telefone2 = new ArrayList<>();
	        	telefone2.add(new Telefone(55,63958741));
	        	telefone2.add(new Telefone(55,15424836));

		        Paciente paciente2 = new Paciente("Unimed", "896", new Date(), emails2,consultas2);
		        paciente2.setNome("Gabriely Rodrigues");
		        paciente2.setEndereco("Rua Tenente Alpion de Souza 115");
		        paciente2.setTelefones(telefone2);

		        if(pacienteDao.salvar(paciente2))
		        	System.out.println("Paciente 2 salvo com sucesso!");
		        
		        Prontuario prontuario2 = new Prontuario(new Date(),"Apendicite", paciente2,Situacao.NORMAL);
		        
		        if(prontuarioDao.salvar(prontuario2))
		        	System.out.println("Prontuario 2 salvo com sucesso!");

		        for (Consulta consulta : consultas2) {
		            consulta.setPaciente(paciente2);
		            consulta.setMedico(medico);
		        }
       
	        }catch(RuntimeException e) {

}
}
}