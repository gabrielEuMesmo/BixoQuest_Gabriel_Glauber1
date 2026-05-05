# BIXOQUEST: Resumo Extenso de Arquitetura e Persistência (Fase 2)

## 1. CONTEXTO DO PROJETO

### 1.1 O BixoQuest: Visão Geral
O **BixoQuest** é um jogo híbrido de **simulação universitária + RPG visual novel**, inspirado especificamente em:
- **Doki Doki Literature Club (DDLC)**: para a mecânica visual novel com diálogos, sprites e escolhas narrativas
- **BitLife**: para a mecânica de simulação de vida com gerenciamento de status, menus textuais e eventos aleatórios

### 1.2 Os Dois Pilares do Jogo

#### Pilar 1: Sistema Visual Novel (estilo DDLC)
Quando o jogador entra em um local (Cantina, Sala de Aula, etc.) e interage com um NPC:
- **Layer 0 (Background)**: foto da universidade em pixel art com variações por `BlocoTempo`
- **Layer 1 (Sprite)**: personagem PNG transparente do NPC
- **Layer 2 (Caixa de Diálogo)**: fundo semi-transparente com a fala do NPC
- **Layer 3 (Botões de Resposta)**: escolhas do jogador em formato pixelado

**Mecânica**: escolha muda `nivelRelacionamento` do NPC e destrava cenas futuras. O foco é **imersão narrativa e espacial**.

#### Pilar 2: Sistema de Simulação de Vida (estilo BitLife)
Quando o jogador NÃO está conversando:
- **HUD fixo**: mostra status vitais (Saúde, Energia, Motivação, Dinheiro, Notas)
- **Interface de menu**: "Celular" ou "Mochila" com abas (Acadêmico, Financeiro, Saúde, Social)
- **Ações por clique**: cada clique consome recursos e avança o tempo
- **Fábrica de Eventos Aleatórios**: pop-ups inesperados com consequências

**Mecânica**: gerenciamento de turnos, recursos e crises através de menus textuais.

### 1.3 Conclusão Estratégica
O BixoQuest **não precisa de engine 3D complexa** (como Unity), mas sim de:
- Domínio total de **interfaces gráficas**
- Sobreposição de **imagens/layers**
- **Gerenciamento de variáveis** de estado

Isso casa **perfeitamente com JavaFX** para a Fase 3.

---

## 2. ANÁLISE DA ARQUITETURA ATUAL (Fase 1 — Motor Lógico)

### 2.1 Estado Atual do Código
Seu projeto já tem uma **Fase 1 blindada** com o motor lógico funcionando. A estrutura base é:

#### Classes principais do Model:
- **`Jogador.java`**: núcleo do personagem com atributos vitais
- **`GameController.java`**: orquestrador da sessão (tempo, fluxo)
- **`SistemaDeRotas.java`**: navegação por mapa/menus
- **`Local.java` + `NPC.java`**: locais físicos e personagens
- **`CenaDialogo.java` + `OpcaoResposta.java`**: estrutura de diálogos VN
- **`BancoDialogoColega.java` + `BancoDialogoProfessor.java`**: conteúdo narrativo (roteiros)
- **`AcaoDialogo.java`**: converte escolha em efeito no jogador
- **`Acao.java`**: histórico de ações do turno

#### Classes de submenu (simulação):
- **`Academico.java`**: ações acadêmicas
- **`Financeiro.java`**: ações financeiras
- **`Saude.java`**: ações de saúde
- **`Social.java`**: ações sociais

#### View:
- **`TerminalView.java`**: interface textual (Fase 2 será substituída por JavaFX)
- **`Game.java`**: lógica de fluxo da interface textual (legada)

### 2.2 O Que Funciona Hoje

#### ✅ Estado do Jogador
```
Jogador
├── nome, saude, energia (semanal), energiaDia (diária)
├── motivacao, dinheiro
├── desempenho_academico_EXA/TEC/ALG
├── porcentagem_curso
├── acoesDoTurno (histórico do turno)
└── SistemaDeRotas rotas (navegação interna)
```

O `Jogador` já contém:
- Atributos vitais (tudo com `clamp` validado)
- Histórico de ações registradas
- Acesso ao sistema de rotas

