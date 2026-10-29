# Plano de IA — P4: Implementação da Entidade Agendamento

**Projeto:** Água Preta UBS  
**Grupo:** Apollo — 3º ano do Ensino Médio  
**Responsável:** Cecília Helena  
**Escopo:** Agendamento — entidade Room e integração necessária  
**Tecnologias:** Kotlin, Android Studio, Jetpack Compose, Room, Coroutines e Flow

## 1. Contexto e objetivo

Analise o projeto existente Água Preta UBS e execute a parte da P4 correspondente à entidade Agendamento, respeitando o PRD, a arquitetura atual e as instruções do repositório.

A P4 solicita a criação das entidades Room, dos DAOs, da interface Retrofit, dos ViewModels e a integração entre essas partes, além da definição de um padrão de branches e mensagens de commit.

Nesta etapa, implemente exclusivamente a parte de Agendamento pela qual Cecília é responsável. As demais entidades e funcionalidades dos outros integrantes não devem ser implementadas ou modificadas.

A entidade Agendamento já foi objeto de uma tarefa de recuperação anterior. Portanto, o objetivo não é recriá-la indiscriminadamente, mas verificar sua implementação, completar o que estiver pendente e integrá-la corretamente ao banco de dados e ao fluxo de agendamento existente.

## 2. Etapa 1 — Análise obrigatória do projeto

Antes de alterar qualquer arquivo:

1. Leia o PRD vigente e localize a definição da entidade Agendamento.
2. Consulte `AGENTS.md` e a documentação relevante.
3. Analise a estrutura atual do código Kotlin e identifique o pacote correto.
4. Confira a configuração do Room, incluindo banco de dados, entidades e DAOs existentes.
5. Verifique se `Agendamento.kt`, `AgendamentoDao.kt` e algum ViewModel correspondente já existem.
6. Identifique as telas de agendamento, confirmação e visualização dos próximos agendamentos.
7. Examine `git status`, o histórico recente e o diff para preservar alterações locais dos demais integrantes.
8. Confira as configurações atuais do Gradle sem modificá-las desnecessariamente.

Depois da análise, apresente um plano de execução com os arquivos que pretende criar ou alterar, justificando cada alteração.

Não duplique classes existentes nem remova implementações funcionais sem necessidade.

Se houver divergência entre o PRD, o código e o protótipo atual, registre a divergência antes de decidir como proceder.

## 3. Etapa 2 — Conferir a entidade `Agendamento`

Verifique se `Agendamento.kt` está implementado corretamente e se corresponde à definição vigente do PRD.

A definição anteriormente documentada contém:

- `id: Long`
- `nome: String`
- `data: String`
- `cartaoSUS: String`
- `horario: String`
- `servico: String`

Confirme os campos, os tipos e a chave primária no PRD e no código existente.

Requisitos:

- Utilizar `@Entity` do Room.
- Utilizar `@PrimaryKey` conforme o padrão existente.
- Conferir o nome da tabela e a estratégia de geração do identificador.
- Verificar os imports e o pacote.
- Manter o código simples, legível e compatível com a configuração atual.
- Preservar a implementação da recuperação anterior, corrigindo somente problemas comprovados.

Não acrescente campos, relacionamentos ou regras de negócio sem respaldo na documentação.

## 4. Etapa 3 — Criar ou completar `AgendamentoDao`

Verifique se já existe um DAO para Agendamento.

Caso não exista, crie `AgendamentoDao.kt` com as operações necessárias ao fluxo previsto no PRD.

As operações poderão incluir:

- Inserir um agendamento.
- Listar os agendamentos salvos.
- Consultar um agendamento específico, caso necessário.
- Excluir ou atualizar registros somente se houver necessidade documentada.

Utilize as anotações adequadas do Room, como `@Dao`, `@Insert`, `@Query`, `@Delete` e `@Update`, conforme as operações realmente necessárias.

Considere `suspend` para operações de escrita e `Flow` para consultas observáveis, respeitando os padrões existentes.

