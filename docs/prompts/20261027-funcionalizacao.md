# Plano de IA — P4: Implementação da Entidade Cadastro

**Projeto:** Água Preta UBS  
**Grupo:** Apollo — 3º ano do Ensino Médio  
**Responsável:** Jonatas Calado  
**Escopo:** Cadastro — entidade Room e integração necessária  
**Tecnologias:** Kotlin, Android Studio, Jetpack Compose, Room, Coroutines e Flow

## 1. Contexto e objetivo

Analise o projeto existente Água Preta UBS e execute a parte da P4 correspondente à entidade de Cadastro, respeitando o PRD, a arquitetura atual e as instruções do repositório.

A P4 solicita a criação das entidades Room, dos DAOs, da interface Retrofit, dos ViewModels e a integração entre essas partes, além da definição de um padrão de branches e mensagens de commit.

Nesta etapa, implemente exclusivamente a parte de Cadastro pela qual Jonatas é responsável. As demais entidades e funcionalidades dos outros integrantes não devem ser implementadas ou modificadas.

O objetivo é criar uma estrutura de Cadastro funcional, integrada ao Room e preparada para ser utilizada pela interface do aplicativo, sem introduzir funcionalidades fora do escopo.

## 2. Etapa 1 — Análise obrigatória do projeto

Antes de alterar qualquer arquivo:

1. Leia o PRD vigente e localize a definição da entidade Cadastro, seus campos e suas responsabilidades.
2. Consulte `AGENTS.md` e a documentação relevante do projeto.
3. Analise a estrutura atual do código Kotlin e identifique o pacote correto para as entidades.
4. Confira como o Room está configurado, incluindo dependências, plugins, banco de dados e convenções existentes.
5. Verifique se já existem `CadastroEntity`, `CadastroDao`, ViewModel ou implementações equivalentes.
6. Identifique as telas existentes relacionadas ao cadastro, caso existam.
7. Examine `git status`, o histórico recente e o diff para preservar alterações locais dos demais integrantes.
8. Verifique as versões e as configurações atuais do Gradle, sem modificá-las desnecessariamente.

Depois da análise, apresente um plano de execução com os arquivos que pretende criar ou alterar, justificando cada alteração.

Não duplique classes existentes nem substitua implementações funcionais sem necessidade.

Se a definição de Cadastro estiver ausente ou divergente entre o PRD e o código, registre a dúvida e procure a documentação existente. Não invente campos para preencher lacunas.

## 3. Etapa 2 — Implementar `CadastroEntity`

Crie ou complete `CadastroEntity.kt` no pacote apropriado, seguindo a estrutura real do projeto.

A definição de Cadastro anteriormente considerada no projeto contém os seguintes campos:

- `id: Long`
- `nome: String`
- `cartaoSUS: String`
- `dataNascimento: String`
- `telefone: String`

Confirme os campos e seus tipos no PRD vigente antes de implementá-los.

Requisitos:

- Utilizar `@Entity` do Room.
- Definir uma chave primária com `@PrimaryKey`, respeitando as convenções do projeto.
- Utilizar `autoGenerate = true` somente se for compatível com a definição e o padrão adotado.
- Utilizar os imports corretos.
- Manter o código simples e legível.
- Respeitar a nomenclatura existente.
- Incluir os comentários de identificação de responsabilidade exigidos pelo repositório, se aplicáveis.

Não acrescente campos, relacionamentos, validações ou regras de negócio sem respaldo no PRD.

## 4. Etapa 3 — Criar ou completar `CadastroDao`

Verifique se o projeto já possui um DAO para Cadastro.

Se não existir, implemente `CadastroDao.kt` com as operações necessárias para a funcionalidade definida no PRD.

As operações poderão incluir:

- Inserir um cadastro.
- Consultar um cadastro pelo identificador ou por outro critério expressamente definido.
- Listar cadastros, caso isso seja necessário para o fluxo previsto.
- Atualizar ou excluir registros somente se houver necessidade documentada.

