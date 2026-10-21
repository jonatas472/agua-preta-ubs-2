| | |
|---|---|
| **Grupo nº** | Apollo |
| **Integrantes (3 a 4)** | Jonatas Calado, Cecilia Helena, Isi Luana, Marilia Gabrielly |
| **Turma** | 3º ano — Ensino Médio |
| **Repositório** | [github.com/jonatas472/agua-preta-ubs-2](https://github.com/jonatas472/agua-preta-ubs-2) |
| **Data de preenchimento** | 09/09/2026 |
| **Última revisão** | 21/10/2026 |
| **Entrega final** | **10/12/2026** |


Bloco 1 — Nome e pitch do app

**Nome do app:** Água Preta UBS

**Pitch em uma frase:**
> O Água Preta UBS reúne informações sobre a UBS de Água Preta e busca facilitar o acesso da comunidade a serviços, agendamentos e orientações sobre vacinação.

Bloco 2 — Problema

**Qual dor real vocês estão resolvendo? Descrevam uma situação concreta que alguém vive hoje.**
- O agendamento de consultas é feito presencialmente, o que pode exigir deslocamento e espera dos moradores.
- A divulgação de informações sobre vacinação nem sempre alcança toda a comunidade.

**Como esse problema é resolvido hoje (sem o app)?**
- Atualmente, o morador precisa buscar atendimento ou informações diretamente na unidade. O grupo identificou esse processo como uma oportunidade para organizar informações e facilitar o acesso.

Bloco 3 — Público-alvo
**Perfil Principal**
- Moradores de Água Preta que utilizam os serviços da UBS, com atenção especial a adultos e idosos que preferem uma interface simples, legível e fácil de navegar.

**Quando/Onde usam**
- Em casa ou no trabalho, quando procuram informações sobre os serviços da unidade ou precisam iniciar um pedido de agendamento.

**Uma pessoa real que testaria o app**
- Uma profissional da UBS indicada por uma integrante do grupo poderá avaliar se as informações e os fluxos propostos correspondem à rotina da unidade. A participação depende de disponibilidade e autorização.

Bloco 4 — Solução em uma tela

Descreva o que a **tela principal** mostra e o que o usuário consegue fazer nela.

- **A tela principal lista:**
- A tela inicial dá acesso ao agendamento, aos serviços e horários da UBS e, como funcionalidade planejada, a informações sobre vacinação. Quando houver dados disponíveis, também poderá destacar o próximo agendamento.

- **A ação principal do usuário é:**
- Abrir o formulário de agendamento ou consultar os serviços disponíveis. O mural de vacinação permanece como parte da proposta e depende de implementação.

-  **Depois de agir, o usuário vê:**
- A confirmação do preenchimento do formulário. Enquanto não houver integração com a UBS, ela não representa uma consulta confirmada. O mural de vacinação ainda depende de implementação.

Bloco 5 — Funcionalidades do MVP

Máximo de **4 funcionalidades**. Se tiver mais, corte. Lembre: *qualidade acima de complexidade*.

| # | Funcionalidade | Essencial? | Quem faz |
|---|---|---|---|
| F1 | Agendamento Local de Consultas | Sim | Jonatas |
| F2 | Painel de Serviços e Horários | Sim | Isi |
| F3 | Mural "Verdade ou Mito" sobre vacinas | Sim | Marília |
| F4 | Visualização dos próximos agendamentos | Não | Cecília |

Bloco 6 — Fora do escopo

O que o app **não** vai fazer nesta entrega. Escrever isso aqui protege vocês de perder o prazo.

- Sincronização em nuvem e autenticação remota.
- Integração com sistemas oficiais do SUS, incluindo o ConecteSUS.
- Chat em tempo real com profissionais da unidade.

Bloco 7 — Caminho técnico

Marque **uma** opção (as três valem a mesma nota):

- [ X ] **Opção A — Room:** dados salvos no próprio celular (lista de compras, agenda, diário de treino, controle financeiro)
- [ ] **Opção B — Retrofit:** dados vindos de uma API pública (notícias, filmes, feed, clima)
- [ ] **Opção C — Desafio:** API + salvar favoritos localmente

**Se escolheu B ou C — qual API?** _(link da documentação + precisa de chave? é gratuita?)_

**Bibliotecas que o grupo vai usar:**
- androidx.room:room-runtime, androidx.room:room-ktx, androidx.navigation:navigation-compose

**Validação e tratamento de falhas:**

- O formulário deve validar os campos obrigatórios e apresentar uma mensagem clara quando houver dados ausentes ou inválidos.
- Falhas de persistência devem ser informadas sem encerrar o aplicativo. A persistência ainda precisa ser integrada ao fluxo.

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

A equipe pode usar ferramentas de IA como apoio, mas cada integrante deve revisar e conseguir explicar as alterações aceitas. As regras do projeto estão em [`../AGENTS.md`](../AGENTS.md).

**Três regras que vamos escrever no nosso `AGENTS.md`** _(o arquivo que diz à IA como trabalhar no nosso projeto)_:

1. Gere códigos comentados: inclua comentários breves quando ajudarem a explicar a lógica, especialmente nas consultas do Room.
2. Priorize código simples: use soluções compatíveis com o nível e os padrões adotados pelo grupo.
3. Não mude o Gradle sem permissão: A IA não pode alterar as dependências do arquivo build.gradle sozinha para evitar que quebre o build do projeto.
   
**Combinados do grupo:**

- [ ] Ninguém clica *Accept* no Agent Mode sem ler a mudança inteira.
- [ ] Quem aceitou o código escreve o comentário de fronteira do arquivo.
- [ ] Antes de cada marco, revisamos juntos: alguém aqui não entende alguma parte?
- [ ] Nenhuma chave de API ou senha vai para o prompt.
- Outro combinado do grupo: a definir.

**Como vamos garantir que todos entendem tudo** _(ex.: quem implementa apresenta o arquivo aos outros; revezar as partes; revisar o pull request do colega)_:

-

Bloco 11 — Marcos até 10/12
Bloco 11 — Marcos até 10/12

As datas abaixo pertencem ao cronograma inicial. O grupo deve atualizar a situação de cada marco conforme o andamento do projeto.

| Marco | Prazo | Como se comprova no GitHub |
|---|---|---|
| M1 — Canvas preenchido + repositório criado | 16/09 | `CANVAS.md` no `main` |
| M2 — PRD aprovado + telas rascunhadas | 30/09 | `PRD.md` + imagens em `docs/` |
| M3 — Funcionalidade base rodando | 21/10 | tela principal lista dados + uma ação + tratamento de falhas |
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
- [ ] O `AGENTS.md` está revisado e atualizado.
- [ ] **Cada integrante consegue abrir o projeto e fazer uma mudança pequena sozinho** — trocar um texto, acrescentar um campo, mudar a ordem da lista.
- [ ] Todo arquivo nosso tem o comentário de fronteira escrito por nós.

Validação do professor

| | |
|---|---|
| Data | |
| Situação | ( ) Aprovado ( ) Aprovado com ajustes ( ) Refazer |
| Observações | |
