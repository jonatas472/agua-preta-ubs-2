# 📄 PRD — Documento de Requisitos do Produto

> **O que é um PRD?** É o documento que responde **o quê** o app faz e **por quê** — não *como* ele é programado.
> Quem lê o PRD deve conseguir entender o app inteiro sem abrir o código.
>
> **Regra do PRD:** todo requisito precisa ser verificável. "O app tem que ser rápido" não é requisito. "A lista abre em menos de 2 segundos" é.
>
> Preencha depois do [`CANVAS.md`](CANVAS.md) aprovado. Substitua todo texto em _itálico_ e apague os exemplos.

| | |
|---|---|
| **App** | |
| **Grupo** | Apollo |
| **Autores** | Jonatas Calado, Cecilia Helena, Isi Luana, Marilia Gabrielly |
| **Versão do documento** | 1.0 |
| **Última atualização** | ___/___/2026 |
| **Status** | ( ) Rascunho (+ ) Em revisão ( ) Aprovado |

---

## 1. Visão do produto

**Pitch:** "O Água Preta UBS ajuda a comunidade a marcar consultas e notificar a comunidade sobre vacinas sem precisar ir ao local para marcar presencialmente.**."

Bloco 2 — Problema

Qual dor real vocês estão resolvendo? Descrevam uma situação concreta que alguém vive hoje.

Estamos tentando resolver os agendamentos de consulta que estão sendo feitos manualmente nas UBS de Água Preta
Junto da Desinformação acerca das vacinas que estão sendo aplicadas nos postos, que apresentam dificuldade em notificar todos da comunidade.
Como esse problema é resolvido hoje (sem o app)?

Esses problemas hoje são resolvidos de maneira manual, com fichas preenchidas manualmente e que requerem muito tempo do morador da comunidade.
Bloco 3 — Público-alvo Perfil Principal

Moradores da comunidade de Água Preta de todas as idades que utilizam o SUS local. O foco principal são adultos e idosos com pouca familiaridade com tecnologia, que necessitam de um sistema visualmente simples, altamente acessível para gerenciar sua saúde.
Quando/Onde usam

Em casa ou no trabalho, no momento em que precisam agendar uma consulta sem enfrentar filas físicas de madrugada, ou quando recebem uma notificação de campanha de vacinação.
Uma pessoa real que testaria o app

Mãe da Cecília (Funcionária da UBS Água Preta). Por trabalhar diretamente no local, ela validará se o aplicativo realmente facilita a rotina de agendamentos da unidade e se a interface está simples o suficiente para os pacientes reais da comunidade utilizarem.
Bloco 4 — Solução em uma tela

Descreva o que a tela principal mostra e o que o usuário consegue fazer nela.

A tela principal lista:

Um menu com três botões grandes e ilustrados: "Agendar Consulta", "Guia de Serviços da UBS" e "Mural Verdade ou Mito (Vacinas)". Abaixo do menu, um card destaca próximo agendamento do usuário (se houver).

A ação principal do usuário é:

Tocar no botão "Agendar Consulta" para abrir o formulário ou tocar em "Verdade ou Mito" para pesquisar sobre uma vacina.

Depois de agir, o usuário vê:

A confirmação do seu agendamento salvo na tela ou a resposta detalhada e oficial desmentindo a informação sobre a vacina.
## 2. Público e cenário de uso

**Usuário-alvo:**

**História de uso (conte como uma cena real):**
> _"São 19h, o idoso acabou de lembrar que precisa ir a UBS. Ele abre o app e marca sua consulta na UBS mais proxima. Em menos de 30 segundos, ele marcar uma consulta para a data que quiser."_

---

## 3. Objetivos e não-objetivos

**Objetivos desta versão (v1.0):**

1. Permitir que o usuário realize e salve localmente um agendamento de consulta.
2. Permitir que o usuário consulte os serviços e horários disponíveis na UBS.
3. Disponibilizar um mural de "Verdade ou Mito" com informações relacionadas às vacinas.