Utilize as anotações adequadas do Room, como `@Dao`, `@Insert`, `@Query`, `@Update` e `@Delete`, conforme as operações efetivamente necessárias.

Considere `suspend` para operações de escrita e `Flow` para consultas observáveis, respeitando os padrões já existentes.

Não crie operações desnecessárias apenas para preencher a interface do DAO.

## 5. Etapa 4 — Integrar Cadastro ao banco Room

Analise a classe de banco de dados existente.

Se Cadastro ainda não estiver integrado, adicione a entidade e disponibilize seu DAO por meio da estrutura atual do Room.

Verifique:

- Se `CadastroEntity` está registrada nas entidades do banco.
- Se o DAO está declarado corretamente.
- Se existe um método para obter a instância de `CadastroDao`.
- Se a configuração atual do banco permanece compatível.
- Se a alteração exige alguma migração de banco de dados.

Não crie uma segunda classe de banco de dados caso já exista uma.

Não altere a versão do banco ou crie migrações sem necessidade comprovada. Se a inclusão da entidade exigir uma alteração estrutural, explique a necessidade e implemente apenas o indispensável.

## 6. Etapa 5 — Criar ou completar o ViewModel de Cadastro

Verifique se existe uma tela ou fluxo de cadastro no aplicativo e se já há um ViewModel correspondente.

Caso seja necessário criar ou completar o ViewModel, implemente apenas a lógica mínima para conectar Cadastro à camada de dados.

O fluxo esperado, quando houver interface correspondente, é:

1. A interface coleta os dados.
2. O ViewModel recebe a ação de salvar.
3. O ViewModel utiliza o DAO, diretamente ou pela camada de dados já existente.
4. A operação de escrita é executada de forma apropriada, utilizando Coroutines.
5. O resultado é comunicado à interface.
6. Erros são tratados sem encerrar o aplicativo inesperadamente.

Utilize `StateFlow`, `Flow` ou os mecanismos já adotados pelo projeto quando forem necessários.

Mantenha a interface Compose separada das operações do banco.

**Importante:** não crie telas novas, navegação, autenticação ou um fluxo completo de perfil do usuário se isso não estiver previsto no PRD ou se não for indispensável para integrar a funcionalidade existente.

Se não houver tela de Cadastro definida, prepare a camada de dados e o ViewModel necessários, mas registre no relatório o que ficou pendente na interface.

## 7. Etapa 6 — Avaliar a integração com Retrofit

A P4 também menciona uma interface Retrofit, mas esta etapa deve permanecer focada em Cadastro.

Consulte o PRD e as orientações do professor para verificar se Cadastro precisa realmente consultar ou enviar dados a uma API.

O escopo anteriormente definido para o Água Preta UBS prioriza o armazenamento local com Room e exclui sincronização em nuvem e integração com sistemas externos do SUS.

Portanto:

- Não invente uma URL de API.
- Não crie endpoints fictícios.
- Não envie dados pessoais ou dados do Cartão SUS para servidores.
- Não implemente sincronização em nuvem ou autenticação.
- Não crie uma interface Retrofit exclusiva para Cadastro sem necessidade documentada.

Se o professor exigir Retrofit nesta etapa, registre a exigência e identifique a API e os endpoints necessários antes de implementar a comunicação. Caso não haja uma API definida, documente essa dependência como pendência, sem simular uma integração real.

## 8. Etapa 7 — Padrão de Git e commits

Siga as convenções de Git estabelecidas pelo grupo.

Para a branch de trabalho, utilize o padrão:

`feature/cadastro-entity`

Antes de criá-la, verifique a branch atual e o estado do repositório. Não mude de branch se isso puder colocar alterações locais em risco.

Adote mensagens claras para os commits, por exemplo:

- `feat: cria entidade Cadastro`
- `feat: adiciona DAO de Cadastro`
- `feat: integra Cadastro ao Room`
- `feat: adiciona ViewModel de Cadastro`
- `fix: corrige persistência de Cadastro`
- `test: adiciona testes para Cadastro`

Agrupe as alterações por finalidade. Não inclua arquivos de Agendamento, Serviço, Vacina ou mudanças de outros integrantes nos commits desta tarefa.

Não faça commit ou push automaticamente. Ao terminar, apresente os arquivos que devem ser incluídos em cada commit e aguarde autorização.

## 9. Etapa 8 — Testes e validação

Depois da implementação, realize as verificações possíveis:

1. Confira os campos de `CadastroEntity` em relação ao PRD.
2. Verifique a anotação `@Entity` e a chave primária.
3. Confira os imports e o pacote.
4. Verifique a integração de `CadastroDao` com o Room.
5. Confirme a utilização correta de Coroutines e Flow, quando aplicáveis.
6. Execute os testes existentes relacionados a Cadastro, caso existam.
7. Execute `assembleDebug`, se o ambiente permitir.
8. Analise o diff para detectar alterações desnecessárias.
9. Confira o estado final do Git.

Se a compilação falhar por uma configuração preexistente do ambiente, identifique o erro e registre-o. Não declare que a compilação passou sem executá-la com sucesso.

Não altere configurações do Gradle ou versões das dependências para resolver problemas fora do escopo sem apresentar a causa e a necessidade.

## 10. Etapa 9 — Documentação da execução

Verifique os padrões de documentação existentes e registre a tarefa nos diretórios já utilizados pelo projeto.

Crie um prompt de execução em `docs/prompts/`, com um nome coerente, por exemplo:

`docs/prompts/20261027-p4-cadastro.md`

Crie também um relatório em `docs/outputs/`, por exemplo:

`docs/outputs/20261027-p4-cadastro.md`

O prompt deve registrar:

- Contexto e objetivo da tarefa.
- Escopo exclusivo de Cadastro.
- Arquivos previstos.
- Restrições e critérios de conclusão.

O relatório deve registrar:

- O que foi realmente implementado.
- Os caminhos dos arquivos criados ou modificados.
- Os campos e as operações implementadas.
- A integração realizada com Room e ViewModel.
- As verificações efetivamente executadas.
- O resultado real da compilação e dos testes, quando executados.
- Pendências e limitações encontradas.

Não apresente como concluídas operações que não foram realizadas.

## 11. Limites obrigatórios do escopo

Nesta etapa, trabalhe somente com Cadastro e com os componentes estritamente necessários à sua integração.

Não implemente ou modifique:

- `Agendamento`, além de compatibilidades indispensáveis devidamente justificadas.
- `ServicoEntity` ou `ServicoDao`.
- `VacinaEntity` ou `VacinaDao`.
- Funcionalidades de outros integrantes.
- Integração com ConecteSUS.
- Login, autenticação ou sincronização em nuvem.
- Telas ou navegação não solicitadas.
- Dependências ou configurações de build sem necessidade comprovada.

Preserve as alterações existentes dos demais integrantes e não execute comandos destrutivos do Git.

## 12. Critérios de conclusão

A tarefa estará concluída quando:

- [ ] A entidade `CadastroEntity` estiver implementada conforme o PRD.
- [ ] O `CadastroDao` estiver criado ou conferido, com as operações necessárias.
- [ ] Cadastro estiver integrado ao Room.
- [ ] O ViewModel estiver integrado ao fluxo existente, quando necessário.
- [ ] A comunicação com Retrofit tiver sido avaliada sem inventar uma API.
- [ ] As verificações possíveis tiverem sido executadas e registradas.
- [ ] O prompt e o relatório estiverem documentados.
- [ ] O diff tiver sido revisado.
- [ ] As alterações estiverem organizadas para commits próprios.
- [ ] Nenhum commit ou push tiver sido realizado sem autorização.

