# Plano de IA — Correção e integração funcional do Água Preta UBS

**Projeto:** Água Preta UBS  
**Tipo:** Correção de bugs, validações e integração entre banco de dados e interface.  
**Branch obrigatória:** `fix/correcao-funcionalidades-app`

## 1. Objetivo geral

Realizar uma análise do aplicativo para identificar e corrigir os problemas encontrados durante os testes manuais.

O objetivo não é apenas modificar elementos visuais, mas garantir que as funcionalidades realmente funcionem, que os dados sejam validados e que as informações exibidas nas telas correspondam aos dados inseridos e armazenados.

Antes de implementar qualquer correção, analisar a arquitetura existente e consultar o PRD, o AGENTS.md e os demais documentos relevantes.

## 2. Regra obrigatória de Git e branch

Antes de realizar qualquer alteração no código:

1. Verificar a branch atual com `git branch --show-current`.
2. Verificar o estado do repositório com `git status`.
3. Analisar alterações locais, arquivos não rastreados e possíveis modificações pendentes.
4. Verificar se a branch `fix/correcao-funcionalidades-app` já existe.
5. Se não existir, criá-la a partir da situação atual do projeto, preservando as alterações locais.
6. Se já existir, verificar seu histórico e conteúdo antes de utilizá-la.

**REGRAS INEGOCIÁVEIS:**

- Nunca realizar alterações diretamente na `main`.
- Todas as correções devem acontecer exclusivamente na branch `fix/correcao-funcionalidades-app`.
- Não apagar, descartar, sobrescrever ou modificar alterações anteriores sem autorização.
- Não realizar merge com a `main`.
- Não executar `git commit` sem autorização explícita do usuário.
- Não executar `git push` sem autorização explícita do usuário.
- Não abrir pull request sem autorização.
- Não utilizar comandos destrutivos, como `git reset --hard`, `git clean` ou equivalentes.
- Ao terminar, deixar todas as alterações pendentes para revisão do usuário.

Caso não seja possível criar ou utilizar a branch com segurança, interromper a execução e explicar o motivo antes de modificar qualquer arquivo.

## 3. Problemas que devem ser investigados e corrigidos

### 3.1. Validação de campos numéricos

**Problema:** atualmente, campos que deveriam aceitar somente números permitem a inserção de letras.

**O que fazer:**
- Identificar todos os campos que exigem valores numéricos.
- Impedir a entrada de caracteres incompatíveis.
- Utilizar o tipo de teclado adequado.
- Implementar validação também na lógica de processamento, não apenas no teclado.
- Exibir mensagens claras quando os dados forem inválidos.

Não aplicar restrições numéricas indiscriminadamente a campos que aceitam texto.

### 3.2. Agendamento sem informações obrigatórias

**Problema:** o aplicativo permite realizar agendamentos sem preencher as informações necessárias.

**O que fazer:**
- Identificar todos os campos obrigatórios no PRD.
- Impedir a confirmação de agendamentos incompletos.
- Validar os campos antes de salvar no banco de dados.
- Exibir mensagens indicando exatamente o que precisa ser preenchido.
- Evitar registros vazios ou incompletos no Room.
- Verificar se os dados realmente foram salvos antes de apresentar uma confirmação de sucesso.

### 3.3. Funcionamento sem conexão com a internet

**Problema:** o aplicativo permite utilizar funcionalidades mesmo sem conexão, sem apresentar qualquer tratamento para essa situação.

**O que fazer:**
- Identificar quais funcionalidades realmente dependem de internet.
- Verificar se existe alguma integração remota prevista no PRD.
- Implementar verificação de conectividade nas operações que exigem conexão.
- Apresentar mensagens adequadas quando a conexão estiver indisponível.
- Impedir operações remotas de serem apresentadas como concluídas quando falharem.

**Importante:** não bloquear funcionalidades locais que foram planejadas para funcionar offline, como consultas a dados já armazenados no Room. Não inventar dependências de internet que não estejam previstas no projeto.

### 3.4. Botões de acessibilidade sem funcionamento

**Problema:** os botões de acessibilidade podem ser acionados, mas não provocam nenhuma mudança na interface.

**O que fazer:**
- Identificar todas as opções de acessibilidade disponíveis.
- Verificar a finalidade prevista de cada botão.
- Implementar o comportamento correspondente.
- Garantir que as alterações sejam percebidas nas telas.
- Verificar se as configurações permanecem aplicadas durante a navegação.
- Evitar botões meramente decorativos ou sem ação.

Não inventar funcionalidades diferentes das previstas no projeto. Caso alguma opção não tenha comportamento definido, documentar a dúvida.

### 3.5. Nome do usuário exibido incorretamente

**Problema:** o usuário informa um nome, mas o aplicativo continua exibindo o nome fixo "João da Silva".

**Hipótese:** as telas podem estar utilizando informações estáticas, mesmo que a estrutura de persistência com Room e DAO já esteja implementada.