**Não-objetivos (fora do escopo — copie o Bloco 6 do Canvas):**

- ❌ Sincronização em nuvem e login.
- ❌ Integração com o sistema real do SUS ou ConecteSUS.
- ❌ Chat em tempo real com médicos ou funcionários.

---

## 4. Requisitos funcionais

Escreva no formato de história de usuário + critério de aceite. Prioridade: **Must** (sem isso não entrega), **Should** (importante), **Could** (se sobrar tempo).

| ID | História de usuário | Critério de aceite | Prioridade |
|---|---|---|---|
| RF01 | Como usuário, quero realizar um agendamento de consulta para não precisar fazer o agendamento presencialmente. | Ao preencher os dados obrigatórios e confirmar, o agendamento é salvo localmente e uma confirmação é exibida na tela. | Must |
| RF02 | Como usuário, quero consultar os serviços e horários da UBS para saber quais serviços estão disponíveis. | Ao acessar o painel de serviços, o usuário consegue visualizar os serviços e seus respectivos horários. | Must |
| RF03 | Como usuário, quero consultar informações sobre vacinas para verificar se uma informação é verdadeira ou falsa. | Ao acessar o mural "Verdade ou Mito", o usuário consegue visualizar a informação e sua explicação correspondente. | Must |
| RF04 | Como usuário, quero visualizar meus próximos agendamentos para saber quando tenho uma consulta marcada. | Após realizar um agendamento, ele aparece na área de próximos agendamentos. | Should |
| RF05 | | | Could |

---

## 5. Requisitos não funcionais

| ID | Requisito | Como será verificado |
|---|---|---|
| RNF01 | O app não pode fechar sozinho durante o uso normal | 5 minutos de uso contínuo sem crash, em 2 celulares diferentes |
| RNF02 | Toda operação que pode falhar está dentro de `try/catch` | Revisão do código: banco, rede e entradas do usuário |
| RNF03 | Nenhuma falha mostra tela branca ou fecha o app — sempre há mensagem ao usuário | Testes de falha da seção 9 |
| RNF04 | O app roda a partir do Android ___ (minSdk) | Instalação em dispositivo real |
| RNF05 | Textos visíveis ficam em `strings.xml`, não escritos direto no código | Revisão do código |
| RNF06 | Todo arquivo do pacote do app tem comentário de fronteira escrito pelo grupo | Revisão do código |
| RNF07 | Qualquer integrante consegue localizar e alterar qualquer parte do app | Teste de mudança ao vivo (rubrica) |
| RNF08 | | |

---

## 6. Telas e navegação

**Mapa de navegação:**

```
[Tela Principal — lista]
      │
      ├── toca no "+"      → [Tela de Cadastro/Detalhe]
      ├── toca em um item  → [Tela de Detalhe]
      └── (estado vazio)   → mensagem "____"
```

| Tela | O que mostra | Ações disponíveis |
|---|---|---|
| Principal | | |
| Detalhe/Cadastro | | |

**Rascunhos das telas:** coloque as imagens ou fotos dos desenhos em `docs/telas/` e liste os arquivos aqui.

- `docs/telas/01-principal.png`
- `docs/telas/02-detalhe.png`

---

## 7. Dados

### Se Opção A ou C (Room)

**Entidade principal:** `Agendamento`

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| `id` | Long | sim | chave primária, autogerada |
| nome | String | sim | Nome do usuário |
| data | String | sim | Data da consulta |
| cartaoSUS | String | sim | Número do cartão SUS |
| horario | String | sim | Horário da consulta |
| servico | String | sim | Serviço/consulta escolhida |

**Operações necessárias:** ( X ) inserir ( X ) listar ( ) atualizar ( ) excluir

### Se Opção B ou C (Retrofit)

| Item | Definição |
|---|---|
| API utilizada | |
| Documentação | |
| Endpoint principal | `GET https://...` |
| Precisa de chave? | ( ) não ( ) sim — onde ela fica guardada: |
| Limite de requisições | |
| Campos usados na tela | |

