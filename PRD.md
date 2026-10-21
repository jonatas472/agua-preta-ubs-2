# PRD — UBS+

Este documento registra o escopo, os requisitos e os critérios de aceite do protótipo. Os requisitos descrevem o resultado esperado; a seção de estado atual separa o que já está demonstrado do que ainda depende de implementação.

| | |
|---|---|
| **App** | UBS+ (Água Preta UBS) |
| **Grupo** | Apollo |
| **Autores** | Jonatas Calado, Cecília Helena, Isi Luana e Marília Gabrielly |
| **Versão do documento** | 1.0 |
| **Última atualização** | 21/10/2026 |
| **Status** | Em revisão |

---

## 1. Visão do produto

**Proposta:** O UBS+ organiza informações sobre os serviços da UBS de Água Preta e apresenta um fluxo local de agendamento e conteúdo educativo sobre vacinação. O protótipo atual não envia pedidos à unidade.

## 1. Visão do produto

O processo de agendamento e a divulgação de informações sobre vacinação foram identificados pelo grupo como oportunidades de melhoria. O aplicativo pretende reunir informações sobre os serviços da unidade e oferecer um fluxo simples para registrar dados de agendamento no próprio dispositivo.

## 2. Público e cenário de uso

**Usuário-alvo:** moradores de Água Preta que utilizam a UBS, com atenção especial a adultos e idosos que se beneficiam de textos legíveis e navegação direta.

**Cenário:** a pessoa consulta os horários da unidade, preenche o formulário de agendamento e recebe uma confirmação do que foi registrado. Como não há integração com a UBS, a interface deve deixar claro que esse registro não confirma uma consulta nem é enviado à unidade.

### Fluxo principal

A tela inicial oferece acesso às áreas disponíveis no protótipo. O fluxo de agendamento abre um formulário e, após a confirmação, apresenta uma tela de resumo. Serviços, perfil e acessibilidade também têm rotas no aplicativo. O mural de vacinação faz parte da proposta, mas ainda não está ligado à navegação atual.
## 2. Público e cenário de uso

**Usuário-alvo:**

**História de uso (conte como uma cena real):**
> _"São 19h, o idoso acabou de lembrar que precisa ir a UBS. Ele abre o app e marca sua consulta na UBS mais proxima. Em menos de 30 segundos, ele marcar uma consulta para a data que quiser."_

---

## 3. Objetivos e limites

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
| RF01 | Como usuário, quero registrar os dados de um agendamento no aplicativo. | Após preencher os campos obrigatórios, o registro é salvo no dispositivo e a confirmação informa que não foi enviado à UBS. | Must |
| RF02 | Como usuário, quero consultar os serviços e horários da UBS para saber quais serviços estão disponíveis. | Ao acessar o painel de serviços, o usuário consegue visualizar os serviços e seus respectivos horários. | Must |
| RF03 | Como usuário, quero consultar informações sobre vacinas com fonte identificada. | Cada conteúdo exibe a afirmação, a explicação e a fonte; o mural ainda precisa ser implementado. | Should |
| RF04 | Como usuário, quero visualizar meus próximos agendamentos para saber quando tenho uma consulta marcada. | Após realizar um agendamento, ele aparece na área de próximos agendamentos. | Should |
| RF05 | Como usuário, quero encontrar controles de acessibilidade e informações de perfil. | As opções disponíveis são acessíveis a partir das rotas de perfil e acessibilidade. | Could |

---

## 5. Requisitos não funcionais

| ID | Requisito | Como será verificado |
|---|---|---|
| RNF01 | O app não pode fechar sozinho durante o uso normal | 5 minutos de uso contínuo sem crash, em 2 celulares diferentes |
| RNF02 | Falhas de leitura ou gravação não encerram o aplicativo e recebem tratamento adequado | Testar estados de erro e revisar a camada de dados |
| RNF03 | Nenhuma falha mostra tela branca ou fecha o app — sempre há mensagem ao usuário | Testes de falha da seção 9 |
| RNF04 | O app instala em dispositivos com Android 7.0 (API 24) ou superior | Instalação em dispositivo ou emulador com API 24 |
| RNF05 | Textos visíveis ficam em `strings.xml`, não escritos direto no código | Revisão do código |
| RNF06 | Todo arquivo do pacote do app tem comentário de fronteira escrito pelo grupo | Revisão do código |
| RNF07 | Qualquer integrante consegue localizar e alterar qualquer parte do app | Teste de mudança ao vivo (rubrica) |

---