## 13. Resposta final obrigatória

Ao finalizar, apresente:

1. Resumo das etapas executadas.
2. Lista dos arquivos criados e modificados.
3. Explicação simples de como Cadastro se conecta ao Room e ao ViewModel.
4. Resultado dos testes e da compilação efetivamente realizados.
5. Pendências que dependam de decisão do professor.
6. Relação dos arquivos previstos para cada commit.
7. Confirmação de que não foi realizado commit nem push.

Execute o trabalho em etapas pequenas, explique as alterações com linguagem adequada a estudantes iniciantes em Android e respeite rigorosamente o escopo de Cadastro.

# Plano de IA — P4: Implementação da Entidade Cadastro

**Projeto:** Água Preta UBS  
**Grupo:** Apollo — 3º ano do Ensino Médio  
**Responsável:** Jonatas Calado  
**Escopo:** Cadastro — entidade Room e integração necessária  
**Tecnologias:** Kotlin, Android Studio, Jetpack Compose, Room, Coroutines e Flow

## 1. Contexto e objetivo

Analise o projeto existente Água Preta UBS e execute a parte da P4 correspondente à entidade de Cadastro, respeitando o PRD, a arquitetura atual e as instruções do repositório.

A P4 solicita a criação das entidades Room, dos DAOs, da interface Retrofit, dos ViewModels e a integração entre essas partes, além da definição de um padrão de branches e mensagens de commit.

Nesta etapa, implemente exclusivamente a parte de Cadastro pela qual Jonatas é responsável. As demais entidades e funcionalidades dos outros integrantes não devem ser implementadas ou modificadas.

O objetivo é criar uma estrutura de Cadastro funcional, integrada ao Room e preparada para ser utilizada pela interface do aplicativo, sem introduzir funcionalidades fora do escopo.

## 2. Etapa 1 — Análise obrigatória do projeto

Antes de alterar qualquer arquivo:

1. Leia o PRD vigente e localize a definição da entidade Cadastro, seus campos e suas responsabilidades.
2. Consulte `AGENTS.md` e a documentação relevante do projeto.
3. Analise a estrutura atual do código Kotlin e identifique o pacote correto para as entidades.
4. Confira como o Room está configurado, incluindo dependências, plugins, banco de dados e convenções existentes.
5. Verifique se já existem `CadastroEntity`, `CadastroDao`, ViewModel ou implementações equivalentes.
6. Identifique as telas existentes relacionadas ao cadastro, caso existam.
7. Examine `git status`, o histórico recente e o diff para preservar alterações locais dos demais integrantes.
8. Verifique as versões e as configurações atuais do Gradle, sem modificá-las desnecessariamente.

Depois da análise, apresente um plano de execução com os arquivos que pretende criar ou alterar, justificando cada alteração.

Não duplique classes existentes nem substitua implementações funcionais sem necessidade.

Se a definição de Cadastro estiver ausente ou divergente entre o PRD e o código, registre a dúvida e procure a documentação existente. Não invente campos para preencher lacunas.

## 3. Etapa 2 — Implementar `CadastroEntity`

Crie ou complete `CadastroEntity.kt` no pacote apropriado, seguindo a estrutura real do projeto.

A definição de Cadastro anteriormente considerada no projeto contém os seguintes campos:

- `id: Long`
- `nome: String`
- `cartaoSUS: String`
- `dataNascimento: String`
- `telefone: String`

Confirme os campos e seus tipos no PRD vigente antes de implementá-los.

Requisitos:

- Utilizar `@Entity` do Room.
- Definir uma chave primária com `@PrimaryKey`, respeitando as convenções do projeto.
- Utilizar `autoGenerate = true` somente se for compatível com a definição e o padrão adotado.
- Utilizar os imports corretos.
- Manter o código simples e legível.
- Respeitar a nomenclatura existente.
- Incluir os comentários de identificação de responsabilidade exigidos pelo repositório, se aplicáveis.