#### ✅ Fluxo de Diálogo
1. Jogador escolhe local → `SistemaDeRotas` navega
2. Local mostra NPCs disponíveis no `BlocoTempo` atual
3. Jogador fala com NPC → `CenaDialogo` é acionada
4. `CenaDialogo` + `nivelRelacionamento` → gera `OpcaoResposta`
5. Jogador escolhe resposta → `AcaoDialogo` é criada
6. `AcaoDialogo.aplicarJogador()` altera estado do `Jogador`
7. Ação é registrada em `Jogador.registrarAcao()`

#### ✅ Gerenciamento de Tempo
- `BlocoTempo`: MANHA_1, MANHA_2, TARDE_1, TARDE_2, NOITE
- `SemanaEnum`: LIVRE, PROVA_M1, PROVA_M2, PROVA_M3, FECHAMENTO
- `GameController` controla avanço de turno

---

## 3. PROBLEMAS IDENTIFICADOS NA ARQUITETURA

### 3.1 Duplicação de Responsabilidades

Você tem **duas arquiteturas paralelas coexistindo**:

#### Arquitetura Legada (antigo):
```
Jogador
├── pag (navegação de menu)
├── Social, Academico, Saude, Financeiro (menus internos)
├── mostrarOpcao() / escolherOpcao() (UI textual)
└── atualizar() (processa menus)
```

Essa arquitetura está no `view/Game.java`, mas **não é a que o `Main` usa agora**.

#### Arquitetura Atual (novo):
```
GameController
├── tempo (semestre, semana, bloco)
├── jogador (Jogador)
├── mapaMundo (Map<String, Local>)
├── SistemaDeRotas (navegação)
└── fluxo: rota → local → NPC → cena → escolha → ação
```

Essa é a que funciona no `Main.java`.

### Consequência
- Código legado não é usado
- Confusão sobre qual é o "estado verdadeiro"
- Dificulta persistência porque não fica claro o que salvar
- Ação pode ser registrada em mais de um lugar

### 3.2 Estado Espalhado por Múltiplas Classes

| Informação | Classe Atual |
|---|---|
| Atributos do jogador | `Jogador` |
| Tempo da sessão | `GameController` |
| Navegação no mapa | `SistemaDeRotas` (dentro de `Jogador`) |
| Relacionamento dos NPCs | Cada NPC (Professor, ColegaDia) |
| Conteúdo narrativo | `BancoDialogo*` |
| Histórico de ações | `Jogador.acoesDoTurno` |

**Problema**: se você salvar só `Jogador`, perde tempo. Se salvar toda a sessão manualmente, fica espalhado.

### 3.3 Falta de Ponto Central de Persistência

Não existe uma classe única que encapsule **tudo que precisa ser salvo para recuperar uma sessão**.

---

## 4. SOLUÇÃO RECOMENDADA: GameSnapshot (Classe de Persistência)

### 4.1 O Conceito de GameSnapshot

**`GameSnapshot`** é uma classe que funciona como **snapshot (fotografia) do estado completo do jogo em um momento específico**.

Ele é o **"master de persistência"** porque:
- agrupa tudo que precisa ser salvo
- é independente de UI (nem depende de `TerminalView`)
- é serializável (pode virar JSON ou arquivo binário)
- permite versionamento futuro

### 4.2 Estrutura Recomendada de GameSnapshot

```java
public class GameSnapshot {
    // Versão do formato (para compatibilidade futura)
    public int snapshotVersion = 1;
    
    // Metadados
    public String slotId;              // "slot1", "slot2", "autosave"
    public long savedAt;               // timestamp em milissegundos
    public String slotNome;            // nome amigável exibido (ex: "Gabriel")
    
    // Estado do jogador
    public Jogador jogador;
    
    // Estado temporal da sessão
    public int semestreAtual;
    public int semanaAtual;
    public String blocoTempo;          // nome do BlocoTempo atual
    public String semeanaAtualEnum;    // tipo da semana (LIVRE, PROVA_M1, etc)
    
    // Estado de navegação VN
    public String rotaPagAtual;        // estado atual de SistemaDeRotas.pag
    public String ultimaEscolhaRotas;  // última navegação feita
    
    // Estado dos NPCs (relacionamento)
    public Map<String, Integer> relacionamentoPorNpc;
    // Exemplo: { "Suco": 3, "Anfranserai": 1, "Maeli": 2 }
    
    // Possíveis extensões futuras
    // public Map<String, Boolean> cenesDestravadas;  // quais cenas já foram vistas
    // public List<EventoAleatorioPendente> eventosAtivos;
    // public Map<String, Object> flagsMundo;
}
```

