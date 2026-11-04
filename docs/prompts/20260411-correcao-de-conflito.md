# Resolução de conflitos — Integração das funcionalidades do Água Preta UBS

Estamos realizando a integração da branch `fix/correcao-funcionalidades-app` com a `main`, utilizando a branch de trabalho `integracao/correcao-funcionalidades`.

O merge já foi iniciado com:

`git merge --no-commit --no-ff origin/fix/correcao-funcionalidades-app`

Existem conflitos pendentes que precisam ser resolvidos manualmente.

## 1. Contexto importante

A versão da branch `fix/correcao-funcionalidades-app` já foi testada anteriormente e apresentou funcionamento correto.

Portanto, preserve ao máximo sua implementação, principalmente:

- Validação dos campos do agendamento.
- Formatação de data e horário.
- Validação do cartão SUS.
- Persistência dos agendamentos utilizando Room.
- Utilização de AgendamentoRepository.
- Exibição da confirmação após salvar.
- Acessibilidade, incluindo aumento de texto e alto contraste.

Não reescreva essas funcionalidades sem necessidade. O objetivo é integrar a implementação já testada com as funcionalidades existentes na `main`.

## 2. Arquivos com conflitos pendentes

Resolva os conflitos dos seguintes arquivos:

1. `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoViewModel.kt`
2. `src/app/src/main/java/br/edu/ifpe/ubsaude/MainActivity.kt`
3. `src/app/src/main/java/br/edu/ifpe/ubsaude/Screens.kt`
4. `src/app/src/main/res/values/strings.xml`

Antes de editar, examine as duas versões de cada arquivo e compreenda suas responsabilidades.

Utilize a versão da `main` e a versão da `fix` como referências para produzir uma implementação integrada. Não escolha automaticamente `ours` ou `theirs`.

## 3. Funcionalidades que precisam ser preservadas da main

O aplicativo já possui funcionalidades integradas ao Room que não podem ser perdidas:

- Entidade Cadastro.
- Entidade Agendamento.
- Entidade Servico.
- ServicoDao.
- ServicoViewModel.
- Listagem dinâmica de serviços.
- Listagem de agendamentos.
- Estados de carregamento e erro.
- Integração dos serviços com o AppDatabase.

O AppDatabase já foi resolvido mantendo a versão da main, com banco na versão 3, entidade Servico, migration 1→2, migration 2→3 e cadastro de serviços de exemplo.

O AgendamentoDao também já foi resolvido mantendo a versão da main.

Não reverta essas decisões.

## 4. Orientações específicas

### AgendamentoViewModel.kt

Combine a implementação testada da fix com os recursos da main.

Preserve:

- AgendamentoRepository.
- AgendamentoValidation.
- Estado de carregamento.
- Tratamento de erros ao carregar.
- Proteção contra salvamentos simultâneos.
- Resultado do salvamento.
- Último agendamento salvo.
- ID retornado pelo Room.
- Tratamento correto de CancellationException.

Garanta que a validação também seja realizada no ViewModel, não somente na interface.

Mantenha compatibilidade com as telas e com o fluxo de confirmação.

### MainActivity.kt

Integre as duas implementações.

É obrigatório preservar:

- AgendamentoViewModel.
- AgendamentoRepository e sua ViewModelProvider.Factory.
- ServicoViewModel.
- Carregamento dos serviços do Room.
- Estados de carregamento e erro.
- Navegação existente.
- Preferências de acessibilidade.
- Aumento da escala de texto.
- Alto contraste.

Não substitua a listagem dinâmica de serviços por uma lista estática.

### Screens.kt

Preserve as melhorias da fix:

- Validação dos campos.
- Formatação de data e horário.
- Campo de cartão SUS limitado a 15 dígitos.
- Teclado adequado aos campos.
- Seleção de serviços.
- Feedback de erros.
- Confirmação do agendamento salvo.
- Acessibilidade funcional.

Preserve também as funcionalidades da main:

- Home com informações dos agendamentos.
- Serviços carregados dinamicamente do Room.
- Estados de carregamento e erro.
- Listagem de meus agendamentos.
- Navegação existente.

A seleção de serviços no formulário deve utilizar os serviços fornecidos pelo ServicoViewModel, evitando divergência entre a lista exibida e os serviços permitidos pela validação.

Na tela de perfil, não utilize o primeiro agendamento como se fosse automaticamente o cadastro oficial do usuário. Preserve a estrutura sem inventar dados pessoais.

### strings.xml

Faça uma mesclagem aditiva dos recursos das duas versões.

Não remova strings utilizadas pelas funcionalidades da main ou da fix.

Garanta que todos os identificadores utilizados no Kotlin existam no arquivo XML.

Evite recursos duplicados com o mesmo nome.

## 5. Arquivos adicionais

Preserve os arquivos já adicionados pela branch fix:

- `AccessibilityPreferences.kt`
- `AgendamentoRepository.kt`
- `AgendamentoValidation.kt`
- `AgendamentoValidationTest.kt`
- `docs/prompts/20261104-correcao-codigo.md`
- `docs/outputs/20261104-correcao-funcionalidades-app.md`

Não remova arquivos ou alterações já incorporados durante o merge.

## 6. Restrições obrigatórias

- Não executar git merge --abort.
- Não executar reset.
- Não executar rebase.
- Não descartar alterações de nenhuma branch.
- Não modificar arquivos que não estejam relacionados à integração.
- Não fazer commit.
- Não fazer push.
- Não executar git merge --continue.
- Não alterar o histórico do Git.

Resolva os conflitos diretamente nos arquivos de trabalho.

## 7. Verificações

Depois das alterações:

1. Verifique se não existem marcadores de conflito (`<<<<<<<`, `=======`, `>>>>>>>`).
2. Verifique se os imports estão corretos.
3. Verifique se as assinaturas dos ViewModels e das telas são compatíveis.
4. Verifique se a integração dinâmica de serviços continua funcionando.
5. Execute os testes disponíveis.
6. Execute `./gradlew assembleDebug` ou, no Windows, `.\gradlew.bat assembleDebug`.
7. Corrija erros causados pelas alterações realizadas, sem introduzir refatorações desnecessárias.

## 8. Relatório final

Ao terminar, apresente:

- Arquivos modificados.
- Como cada conflito foi resolvido.
- Funcionalidades preservadas da main.
- Funcionalidades preservadas da fix.
- Testes executados e seus resultados.
- Problemas encontrados ou limitações.

Não finalize o merge e não crie commits. A decisão de registrar e concluir a integração será feita manualmente após a revisão.