## 6. Telas e navegação

**Mapa de navegação:**

```
[Início]
      ├── Agendar consulta → Formulário → Confirmação
      ├── Serviços e horários
      ├── Meus agendamentos
      ├── Perfil
      └── Acessibilidade

O mural de vacinação está planejado, mas não está conectado à navegação atual.
```

| Tela | O que mostra | Ações disponíveis |
|---|---|---|
| Início | Acesso às principais áreas e destaque de agendamento demonstrativo | Abrir uma área pelo menu ou pela navegação |
| Formulário | Campos para dados do paciente e consulta | Preencher e confirmar; o fluxo atual é demonstrativo |
| Confirmação | Resumo do formulário | Voltar ao início |
| Serviços, agendamentos, perfil e acessibilidade | Conteúdo de demonstração do protótipo | Navegar pelas opções disponíveis |

**Rascunhos das telas:** não há imagens de rascunho registradas neste repositório. As telas do protótipo estão em `src/app/src/main/java/br/edu/ifpe/ubsaude/Screens.kt`.

---

## 7. Dados

### Se Opção A ou C (Room)

**Entidades do aplicativo:** 

`Cadastro`

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| `id` | Int | sim | chave primária, autogerada |
| `nome` | String | sim | Nome do usuário |
| `cartaoSUS` | String | sim | Número do cartão SUS |
| `dataNascimento` | String | sim | Data de nascimento |
| `telefone` | String | sim | Telefone do usuário |

`Agendamento`

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| `id` | Int | sim | chave primária, autogerada |
| nome | String | sim | Nome do usuário |
| data | String | sim | Data da consulta |
| cartaoSUS | String | sim | Número do cartão SUS |
| horario | String | sim | Horário da consulta |
| servico | String | sim | Serviço/consulta escolhida |

#### Serviço

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| id | Int | sim | chave primária, autogerada |
| nome | String | sim | Nome do serviço |
| descricao | String | sim | Descrição do serviço |
| horario | String | sim | Horário de atendimento |

**Operações necessárias:** ( X ) inserir ( X ) listar ( ) atualizar ( ) excluir
#### Vacina

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| `id` | Int | sim | chave primária, autogerada |
| `vacina` | String | sim | Nome da vacina |
| `afirmacao` | String | sim | Afirmação apresentada no mural |
| `resposta` | String | sim | Classificação da afirmação |
| `explicacao` | String | sim | Contexto e fonte a serem definidos |

**Operações previstas:** inserir e listar.

### Integração remota

| Item | Definição |
|---|---|
| API utilizada | Não prevista nesta versão |
| Documentação | Não se aplica |
| Endpoint principal | Não se aplica |
| Precisa de chave? | Não |
| Limite de requisições | Não se aplica |
| Campos usados na tela | Não se aplica |

Não há integração com API nesta versão.

---

## 8. Arquitetura e tecnologias

| Item | Escolha |
|---|---|
| Linguagem | Kotlin |
| Interface | ( X ) Jetpack Compose ( ) XML/Views |
| Persistência | Room (entidades declaradas; banco e DAO ainda não integrados) |
| Rede | ( ) Retrofit ( ) — |
| Outras bibliotecas | Room KTX e Navigation Compose |
| `minSdk` / `targetSdk` | 24 / 36; `compileSdk` 37 |

**Arquivos principais do protótipo:**

```
src/app/src/main/java/br/edu/ifpe/ubsaude/
├── Data.kt
├── MainActivity.kt
├── Screens.kt
└── ui/theme/
```

---

## 9. Tratamento de erros

Liste o que pode dar errado e **o que o usuário vê** em cada caso. Cada linha aqui deve virar um `try/catch` no código.

| Situação de falha | O que o app faz | Mensagem para o usuário |
|---|---|---|
| Sem internet | Não se aplica: o protótipo não faz chamadas de rede. | — |
| API fora do ar / erro 500 | Não se aplica: não há integração com API. | — |
| Lista vazia (nenhum dado ainda) | Mantém a tela funcionando e informa que não existem agendamentos. | "Nenhum agendamento encontrado." |
| Campo obrigatório em branco | Impede o salvamento até que os campos sejam preenchidos. | "Por favor, preencha todos os campos corretamente para realizar o agendamento." |
| Erro ao salvar no banco | Após a integração com Room, manter a tela aberta e informar que o registro não foi salvo. | "Não foi possível salvar. Tente novamente." |

---

## 10. Identidade visual e publicação

Os materiais de loja não fazem parte do protótipo atual e ainda não foram preparados.