**O que fazer:**
- Investigar o cadastro e verificar se os dados são realmente armazenados.
- Conferir as entidades, DAOs, repositórios e ViewModels existentes.
- Identificar se as telas utilizam valores fixos diretamente no código.
- Substituir os valores estáticos pela consulta dos dados reais.
- Garantir que o nome informado seja exibido corretamente no perfil e nas demais telas correspondentes.
- Verificar se a interface é atualizada após o cadastro ou alteração das informações.
- Utilizar os mecanismos existentes de observação de dados, como Flow, quando aplicável.

Não basta trocar "João da Silva" por outro texto. A interface deve apresentar os dados reais do usuário.

### 3.6. Serviço selecionado não corresponde ao serviço exibido

**Problema:** o usuário seleciona diferentes serviços, mas o agendamento continua apresentando "Clínico Geral".

**O que fazer:**
- Investigar a seleção de serviços na interface.
- Verificar como o serviço selecionado é enviado ao ViewModel.
- Conferir se o Repository e o DAO recebem o valor correto.
- Garantir que o serviço selecionado seja armazenado no agendamento.
- Verificar se a tela de confirmação e a tela de próximos agendamentos consultam o valor salvo.
- Eliminar valores fixos que estejam substituindo a seleção realizada pelo usuário.

O serviço exibido deve ser exatamente aquele escolhido durante o agendamento.

## 4. Investigação da arquitetura e integração

Como o projeto já possui estruturas relacionadas ao Room, DAO e persistência de dados, não criar uma segunda implementação sem necessidade.

Investigar o fluxo completo:

**Interface (Compose) → ViewModel → Repository → DAO → Room**

Também verificar o caminho de retorno:

**Room → DAO → Repository → ViewModel → Interface**

Identificar:
- Dados que são salvos, mas não consultados.
- Telas que utilizam informações estáticas.
- ViewModels que não recebem os dados corretos.
- Repositórios que não estão conectados às telas.
- Consultas que não atualizam a interface.
- Valores padrão que substituem informações reais.
- Problemas de estado e atualização da interface.

Corrigir as conexões necessárias, respeitando a arquitetura já existente.

## 5. Testes obrigatórios

Após implementar as correções, realizar testes para verificar:

1. Campos numéricos não aceitam letras indevidas.
2. Campos obrigatórios impedem o agendamento incompleto.
3. Mensagens de validação aparecem corretamente.
4. Funcionalidades locais continuam funcionando sem internet, quando previsto.
5. Funcionalidades que dependem de conexão tratam a ausência de internet.
6. Cada botão de acessibilidade executa sua função.
7. O nome cadastrado é armazenado e exibido corretamente.
8. O nome continua correto após fechar e abrir o aplicativo.
9. Diferentes serviços podem ser selecionados.
10. O serviço escolhido permanece correto no agendamento salvo.
11. Os dados apresentados nas telas correspondem aos registros do banco.
12. Não existem regressões nas funcionalidades que já estavam funcionando.

Executar também a compilação:

`./gradlew assembleDebug`

No Windows PowerShell:

`.\gradlew assembleDebug`

Se algum teste não puder ser executado, informar o motivo. Não declarar uma funcionalidade como corrigida sem evidência de verificação.

## 6. Documentação

Criar dois arquivos:

**Arquivo 1 — Prompt da execução**

Local: `docs/prompts/`

Nome sugerido: `20261104-correcao-funcionalidades-app.md`

Registrar:
- Objetivo da tarefa.
- Problemas identificados.
- Escopo das correções.
- Regras de branch e Git.
- Critérios de validação.

**Arquivo 2 — Relatório da execução**

Local: `docs/outputs/`

Nome sugerido: `20261104-correcao-funcionalidades-app.md`

Registrar somente o que realmente foi executado:
- Problemas encontrados durante a investigação.
- Arquivos analisados e modificados.
- Correções implementadas.
- Testes realizados e respectivos resultados.
- Problemas que permaneceram sem solução.
- Resultado da compilação.
- Branch utilizada.
- Situação final do Git.

Não registrar como concluída uma correção que não tenha sido implementada ou testada.

## 7. Limites de atuação

- Não refatorar partes do aplicativo sem necessidade.
- Não modificar o design geral sem relação com os problemas apresentados.
- Não remover funcionalidades existentes.
- Não criar entidades, tabelas ou estruturas duplicadas.
- Não alterar o PRD para justificar comportamentos incorretos.
- Não inventar integrações, APIs ou serviços externos.
- Não substituir a arquitetura atual por outra.
- Preservar as alterações anteriores do projeto.

Se durante a investigação forem encontrados outros problemas diretamente relacionados às funcionalidades analisadas, documentá-los e avaliar sua correção sem ampliar indevidamente o escopo.

## 8. Entrega final

Ao concluir:

1. Apresentar um resumo das correções realizadas.
2. Informar quais problemas foram resolvidos e quais permaneceram.
3. Apresentar os resultados dos testes.
4. Informar o resultado da compilação.
5. Listar os arquivos modificados e criados.
6. Informar a branch utilizada.
7. Apresentar o estado final do repositório com `git status`.

**NÃO REALIZAR COMMIT, PUSH, MERGE OU PULL REQUEST.**

A execução deve terminar com as alterações salvas localmente na branch de correção, aguardando a revisão e a autorização explícita do usuário para qualquer operação de Git que registre ou publique essas alterações.