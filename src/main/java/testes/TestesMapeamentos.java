package testes;

import java.text.SimpleDateFormat;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import classes.*;
import dao.*;
import util.JPAUtil;

public class TestesMapeamentos {
	
    public static void main(String[] args) {
    	  EntityManager em = JPAUtil.getEntityManager();

        try {               
             //CriaObjetos(em);//Após rodar uma vez a persistencia comente 
        	                  //para criar mais registros iguais
        	
           TestaOperacoesDao(em);        	

        } catch (NullPointerException e) {
            System.err.println("Ocorreu um NullPointerException: " + e.getMessage());
        } catch (PersistenceException e) {
            System.err.println("Ocorreu um erro de persistência: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Ocorreu uma RuntimeException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocorreu uma exceção genérica: " + e.getMessage());
        }
    }
     
    private static String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    private static String formatarHorario(Date horario) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(horario);
    }
    
    private static void TestaOperacoesDao(EntityManager em) {      
    	//ListarTodos(em);
		//BuscarPorId(em); 
        //BuscaEspecializada(em);
		//Atualizar(em);
        Remover(em);
    }
    
    private static void CriaObjetos(EntityManager em) {          
    	/*** CRIACAO DE MEDICOS***/
    	MedicoDAO medicoDao = new MedicoDAO(em);
        List<Medico> medicos = CriaMedicos();
        
    	 for (Medico medico : medicos) {
         	  if(medicoDao.salvar(medico))
                   System.out.println("Medico salvo com sucesso!");
   		}
    	

         /*** CRIACAO DE PACIENTES***/
        List<Consulta> consultas = CriaConsultas();
        List<String> emails = CriaEmails();
        List<Telefone> telefones = CriaTelefones();
        
        PacienteDAO pacienteDao = new PacienteDAO(em);
        
        Paciente paciente = new Paciente("DoctorClin", "043840520-89", new Date(), emails, consultas);
        paciente.setNome("Alberto Rodrigues");
        paciente.setEndereco("Rua Pereira de Souza, 223 - Bairro Amigos, POA/RS");
        paciente.setTelefones(telefones);
        
        if(pacienteDao.salvar(paciente))
            System.out.println("Paciente 1 salvo com sucesso!");
        
        for (Consulta consulta : consultas) {
            consulta.setPaciente(paciente);
            consulta.setMedico(medicos.get(0));
        }
       
        /******/
        
        List<Consulta> consultas2 = CriaConsultas();
        List<String> emails2 = CriaEmails();
        List<Telefone> telefones2 = CriaTelefones();
        
        Paciente paciente2 = new Paciente("Unimed", "124874596-74", new Date(), emails2, consultas2);
        paciente2.setNome("Maria de Souza");
        paciente2.setEndereco("Rua Alice Datafiles, 451 - Bairro Navegantes, POA/RS");
        paciente2.setTelefones(telefones2);
        
        
        for (Consulta consulta : consultas2) {
            consulta.setPaciente(paciente2);
            consulta.setMedico(medicos.get(1));
        }
        
        if(pacienteDao.salvar(paciente2))
            System.out.println("Paciente 2 salvo com sucesso!");


        
        
        
        
        /*** CRIACAO DE PRONTUARIOS ***/
        ProntuarioDAO prontuarioDao = new ProntuarioDAO(em);
        
        Prontuario prontuario = new Prontuario(new Date(),"Ressonância Magnética", paciente, Situacao.HOSPITALIZADO);

        if(prontuarioDao.salvar(prontuario))
            System.out.println("Prontuario 1 salvo com sucesso!");
        
        Prontuario prontuario2 = new Prontuario(new Date(), "Apendicite", paciente2, Situacao.NORMAL);
        
        if(prontuarioDao.salvar(prontuario2))
            System.out.println("Prontuario 2 salvo com sucesso!");
        
        /******/
         
    }
    
    private static List<Consulta> CriaConsultas() {
        List<Consulta> consultas = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            consultas.add(new Consulta(new Date(), new Date()));
        }
        
        return consultas;
    }
    private static List<Medico> CriaMedicos() {
    	List<Medico> medicos = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String crm = "CRM" + (i + 1); 
            String especialidade = "Especialidade" + (i + 1); 

            Medico medico = new Medico(crm, especialidade);
            medicos.add(medico);
        }
        
        return medicos;
    }
    private static List<String> CriaEmails(){
    	 List<String> emails = new ArrayList<>();

         for (int i = 0; i < 5; i++) {
             String email = "abc" + (i + 1) + "@gmail.com";
             emails.add(email);
         }
         return emails;
    }
    private static List<Telefone> CriaTelefones(){
    	List<Telefone> telefones = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Telefone telefone = new Telefone();
            telefone.setDdd(51);
            telefone.setNumero(30225526 + i);
            telefones.add(telefone);
        }
        
        return telefones;
    }

    private static void ListarTodos(EntityManager em) {
    	PessoaDAO pessoaDao = new PessoaDAO(em);
        ConsultaDAO consultaDao = new ConsultaDAO(em);
        TelefoneDAO telefoneDao = new TelefoneDAO(em);
        ProntuarioDAO prontuarioDao = new ProntuarioDAO(em);
        PacienteDAO pacienteDao = new PacienteDAO(em);
        MedicoDAO medicoDao = new MedicoDAO(em);
        
      System.out.println("\n\n ************ LISTAR TODOS ************\n\n");
	
      System.out.println("\n\n -- PESSOAS --\n\n");
     
      List<Pessoa> pessoaRetorno = pessoaDao.buscarTodos();  
      for (Pessoa pessoa : pessoaRetorno) {
    	  StringBuilder stringBuilder = new StringBuilder();
          stringBuilder
          .append("idPessoa= ").append(pessoa.getIdPessoa() + "\n")
                       .append("Nome =").append(pessoa.getNome() + "\n")
                       .append("Endereco =").append(pessoa.getEndereco() + "\n");
          List<Telefone> telefones = pessoa.getTelefones();
          if (telefones != null) {
              for (int i = 0; i < telefones.size(); i++) {
                  Telefone telefone = telefones.get(i);
                  stringBuilder.append(telefone  + "\n");
              }
          }
          System.out.println(stringBuilder.toString()); 
	}
     
      
		
      System.out.println("\n\n -- CONSULTAS --\n\n");
      List<Consulta> consultaRetorno = consultaDao.buscarTodos();
      for (Consulta consulta : consultaRetorno) {
    	  StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2
                  .append("idConsulta= ").append(consulta.getIdConsulta() +"\n")
                  .append("Data = ").append(formatarData(consulta.getData()) +"\n")
                  .append("Horario = ").append(formatarHorario(consulta.getHorario())).append("\n")
                  .append("Medico = ").append(consulta.getMedico().getCrm()).append("\n")
                  .append("Paciente = ").append(consulta.getPaciente().getNome());

          System.out.println(stringBuilder2.toString());
	}
	
      System.out.println("\n\n -- MEDICOS --\n\n");
      List<Medico> medicoRetorno = medicoDao.buscarTodos();
      for (Medico medico : medicoRetorno) {
    	  StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3
              .append("idMedico= ").append(medico.getIdMedico()).append("\n")
              .append("CRM = ").append(medico.getCrm()).append("\n")
              .append("Especialidade = ").append(medico.getEspecialidade()).append("\n")
              .append("\nConsultas:\n");
          List<Consulta> consultas = medico.getConsultas();
          for (Consulta consulta : consultas) {
              stringBuilder3
                  .append("idConsulta = ").append(consulta.getIdConsulta()).append("\n")
                  .append("Data = ").append(formatarData(consulta.getData())).append("\n")
                  .append("Horario = ").append(formatarHorario(consulta.getHorario())).append("\n")
                  .append("Paciente = ").append(consulta.getPaciente().getNome()).append("\n\n");
          }
          System.out.println(stringBuilder3.toString());
	}
    
      System.out.println("\n\n -- PACIENTES --\n\n");
      List<Paciente> pacienteRetorno = pacienteDao.buscarTodos();
		for (Paciente paciente : pacienteRetorno) {
		    StringBuilder stringBuilder4 = new StringBuilder();
		    stringBuilder4
		        .append("Convenio: ").append(paciente.getConvenio()).append("\n")
		        .append("CPF: ").append(paciente.getCpf()).append("\n")
		        .append("Data de Nascimento: ").append(formatarData(paciente.getDataNascimento())).append("\n")
		        .append("Emails: ");
		
		    List<String> emailsPaciente = paciente.getEmails();
		    for (String email : emailsPaciente) {
		        stringBuilder4.append(email).append(", ");
		    }
		
		    stringBuilder4.append("\n\nConsultas: \n\n");
		
		    List<Consulta> consultasPaciente = paciente.getConsultas();
		    for (Consulta consulta : consultasPaciente) {
		        stringBuilder4
		            .append("idConsulta: ").append(consulta.getIdConsulta()).append("\n")
		            .append("Data: ").append(formatarData(consulta.getData())).append("\n")
		            .append("Horario: ").append(formatarHorario(consulta.getHorario())).append("\n")
		            .append("Medico: ").append(consulta.getMedico().getCrm()).append("\n\n");
		    }
		
		    System.out.println(stringBuilder4.toString());
		}
  
  
		
	      System.out.println("\n\n -- PRONTUARIOS --\n\n");

		 List<Prontuario> prontuarioRetorno = prontuarioDao.buscarTodos();
		 for (Prontuario prontuario : prontuarioRetorno) {
		  StringBuilder stringBuilder5 = new StringBuilder();
	      stringBuilder5
	          .append("idProntuario = ").append(prontuario.getIdProntuario() + "\n")
	          .append("Data = ").append(formatarData(prontuario.getData()) + "\n")
	          .append("Descrição = ").append(prontuario.getDescricao() + "\n")
	          .append("Paciente = ").append(prontuario.getPaciente().getNome() + "\n")
	          .append("Situação = ").append(prontuario.getSituacao().toString()+ "\n");
	
	      System.out.println(stringBuilder5.toString());
		 }
	    
	      System.out.println("\n\n -- TELEFONES --\n\n");

		 List<Telefone> telefoneRetorno = telefoneDao.buscarTodos();
		 for (Telefone telefone : telefoneRetorno) {
		  StringBuilder stringBuilder6 = new StringBuilder();
	      stringBuilder6
	          .append("idTelefone = ").append(telefone.getIdTelefone() + "\n")
	          .append("DDD = ").append(telefone.getDdd() + "\n")
	          .append("Número = ").append(telefone.getNumero() + "\n");
	
	      System.out.println(stringBuilder6.toString());
		 }
    }
    
    private static void BuscarPorId(EntityManager em) {
    	PessoaDAO pessoaDao = new PessoaDAO(em);
        ConsultaDAO consultaDao = new ConsultaDAO(em);
        TelefoneDAO telefoneDao = new TelefoneDAO(em);
        ProntuarioDAO prontuarioDao = new ProntuarioDAO(em);
        PacienteDAO pacienteDao = new PacienteDAO(em);
        MedicoDAO medicoDao = new MedicoDAO(em);
        
    System.out.println("\n\n ************ BUSCAR POR ID ************\n\n");
    	
        System.out.println("\n\n -- PESSOA --\n\n");
	      Pessoa pessoaRetorno = pessoaDao.buscarID(1L);            
	      StringBuilder stringBuilder = new StringBuilder();
	      stringBuilder
	      .append("idPessoa= ").append(pessoaRetorno.getIdPessoa() + "\n")
	                   .append("Nome =").append(pessoaRetorno.getNome() + "\n")
	                   .append("Endereco =").append(pessoaRetorno.getEndereco() + "\n");
	      List<Telefone> telefones = pessoaRetorno.getTelefones();
	      if (telefones != null) {
	          for (int i = 0; i < telefones.size(); i++) {
	              Telefone telefone = telefones.get(i);
	              stringBuilder.append(telefone  + "\n");
	          }
	      }
	      System.out.println(stringBuilder.toString()); 
      
	   System.out.println("\n\n -- CONSULTA --\n\n");
	      Consulta consultaRetorno = consultaDao.buscarID(1L);
	
	      StringBuilder stringBuilder2 = new StringBuilder();
	      stringBuilder2
	              .append("idConsulta= ").append(consultaRetorno.getIdConsulta() +"\n")
	              .append("Data = ").append(formatarData(consultaRetorno.getData()) +"\n")
	              .append("Horario = ").append(formatarHorario(consultaRetorno.getHorario())).append("\n")
	              .append("Medico = ").append(consultaRetorno.getMedico().getCrm()).append("\n")
	              .append("Paciente = ").append(consultaRetorno.getPaciente().getNome());
	
	      System.out.println(stringBuilder2.toString());
      

		System.out.println("\n\n -- MEDICO --\n\n");
	      Medico medicoRetorno = medicoDao.buscarID(1L);
		      StringBuilder stringBuilder3 = new StringBuilder();
		      stringBuilder3
		          .append("idMedico= ").append(medicoRetorno.getIdMedico()).append("\n")
		          .append("CRM = ").append(medicoRetorno.getCrm()).append("\n")
		          .append("Especialidade = ").append(medicoRetorno.getEspecialidade()).append("\n")
		          .append("\nConsultas:\n");
		
		      List<Consulta> consultas = medicoRetorno.getConsultas();
		      for (Consulta consulta : consultas) {
		          stringBuilder3
		              .append("idConsulta = ").append(consulta.getIdConsulta()).append("\n")
		              .append("Data = ").append(formatarData(consulta.getData())).append("\n")
		              .append("Horario = ").append(formatarHorario(consulta.getHorario())).append("\n")
		              .append("Paciente = ").append(consulta.getPaciente().getNome()).append("\n\n");
		      }
		
		      System.out.println(stringBuilder3.toString());


		System.out.println("\n\n -- PACIENTE --\n\n");
	      Paciente pacienteRetorno = pacienteDao.buscarID(1L);
	      StringBuilder stringBuilder4 = new StringBuilder();
	      stringBuilder4
	      	  .append("idPessoaPaciente: ").append(pacienteRetorno.getIdPessoa()).append("\n")
	          .append("Convenio: ").append(pacienteRetorno.getConvenio()).append("\n")
	          .append("CPF: ").append(pacienteRetorno.getCpf()).append("\n")
	          .append("Data de Nascimento: ").append(formatarData(pacienteRetorno.getDataNascimento())).append("\n")
	          .append("Emails: ");
	
	      List<String> emailsPaciente = pacienteRetorno.getEmails();
	      for (String email : emailsPaciente) {
	          stringBuilder4.append(email).append(", ");
	      }
	
	      stringBuilder4.append("\n\nConsultas: \n\n");
	
	      List<Consulta> consultasPaciente = consultaRetorno.getPaciente().getConsultas();
	      for (Consulta consulta : consultasPaciente) {
	          stringBuilder4
	              .append("idConsulta: ").append(consulta.getIdConsulta()).append("\n")
	              .append("Data: ").append(formatarData(consulta.getData())).append("\n")
	              .append("Horario: ").append(formatarHorario(consulta.getHorario())).append("\n")
	              .append("Medico: ").append(consulta.getMedico().getCrm()).append("\n\n");
	      }
	
	      System.out.println(stringBuilder4.toString());

		System.out.println("\n\n -- PRONTUARIO --\n\n");
	      Prontuario prontuarioRetorno = prontuarioDao.buscarID(1L);
	      StringBuilder stringBuilder5 = new StringBuilder();
	      stringBuilder5
	          .append("idProntuario = ").append(prontuarioRetorno.getIdProntuario() + "\n")
	          .append("Data = ").append(formatarData(prontuarioRetorno.getData()) + "\n")
	          .append("Descrição = ").append(prontuarioRetorno.getDescricao() + "\n")
	          .append("Paciente = ").append(prontuarioRetorno.getPaciente().getNome() + "\n")
	          .append("Situação = ").append(prontuarioRetorno.getSituacao().toString());
	
	      System.out.println(stringBuilder5.toString());
    }
    private static void Remover(EntityManager em) {
    	PessoaDAO pessoaDao = new PessoaDAO(em);
        ConsultaDAO consultaDao = new ConsultaDAO(em);
        TelefoneDAO telefoneDao = new TelefoneDAO(em);
        ProntuarioDAO prontuarioDao = new ProntuarioDAO(em);
        PacienteDAO pacienteDao = new PacienteDAO(em);
        MedicoDAO medicoDao = new MedicoDAO(em);
        
        System.out.println("\n\n ************ REMOVER ************\n\n");
    	//Remove telefone
        if(telefoneDao.remover(1L))
        	System.out.println("Telefone 1 removido com sucessor!");
        
        //Remove prontuario
        if(prontuarioDao.remover(1L))
        	System.out.println("Prontuario 1 removido com sucessor!");
        
      //Remove consulta
        if(consultaDao.remover(1L))
        	System.out.println("Consulta  1 removido com sucessor!");
       
        //Remove paciente
        if(pacienteDao.remover(1L))
        	System.out.println("Paciente  1 removido com sucessor!");
        
        //remove medico
        if(medicoDao.remover(1L))
        	System.out.println("Medico 1 removido com sucessor!");
    }
    
    private static void Atualizar(EntityManager em) {
        PacienteDAO pacienteDao = new PacienteDAO(em);
        MedicoDAO medicoDao = new MedicoDAO(em);
        
        System.out.println("\n\n ************ ATUALIZAR ************\n\n");
        //Atualizando médico
        Medico medico = new Medico("CRM11", "Pediatra");
        medico.setIdMedico(2L);
        if(medicoDao.atualizar(medico))
            System.out.println("Medico 2 foi atualizado com sucesso!");
        	
       //Atualizando pessoa
  	    Paciente paciente = new Paciente();
  	    paciente.setNome("Pedro Maria da Sila");
  	    paciente.setIdPessoa(1L);
  	    paciente.setTelefones(pacienteDao.buscarID(1).getTelefones());
  	    paciente.setEmails(pacienteDao.buscarID(1).getEmails());
  	    paciente.setCpf(pacienteDao.buscarID(1).getCpf());
  	    paciente.setDataNascimento(new Date());
  	    paciente.setConvenio("IPE");
        paciente.setEndereco("Rua Alice Datafiles, 451 - Bairro Navegantes, POA/RS");
  	
  	    if(pacienteDao.atualizar(paciente))
  	        System.out.println("Paciente 1 foi atualizado com sucesso!");

    }
    
    private static void BuscaEspecializada(EntityManager em) {
    	PessoaDAO pessoaDao = new PessoaDAO(em);
        ConsultaDAO consultaDao = new ConsultaDAO(em);
        TelefoneDAO telefoneDao = new TelefoneDAO(em);
        ProntuarioDAO prontuarioDao = new ProntuarioDAO(em);
        PacienteDAO pacienteDao = new PacienteDAO(em);
        MedicoDAO medicoDao = new MedicoDAO(em);
        
        System.out.println("\n\n ************ BUSCA ESPECIALIZADA ************\n\n");
        
    	//Busca consultas passando o CRM do médico
        System.out.println("\n\n -- Buscar consultar por CRM do médico -- \n\n");
        List<Consulta> consultas = consultaDao.buscarConsultaCrmMedico("CRM4");
        System.out.println("CRM: CRM4");
        for (Consulta consulta : consultas) {
        	System.out.println("ID Consulta: " +consulta.getIdConsulta() + "\n");
  		}
        
        //Busca médico passando seu CRM
        System.out.println("\n\n -- Buscar Médico por CRM do médico -- \n\n");
        Medico medico = medicoDao.buscarCrm("CRM5");
        System.out.println("CRM: CRM5");
        System.out.println("Especialidade: " + medico.getEspecialidade() + "\n");
        
        //Busca paciente passando seu CPF
        System.out.println("\n\n -- Buscar Paciente por CPF -- \n\n");
        System.out.println("CPF: 124874596-74");
        Paciente paciente = pacienteDao.buscarCpf("124874596-74");
        System.out.println("Nome paciente: " + paciente.getNome() + "\n");
        
        //Busca pessoa por nome
        System.out.println("\n\n -- Buscar pessoa por nome completo -- \n\n");
        System.out.println("Pessoa: Alberto Rodrigues");
        Pessoa pessoa = pessoaDao.buscarNome("Alberto Rodrigues");
        if(pessoa != null)
        	System.out.println("Endereço: " + pessoa.getEndereco() + "\n");
        
        System.out.println("\n\n -- Buscar prontuários por Situação -- \n\n");
        System.out.println("Situação: HOSPITALIZADO\n");

        List<Prontuario> prontuarios = prontuarioDao.buscarSituacao(Situacao.HOSPITALIZADO);
        
        for (Prontuario prontuario : prontuarios) {
        	System.out.println("Descrição: " + prontuario.getDescricao() + "\n");
  		}
        
        System.out.println("\n\n -- Buscar Numeors por DDD -- \n\n");
        System.out.println("DDD: 51\n");
        List<Telefone> telefones = telefoneDao.buscarDDD(51);
        
        for (Telefone telefone : telefones) {
        	System.out.println(telefone.getNumero());
  		}
        
    }
}


