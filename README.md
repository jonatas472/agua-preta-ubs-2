# 🏥 Água Preta UBS

<p align="center">
  <img src="https://shields.io" alt="Status">
  <img src="https://shields.io" alt="Kotlin">
  <img src="https://shields.io" alt="Jetpack Compose">
</p>

---

## 📝 Sobre o Projeto

O **Água Preta UBS** é um aplicativo Android nativo desenvolvido pelo **Grupo Apollo** (alunos do 3º ano do Ensino Médio). O projeto nasceu com o propósito de aproximar a tecnologia da comunidade, facilitando o acesso dos moradores do município de Água Preta aos serviços essenciais da Unidade Básica de Saúde local.

## 🎯 Objetivos do Aplicativo

O app foi projetado para centralizar as seguintes funcionalidades no dispositivo do cidadão:
* **📅 Agendamento Prático:** Realizar marcações de consultas médicas diretamente pelo celular.
* **🏥 Guia de Serviços:** Consultar os serviços oferecidos e os respectivos horários de funcionamento da UBS.
* **💉 Carteira de Vacinação:** Acessar informações detalhadas, cronogramas e orientações sobre vacinas.
* **📋 Painel de Compromissos:** Visualizar de forma rápida e clara os próximos agendamentos confirmados.

---

## 🛠️ Tecnologias e Arquitetura

O ecossistema de desenvolvimento foi estruturado com as tecnologias mais modernas recomendadas para o desenvolvimento Android nativo:

* **Linguagem:** [Kotlin](https://kotlinlang.org) — Código moderno, seguro e conciso.
* **Interface Gráfica:** [Jetpack Compose](https://android.com) — UI declarativa e reativa.
* **Design System:** [Material 3](https://material.io) — Componentes visuais modernos e acessíveis com o padrão do Google.
* **Navegação:** [Navigation Compose](https://android.com) — Transições fluidas e tipadas entre telas.
* **Banco de Dados Local:** [Room Database](https://android.com) — Abstração SQLite para persistência e cache de dados offline.
* **Assincronismo:** [Coroutines](https://kotlinlang.orgdocs/coroutines-overview.html) & [Flow](https://kotlinlang.orgdocs/flow.html) — Gerenciamento reativo de fluxos de dados em segundo plano.

---

## 🗃️ Estrutura de Entidades

Para garantir foco e organização na entrega, o domínio do aplicativo foi dividido em 4 entidades principais, cada uma sob a responsabilidade de um desenvolvedor:

* **`Cadastro`** 👤 *(Responsável: Jonatas)* — Gestão do perfil do usuário e dados pessoais locais.
* **`Agendamento`** 📅 *(Responsável: Cecília)* — Controle e fluxo de marcação de consultas na agenda.
* **`Serviço`** 🏥 *(Responsável: Isi)* — Catálogo descritivo de atendimentos e horários da UBS.
* **`Vacina`** 💉 *(Responsável: Marilia)* — Gerenciamento do histórico informacional de imunização.

---

## 👥 Equipe e Responsabilidades

| Integrante | Papel Principal no Projeto |
| :--- | :--- |
| **Jonatas Calado** | Arquitetura de dados, persistência local e configuração do Room. |
| **Cecília Helena** | UI/UX Design, prototipagem e definição da identidade visual. |
| **Isi Luana** | Escrita da documentação técnica, configuração e gerenciamento do build. |
| **Marilia Gabrielly** | Desenvolvimento das telas e implementação das interfaces em Compose. |

---

## 🚫 Fora do Escopo (Limitações da Versão)

Por se tratar de um projeto educacional focado em desenvolvimento local (Client-Side), as seguintes funcionalidades **não** serão implementadas:
* Sincronização ou persistência de dados em nuvem (Cloud API);
* Sistema de autenticação de usuários (Login/Sign-up remoto);
* Integração oficial com sistemas governamentais (SUS / ConecteSUS);
* Canal de comunicação em tempo real (Chat) com funcionários ou médicos.

---

## 📄 Documentação do Projeto

O planejamento e as tomadas de decisão estão documentados detalhadamente nos arquivos abaixo:

* [**`CANVAS.md`**](./CANVAS.md) — Modelo de negócios e proposta de valor do aplicativo.
* [**`PRD.md`**](./PRD.md) — Requisitos de Produto, jornadas de usuário e critérios de aceite.
* [**`AGENTS.md`**](./AGENTS.md) — Definição do ecossistema de agentes e automações.
* [**`USO_DE_IA.md`**](./docs/USO_DE_IA.md) — Guia de governança e boas práticas no uso de Inteligência Artificial pela equipe.

---

## 🚀 Como Executar o Projeto

Para testar ou compilar o aplicativo localmente, siga estes passos:

1. **Clonar o Repositório:**
   ```bash
   git clone https://github.com
   ```
2. **Abrir no Android Studio:**
   * Abra o Android Studio (versão Hedgehog ou superior recomendada).
   * Selecione `Open` e aponte para a pasta raiz do projeto clonado.
3. **Sincronizar o Gradle:**
   * Aguarde o término do download das dependências e a sincronização do Gradle.
4. **Executar:**
   * Conecte um dispositivo físico via depuração USB ou ligue um Emulador (API 30+ recomendada).
   * Clique no botão **Run** (`Shift + F10` ou o ícone de play verde).

---

<p align="center"><b>Grupo Apollo — 3º ano do Ensino Médio</b></p>