**Exemplo de resposta da API** _(cole um trecho real do JSON)_:

```json

```

---

## 8. Arquitetura e tecnologias

| Item | Escolha |
|---|---|
| Linguagem | Kotlin |
| Interface | ( X ) Jetpack Compose ( ) XML/Views |
| Persistência | (X ) Room ( ) — |
| Rede | ( ) Retrofit ( ) — |
| Outras bibliotecas | Room KTX |
| `minSdk` / `targetSdk` | Verificar valores definidos no build.gradle.kts |

**Organização de pastas do projeto:**

```
app/src/main/java/br/edu/ifpe<ubsaude/
├── data/
│   ├── local/
│   ├── remote/
│   └── repository/
│
├── model/
│
├── ui/
│   ├── theme/
│   ├── navigation/
│   └── features/
│
└── MainActivity.kt
```

---

## 9. Tratamento de erros

Liste o que pode dar errado e **o que o usuário vê** em cada caso. Cada linha aqui deve virar um `try/catch` no código.

| Situação de falha | O que o app faz | Mensagem para o usuário |
|---|---|---|
| Sem internet (Opção B/C) | Mantém a tela e mostra aviso + botão "Tentar de novo" | "Sem conexão. Verifique a internet e tente novamente." |
| API fora do ar / erro 500 | | |
| Lista vazia (nenhum dado ainda) | Mantém a tela funcionando e informa que não existem agendamentos. | "Nenhum agendamento encontrado." |
| Campo obrigatório em branco | Impede o salvamento até que os campos sejam preenchidos. | "Por favor, preencha todos os campos corretamente para realizar o agendamento." |
| Erro ao salvar no banco | Captura o erro e mantém o aplicativo aberto. | "Não foi possível salvar o agendamento. Tente novamente." |

---

## 10. Identidade visual e publicação

