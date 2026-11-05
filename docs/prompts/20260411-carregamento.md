# Planejamento de IA — Estado Carregando (Loading)

**Projeto:** Água Preta UBS  
**Responsável:** Jonatas Calado  
**Branch:** `feature/estados-da-tela`

## 1. Objetivo

Implementar o estado Carregando da tela principal do aplicativo, garantindo que o usuário receba uma indicação visual enquanto os dados estão sendo processados ou carregados.

Além disso, será responsável por coordenar a estrutura compartilhada de UiState e sua integração com o ViewModel, permitindo que os outros três integrantes implementem seus estados seguindo o mesmo padrão.

## 2. Investigação inicial

Antes de modificar qualquer arquivo:

- Ler AGENTS.md e PRD.
- Analisar a estrutura atual do projeto.
- Identificar a tela principal.
- Localizar o ViewModel responsável pelos dados da tela.
- Verificar os Repositories e DAOs utilizados.
- Identificar como os dados são carregados atualmente.
- Verificar se já existe alguma estrutura de estado que possa ser reutilizada.
- Conferir as alterações pendentes no Git.

Não criar estruturas duplicadas.

## 3. Implementação do UiState

Criar `UiState.kt` no pacote adequado.(Caso UiState.kt já exista, não crie um novo.)

A estrutura deverá contemplar os quatro estados exigidos:

- Carregando.
- Vazio.
- Erro.
- Sucesso.

Definir um contrato compartilhado que permita aos outros integrantes implementar seus componentes sem criar estruturas independentes.

O estado de Sucesso deverá permitir transportar os dados necessários para a interface.

O estado de Erro deverá permitir apresentar uma mensagem apropriada e realizar nova tentativa.

A estrutura deverá utilizar recursos adequados do Kotlin, como sealed class ou sealed interface, conforme a arquitetura existente.

## 4. Implementação do estado Carregando

Criar o componente visual utilizando Jetpack Compose.

Requisitos:

- Exibir um indicador de progresso.
- Apresentar uma mensagem de carregamento, se apropriado.
- Manter a identidade visual do Água Preta UBS.
- Evitar que a tela fique branca durante o carregamento.
- Garantir que o indicador desapareça quando a operação terminar.

Os textos deverão ser definidos em `strings.xml`.

## 5. Integração com ViewModel

Implementar ou ajustar o fluxo de carregamento.

O ViewModel deverá:

- Alterar o estado para Carregando quando iniciar uma operação.
- Aguardar o resultado real da consulta.
- Encaminhar os resultados para os estados correspondentes.
- Tratar falhas sem encerrar o aplicativo.
- Permitir que o estado seja observado pela interface.

Não criar um ViewModel independente para cada estado.

Não adicionar um else genérico ao final de um when para esconder estados não tratados.

## 6. Integração com os integrantes

Disponibilizar aos demais integrantes:

- Localização do UiState.kt.
- Estrutura e nomes dos estados.
- Forma de acessar o estado no ViewModel.
- Contrato para apresentar dados no estado Sucesso.
- Método utilizado para tentar novamente uma operação.

Conferir se os componentes desenvolvidos por Cecília, Isi e Marilia respeitam a estrutura compartilhada.

## 7. Testes

- [ ] O indicador aparece durante o carregamento.
- [ ] A interface não fica branca.
- [ ] O indicador desaparece após a conclusão.
- [ ] O estado muda corretamente para Vazio, Erro ou Sucesso.
- [ ] O ViewModel não apresenta erros de estado.
- [ ] Os quatro estados funcionam integrados.

## 8. Documentação

Registrar a implementação em `docs/outputs/`.

Documentar os arquivos modificados, decisões técnicas, testes e resultados reais.

## 9. Restrições

- Não modificar diretamente a main.
- Trabalhar exclusivamente em `feature/estados-da-tela`.
- Preservar alterações locais.
- Não refatorar partes sem necessidade.
- Não duplicar UiState ou ViewModel.
- Não realizar commit, push, merge ou Pull Request sem autorização explícita.