Verifique se as consultas SQL utilizam corretamente o nome da tabela e os campos da entidade.

Não crie operações desnecessárias apenas para completar a interface.

## 5. Etapa 4 — Integrar Agendamento ao Room

Analise a classe de banco de dados existente.

Se Agendamento ainda não estiver integrado, registre a entidade na configuração do banco e disponibilize seu DAO.

Verifique:

- Se a entidade está registrada no Room.
- Se o DAO está declarado corretamente.
- Se existe um método para acessar `AgendamentoDao`.
- Se a instância do banco é compartilhada conforme a arquitetura existente.
- Se a alteração exige alguma migração de banco de dados.

Não crie uma segunda classe `RoomDatabase`.

Não altere a versão do banco ou crie migrações sem necessidade comprovada. Se a mudança exigir uma alteração estrutural, explique o motivo e implemente apenas o indispensável.

## 6. Etapa 5 — Criar ou completar o ViewModel de Agendamento

Verifique se já existe um ViewModel responsável pelo fluxo de agendamento.

Caso seja necessário criar ou completar essa camada, implemente somente a lógica necessária para conectar a interface ao DAO.

O fluxo esperado é:

1. A interface coleta os dados do formulário.
2. O ViewModel recebe a solicitação de salvar.
3. Os campos obrigatórios são validados conforme o PRD.
4. O ViewModel solicita a gravação pelo DAO.
5. O resultado é comunicado à interface.
6. A lista de agendamentos é atualizada quando necessário.
7. Erros são tratados sem encerrar o aplicativo inesperadamente.

Utilize Coroutines e `Flow` ou `StateFlow` quando apropriado e compatível com o projeto.

Mantenha as operações do banco fora da interface Compose.

Não implemente login, sincronização em nuvem ou integração com sistemas do SUS.

## 7. Etapa 6 — Integrar o fluxo de agendamento à interface

Confira as telas já existentes e conecte-as à camada de dados.

A integração deve contemplar, quando previsto no PRD e já suportado pela interface:

- Preenchimento do formulário.
- Validação dos campos obrigatórios.
- Salvamento local.
- Confirmação de agendamento.
- Exibição dos agendamentos salvos.
- Mensagens de lista vazia, sucesso e erro.

Utilize o protótipo atual como referência visual, sem recriar telas que já estejam implementadas corretamente.

Não altere a identidade visual ou a navegação sem necessidade.

Não implemente novas funcionalidades fora do escopo de Agendamento.

## 8. Etapa 7 — Avaliar a integração com Retrofit

A P4 menciona a criação de uma interface Retrofit, mas esta etapa deve permanecer focada em Agendamento.

O PRD anteriormente definido prioriza o armazenamento local com Room e exclui integração com sistemas externos do SUS e sincronização em nuvem.

Portanto:

- Não invente uma URL de API.
- Não crie endpoints fictícios.
- Não envie dados pessoais ou dados do Cartão SUS para servidores.
- Não implemente sincronização em nuvem.
- Não crie uma interface Retrofit exclusiva para Agendamento sem necessidade documentada.

Se o professor exigir integração com uma API real, registre a exigência e identifique a fonte, os endpoints e o formato dos dados antes de implementar a comunicação.

## 9. Etapa 8 — Padrão de Git e commits

Siga as convenções de Git do grupo.

Branch sugerida:

`feature/agendamento-entity`

Antes de criar ou mudar de branch, confira o estado do repositório e preserve as alterações locais.

Adote mensagens claras, como:

- `feat: completa entidade Agendamento`
- `feat: adiciona DAO de Agendamento`
- `feat: integra agendamentos ao Room`
- `feat: conecta formulário ao ViewModel`
- `fix: corrige persistência de agendamentos`
- `test: adiciona testes de agendamento`

Agrupe as alterações por finalidade. Não inclua arquivos de Cadastro, Serviço ou Vacina nos commits desta tarefa.