### 4.3 Campos que Devem Ser Salvos (vs. que não devem)

#### ✅ DEVE SALVAR (estado da campanha)
| Campo | Classe | Por quê |
|---|---|---|
| `nome, saude, energia, motivacao, dinheiro` | `Jogador` | Identidade e status pessoal |
| `desempenho_academico_*` | `Jogador` | Progresso do curso |
| `porcentagem_curso` | `Jogador` | Condição de vitória |
| `acoesDoTurno` | `Jogador` | Histórico (opcional, mas útil) |
| `semestreAtual, semanaAtual, blocoTempo` | `GameController` | Contexto temporal |
| `rotas.pag` | `SistemaDeRotas` | Onde o jogador estava navegando |
| `nivelRelacionamento` | Cada NPC | Progresso narrativo com NPC |
| `savedAt` | (novo) | Quando foi salvo |

#### ❌ NÃO PRECISA SALVAR (reconstrói ao carregar)
| Coisa | Por quê |
|---|---|
| Conteúdo textual dos bancos (`BancoDialogoColega`) | Conteúdo scriptado não muda |
| Estrutura fixa de menus | Recria ao iniciar |
| Estrutura do mapa (`ConstrutorDeMapa`) | Se estática, reconstrói |
| `TerminalView` ou qualquer UI | Estado de interface não é dados |
| `Social, Academico, Saude, Financeiro` (objetos legados) | Se não estão sendo usados no fluxo atual |

### 4.4 Fluxo de Persistência com GameSnapshot

#### SALVAR (no momento que o jogador clica "Save"):
```
GameController.salvarJogo(slotId)
├─ capturar Jogador
├─ capturar tempo (semestre, semana, bloco)
├─ capturar SistemaDeRotas.pag
├─ capturar relacionamentoPorNpc do mapa
├─ criar GameSnapshot com tudo isso
└─ serializar para JSON e salvar em arquivo
   arquivo: saves/slot1.json
```

#### CARREGAR (ao iniciar ou "Load"):
```
GameController.carregarJogo(slotId)
├─ desserializar JSON → GameSnapshot
├─ restaurar Jogador (atributos + histórico)
├─ restaurar tempo (semestre, semana, bloco)
├─ recriar mapa com ConstrutorDeMapa
├─ aplicar relacionamentoPorNpc em cada NPC
├─ restaurar SistemaDeRotas.pag (navegação)
└─ jogo volta ao estado exato anterior
```

---

## 5. DETALHES CRÍTICOS: `relacionamentoPorNpc`

### 5.1 O Que É

`relacionamentoPorNpc` é um mapa:
```
Map<String, Integer> relacionamentoPorNpc = {
    "Suco": 2,
    "Anfranserai": 1,
    "Maeli": 3,
    "Gabriel": 0
}
```

Cada chave é o **nome do NPC** (String) e cada valor é o **nível de relacionamento** (Integer).

### 5.2 Por Que Precisa Salvar

Seu sistema atual:
1. `BancoDialogoColega.getCenaAtual(npc)` retorna a cena baseada em `npc.getNivelRelacionamento()`
2. Cada escolha chamada `AcaoDialogo.aplicarJogador()` altera esse nível
3. **Sem salvá-lo, todas as cenas voltam ao nível 0 ao carregar**

Então se o jogador:
- Conversa com Suco 5 vezes
- Relacionamento vai de 0 → 1 → 2 → 3 → 4 → 5
- Cenas progressivas são desbloqueadas

Se perde o save sem `relacionamentoPorNpc`, volta para cena inicial com Suco.

### 5.3 Como Implementar (Alto Nível)

#### Na hora de SALVAR:
```java
// Em SaveLoadService ou GameController
Map<String, Integer> relacionamentoPorNpc = new HashMap<>();

for (Local local : mapaMundo.values()) {
    for (NPC npc : local.getTodosPersonagens()) {
        // Agrega relacionamentos por nome
        relacionamentoPorNpc.putIfAbsent(npc.getNome(), npc.getNivelRelacionamento());
    }
}

snapshot.relacionamentoPorNpc = relacionamentoPorNpc;
```

