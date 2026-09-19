| | |
|---|---|
| **Grupo nº** | Apollo |
| **Integrantes (3 a 4)** | Jonatas Calado, Cecilia Helena, Isi Luana, Marilia Gabrielly |
| **Turma** | 3º ano — Ensino Médio |
| **Repositório** | `[https://github.com/jonatas472/agua-preta-ubs-2]` |
| **Data de preenchimento** | 09/09/2026|
| **Entrega final** | **10/12/2026** |


Bloco 1 — Nome e pitch do app

**Nome do app:** Água Preta UBS

**Pitch em uma frase:**
> "O Água Preta UBS ajuda a comunidade a marcar consultas e notificar a comunidade sobre vacinas sem precisar ir ao local para marcar presencialmente.**."

Bloco 2 — Problema

**Qual dor real vocês estão resolvendo? Descrevam uma situação concreta que alguém vive hoje.**
- Estamos tentando resolver os agendamentos de consulta que estão sendo feitos manualmente nas UBS de Água Preta
- Junto da Desinformação acerca das vacinas que estão sendo aplicadas nos postos, que apresentam dificuldade em notificar todos da comunidade.

**Como esse problema é resolvido hoje (sem o app)?**
- Esses problemas hoje são resolvidos de maneira manual, com fichas preenchidas manualmente e que requerem muito tempo do morador da comunidade.

Bloco 3 — Público-alvo
**Perfil Principal**
- Moradores da comunidade de Água Preta de todas as idades que utilizam o SUS local. O foco principal são adultos e idosos com pouca familiaridade com tecnologia, que necessitam de um sistema visualmente simples, altamente acessível para gerenciar sua saúde.

**Quando/Onde usam**
- Em casa ou no trabalho, no momento em que precisam agendar uma consulta sem enfrentar filas físicas de madrugada, ou quando recebem uma notificação de campanha de vacinação.

**Uma pessoa real que testaria o app**
- Mãe da Cecília (Funcionária da UBS Água Preta). Por trabalhar diretamente no local, ela validará se o aplicativo realmente facilita a rotina de agendamentos da unidade e se a interface está simples o suficiente para os pacientes reais da comunidade utilizarem.

Bloco 4 — Solução em uma tela

Descreva o que a **tela principal** mostra e o que o usuário consegue fazer nela.

- **A tela principal lista:**
- Um menu com três botões grandes e ilustrados: "Agendar Consulta", "Guia de Serviços da UBS" e "Mural Verdade ou Mito (Vacinas)". Abaixo do menu, um card destaca próximo agendamento do usuário (se houver).

- **A ação principal do usuário é:**
- Tocar no botão "Agendar Consulta" para abrir o formulário ou tocar em "Verdade ou Mito" para pesquisar sobre uma vacina.

-  **Depois de agir, o usuário vê:**
-  A confirmação do seu agendamento salvo na tela ou a resposta detalhada e oficial desmentindo a informação sobre a vacina.

Bloco 5 — Funcionalidades do MVP

Máximo de **4 funcionalidades**. Se tiver mais, corte. Lembre: *qualidade acima de complexidade*.

| # | Funcionalidade | Essencial? | Quem faz |
|---|---|---|---|
| F1 | Agendamento Local de Consultas | Sim | Jonatas |
| F2 | Painel de Serviços e Horários | Sim | Isi |
| F3 | Mural "Verdade ou Mito" (Vacinas) | Sim/Não | Marilia |
| F4 | Ajustes de Acessibilidade | Não | Cecília |

Bloco 6 — Fora do escopo

O que o app **não** vai fazer nesta entrega. Escrever isso aqui protege vocês de perder o prazo.

- ❌ Sincronização em nuvem e login
- ❌ Integração com o sistema real do SUS (ConecteSUS)
- ❌Chat em tempo real com médicos ou funcionários

Bloco 7 — Caminho técnico

Marque **uma** opção (as três valem a mesma nota):

- [ X ] **Opção A — Room:** dados salvos no próprio celular (lista de compras, agenda, diário de treino, controle financeiro)
- [ ] **Opção B — Retrofit:** dados vindos de uma API pública (notícias, filmes, feed, clima)
- [ ] **Opção C — Desafio:** API + salvar favoritos localmente

**Se escolheu B ou C — qual API?** _(link da documentação + precisa de chave? é gratuita?)_

**Bibliotecas que o grupo vai usar:**
- androidx.room:room-runtime, androidx.room:room-ktx, androidx.navigation:navigation-compose

**Onde entra o `try/catch`?** _(qual operação pode falhar: banco vazio, internet caindo, API fora do ar, campo em branco)_

- Pode falhar: O usuário tentar salvar o agendamento deixando o campo do nome ou do cartão SUS em branco, ou digitar letras no lugar de números.
- O usuário vê a mensagem: Um aviso na tela (Toast) dizendo: "Por favor, preencha todos os campos corretamente para realizar o agendamento."

- Bloco 8 — Identidade visual

