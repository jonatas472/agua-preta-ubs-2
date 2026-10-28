# Uso de Inteligência Artificial

## 1. Objetivo

Este documento registra de forma transparente o uso de ferramentas de inteligência artificial no desenvolvimento e na manutenção deste projeto. O escopo confirmado nesta versão é a tarefa de revisão da entidade `Servico` e documentação realizada em 22 de outubro de 2026; não representa um histórico completo das contribuições anteriores ao projeto.

## 2. Ferramentas de IA utilizadas

Foi utilizado o GitHub Copilot integrado ao Visual Studio Code para auxiliar na inspeção do repositório, análise da entidade e elaboração dos registros desta tarefa. O modelo e sua versão não foram identificados no contexto disponível e, por isso, não são especificados.

## 3. Finalidades de utilização

Nesta execução, a IA foi utilizada para:

- localizar e analisar a classe `Servico` e compará-la com as entidades Room próximas e com o modelo descrito no PRD;
- verificar as dependências e configurações de persistência relevantes;
- apoiar a redação deste documento e do registro de solicitação e resultado.

Não foram realizadas alterações de código nem criação ou atualização de testes nesta execução.

## 4. Alterações relacionadas ao projeto

A análise confirmou que não existe `Servico.kt`: `Servico` está declarada em `src/app/src/main/java/br/edu/ifpe/ubsaude/Data.kt`. A classe representa dados persistidos e já possui `@Entity`, o import `androidx.room.Entity`, `@PrimaryKey(autoGenerate = true)` e os campos definidos no PRD (`nome`, `descricao` e `horario`). Por isso, nenhuma alteração Kotlin foi necessária e nenhum commit de código foi criado.

Esta tarefa criou `USO_DE_IA.md` e os registros `docs/prompts/20261022-revisao-servico-uso-ia.md` e `docs/outputs/20261022-revisao-servico-uso-ia.md`. Este documento não atribui à IA mudanças anteriores no repositório.

## 5. Validação e revisão humana

Foram inspecionados o código da entidade, `Agendamento.kt`, o trecho correspondente do PRD, as dependências e configurações Gradle e o estado/histórico do Git. A compilação `:app:assembleDebug` foi tentada pelo Gradle Wrapper, mas não iniciou porque `JAVA_HOME` não está definido e não há comando `java` no PATH. Os testes não foram executados pelo mesmo bloqueio.

As alterações e sugestões produzidas com auxílio de IA devem ser revisadas por uma pessoa desenvolvedora, com atenção proporcional ao impacto e à criticidade. Para concluir a validação técnica do build, é necessário disponibilizar e configurar um JDK compatível e repetir a compilação e os testes pertinentes.

## 6. Limitações e transparência

A IA pode produzir análises incorretas ou incompletas e não substitui conhecimento do domínio, revisão de código, testes ou validação no ambiente de execução. As conclusões deste registro se limitam aos arquivos e evidências consultados nesta tarefa. O módulo declara `room-runtime` e `room-ktx`; não foi localizada configuração de processador Room (KSP/KAPT), e a integração efetiva de um banco de dados não foi validada aqui.

## 7. Atualização do documento

Atualize este arquivo quando houver mudanças relevantes nas ferramentas ou modelos identificados, nas finalidades de utilização ou nas práticas de uso de IA. Registre contribuições verificáveis e resultados efetivamente observados, distinguindo-os de informações desconhecidas e recomendações.