#### Na hora de CARREGAR:
```java
// Recriar o mapa
Map<String, Local> mapaMundo = ConstrutorDeMapa.gerarMapaUefs();

// Aplicar relacionamentos salvos
for (Local local : mapaMundo.values()) {
    for (NPC npc : local.getTodosPersonagens()) {
        Integer salvo = snapshot.relacionamentoPorNpc.get(npc.getNome());
        if (salvo != null) {
            // Restaurar relacionamento
            for (int i = 0; i < salvo; i++) {
                npc.aumentarRelacionamento();
            }
        }
    }
}
```

### 5.4 Observação Importante
Se tiver NPCs com nomes duplicados em locais diferentes, considere usar:
- `localId + "|" + npcNome` como chave
- Ou um `npcId` único por NPC

---

## 6. SNAPSHOTVERSION: Versionamento de Saves

### 6.1 O Que É

`int snapshotVersion` é um **número que identifica a "geração" do arquivo de save**.

```java
public int snapshotVersion = 1;  // v1 do formato
```

### 6.2 Por Que Precisa

Seu jogo vai evoluir:
- **v1**: Jogador + tempo + relacionamento
- **v2**: Jogador + tempo + relacionamento + inventário
- **v3**: Jogador + tempo + relacionamento + inventário + cenas vistas
- **v4**: Jogador + tempo + relacionamento + inventário + cenas vistas + eventos pendentes

Se um save v1 tentar carregar em código v3, o programa pode quebrar.

### 6.3 Como Usar

#### Na estrutura de dados:
```java
public class GameSnapshot {
    public int snapshotVersion = 1;  // começa em 1
    // ... resto dos campos
}
```

#### Na hora de carregar:
```java
public static GameSnapshot carregarDoJSON(String json) {
    GameSnapshot snap = gson.fromJson(json, GameSnapshot.class);
    
    // Verificar versão
    if (snap.snapshotVersion == 1) {
        // Carregar normal
        return snap;
    } else if (snap.snapshotVersion == 2) {
        // Carregar normal + processar novos campos
        return snap;
    } else {
        throw new Exception("Save incompatível: versão " + snap.snapshotVersion);
    }
}
```

### 6.4 Valor Inicial
Comece com `snapshotVersion = 1` agora. Quando adicionar novos campos, mude para `2`.

---

## 7. PLANEJAMENTO DAS FASES

### 7.1 Fase 1: Motor Lógico (✅ CONCLUÍDA)

**Objetivo**: criar todas as regras do jogo sem UI.

**Entregáveis**:
- ✅ `Jogador` com atributos e validações
- ✅ `SistemaDeRotas` com navegação
- ✅ `CenaDialogo` e `OpcaoResposta` com mecânica de VN
- ✅ `AcaoDialogo` com impacto no jogador
- ✅ `BancoDialogo*` com roteiro
- ✅ `Local` com agenda de NPCs
- ✅ `GameController` orquestrando sessão

**Testes**: todos os testes em `/test` passando.

---

### 7.2 Fase 2: Persistência (🔄 EM EXECUÇÃO)

**Objetivo**: o jogo lembra quem o jogador é mesmo desligando o PC.

**Tarefas principais**:

1. **Criar infraestrutura de save/load**
   - [ ] `GameSnapshot.java` (classe de persistência)
   - [ ] `SaveLoadService.java` (lógica de serialização)
   - [ ] `SaveSlotManager.java` (gestão de múltiplos slots)

2. **Implementar JSON como formato**
   - [ ] adicionar dependência `com.google.code.gson:gson` (ou Jackson)
   - [ ] métodos `toJSON()` e `fromJSON()`

3. **Integrar no GameController**
   - [ ] `GameController.salvarJogo(slotId)`
   - [ ] `GameController.carregarJogo(slotId)`
   - [ ] chamadas no fluxo de menu principal

4. **Testar ciclos completos**
   - [ ] salvar no meio de um diálogo
   - [ ] salvar com relacionamento alterado
   - [ ] carregar e continuar de onde parou
   - [ ] múltiplos slots funcionando

**Por que agora**:
- Motor lógico está sólido
- Não quebra nada (é camada adicional)
- Prepara para Fase 3 (JavaFX pode usar os mesmos mechanics de save)

**Duração estimada**: 2-3 sprints curtas

---

### 7.3 Fase 3: Interface Gráfica (📅 PRÓXIMA)

**Objetivo**: sair do terminal e ter interface visual completa em JavaFX.

**Componentes visuais**:
1. **HUD fixo** (topo/lateral)
   - barras de progresso com cores (energia, saúde, motivação)
   - dinheiro e tempo atual
   - fonte pixel art (Press Start 2P)

