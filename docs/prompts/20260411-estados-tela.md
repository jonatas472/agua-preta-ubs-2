# Planejamento de IA — Implementação dos quatro estados da tela principal

## Projeto
Água Preta UBS — Grupo Apollo

## Objetivo
Implementar os estados Carregando, Vazio, Erro e Sucesso na tela principal do aplicativo, conforme a atividade proposta pelo professor.

## 1. Preparação obrigatória

Antes de alterar qualquer arquivo:

1. Ler o AGENTS.md e o PRD.
2. Analisar a estrutura atual do projeto.
3. Identificar a tela principal, ViewModels, Repositories, DAOs e fontes de dados.
4. Conferir as dependências e convenções já utilizadas.
5. Verificar `git status`, branch atual e histórico recente.
6. Identificar alterações locais que precisam ser preservadas.

Não presumir que os arquivos ou componentes ainda não existem. Verificar antes de criar.

## 2. Branch obrigatória

Utilizar exclusivamente:

`feature/estados-da-tela`

Se a branch não existir, criá-la com segurança, preservando as alterações locais.

Nunca modificar diretamente a main.

Não executar commit, push, merge ou pull request sem autorização explícita do usuário. A atividade exige commits separados no resultado final, portanto, preparar a organização dos commits por finalidade e aguardar autorização para executá-los.

## 3. Arquitetura compartilhada

Criar o arquivo `UiState.kt` no pacote adequado do projeto.

Ele deverá representar os quatro estados exigidos:

- Carregando
- Vazio
- Erro
- Sucesso

Utilizar uma estrutura compatível com Kotlin e com a arquitetura existente, preferencialmente uma hierarquia de estados selada quando apropriado.

O estado de sucesso deverá transportar os dados necessários para a interface.

O estado de erro deverá disponibilizar informação suficiente para apresentar uma mensagem e permitir nova tentativa.

Não criar quatro ViewModels ou quatro sistemas independentes de estado.

## 4. Integração com ViewModel

O ViewModel será responsável por controlar as transições entre estados.

Implementar o fluxo:

- Início da operação: Carregando.
- Operação concluída sem dados: Vazio.
- Operação concluída com dados: Sucesso.
- Operação com falha: Erro.

Utilizar try/catch nas operações que podem falhar.

O botão de nova tentativa deverá chamar novamente a operação original do ViewModel.

Não capturar indiscriminadamente todas as exceções sem distinguir cancelamento de corrotinas e falhas reais.

Não apresentar sucesso antes da conclusão real da operação.

Não adicionar um `else` genérico ao final de um `when` que esconda estados não tratados. O tratamento dos estados deve ser explícito e exaustivo.

## 5. Integração da interface

A tela principal deverá observar o estado disponibilizado pelo ViewModel e desenhar a interface correspondente.

Implementar os quatro casos:

- Carregando: indicador de progresso.
- Vazio: mensagem de ausência de dados.
- Erro: mensagem de falha e botão Tentar novamente.
- Sucesso: conteúdo real da tela principal.

Todos os textos visíveis devem ficar em `strings.xml`.

Preservar a identidade visual e os componentes já existentes.

## 6. Divisão de responsabilidades

- Jonatas Calado: Carregando e coordenação da integração do UiState e ViewModel.
- Cecília Helena: Vazio.
- Isi Luana: Erro.
- Marilia Gabrielly: Sucesso.

Cada integrante deverá trabalhar no seu componente, respeitando o contrato compartilhado.

Antes de integrar alterações simultâneas, conferir conflitos de arquivos e combinar interfaces, nomes de funções e parâmetros.

Não criar implementações duplicadas.

## 7. Testes e sabotagem

Realizar o teste em modo avião e verificar o comportamento da tela principal.

Executar cinco sabotagens controladas, registrando cada uma em `docs/sabotagem.md`.

Para cada teste, registrar:

- Número e descrição da sabotagem.
- O que foi alterado ou interrompido de propósito.
- Como o teste foi executado.
- Qual estado apareceu.
- Qual mensagem foi exibida.
- Se o aplicativo permaneceu aberto.
- Se a recuperação funcionou.
- Resultado: aprovado ou reprovado.

As cinco sabotagens devem ser diferentes e representar falhas plausíveis. Não inventar resultados: executar os testes e registrar o que realmente aconteceu.

Restaurar cada sabotagem depois do teste, sem deixar alterações artificiais no código de produção.

A aprovação exige que o aplicativo não quebre em nenhuma das cinco situações.

## 8. Documentação

Criar ou atualizar:

- `docs/sabotagem.md`: resultados reais das cinco sabotagens.
- `strings.xml`: mensagens dos quatro estados.
- `UiState.kt`: definição compartilhada dos estados.

Não criar arquivos duplicados se já houver uma estrutura equivalente.

## 9. Commits e Pull Request

Organizar as alterações em commits separados por finalidade, conforme solicitado pelo professor.

Exemplo de organização:

- `feat: adiciona UiState da tela principal`
- `feat: implementa estados de carregamento e vazio`
- `feat: implementa estados de erro e sucesso`
- `test: documenta testes de sabotagem`

Os exemplos são sugestões de separação, não autorização para executar commits.

O Pull Request deverá ter como origem `feature/estados-da-tela` e como destino a branch principal definida pelo repositório.

Não abrir o Pull Request sem autorização do usuário.

## 10. Validação final

Executar:

`.\gradlew assembleDebug`

Verificar:
- Os quatro estados aparecem nas condições corretas.
- O botão Tentar novamente funciona.
- O modo avião não provoca tela branca nem encerra o aplicativo.
- Os cinco testes de sabotagem foram executados e documentados.
- Os textos visíveis estão no strings.xml.
- O código não contém um else genérico que esconda estados não tratados.
- Não existem conflitos ou alterações alheias à atividade.

## 11. Entrega

Apresentar:
- Arquivos criados e modificados.
- Responsabilidade de cada integrante.
- Resultado dos testes.
- Conteúdo de docs/sabotagem.md.
- Resultado da compilação.
- Estado da branch.
- Estado final do Git.

Aguardar autorização para commit, push e abertura do Pull Request.

Ao final da execução deste planejamento, gerar um documento em docs/outputs registrando o que foi feito seguinto este prompt