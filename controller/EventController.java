package controller;

import model.Event;
import model.EventCategory;
import model.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EventController {
    private static final String ARQUIVO_DADOS = "events.data";
    
    private List<Event> eventos;
    private List<User> usuarios;
    private User usuarioAtual;
    
    public EventController() {
        this.eventos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        carregarDados();
    }
    
    public boolean cadastrarUsuario(String nome, String email, String telefone, int idade) {
        User novoUsuario = new User(nome, email, telefone, idade);
        if (usuarios.contains(novoUsuario)) {
            return false; // Usuário já existe
        }
        usuarios.add(novoUsuario);
        salvarDados();
        return true;
    }
    
    public User fazerLogin(String email) {
        for (User usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                usuarioAtual = usuario;
                return usuario;
            }
        }
        return null;
    }
    
    public User getUsuarioAtual() {
        return usuarioAtual;
    }
    
    public void fazerLogout() {
        usuarioAtual = null;
    }
    
    public boolean criarEvento(String nome, String endereco, EventCategory categoria,
                              LocalDateTime dataHora, String descricao) {
        Event novoEvento = new Event(nome, endereco, categoria, dataHora, descricao);
        if (eventos.contains(novoEvento)) {
            return false; // Evento já existe
        }
        eventos.add(novoEvento);
        salvarDados();
        return true;
    }
    
    public List<Event> obterTodosEventos() {
        return new ArrayList<>(eventos);
    }
    
    public List<Event> obterEventosProximos() {
        return eventos.stream()
                .filter(Event::ehProximo)
                .sorted(Comparator.comparing(Event::getDataHora))
                .collect(Collectors.toList());
    }
    
    public List<Event> obterEventosAtuais() {
        return eventos.stream()
                .filter(Event::estaAcontecendoAgora)
                .collect(Collectors.toList());
    }
    
    public List<Event> obterEventosPassados() {
        return eventos.stream()
                .filter(Event::jaOcorreu)
                .sorted(Comparator.comparing(Event::getDataHora).reversed())
                .collect(Collectors.toList());
    }
    
    public List<Event> obterEventosDoUsuario(User usuario) {
        return eventos.stream()
                .filter(evento -> evento.ehParticipante(usuario))
                .sorted(Comparator.comparing(Event::getDataHora))
                .collect(Collectors.toList());
    }
    
    public boolean participarEvento(Event evento, User usuario) {
        if (evento.ehParticipante(usuario)) {
            return false;
        }
        evento.adicionarParticipante(usuario);
        salvarDados();
        return true;
    }
    
    public boolean cancelarParticipacao(Event evento, User usuario) {
        if (!evento.ehParticipante(usuario)) {
            return false; 
        }
        evento.removerParticipante(usuario);
        salvarDados();
        return true;
    }
    
    public List<Event> buscarEventosPorCategoria(EventCategory categoria) {
        return eventos.stream()
                .filter(evento -> evento.getCategoria() == categoria)
                .sorted(Comparator.comparing(Event::getDataHora))
                .collect(Collectors.toList());
    }
    
    public List<Event> buscarEventosPorNome(String nome) {
        return eventos.stream()
                .filter(evento -> evento.getNome().toLowerCase().contains(nome.toLowerCase()))
                .sorted(Comparator.comparing(Event::getDataHora))
                .collect(Collectors.toList());
    }
    
    @SuppressWarnings("unchecked")
    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
            eventos = (List<Event>) ois.readObject();
            usuarios = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {

            eventos = new ArrayList<>();
            usuarios = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            eventos = new ArrayList<>();
            usuarios = new ArrayList<>();
        }
    }
    
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            oos.writeObject(eventos);
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}