2. **Modo Visual Novel** (centro dinâmico)
   - background (foto universidade pixel art)
   - sprite do NPC
   - caixa de diálogo inferior
   - botões de resposta

3. **Modo Simulação** (menu "Celular/Mochila")
   - overlay modal com apps
   - menu acadêmico/financeiro/saúde/social

4. **Mapa de Locomoção**
   - vista top-down da universidade
   - clique em local = fade in/out

5. **Máquina de Estados**
   - `DASHBOARD`, `MAPA`, `VN_DIALOGO`, `MENU_CELULAR`, `EVENTO_ALEATORIO`, `RELATORIO_TURNO`

**Padrão de integração**:
- Model (Fase 1) **não muda** estruturalmente
- View (JavaFX) apenas renderiza o estado do Model
- Controller (MVC de UI) traduz cliques para chamadas no Model
- Observer pattern: View "escuta" mudanças no Model

**Por que depois de Persistência**:
- Fase 2 prepara save/load que a UI vai usar
- Model está blindado
- UI é "cosmética pura"

---

## 8. MAPA TÉCNICO RESUMO: O QUE FICA ONDE

### 8.1 Arquivos Essenciais Atuais

```
src/
├── model/
│   ├── Jogador.java         ← NÚCLEO (estado pessoal, histórico)
│   ├── GameSnapshot.java    ← NOVO (raiz de persistência Fase 2)
│   ├── SaveLoadService.java ← NOVO (lógica save/load)
│   ├── SistemaDeRotas.java  ← navegação/estado de menu
│   ├── Local.java           ← locais do mapa
│   ├── NPC.java + Professor.java + ColegaDia.java ← personagens
│   ├── CenaDialogo.java     ← estrutura de cena VN
│   ├── OpcaoResposta.java   ← escolhas em diálogo
│   ├── AcaoDialogo.java     ← consequência da escolha
│   ├── BancoDialogoColega.java ← roteiro (conteúdo narrativo)
│   ├── BancoDialogoProfessor.java ← roteiro (conteúdo narrativo)
│   ├── ConstrutorDeMapa.java ← fábrica do mapa
│   └── enums/
│       ├── BlocoTempo.java
│       └── SemanaEnum.java
├── controller/
│   ├── GameController.java  ← orquestrador da sessão
│   └── (JavaFX controller vem na Fase 3)
└── view/
    ├── TerminalView.java    ← Fase 2, será substituída por JavaFX
    └── Game.java            ← código legado (não se preocupe agora)
```

### 8.2 O Que Salvar (Fase 2)

**Raiz de Save**: `GameSnapshot`

Que contém:
- `Jogador` (todo objeto com atributos)
- tempo: `semestreAtual, semanaAtual, blocoTempo, semeanaAtualEnum`
- navegação: `rotaPagAtual, ultimaEscolhaRotas`
- relacionamento: `relacionamentoPorNpc` (Map)
- metadados: `slotId, savedAt, snapshotVersion`

### 8.3 Arquivos Que Não Precisa Salvar

- Bancos de diálogo (conteúdo scriptado, recria sempre)
- Menu legado (`Academico`, `Social`, etc., se não usar)
- `TerminalView` (será substituído)
- constantes de máximos/mínimos (hardcoded)

---

## 9. CICLO DE VIDA COMPLETO DE UMA SESSÃO

### 9.1 Iniciar Novo Jogo

```
Main
├─ GameController.novoJogo(nomeJogador)
│  ├─ criar Jogador(nome, stats iniciais)
│  ├─ gerar MapaMundo com ConstrutorDeMapa
│  ├─ inicializar SistemaDeRotas
│  ├─ zerar tempo (semestre 1, semana 1, bloco MANHA_1)
│  └─ começar loop de turnos
│
└─ loopTurno()
   ├─ exibir HUD (Jogador status)
   ├─ exibir menu navegação (SistemaDeRotas)
   ├─ ler entrada
   ├─ navegar ou abrir diálogo
   ├─ processar ação (AcaoDialogo)
   ├─ verificar game over
   ├─ avançar tempo
   └─ voltar ao topo ou finalizar
```

### 9.2 Salvar Jogo (Fase 2)

