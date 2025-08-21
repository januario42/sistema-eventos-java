# 🎉 Sistema de Gerenciamento de Eventos

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MVC](https://img.shields.io/badge/Pattern-MVC-blue?style=for-the-badge)
![Console](https://img.shields.io/badge/Interface-Console-green?style=for-the-badge)

**Sistema completo de cadastro e notificação de eventos desenvolvido em Java**  
*Seguindo rigorosamente o padrão arquitetural MVC*

</div>

---

## 🚀 Sobre o Projeto

Este é um sistema robusto de gerenciamento de eventos que permite cadastrar usuários, criar eventos com categorias predefinidas, gerenciar participações e muito mais! Desenvolvido com foco na **arquitetura MVC** e **programação orientada a objetos**.

### ✨ Principais Destaques
- 🏗️ **Arquitetura MVC** bem definida e organizada
- 💾 **Persistência automática** em arquivo `events.data`
- ⏰ **Detecção inteligente** de eventos em tempo real
- 🔍 **Sistema de busca** avançado por nome e categoria
- 📱 **Interface console** intuitiva e interativa

---

## 🏛️ Arquitetura MVC

### 📊 **Model (Modelo)**
\`\`\`
🧑‍💼 User.java          → Gerencia dados dos usuários
🎪 Event.java         → Controla informações dos eventos  
📂 EventCategory.java → Define categorias predefinidas
\`\`\`

### 👁️ **View (Visão)**
\`\`\`
💻 ConsoleView.java   → Interface interativa no console
\`\`\`

### 🎮 **Controller (Controlador)**
\`\`\`
⚙️ EventController.java → Lógica de negócio e persistência
\`\`\`

---

## 🎯 Funcionalidades Completas

### 👥 **Gerenciamento de Usuários**
- ✅ Cadastro com **4 atributos** (nome, email, telefone, idade)
- ✅ Sistema de **login seguro** por email
- ✅ **Sessão ativa** durante uso do sistema

### 🎪 **Gerenciamento de Eventos**
- ✅ **Criação completa** com todos os atributos obrigatórios:
  - 📝 Nome do evento
  - 📍 Endereço/localização
  - 🏷️ Categoria (predefinida)
  - ⏰ Data e horário
  - 📄 Descrição detalhada
- ✅ **Participação inteligente** (confirmar/cancelar)
- ✅ **Visualização organizada** por status

### 🔥 **Funcionalidades Avançadas**
- 🕐 **Ordenação temporal** - eventos por proximidade
- ⚡ **Detecção em tempo real** - "acontecendo agora"
- 📚 **Histórico completo** - eventos finalizados
- 🔍 **Busca inteligente** - por nome e categoria
- 💾 **Auto-save** - persistência automática

---

## 🚀 Como Executar

### 📋 **Pré-requisitos**
- ☕ Java 8 ou superior instalado
- 💻 Terminal/Prompt de comando

### 🔧 **Passos de Instalação**

1. **Clone ou baixe** o projeto
2. **Compile** todos os arquivos:
   \`\`\`bash
   javac -d bin src/**/*.java src/*.java
   \`\`\`
3. **Execute** o sistema:
   \`\`\`bash
   java -cp bin Main
   \`\`\`

### 🎮 **Primeira Execução**
1. 📝 Cadastre-se como novo usuário
2. 🎪 Crie seus primeiros eventos
3. 👥 Participe de eventos disponíveis
4. 📊 Explore todas as funcionalidades!

---

## 📁 Estrutura do Projeto

\`\`\`
📦 Sistema de Eventos
├── 🚀 src/
│   ├── 📄 Main.java                    # Ponto de entrada
│   ├── 📊 model/
│   │   ├── 👤 User.java               # Modelo de usuário
│   │   ├── 🎪 Event.java              # Modelo de evento
│   │   └── 📂 EventCategory.java      # Categorias
│   ├── 👁️ view/
│   │   └── 💻 ConsoleView.java        # Interface console
│   └── 🎮 controller/
│       └── ⚙️ EventController.java    # Lógica de negócio
└── 💾 events.data                      # Dados persistidos (auto-criado)
\`\`\`

---

## 🏆 Tecnologias e Padrões

| Conceito | Implementação |
|----------|---------------|
| 🏗️ **Arquitetura** | Padrão MVC rigoroso |
| 🧬 **Paradigma** | Orientação a Objetos |
| 💾 **Persistência** | Serialização Java |
| 🎨 **Interface** | Console interativo |
| 📊 **Estruturas** | Collections Framework |

---

## 🎯 Categorias de Eventos Disponíveis

- 🎵 **MUSICA** - Shows, concertos, festivais
- 🎭 **TEATRO** - Peças, espetáculos, performances  
- 🏃 **ESPORTE** - Jogos, competições, atividades
- 🎓 **EDUCACAO** - Cursos, palestras, workshops
- 🍽️ **GASTRONOMIA** - Festivais, degustações
- 💼 **NEGOCIOS** - Networking, conferências
- 🎨 **ARTE** - Exposições, vernissages
- 💻 **TECNOLOGIA** - Hackathons, meetups

---

<div align="center">

### 🌟 **Desenvolvido com dedicação seguindo as melhores práticas de programação!** 🌟

*Sistema completo, funcional e pronto para uso!*

</div>