Não faça commit ou push automaticamente. Ao terminar, apresente os arquivos que devem ser incluídos em cada commit e aguarde autorização.

## 10. Etapa 9 — Testes e validação

Realize as verificações possíveis:

1. Confira os campos da entidade em relação ao PRD.
2. Verifique a anotação `@Entity` e a chave primária.
3. Confira os imports e o pacote.
4. Verifique a integração do DAO com o Room.
5. Confirme a utilização correta de Coroutines e Flow, quando aplicáveis.
6. Verifique se os agendamentos são gravados e consultados corretamente.
7. Execute os testes existentes relacionados à funcionalidade.
8. Execute `assembleDebug`, se o ambiente permitir.
9. Analise o diff e o estado final do Git.

Se a compilação falhar por configuração preexistente do ambiente, registre o erro real. Não declare que a compilação ou os testes passaram sem executá-los com sucesso.

Não altere versões de dependências ou configurações do Gradle sem apresentar a causa e a necessidade.

## 11. Etapa 10 — Documentação da execução

Crie um registro em `docs/prompts/`:

`docs/prompts/20261027-p4-agendamento.md`

Crie também um relatório em `docs/outputs/`:

`docs/outputs/20261027-p4-agendamento.md`

O prompt deve registrar:

- Contexto e objetivo da tarefa.
- Escopo exclusivo de Agendamento.
- Arquivos previstos.
- Restrições e critérios de conclusão.

O relatório deve registrar:

- O que foi realmente implementado.
- Os caminhos dos arquivos criados ou modificados.
- Os campos da entidade conferidos.
- As operações implementadas no DAO.
- A integração realizada com Room e ViewModel.
- Os testes e a compilação efetivamente executados.
- As pendências encontradas.

Não declare como concluídas ações que não foram realizadas.

## 12. Limites obrigatórios do escopo

Trabalhe somente com Agendamento e os componentes estritamente necessários à sua integração.

Não implemente ou modifique:

- `CadastroEntity` ou `CadastroDao`.
- `ServicoEntity` ou `ServicoDao`.
- `VacinaEntity` ou `VacinaDao`.
- Funcionalidades de outros integrantes.
- Login, autenticação ou sincronização em nuvem.
- Integração com ConecteSUS.
- Telas ou navegação não solicitadas.
- Dependências ou configurações de build sem necessidade comprovada.

Preserve as alterações dos demais integrantes e não execute comandos destrutivos do Git.

## 13. Critérios de conclusão

- [ ] A entidade Agendamento está correta conforme o PRD.
- [ ] Não há declaração duplicada da entidade.
- [ ] O DAO está criado ou conferido.
- [ ] Agendamento está integrado ao Room.
- [ ] O ViewModel está integrado ao fluxo existente, quando necessário.
- [ ] O formulário e a visualização utilizam os dados corretamente.
- [ ] As verificações executadas estão documentadas.
- [ ] O prompt e o relatório estão em seus diretórios corretos.
- [ ] O diff foi revisado.
- [ ] As alterações estão organizadas para commits próprios.
- [ ] Nenhum commit ou push foi realizado sem autorização.

## 14. Resposta final obrigatória

Ao finalizar, apresente:

1. Resumo das etapas executadas.
2. Lista dos arquivos criados e modificados.
3. Explicação simples da ligação entre formulário, ViewModel, DAO e Room.
4. Resultado real dos testes e da compilação.
5. Pendências.
6. Relação dos arquivos previstos para cada commit.
7. Confirmação de que não foi realizado commit nem push.

Execute o trabalho em etapas pequenas, com explicações adequadas a estudantes iniciantes em Android, respeitando rigorosamente o escopo de Agendamento. Antes de iniciar qualquer alteração, verifique o estado do Git. Se estiver na main, crie e mude para a branch feature/agendamento-entity antes de editar arquivos. Preserve todas as alterações existentes. Confirme que está na branch correta antes de continuar. Não faça commit nem push sem minha autorização.