| Item | Definição | Onde fica |
|---|---|---|
| Nome do app | UBS+ | `strings.xml` |
| Cor principal | `#668D3D` | `Color.kt` |
| Cor secundária | `Definida posteriormente pelo grupo` | `Color.kt` |
| Ícone 512×512 | | `loja/icone-512.png` |
| `applicationId` | br.edu.ifpe.ubsaude` | `build.gradle.kts` |
| `versionName` / `versionCode` | `1.0` / `1` | `build.gradle.kts` |

**Material da loja** (Etapa 4 do projeto):

| Artefato | Limite | Conteúdo |
|---|---|---|
| Título | 30 caracteres | Água Preta UBS |
| Descrição curta | 80 caracteres | Aplicativo para facilitar o acesso aos serviços da UBS. |
| Descrição completa | — | _(escreva em `loja/descricao.md`)_ |
| Imagem de destaque | 1024×500 | `loja/destaque-1024x500.png` |
| Screenshots | mín. 2 | `loja/screenshots/` |
| Esboço de privacidade | — | `loja/privacidade.md` — o app coleta algum dado? Sai do celular? |
| Arquivo `.aab` | — | `loja/app-release.aab` |

---

## 11. Plano de testes

| # | O que testar | Passos | Resultado esperado | OK? |
|---|---|---|---|---|
| T1 | Abrir o app pela primeira vez | Instalar e abrir | Tela principal aparece com estado vazio explicado | |
| T2 | Ação principal | Abrir "Agendar Consulta", preencher os campos e confirmar. | Agendamento é salvo e confirmação aparece. | |
| T3 | Falha de rede/banco | Ativar modo avião e repetir T2 | Mensagem clara, app não fecha | |
| T4 | Reabrir o app | Fechar e abrir de novo | Dados continuam lá (Opção A/C) | |
| T5 | Teste com usuário externo | Pessoa de fora usa sem explicação | Consegue completar a ação principal | |
| T6 | Campo Obrigatório Vazio | Tentar confirmar o agendamento sem preencher todos os campos. | Mensagem de erro aparece e o app não fecha. | |
| T7 | Lista de agendamentos | Realizar um agendamento e acessar os próximos agendamentos. | Realizar um agendamento e acessar os próximos agendamentos. | |
| T8 | Serviços | Acessar "Guia de Serviços da UBS". | Serviços e horários são exibidos. | | |
| T9 | Vacinas | Acessar "Verdade ou Mito". | As informações sobre vacinas são exibidas corretamente. | | |

**Testado em:** _(modelo do celular e versão do Android — pelo menos 2 aparelhos)_

---

## 12. Cronograma

| Marco | Prazo | Responsável | Status |
|---|---|---|---|
| M1 — Canvas + repositório | 16/09 | | |
| M2 — PRD aprovado + telas | 30/09 | | |
| M3 — Funcionalidade base | 21/10 | | |
| M4 — Dados e erros tratados | 11/11 | | |
| M5 — Identidade + `.apk` testado | 25/11 | | |
| M6 — `.aab` + loja + README | 02/12 | | |
| **Entrega e apresentação** | **10/12** | grupo | |

---

## 13. Riscos

| Risco | Impacto | Plano B |
|---|---|---|
| A API escolhida sai do ar ou passa a exigir pagamento | Alto | Trocar para ____ ou usar dados locais |
| Integrante fica sem computador | Médio | Outro integrante poderá acessar o projeto pelo GitHub e continuar a implementação. |
| O banco de dados Room apresentar erro ao salvar os agendamentos. | Alto | Utilizar try/catch, mostrar uma mensagem amigável e, se necessário para a apresentação, utilizar temporariamente uma lista em memória. |
| Perda de código ou conflitos no GitHub próximos do prazo. | Alto | Realizar commits e push regularmente e manter a versão funcional anterior no repositório. |
| Alguma funcionalidade ultrapassar o tempo disponível. | Médio | Priorizar as funcionalidades Must e deixar funcionalidades Should/Could para depois. |

---

## 14. Como vamos orientar a implementação com IA

A implementação usa o **Gemini no Android Studio**. Este PRD é o documento que diz à IA o que construir — quanto mais preciso ele estiver, menos a IA inventa. Regras completas em [`docs/USO_DE_IA.md`](docs/USO_DE_IA.md).

**Recursos que vamos usar:** ( X ) Chat ( X ) Agent Mode ( X ) Explain Code ( X ) Ask Gemini no Logcat ( ) Generate Unit Tests ( ) Transform UI

**Regras que colocamos no `AGENTS.md`** _(resumo — o arquivo fica na raiz do repositório)_:

- Gerar códigos comentados: a IA deve incluir comentários breves explicando a lógica de cada função gerada, especialmente nas operações do Room.
- Priorizar código simples: não utilizar bibliotecas complexas ou arquiteturas avançadas sem necessidade. O código deve seguir o nível ensinado em sala de aula.
- Não alterar o Gradle sem permissão: a IA não pode alterar as dependências do build.gradle sem autorização do grupo.

**Divisão do perímetro explicável** — quem responde por explicar o quê na apresentação:

| Parte do código | Responsável |
|---|---|
| Telas (`ui/`) | Marilia |
| Dados (`data/`) | Jonatas |
| Identidade visual e recursos | Cecilia |
| Build e artefatos de loja | Isi |

**Decisões que o grupo tomou contra a sugestão da IA** _(preencher ao longo do projeto — isso conta a favor na avaliação)_:

-O grupo escolheu Room em vez de uma API externa para o armazenamento dos dados.
-O grupo decidiu manter o aplicativo sem login e sem sincronização em nuvem na versão inicial.
-O grupo decidiu priorizar uma interface simples e acessível para adultos e idosos.
-O grupo decidiu limitar o MVP a quatro funcionalidades.
-

---

## 15. Histórico de versões deste documento

| Versão | Data | Autor | O que mudou |
|---|---|---|---|
| 1.0 | 23/09/2026 | Grupo Apollo | Versão inicial |
| | | | |

