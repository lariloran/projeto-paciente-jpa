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
            MedicoDAO medicoDao = new MedicoDAO(em);
            PacienteDAO pacienteDao = new PacienteDAO(em);
            ProntuarioDAO prontuarioDao = new ProntuarioDAO(em);
            PessoaDAO pessoaDao = new PessoaDAO(em);
            ConsultaDAO consultaDao = new ConsultaDAO(em);
          
            /****************Criação de objetos*****************/
            /*List<Consulta> consultas = new ArrayList<>();
            consultas.add(new Consulta(new Date(), new Date()));
            consultas.add(new Consulta(new Date(), new Date())); 
            

            List<Consulta> consultas2 = new ArrayList<>();
            consultas2.add(new Consulta(new Date(), new Date()));
            consultas2.add(new Consulta(new Date(), new Date()));
            
            Medico medico = new Medico("123", "Oftalmo");
            if(medicoDao.salvar(medico))
                System.out.println("Medico salvo com sucesso!");

            List<String> emails = new ArrayList<>();
            emails.add("abc@gmail");
            emails.add("abc2@gmail");
            
            List<Telefone> telefone = new ArrayList<>();
            telefone.add(new Telefone(51, 30225526));
            telefone.add(new Telefone(51, 25894178));
            
            Paciente paciente = new Paciente("Doctor", "043", new Date(), emails, consultas);
            paciente.setNome("Alberto Rodrigues");
            paciente.setEndereco("Rua Pereira de Souza 223");
            paciente.setTelefones(telefone);
            
            if(pacienteDao.salvar(paciente))
                System.out.println("Paciente 1 salvo com sucesso!");
            
            Prontuario prontuario = new Prontuario(new Date(),"Cirurgia Geral", paciente, Situacao.HOSPITALIZADO);

            if(prontuarioDao.salvar(prontuario))
                System.out.println("Prontuario salvo com sucesso!");
            
            for (Consulta consulta : consultas) {
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);
            }
            
         
            
            List<String> emails2 = new ArrayList<>();
            emails2.add("arererer@gmail");
            emails2.add("acvdccd@gmail");

            List<Telefone> telefone2 = new ArrayList<>();
            telefone2.add(new Telefone(55, 63958741));
            telefone2.add(new Telefone(55, 15424836));

            Paciente paciente2 = new Paciente("Unimed", "896", new Date(), emails2, consultas2);
            paciente2.setNome("Gabriely Rodrigues");
            paciente2.setEndereco("Rua Tenente Alpion de Souza 115");
            paciente2.setTelefones(telefone2);

            for (Consulta consulta : consultas2) {
                consulta.setPaciente(paciente2);
                consulta.setMedico(medico);
            }
            
            if(pacienteDao.salvar(paciente2))
                System.out.println("Paciente 2 salvo com sucesso!");
            
            Prontuario prontuario2 = new Prontuario(new Date(), "Apendicite", paciente2, Situacao.NORMAL);
            
            if(prontuarioDao.salvar(prontuario2))
                System.out.println("Prontuario 2 salvo com sucesso!");
            */
            /****************Fim criação*********************/
            
//            //Lista Pessoa
//            System.out.println("\nLISTAR TODOS");
//    		for (Pessoa p : pessoaDao.buscarTodos())
//    			System.out.println(p.toString());
//    		
//            //Lista Consulta
//            System.out.println("\nLISTAR TODOS");
//    		for (Consulta p : consultaDao.buscarTodos())
//    			System.out.println(p.toString());
//    		
//
//            //Lista Medico
//            System.out.println("\nLISTAR TODOS");
//    		for (Medico p : medicoDao.buscarTodos())
//    			System.out.println(p.toString());
//
//            //Lista Paciente
//            System.out.println("\nLISTAR TODOS");
//    		for (Paciente p : pacienteDao.buscarTodos())
//    			System.out.println(p.toString());
//    		
//
//            //Lista Prontuario
//            System.out.println("\nLISTAR TODOS");
//    		for (Prontuario p : prontuarioDao.buscarTodos())
//    			System.out.println(p.toString());
//    		
//
//            //Lista Telefone
//            System.out.println("\nLISTAR TODOS");
//    		for (Telefone p : telefoneDao.buscarTodos())
//    			System.out.println(p.toString());
            
            /****************/
    		
            /**BUSCA POR ID***
            //Lista Pessoa
            System.out.println("\nBUSCAR POR ID");
            
            Pessoa pessoaRetorno = pessoaDao.buscarID(1L);            
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
            .append("\nPESSOA\n")
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
            
            Consulta consultaRetorno = consultaDao.buscarID(1L);

            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2
            		.append("\nCONSULTA\n")
                    .append("idConsulta= ").append(consultaRetorno.getIdConsulta() +"\n")
                    .append("Data = ").append(formatarData(consultaRetorno.getData()) +"\n")
                    .append("Horario = ").append(formatarHorario(consultaRetorno.getHorario())).append("\n")
                    .append("Medico = ").append(consultaRetorno.getMedico().getCrm()).append("\n")
                    .append("Paciente = ").append(consultaRetorno.getPaciente().getNome());

            System.out.println(stringBuilder2.toString());
            

            Medico medicoRetorno = medicoDao.buscarID(1L);

            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3
                .append("\nMÉDICO\n")
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


            Paciente pacienteRetorno = pacienteDao.buscarID(1L);

            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4
                .append("\nPACIENTE\n")
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


            Prontuario prontuarioRetorno = prontuarioDao.buscarID(1L);

            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5
                .append("\nPRONTUÁRIO\n\n")
                .append("idProntuario = ").append(prontuarioRetorno.getIdProntuario() + "\n")
                .append("Data = ").append(formatarData(prontuarioRetorno.getData()) + "\n")
                .append("Descrição = ").append(prontuarioRetorno.getDescricao() + "\n")
                .append("Paciente = ").append(prontuarioRetorno.getPaciente().getNome() + "\n")
                .append("Situação = ").append(prontuarioRetorno.getSituacao().toString());

            System.out.println(stringBuilder5.toString());
*/

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
}