| Item | Definição | Onde fica |
|---|---|---|
| Nome do app | UBS+ | `strings.xml` |
| Cor principal | `#668D3D` | `Color.kt` |
| Cor secundária | Ainda não definida | `Color.kt` |
| Ícone 512×512 | Ainda não disponível | `loja/icone-512.png` |
| `applicationId` | `br.edu.ifpe.ubsaude` | `src/app/build.gradle.kts` |
| `versionName` / `versionCode` | `1.0` / `1` | `build.gradle.kts` |

**Material da loja** (Etapa 4 do projeto):

| Artefato | Limite | Conteúdo |
|---|---|---|
| Título | 30 caracteres | Água Preta UBS |
| Descrição curta | 80 caracteres | Aplicativo para facilitar o acesso aos serviços da UBS. |
| Descrição completa | — | Ainda não preparada (`loja/descricao.md`) |
| Imagem de destaque | 1024×500 | `loja/destaque-1024x500.png` |
| Screenshots | mín. 2 | `loja/screenshots/` |
| Esboço de privacidade | — | `loja/privacidade.md` — o app coleta algum dado? Sai do celular? |
| Arquivo `.aab` | — | `loja/app-release.aab` |

---

## 11. Plano de testes

Os testes ligados ao Room e ao mural de vacinação só podem ser executados depois que essas partes forem integradas.

| # | O que testar | Passos | Resultado esperado | OK? |
|---|---|---|---|---|
| T1 | Abrir o app pela primeira vez | Instalar e abrir | Tela principal aparece com estado vazio explicado | |
| T2 | Ação principal | Abrir "Agendar Consulta", preencher os campos e confirmar. | Agendamento é salvo e confirmação aparece. | |
| T3 | Falha ao salvar | Após integrar o Room, simular uma falha de gravação | Mensagem clara; o aplicativo permanece aberto | |
| T4 | Reabrir o app | Fechar e abrir de novo | Dados continuam lá (Opção A/C) | |
| T5 | Teste com usuário externo | Pessoa de fora usa sem explicação | Consegue completar a ação principal | |
| T6 | Campo Obrigatório Vazio | Tentar confirmar o agendamento sem preencher todos os campos. | Mensagem de erro aparece e o app não fecha. | |
| T7 | Lista de agendamentos | Registrar um agendamento e abrir a lista. | O registro aparece com data, horário e serviço. | |
| T8 | Serviços | Acessar "Guia de Serviços da UBS". | Serviços e horários são exibidos. | | |
| T9 | Vacinas | Após integrar o mural, abrir um item de conteúdo. | A afirmação, explicação e fonte são exibidas. | | |

**Dispositivos testados:** ainda não registrados. Anotar modelo e versão do Android após os testes.

---

## 12. Cronograma

O cronograma abaixo é o plano inicial; o status dos marcos ainda precisa ser atualizado pelo grupo.

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
| A persistência local apresenta falhas durante a integração | Alto | Manter o fluxo demonstrativo e informar claramente que os dados não foram salvos |
| Integrante fica sem computador | Médio | Outro integrante poderá acessar o projeto pelo GitHub e continuar a implementação. |
| O banco de dados Room apresentar erro ao salvar os agendamentos. | Alto | Utilizar try/catch, mostrar uma mensagem amigável e, se necessário para a apresentação, utilizar temporariamente uma lista em memória. |
| Perda de código ou conflitos no GitHub próximos do prazo. | Alto | Realizar commits e push regularmente e manter a versão funcional anterior no repositório. |
| Alguma funcionalidade ultrapassar o tempo disponível. | Médio | Priorizar as funcionalidades Must e deixar funcionalidades Should/Could para depois. |

---

## 14. Uso de ferramentas de IA

A equipe pode usar ferramentas de IA como apoio. Cada integrante deve revisar as alterações antes de aceitá-las e conseguir explicar o código incluído no projeto. As orientações completas estão em [AGENTS.md](AGENTS.md).

As decisões de escopo do grupo são manter os dados no dispositivo, não incluir login nem sincronização em nuvem e priorizar uma interface simples. O MVP terá no máximo quatro funcionalidades.

---

## 15. Histórico de versões deste documento
## 15. Histórico de versões

| Versão | Data | Responsável | Alterações |
|---|---|---|---|
| 1.0 | 23/09/2026 | Grupo Apollo | Versão inicial |
| 1.1 | 21/10/2026 | Grupo Apollo | Revisão editorial e alinhamento com o protótipo atual |

