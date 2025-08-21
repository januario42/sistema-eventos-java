package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    private String nome;
    private String endereco;
    private EventCategory categoria;
    private LocalDateTime dataHora;
    private String descricao;
    private List<User> participantes;
    
    public Event(String nome, String endereco, EventCategory categoria, 
                LocalDateTime dataHora, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    public EventCategory getCategoria() { return categoria; }
    public void setCategoria(EventCategory categoria) { this.categoria = categoria; }
    
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public List<User> getParticipantes() { return participantes; }
    
    public void adicionarParticipante(User usuario) {
        if (!participantes.contains(usuario)) {
            participantes.add(usuario);
        }
    }
    
    public void removerParticipante(User usuario) {
        participantes.remove(usuario);
    }
    
    public boolean ehParticipante(User usuario) {
        return participantes.contains(usuario);
    }
    
    public boolean estaAcontecendoAgora() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime fimEvento = dataHora.plusHours(2);
        return agora.isAfter(dataHora) && agora.isBefore(fimEvento);
    }
    
    public boolean jaOcorreu() {
        LocalDateTime fimEvento = dataHora.plusHours(2);
        return LocalDateTime.now().isAfter(fimEvento);
    }
    
    public boolean ehProximo() {
        return LocalDateTime.now().isBefore(dataHora);
    }
    
    @Override
    public String toString() {
        return String.format("""
            ═══════════════════════════════════════
            📅 %s
            📍 %s
            🏷️  %s
            ⏰ %s
            📝 %s
            👥 %d participante(s)
            ═══════════════════════════════════════""",
            nome, endereco, categoria, dataHora.format(FORMATADOR), 
            descricao, participantes.size());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event evento = (Event) obj;
        return nome.equals(evento.nome) && dataHora.equals(evento.dataHora);
    }
    
    @Override
    public int hashCode() {
        return nome.hashCode() + dataHora.hashCode();
    }
}