Não acrescente campos, relacionamentos, validações ou regras de negócio sem respaldo no PRD.

## 4. Etapa 3 — Criar ou completar `CadastroDao`

Verifique se o projeto já possui um DAO para Cadastro.

Se não existir, implemente `CadastroDao.kt` com as operações necessárias para a funcionalidade definida no PRD.

As operações poderão incluir:

- Inserir um cadastro.
- Consultar um cadastro pelo identificador ou por outro critério expressamente definido.
- Listar cadastros, caso isso seja necessário para o fluxo previsto.
- Atualizar ou excluir registros somente se houver necessidade documentada.

Utilize as anotações adequadas do Room, como `@Dao`, `@Insert`, `@Query`, `@Update` e `@Delete`, conforme as operações efetivamente necessárias.

Considere `suspend` para operações de escrita e `Flow` para consultas observáveis, respeitando os padrões já existentes.

Não crie operações desnecessárias apenas para preencher a interface do DAO.

## 5. Etapa 4 — Integrar Cadastro ao banco Room

Analise a classe de banco de dados existente.

Se Cadastro ainda não estiver integrado, adicione a entidade e disponibilize seu DAO por meio da estrutura atual do Room.

Verifique:

- Se `CadastroEntity` está registrada nas entidades do banco.
- Se o DAO está declarado corretamente.
- Se existe um método para obter a instância de `CadastroDao`.
- Se a configuração atual do banco permanece compatível.
- Se a alteração exige alguma migração de banco de dados.

Não crie uma segunda classe de banco de dados caso já exista uma.

Não altere a versão do banco ou crie migrações sem necessidade comprovada. Se a inclusão da entidade exigir uma alteração estrutural, explique a necessidade e implemente apenas o indispensável.

## 6. Etapa 5 — Criar ou completar o ViewModel de Cadastro

Verifique se existe uma tela ou fluxo de cadastro no aplicativo e se já há um ViewModel correspondente.

Caso seja necessário criar ou completar o ViewModel, implemente apenas a lógica mínima para conectar Cadastro à camada de dados.

O fluxo esperado, quando houver interface correspondente, é:

1. A interface coleta os dados.
2. O ViewModel recebe a ação de salvar.
3. O ViewModel utiliza o DAO, diretamente ou pela camada de dados já existente.
4. A operação de escrita é executada de forma apropriada, utilizando Coroutines.
5. O resultado é comunicado à interface.
6. Erros são tratados sem encerrar o aplicativo inesperadamente.

Utilize `StateFlow`, `Flow` ou os mecanismos já adotados pelo projeto quando forem necessários.

Mantenha a interface Compose separada das operações do banco.

**Importante:** não crie telas novas, navegação, autenticação ou um fluxo completo de perfil do usuário se isso não estiver previsto no PRD ou se não for indispensável para integrar a funcionalidade existente.

Se não houver tela de Cadastro definida, prepare a camada de dados e o ViewModel necessários, mas registre no relatório o que ficou pendente na interface.

## 7. Etapa 6 — Avaliar a integração com Retrofit

A P4 também menciona uma interface Retrofit, mas esta etapa deve permanecer focada em Cadastro.

Consulte o PRD e as orientações do professor para verificar se Cadastro precisa realmente consultar ou enviar dados a uma API.

O escopo anteriormente definido para o Água Preta UBS prioriza o armazenamento local com Room e exclui sincronização em nuvem e integração com sistemas externos do SUS.

Portanto:

- Não invente uma URL de API.
- Não crie endpoints fictícios.
- Não envie dados pessoais ou dados do Cartão SUS para servidores.
- Não implemente sincronização em nuvem ou autenticação.
- Não crie uma interface Retrofit exclusiva para Cadastro sem necessidade documentada.