```
Jogador clica "Salvar"
│
├─ GameController.salvarJogo(slotId)
│  ├─ capturar estado atual:
│  │  ├─ Jogador
│  │  ├─ tempo
│  │  ├─ SistemaDeRotas.pag
│  │  └─ relacionamentoPorNpc
│  │
│  ├─ criar GameSnapshot
│  │  └─ snapshotVersion = 1
│  │  └─ savedAt = System.currentTimeMillis()
│  │
│  └─ SaveLoadService.salvarJSON(snapshot, "saves/slotId.json")
│     └─ serializar com GSON
│        └─ gravar em arquivo
│
└─ mensagem "Jogo salvo!"
```

### 9.3 Carregar Jogo (Fase 2)

```
Jogador clica "Carregar slot1"
│
├─ GameController.carregarJogo(slotId)
│  ├─ SaveLoadService.carregarJSON("saves/slot1.json")
│  │  └─ desserializar de arquivo
│  │
│  ├─ GameSnapshot snapshot = resultado
│  │
│  ├─ restaurar Jogador
│  │  └─ jogador = snapshot.jogador
│  │
│  ├─ restaurar tempo
│  │  ├─ semestreAtual = snapshot.semestreAtual
│  │  ├─ semanaAtual = snapshot.semanaAtual
│  │  └─ blocoTempo = snapshot.blocoTempo
│  │
│  ├─ recriar mapa
│  │  └─ mapaMundo = ConstrutorDeMapa.gerarMapaUefs()
│  │
│  ├─ aplicar relacionamentoPorNpc
│  │  └─ para cada NPC em mapa:
│  │     └─ npc.setNivelRelacionamento(snapshot.relacionamentoPorNpc[npcNome])
│  │
│  ├─ restaurar navegação
│  │  └─ rotas.pag = snapshot.rotaPagAtual
│  │
│  └─ retomar loop de turnos
│
└─ aparecer na tela exata de antes
```

### 9.4 Validar Integridade do Save

```
ao carregar:
├─ verificar snapshotVersion
├─ verificar se Jogador está válido
├─ verificar se tempo está válido
├─ verificar se relacionamentoPorNpc está completo
└─ se tudo OK → carregar
   senão → erro ou usar fallback
```

---

## 10. CLASSE JOGADOR: O NÚCLEO DA PERSISTÊNCIA

### 10.1 Campos de Jogador que Devem Ser Salvos

```java
public class Jogador {
    // PESSOAL
    public String nome;                      // ← SALVAR
    
    // STATUS VITAIS
    public int saude;                         // ← SALVAR
    public int energia;                       // ← SALVAR (semanal)
    public int energiaDia;                    // ← SALVAR (diária)
    public int motivacao;                     // ← SALVAR
    
    // FINANCEIRO
    public double dinheiro;                   // ← SALVAR
    
    // PROGRESSO ACADÊMICO
    public int desempenho_academico_EXA;      // ← SALVAR
    public int desempenho_academico_TEC;      // ← SALVAR
    public int desempenho_academico_ALG;      // ← SALVAR
    public double porcentagem_curso;          // ← SALVAR
    
    // HISTÓRICO
    public List<Acao> acoesDoTurno;           // ← SALVAR (opcional mas útil)
    
    // NAVEGAÇÃO (coexiste mas será sincronizado via GameSnapshot)
    private String pag = "";                  // ← NÃO direto, via GameSnapshot.rotaPagAtual
    private final SistemaDeRotas rotas;       // ← NÃO direto, via GameSnapshot
    
    // MENUS LEGADOS (não salvar se não usar)
    Social social;                            // ← NÃO SALVAR (legado)
    Academico academico;                      // ← NÃO SALVAR (legado)
    Saude saudeClasse;                        // ← NÃO SALVAR (legado)
    Financeiro financeiro;                    // ← NÃO SALVAR (legado)
}
```

### 10.2 Como Jogador Entra no GameSnapshot

```java
public class GameSnapshot {
    public Jogador jogador;  // ← O JOGADOR INTEIRO entra aqui
    
    // E junto salvam-se:
    // - todos os atributos vitais
    // - o histórico de ações
    // - o estado pessoal
}
```

Portanto, **salvar `GameSnapshot.jogador` = salvar todo o jogador**.

### 10.3 Por Que Não Salvar só Jogador?

Se você tentasse salvar apenas `Jogador`:
- ✅ salvaria atributos pessoais
- ✅ salvaria histórico
- ❌ perderia tempo (semestre, semana, bloco)
- ❌ perderia navegação (onde estava no mapa)
- ❌ perderia relacionamento dos NPCs
- ❌ perderia estado global da sessão

