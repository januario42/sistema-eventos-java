package view;

import controller.EventController;
import model.Event;
import model.EventCategory;
import model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;
    private final EventController controlador;
    private final DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public ConsoleView(EventController controlador) {
        this.scanner = new Scanner(System.in);
        this.controlador = controlador;
    }
    
    public void iniciar() {
        mostrarBoasVindas();
        
        while (true) {
            if (controlador.getUsuarioAtual() == null) {
                mostrarMenuLogin();
            } else {
                mostrarMenuPrincipal();
            }
        }
    }
    
    private void mostrarBoasVindas() {
        System.out.println("""
            ╔══════════════════════════════════════════════════════════════╗
            ║                    SISTEMA DE EVENTOS                        ║
            ║                   Padrão MVC - Java                          ║
            ╚══════════════════════════════════════════════════════════════╝
            """);
    }
    
    private void mostrarMenuLogin() {
        System.out.println("""
            
            ┌─────────────────────────────────────┐
            │           MENU DE ACESSO            │
            ├─────────────────────────────────────┤
            │ 1. Fazer Login                      │
            │ 2. Cadastrar Usuário                │
            │ 3. Sair                             │
            └─────────────────────────────────────┘
            """);
        
        System.out.print("Escolha uma opção: ");
        int opcao = obterEntradaInteira();
        
        switch (opcao) {
            case 1 -> processarLogin();
            case 2 -> processarCadastroUsuario();
            case 3 -> {
                System.out.println("Obrigado por usar o Sistema de Eventos!");
                System.exit(0);
            }
            default -> System.out.println("❌ Opção inválida!");
        }
    }
    
    private void mostrarMenuPrincipal() {
        User usuario = controlador.getUsuarioAtual();
        System.out.printf("""
            
            ┌─────────────────────────────────────┐
            │    Bem-vindo, %s!%-18s │
            ├─────────────────────────────────────┤
            │ 1. Criar Evento                     │
            │ 2. Consultar Eventos                │
            │ 3. Participar de Evento             │
            │ 4. Meus Eventos                     │
            │ 5. Cancelar Participação            │
            │ 6. Eventos por Status               │
            │ 7. Buscar Eventos                   │
            │ 8. Logout                           │
            └─────────────────────────────────────┘
            """, usuario.getNome(), "");
        
        System.out.print("Escolha uma opção: ");
        int opcao = obterEntradaInteira();
        
        switch (opcao) {
            case 1 -> processarCriarEvento();
            case 2 -> processarVisualizarTodosEventos();
            case 3 -> processarParticiparEvento();
            case 4 -> processarVisualizarEventosUsuario();
            case 5 -> processarCancelarParticipacao();
            case 6 -> processarVisualizarEventosPorStatus();
            case 7 -> processarBuscarEventos();
            case 8 -> controlador.fazerLogout();
            default -> System.out.println("❌ Opção inválida!");
        }
    }
    
    private void processarLogin() {
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        
        User usuario = controlador.fazerLogin(email);
        if (usuario != null) {
            System.out.println("✅ Login realizado com sucesso!");
        } else {
            System.out.println("❌ Usuário não encontrado!");
        }
    }
    
    private void processarCadastroUsuario() {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Idade: ");
        int idade = obterEntradaInteira();
        
        if (controlador.cadastrarUsuario(nome, email, telefone, idade)) {
            System.out.println("✅ Usuário cadastrado com sucesso!");
        } else {
            System.out.println("❌ Email já cadastrado!");
        }
    }
    
    private void processarCriarEvento() {
        System.out.println("\n=== CRIAR EVENTO ===");
        
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        System.out.println("\nCategorias disponíveis:");
        EventCategory[] categorias = EventCategory.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.printf("%d. %s\n", i + 1, categorias[i]);
        }
        
        System.out.print("Escolha a categoria (número): ");
        int indicCategoria = obterEntradaInteira() - 1;
        
        if (indicCategoria < 0 || indicCategoria >= categorias.length) {
            System.out.println("❌ Categoria inválida!");
            return;
        }
        
        EventCategory categoria = categorias[indicCategoria];
        
        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        
        LocalDateTime dataHora;
        try {
            dataHora = LocalDateTime.parse(dataHoraStr, formatadorData);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Formato de data inválido!");
            return;
        }
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        if (controlador.criarEvento(nome, endereco, categoria, dataHora, descricao)) {
            System.out.println("✅ Evento criado com sucesso!");
        } else {
            System.out.println("❌ Evento já existe!");
        }
    }
    
    private void processarVisualizarTodosEventos() {
        List<Event> eventos = controlador.obterEventosProximos();
        
        if (eventos.isEmpty()) {
            System.out.println("📅 Nenhum evento próximo encontrado.");
            return;
        }
        
        System.out.println("\n=== PRÓXIMOS EVENTOS ===");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.printf("\n[%d] %s\n", i + 1, eventos.get(i));
        }
    }
    
    private void processarParticiparEvento() {
        List<Event> eventos = controlador.obterEventosProximos();
        
        if (eventos.isEmpty()) {
            System.out.println("📅 Nenhum evento disponível para participação.");
            return;
        }
        
        System.out.println("\n=== PARTICIPAR DE EVENTO ===");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.printf("\n[%d] %s\n", i + 1, eventos.get(i));
        }
        
        System.out.print("Escolha o evento (número): ");
        int indiceEvento = obterEntradaInteira() - 1;
        
        if (indiceEvento < 0 || indiceEvento >= eventos.size()) {
            System.out.println("❌ Evento inválido!");
            return;
        }
        
        Event evento = eventos.get(indiceEvento);
        User usuario = controlador.getUsuarioAtual();
        
        if (controlador.participarEvento(evento, usuario)) {
            System.out.println("✅ Participação confirmada!");
        } else {
            System.out.println("❌ Você já está participando deste evento!");
        }
    }
    
    private void processarVisualizarEventosUsuario() {
        User usuario = controlador.getUsuarioAtual();
        List<Event> eventosUsuario = controlador.obterEventosDoUsuario(usuario);
        
        if (eventosUsuario.isEmpty()) {
            System.out.println("📅 Você não está participando de nenhum evento.");
            return;
        }
        
        System.out.println("\n=== MEUS EVENTOS ===");
        for (int i = 0; i < eventosUsuario.size(); i++) {
            Event evento = eventosUsuario.get(i);
            String status = evento.estaAcontecendoAgora() ? " [ACONTECENDO AGORA]" : 
                           evento.jaOcorreu() ? " [FINALIZADO]" : " [PRÓXIMO]";
            System.out.printf("\n[%d]%s %s\n", i + 1, status, evento);
        }
    }
    
    private void processarCancelarParticipacao() {
        User usuario = controlador.getUsuarioAtual();
        List<Event> eventosUsuario = controlador.obterEventosDoUsuario(usuario);
        
        if (eventosUsuario.isEmpty()) {
            System.out.println("📅 Você não está participando de nenhum evento.");
            return;
        }
        
        System.out.println("\n=== CANCELAR PARTICIPAÇÃO ===");
        for (int i = 0; i < eventosUsuario.size(); i++) {
            System.out.printf("\n[%d] %s\n", i + 1, eventosUsuario.get(i));
        }
        
        System.out.print("Escolha o evento para cancelar (número): ");
        int indiceEvento = obterEntradaInteira() - 1;
        
        if (indiceEvento < 0 || indiceEvento >= eventosUsuario.size()) {
            System.out.println("❌ Evento inválido!");
            return;
        }
        
        Event evento = eventosUsuario.get(indiceEvento);
        
        if (controlador.cancelarParticipacao(evento, usuario)) {
            System.out.println("✅ Participação cancelada!");
        } else {
            System.out.println("❌ Erro ao cancelar participação!");
        }
    }
    
    private void processarVisualizarEventosPorStatus() {
        System.out.println("""
            
            ┌─────────────────────────────────────┐
            │        EVENTOS POR STATUS           │
            ├─────────────────────────────────────┤
            │ 1. Próximos Eventos                 │
            │ 2. Eventos Acontecendo Agora        │
            │ 3. Eventos Finalizados              │
            └─────────────────────────────────────┘
            """);
        
        System.out.print("Escolha uma opção: ");
        int opcao = obterEntradaInteira();
        
        switch (opcao) {
            case 1 -> mostrarListaEventos("PRÓXIMOS EVENTOS", controlador.obterEventosProximos());
            case 2 -> mostrarListaEventos("EVENTOS ACONTECENDO AGORA", controlador.obterEventosAtuais());
            case 3 -> mostrarListaEventos("EVENTOS FINALIZADOS", controlador.obterEventosPassados());
            default -> System.out.println("❌ Opção inválida!");
        }
    }
    
    private void processarBuscarEventos() {
        System.out.println("""
            
            ┌─────────────────────────────────────┐
            │           BUSCAR EVENTOS            │
            ├─────────────────────────────────────┤
            │ 1. Por Nome                         │
            │ 2. Por Categoria                    │
            └─────────────────────────────────────┘
            """);
        
        System.out.print("Escolha uma opção: ");
        int opcao = obterEntradaInteira();
        
        switch (opcao) {
            case 1 -> {
                System.out.print("Digite o nome do evento: ");
                String nome = scanner.nextLine();
                mostrarListaEventos("RESULTADOS DA BUSCA", controlador.buscarEventosPorNome(nome));
            }
            case 2 -> {
                System.out.println("\nCategorias disponíveis:");
                EventCategory[] categorias = EventCategory.values();
                for (int i = 0; i < categorias.length; i++) {
                    System.out.printf("%d. %s\n", i + 1, categorias[i]);
                }
                
                System.out.print("Escolha a categoria (número): ");
                int indiceCategoria = obterEntradaInteira() - 1;
                
                if (indiceCategoria >= 0 && indiceCategoria < categorias.length) {
                    EventCategory categoria = categorias[indiceCategoria];
                    mostrarListaEventos("EVENTOS DA CATEGORIA: " + categoria, 
                                      controlador.buscarEventosPorCategoria(categoria));
                } else {
                    System.out.println("❌ Categoria inválida!");
                }
            }
            default -> System.out.println("❌ Opção inválida!");
        }
    }
    
    private void mostrarListaEventos(String titulo, List<Event> eventos) {
        System.out.println("\n=== " + titulo + " ===");
        
        if (eventos.isEmpty()) {
            System.out.println("📅 Nenhum evento encontrado.");
            return;
        }
        
        for (int i = 0; i < eventos.size(); i++) {
            System.out.printf("\n[%d] %s\n", i + 1, eventos.get(i));
        }
    }
    
    private int obterEntradaInteira() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("❌ Por favor, digite um número válido: ");
            }
        }
    }
}