Se o professor exigir Retrofit nesta etapa, registre a exigência e identifique a API e os endpoints necessários antes de implementar a comunicação. Caso não haja uma API definida, documente essa dependência como pendência, sem simular uma integração real.

## 8. Etapa 7 — Padrão de Git e commits

Siga as convenções de Git estabelecidas pelo grupo.

Para a branch de trabalho, utilize o padrão:

`feature/cadastro-entity`

Antes de criá-la, verifique a branch atual e o estado do repositório. Não mude de branch se isso puder colocar alterações locais em risco.

Adote mensagens claras para os commits, por exemplo:

- `feat: cria entidade Cadastro`
- `feat: adiciona DAO de Cadastro`
- `feat: integra Cadastro ao Room`
- `feat: adiciona ViewModel de Cadastro`
- `fix: corrige persistência de Cadastro`
- `test: adiciona testes para Cadastro`

Agrupe as alterações por finalidade. Não inclua arquivos de Agendamento, Serviço, Vacina ou mudanças de outros integrantes nos commits desta tarefa.

Não faça commit ou push automaticamente. Ao terminar, apresente os arquivos que devem ser incluídos em cada commit e aguarde autorização.

## 9. Etapa 8 — Testes e validação

Depois da implementação, realize as verificações possíveis:

1. Confira os campos de `CadastroEntity` em relação ao PRD.
2. Verifique a anotação `@Entity` e a chave primária.
3. Confira os imports e o pacote.
4. Verifique a integração de `CadastroDao` com o Room.
5. Confirme a utilização correta de Coroutines e Flow, quando aplicáveis.
6. Execute os testes existentes relacionados a Cadastro, caso existam.
7. Execute `assembleDebug`, se o ambiente permitir.
8. Analise o diff para detectar alterações desnecessárias.
9. Confira o estado final do Git.

Se a compilação falhar por uma configuração preexistente do ambiente, identifique o erro e registre-o. Não declare que a compilação passou sem executá-la com sucesso.

Não altere configurações do Gradle ou versões das dependências para resolver problemas fora do escopo sem apresentar a causa e a necessidade.

## 10. Etapa 9 — Documentação da execução

Verifique os padrões de documentação existentes e registre a tarefa nos diretórios já utilizados pelo projeto.

Crie um prompt de execução em `docs/prompts/`, com um nome coerente, por exemplo:

`docs/prompts/20261027-p4-cadastro.md`

Crie também um relatório em `docs/outputs/`, por exemplo:

`docs/outputs/20261027-p4-cadastro.md`

O prompt deve registrar:

- Contexto e objetivo da tarefa.
- Escopo exclusivo de Cadastro.
- Arquivos previstos.
- Restrições e critérios de conclusão.

O relatório deve registrar:

- O que foi realmente implementado.
- Os caminhos dos arquivos criados ou modificados.
- Os campos e as operações implementadas.
- A integração realizada com Room e ViewModel.
- As verificações efetivamente executadas.
- O resultado real da compilação e dos testes, quando executados.
- Pendências e limitações encontradas.

Não apresente como concluídas operações que não foram realizadas.

## 11. Limites obrigatórios do escopo

Nesta etapa, trabalhe somente com Cadastro e com os componentes estritamente necessários à sua integração.

Não implemente ou modifique:

- `Agendamento`, além de compatibilidades indispensáveis devidamente justificadas.
- `ServicoEntity` ou `ServicoDao`.
- `VacinaEntity` ou `VacinaDao`.
- Funcionalidades de outros integrantes.
- Integração com ConecteSUS.
- Login, autenticação ou sincronização em nuvem.
- Telas ou navegação não solicitadas.
- Dependências ou configurações de build sem necessidade comprovada.

Preserve as alterações existentes dos demais integrantes e não execute comandos destrutivos do Git.

## 12. Critérios de conclusão

A tarefa estará concluída quando:

- [ ] A entidade `CadastroEntity` estiver implementada conforme o PRD.
- [ ] O `CadastroDao` estiver criado ou conferido, com as operações necessárias.
- [ ] Cadastro estiver integrado ao Room.
- [ ] O ViewModel estiver integrado ao fluxo existente, quando necessário.
- [ ] A comunicação com Retrofit tiver sido avaliada sem inventar uma API.
- [ ] As verificações possíveis tiverem sido executadas e registradas.
- [ ] O prompt e o relatório estiverem documentados.
- [ ] O diff tiver sido revisado.
- [ ] As alterações estiverem organizadas para commits próprios.
- [ ] Nenhum commit ou push tiver sido realizado sem autorização.

## 13. Resposta final obrigatória

Ao finalizar, apresente:

1. Resumo das etapas executadas.
2. Lista dos arquivos criados e modificados.
3. Explicação simples de como Cadastro se conecta ao Room e ao ViewModel.
4. Resultado dos testes e da compilação efetivamente realizados.
5. Pendências que dependam de decisão do professor.
6. Relação dos arquivos previstos para cada commit.
7. Confirmação de que não foi realizado commit nem push.

Execute o trabalho em etapas pequenas, explique as alterações com linguagem adequada a estudantes iniciantes em Android e respeite rigorosamente o escopo de Cadastro.

## Atualização do plano de IA — Água Preta UBS

### 1. Configurar o KSP e o Room no Gradle

- Verifique os arquivos `gradle/libs.versions.toml` e `app/build.gradle.kts`.
- Adicione o plugin **KSP** ao projeto, respeitando as versões e a compatibilidade com o Kotlin já utilizado.
- Adicione a dependência do compilador do Room:

  `ksp(libs.androidx.room.compiler)`

- Confira se o alias `libs.androidx.room.compiler` está definido corretamente no catálogo de versões.
- Verifique se o plugin KSP está declarado e aplicado corretamente no módulo `app`.
- Preserve as dependências e configurações existentes que não precisem ser alteradas.
- Não atualize versões do Gradle, Kotlin ou outras dependências sem necessidade e sem explicar o motivo.

**Objetivo:** permitir que o Room processe as entidades e gere o código necessário para o funcionamento do banco de dados. A configuração do KSP, sozinha, não cria tabelas automaticamente: as entidades também precisam estar corretamente registradas na configuração do Room Database.

### 2. Pacote antigo `com.example`

A exclusão do pacote antigo `com.example` será realizada manualmente por mim.

- Não exclua, mova ou renomeie arquivos desse pacote.
- Não faça alterações relacionadas à remoção desse pacote.
- Continue trabalhando exclusivamente na estrutura atual do projeto, respeitando o pacote `br.edu.ifpe.ubsaude`.

### 3. Preservar o escopo original

- Mantenha a arquitetura e as funcionalidades já definidas no PRD.
- Não crie funcionalidades novas nem reestruture partes do aplicativo sem necessidade.
- Não duplique entidades, dependências ou configurações que já existam.
- Não altere arquivos de outros integrantes sem necessidade para a tarefa.

### 4. Verificação final

Após as alterações:

1. Revise o diff para identificar mudanças desnecessárias.
2. Execute `.\gradlew assembleDebug` na raiz do projeto, se o ambiente permitir.
3. Registre no relatório quais arquivos foram alterados e o que foi feito.
4. Informe o resultado real da compilação, incluindo eventuais erros. Não declare sucesso sem confirmação.
5. Não faça commit, push ou operações destrutivas automaticamente.

### 5. Registro da execução

Mantenha o padrão de documentação do projeto:

- Registre as instruções utilizadas em um arquivo dentro de `docs/prompts/`.
- Registre as ações executadas, os arquivos alterados e os testes realizados em um arquivo dentro de `docs/outputs/`.

Os registros devem refletir o trabalho realmente realizado, sem afirmar que alguma etapa foi concluída se ela não tiver sido executada.