Por isso **`GameSnapshot` é a classe certa**: ela guarda `Jogador` + contexto.

### 10.4 Fluxo de Serialização do Jogador

```
Jogador object
├─ toString() = texto legível (relatório)
├─ toJSON() = GSON converte para JSON
│  └─ JSON arquivo
│
└─ fromJSON() = GSON reconstrói objeto
   └─ Jogador restaurado com atributos idênticos
```

GSON automaticamente serializa:
- `nome` (String)
- `saude, energia, motivacao` (int)
- `dinheiro` (double)
- `acoesDoTurno` (List)
- etc.

---

## 11. ESTRUTURA DE DIRETÓRIOS DE SAVE

### 11.1 Onde Guardar os Saves

Na verdade, você decide, mas recomendo:

```
PBL - IMPLEMENTAR/
├── src/
├── test/
├── saves/              ← NOVO (criado automaticamente)
│   ├── slot1.json
│   ├── slot2.json
│   ├── slot3.json
│   └── autosave.json
└── ...
```

### 11.2 Conteúdo de um slot1.json (exemplo)

```json
{
  "snapshotVersion": 1,
  "slotId": "slot1",
  "slotNome": "Gabriel",
  "savedAt": 1677326400000,
  "semestreAtual": 2,
  "semanaAtual": 3,
  "blocoTempo": "TARDE_1",
  "semeanaAtualEnum": "LIVRE",
  "rotaPagAtual": "11",
  "ultimaEscolhaRotas": "Cantina do M5",
  "jogador": {
    "nome": "Gabriel",
    "saude": 75,
    "energia": 45,
    "energiaDia": 50,
    "motivacao": 82,
    "dinheiro": 250.50,
    "desempenho_academico_EXA": 15,
    "desempenho_academico_TEC": 12,
    "desempenho_academico_ALG": 18,
    "porcentagem_curso": 25.5,
    "acoesDoTurno": [
      {
        "tipo": "DIALOGO",
        "personagem": "Suco",
        "saldoSaude": 5,
        "saldoMotivacao": 10,
        "saldoDinheiro": -30.0,
        "saldoDes_acad_EXA": 0,
        "saldoDes_acad_TEC": 0,
        "saldoDes_acad_ALG": 0
      }
    ]
  },
  "relacionamentoPorNpc": {
    "Suco": 3,
    "Anfranserai": 1,
    "Maeli": 2,
    "Gabriel": 0
  }
}
```

---

## 12. CHECKLIST DE IMPLEMENTAÇÃO (Fase 2)

### 12.1 Criar Infraestrutura

- [ ] Criar classe `GameSnapshot.java`
  - [ ] campos: `snapshotVersion, slotId, savedAt, slotNome, jogador, tempo, navegação, relacionamentoPorNpc`
  - [ ] constructor padrão (sem args)

- [ ] Criar classe `SaveLoadService.java`
  - [ ] `static salvarJSON(GameSnapshot, String filepath)`
  - [ ] `static GameSnapshot carregarJSON(String filepath)`
  - [ ] validações

- [ ] Criar classe `SaveSlotManager.java`
  - [ ] `listarSlots()`
  - [ ] `deletarSlot(slotId)`
  - [ ] `salvarSlot(slotId, snapshot)`
  - [ ] `carregarSlot(slotId)`

### 12.2 Integrar no GameController

- [ ] adicionar metodo `salvarJogo(slotId)`
  - [ ] capturar estado
  - [ ] criar snapshot
  - [ ] chamar SaveLoadService

- [ ] adicionar metodo `carregarJogo(slotId)`
  - [ ] chamar SaveLoadService
  - [ ] restaurar estado
  - [ ] validar integridade

- [ ] adicionar menu principale (texto)
  - [ ] opção "Novo Jogo"
  - [ ] opção "Carregar Jogo"
  - [ ] opção "Salvar Jogo"
  - [ ] listar slots disponíveis

### 12.3 Testes

- [ ] teste: salvar novo jogo mid-turn
- [ ] teste: carregar e continuar exatamente de onde parou
- [ ] teste: relacionamento restaurado corretamente
- [ ] teste: tempo restaurado corretamente
- [ ] teste: histórico de ações restaurado
- [ ] teste: múltiplos slots funcionam independentemente
- [ ] teste: versão snapshot é validada
- [ ] teste: arquivo corrompido gera erro tratável