| Item | Definição do grupo |
|---|---|
| Nome exibido (`strings.xml`) | UBS+ |
| Cor principal (hex, em `Color.kt`) | `#668D3D` |
| Ideia do ícone (512×512) |  Lâmpada estilizada em verde, representando conhecimento e soluções para a Unidade Básica de Saúde, com uma chama em tons de laranja/vermelho e elementos relacionados à saúde.  |
| `applicationId` | `br.edu.ifpe.ubsaude` |
| Versão inicial | `1.0` (versionCode `1`) |

Bloco 9 — Equipe, papéis e riscos

| Integrante | Papel principal | Responsável por |
|---|---|---|
| Marília | Dev / telas | Criar as telas XML/Compose (Home, Agendamento e Vacinas). |
| Jonatas | Dev / dados (Room ou Retrofit) | Criar o banco de dados Room para salvar e listar as consultas.   |
| Cecília | Design e identidade visual | Criar o logotipo, escolher a paleta de cores e o visual do app. |
| Isi | Documentação, build e entrega | Escrever os relatórios, configurar o GitHub e gerar o arquivo .apk. |

**Riscos — o que pode dar errado e o plano B:**

| Risco | Plano B |
|---|---|
| O banco de dados Room quebrar ou dar erro ao tentar salvar os agendamentos. | Usar o bloco try/catch para capturar a falha, exibir um aviso amigável para o usuário e armazenar os dados temporariamente em uma lista na memória (ArrayList) para não travar a apresentação. |
| Perda de código ou conflitos complexos no GitHub (erros de merge) perto do prazo final. | Criar o hábito de enviar commits e dar push no repositório ao final de cada dia de trabalho, garantindo que a versão funcional anterior esteja sempre salva na nuvem. |

Bloco 10 — Acordo de trabalho com IA

A implementação pode ser feita com o **Gemini no Android Studio**. Vocês orientam, ele digita — e cada integrante precisa saber explicar o que entrou no projeto. Regras completas em [`docs/USO_DE_IA.md`](docs/USO_DE_IA.md).

**Três regras que vamos escrever no nosso `AGENTS.md`** _(o arquivo que diz à IA como trabalhar no nosso projeto)_:

1. Gere códigos comentados: A IA deve incluir comentários breves explicando a lógica de cada função gerada, especialmente nas queries do Room.
2. Priorize código simples: Proibir a IA de usar bibliotecas complexas ou arquiteturas avançadas de nível sênior; o código deve seguir o padrão básico ensinado em sala de aula.
3. Não mude o Gradle sem permissão: A IA não pode alterar as dependências do arquivo build.gradle sozinha para evitar que quebre o build do projeto.
   
**Combinados do grupo:**

- [ ] Ninguém clica *Accept* no Agent Mode sem ler a mudança inteira.
- [ ] Quem aceitou o código escreve o comentário de fronteira do arquivo.
- [ ] Antes de cada marco, revisamos juntos: alguém aqui não entende alguma parte?
- [ ] Nenhuma chave de API ou senha vai para o prompt.
- Outro combinado nosso:

**Como vamos garantir que todos entendem tudo** _(ex.: quem implementa apresenta o arquivo aos outros; revezar as partes; revisar o pull request do colega)_:

-

Bloco 11 — Marcos até 10/12

| Marco | Prazo | Como se comprova no GitHub |
|---|---|---|
| M1 — Canvas preenchido + repositório criado | 16/09 | `CANVAS.md` no `main` |
| M2 — PRD aprovado + telas rascunhadas | 30/09 | `PRD.md` + imagens em `docs/` |
| M3 — Funcionalidade base rodando | 21/10 | tela principal lista dados + 1 ação + `try/catch` |
| M4 — Dados completos (Room/Retrofit) e erros tratados | 11/11 | commits da camada de dados |
| M5 — Identidade visual + `.apk` de release testado | 25/11 | ícone, cores, `.apk` testado por 2 pessoas de fora |
| M6 — `.aab` + material de loja + `README.md` | 02/12 | pasta `loja/` + `README.md` completo |
| **Entrega e apresentação** | **10/12** | tag `v1.0` no repositório |

Bloco 12 — Definição de pronto

O grupo só considera o app pronto quando **todas** estas frases forem verdadeiras:

- [ ] O app abre e não fecha sozinho depois de 5 minutos de uso.
- [ ] A tela principal mostra dados reais (não texto de exemplo fixo no código).
- [ ] A ação principal funciona e o resultado aparece na tela.
- [ ] Quando algo falha, aparece uma mensagem clara — o app não quebra.
- [ ] O app tem nome, ícone e cor próprios (nada de ícone padrão do Android).
- [ ] Duas pessoas de fora do grupo instalaram o `.apk` e conseguiram usar sem explicação.
- [ ] O `README.md` explica o que o app faz, com o que foi feito e como gerar o build.
- [ ] O `docs/USO_DE_IA.md` e o `AGENTS.md` estão preenchidos.
- [ ] **Cada integrante consegue abrir o projeto e fazer uma mudança pequena sozinho** — trocar um texto, acrescentar um campo, mudar a ordem da lista.
- [ ] Todo arquivo nosso tem o comentário de fronteira escrito por nós.

Validação do professor

| | |
|---|---|
| Data | |
| Situação | ( ) Aprovado ( ) Aprovado com ajustes ( ) Refazer |
| Observações | |
