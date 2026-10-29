# Relatório de execução — P4: Agendamento

**Projeto:** Água Preta UBS  
**Escopo:** persistência local e integração do fluxo de Agendamento  
**Branch:** `feature/agendamento-entity`

## 1. Resumo

Foi conectado o formulário de Agendamento ao Room por meio de um DAO e de um ViewModel. O formulário agora valida campos obrigatórios, grava os dados localmente e somente navega para a confirmação após o resultado bem-sucedido. A tela de confirmação, o card inicial e a tela “Meus agendamentos” exibem os dados salvos. A tela apresenta mensagens para lista vazia e para falhas de leitura ou gravação.

Não foram alteradas dependências Gradle, nem implementadas Retrofit, chamadas remotas, autenticação ou sincronização.

## 2. Entidade e divergência documental

A entidade existente em `src/app/src/main/java/br/edu/ifpe/ubsaude/Agendamento.kt` foi conferida e não precisou ser alterada. Ela contém:

| Campo | Tipo | Regra |
|---|---|---|
| `id` | `Int` | Chave primária autogerada, padrão `0` |
| `nome` | `String` | Obrigatório |
| `data` | `String` | Obrigatório |
| `cartaoSUS` | `String` | Obrigatório |
| `horario` | `String` | Obrigatório |
| `servico` | `String` | Obrigatório |

Há uma divergência que deve ser resolvida documentalmente pelo grupo: o `docs/PRD.md` presente na árvore de trabalho se identifica como rascunho histórico e descreve `id: Long`; o plano também cita uma definição anterior com `Long`. A entidade já existente e a versão completa do PRD preservada no histórico (`PRD.md`, commit `7e63f59`) especificam `id: Int`. Mantive `Int` para preservar a definição usada pelo projeto e não alterar a entidade com base em uma fonte marcada como histórica.

## 3. Implementação realizada

### DAO

Criado `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoDao.kt` com:

- `inserir(agendamento)`: inserção `suspend`, retornando o identificador gerado.
- `listar()`: consulta Room via `Flow<List<Agendamento>>`, ordenada por identificador decrescente para apresentar primeiro o registro inserido mais recentemente.

Não foram incluídas operações de atualização ou exclusão, ausentes do requisito documentado.

### Banco Room e migração

`src/app/src/main/java/br/edu/ifpe/ubsaude/AppDatabase.kt` agora registra `Cadastro` e `Agendamento`, disponibiliza `agendamentoDao()` e avança o banco da versão 1 para 2. A migração cria somente a tabela `Agendamento`; não apaga nem recria os dados existentes de `Cadastro`. A versão foi alterada porque o banco v1 já está configurado no projeto, e incluir uma entidade sem migração deixaria instalações existentes sujeitas à validação de esquema do Room.

### ViewModel e interface

Criado `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoViewModel.kt`. Ele obtém o DAO da instância compartilhada do banco, observa a lista, valida os cinco campos obrigatórios, salva por coroutine e publica o resultado à tela. Exceções SQLite são registradas no log e retornam uma mensagem de falha; falhas ao observar a lista também são registradas e exibidas como erro, não como lista vazia normal.

`MainActivity.kt` obtém o ViewModel pelo `ViewModelProvider` e compartilha os estados de carregamento, erro, salvamento e lista com as telas. Em `Screens.kt`, o formulário salva os valores preenchidos; a confirmação apresenta serviço, data e horário; a tela inicial mostra o registro mais recente; e “Meus agendamentos” lista todos os registros ou informa carregamento, erro ou estado vazio. As novas mensagens e rótulos foram registrados em `strings.xml`.

A tela de Perfil continua exibindo seus dados demonstrativos anteriores; não foi alterada por estar fora do fluxo de Agendamento solicitado.

## 4. Arquivos criados ou modificados

| Arquivo | Ação |
|---|---|
| `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoDao.kt` | Criado |
| `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoViewModel.kt` | Criado |
| `src/app/src/main/java/br/edu/ifpe/ubsaude/AppDatabase.kt` | Modificado |
| `src/app/src/main/java/br/edu/ifpe/ubsaude/MainActivity.kt` | Modificado |
| `src/app/src/main/java/br/edu/ifpe/ubsaude/Screens.kt` | Modificado |
| `src/app/src/main/res/values/strings.xml` | Modificado |
| `docs/prompts/20261027-p4-agendamento.md` | Criado |
| `docs/outputs/20261027-p4-agendamento.md` | Criado |

`docs/prompts/20261029-agendamento.md` já estava não rastreado no estado inicial e foi preservado sem alterações; ele não faz parte das sugestões de commits abaixo.

## 5. Verificações e resultados

- **Branch:** conferida antes das alterações; trabalho feito em `feature/agendamento-entity`, criada a partir de `main`.
- **`git diff --check`: passou**, sem erros de whitespace.
- **`strings.xml`: passou** na leitura pelo parser XML do PowerShell (46 elementos `<string>`).
- **`:app:assembleDebug`: não executou até a compilação.** O Gradle interrompeu a configuração porque não encontrou Android SDK. `ANDROID_HOME` e `ANDROID_SDK_ROOT` estavam vazios, e os caminhos locais usuais verificados não continham SDK.
- **`:app:testDebugUnitTest`: não executou.** Foi bloqueado pela mesma ausência de SDK, antes de executar os testes.
- **Testes específicos de Agendamento:** não existem no projeto. O teste unitário encontrado é apenas o teste de exemplo de soma.
- **Dependências/Gradle:** não alterados.
- **Commit e push:** não realizados.

## 6. Pendências

- Configurar um Android SDK compatível com o projeto e repetir `:app:assembleDebug` e `:app:testDebugUnitTest`; até então, compilação e testes Kotlin/Room não estão confirmados.
- Atualizar ou esclarecer a documentação do PRD: o documento disponível no diretório `docs/` é histórico e conflita quanto ao tipo do identificador. A entidade permaneceu `Int`, conforme o código e o PRD completo preservado no histórico.
- Adicionar testes próprios de DAO, migração e ViewModel quando houver infraestrutura de teste Room disponível; não foram adicionadas dependências nesta tarefa.

## 7. Organização sugerida para commits

1. **`feat: adiciona DAO e ViewModel de Agendamento`**
   - `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoDao.kt`
   - `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoViewModel.kt`
2. **`feat: integra Agendamento ao Room e ao fluxo existente`**
   - `src/app/src/main/java/br/edu/ifpe/ubsaude/AppDatabase.kt`
   - `src/app/src/main/java/br/edu/ifpe/ubsaude/MainActivity.kt`
   - `src/app/src/main/java/br/edu/ifpe/ubsaude/Screens.kt`
   - `src/app/src/main/res/values/strings.xml`
3. **`docs: registra implementação P4 de Agendamento`**
   - `docs/prompts/20261027-p4-agendamento.md`
   - `docs/outputs/20261027-p4-agendamento.md`

Os commits aguardam autorização. Nenhum commit ou push foi realizado.