### 12.4 Documentação

- [ ] escrever README para Save/Load
- [ ] comentar classes

---

## 13. PRÓXIMAS ETAPAS RECOMENDADAS

### 13.1 Imediato (próximo sprint)
1. Criar `GameSnapshot.java` com campos mínimos
2. Implementar save/load básico com GSON
3. Testar ciclo salvar → carregar
4. Integrar com `GameController`

### 13.2 Curto prazo (2 sprints)
1. Interface de menu para escolher slots
2. Validação de integridade
3. Versionamento de snapshots
4. Behandlung de erros robusto

### 13.3 Medio prazo (preparação para Fase 3)
1. Revisar toda a Fase 2
2. Documentar bem para JavaFX usar
3. Garantir que Model está blindado
4. Começar design de JavaFX

---

## 14. RESUMO FINAL SIMPLIFICADO

### O Que Você Fez (Fase 1)
✅ Motor lógico solido: `Jogador`, `SistemaDeRotas`, diálogos VN, mecânica de ações.

### O Que Você Está Fazendo (Fase 2)
🔄 Persistência de dados: criar `GameSnapshot` que agrupa tudo que precisa ser salvo de forma centralizada.

### O Que Falta Fazer (Fase 3)
⏰ Interface visual: usar JavaFX para renderizar esse mesmo Model com gráficos, layers e UI bonita.

### A Chave da Fase 2
💾 Uma classe única (`GameSnapshot`) que encapsula **todo o estado de uma sessão**, permitindo save/load robusto sem quebrar nada que já funciona.

### Por Que Isso É Importante
Sem persistência, o jogador perde todo progresso ao desligar.  
Com persistência, o BixoQuest fica real.

---

## 15. DIAGRAMA VISUAL (ASCII)

```
┌─────────────────────────────────────────────────────────────┐
│                     BIXOQUEST ARQUITETURA                   │
└─────────────────────────────────────────────────────────────┘

FASE 1 (Motor Lógico - ✅ Concluída)
           Model
         ┌─────────┐
         │ Jogador │◄───────┐
         ├─────────┤        │
   ┌─────►  Status │        │
   │     │  Hist.  │        │
   │     │ Rotas   │        │
   │     └─────────┘        │
   │            ▲            │
   │            │            │
   │     ┌──────┴──────┐    │
   │     │             │    │
   │  Local         GameController
   │   │ NPC      (Orquestrador)
   │   │  │          │
   │   └─┼──────┐    │
   │     │      │    │
   │  CenaDialogo  AcaoDialogo
   │  OpcaoResposta
   │    (VN)
   │
   └─ View (TerminalView)


FASE 2 (Persistência - 🔄 Atual)
              NEW ⭐
         ┌─────────────────┐
         │  GameSnapshot   │ ◄─────┐
         ├─────────────────┤       │
         │ Jogador         │       │
         │ tempo           │       │
         │ navegação       │       │ Salva
         │ relacionamentos │       │ Carrega
         │ snapshotVersion │       │
         └─────────────────┘       │
                ▲                  │
                │                  │
         SaveLoadService ◄─────────┤
         (serializar/JSON)         │
                ▲                  │
                │                  │
           arquivo.json ◄──────────┘


FASE 3 (JavaFX Visual - 📅 Próxima)
                View (JavaFX)
    ┌─────────────────────────────────┐
    │  HUD (barras + tempo)            │
    ├─────────────────────────────────┤
    │ Visual Novel Layer              │
    │  - background                   │
    │  - sprite NPC                   │
    │  - diálogo                      │
    │  - botões resposta              │
    ├─────────────────────────────────┤
    │ Mapa / Menu Celular             │
    └─────────────────────────────────┘
         ▲
         │ Renderiza
         │
    Model (mesma Fase 1)
    + GameSnapshot (Fase 2)
```

---

## 📋 CONCLUSÃO

O **BixoQuest** é um projeto bem pensado que combina narrativa visual (DDLC) com simulação de vida (BitLife).

1. **Fase 1** estabeleceu o motor lógico sólido
2. **Fase 2** vai transformá-lo em persistente (salvar/carregar)
3. **Fase 3** vai envolvê-lo em uma UI visual bonita

A **chave de tudo** é manter o **Model independente da View**, e a **classe GameSnapshot** é o catalisador que faz isso acontecer.

Você tem tudo que precisa. Agora é colocar a mão na massa! 🚀

