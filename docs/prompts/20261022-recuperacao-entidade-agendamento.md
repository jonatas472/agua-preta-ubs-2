# Solicitação de Recuperação: Entidade `Agendamento`

## Contexto

A entidade `Agendamento` já estava especificada no PRD, mas sua implementação estava junto das demais entidades em `src/app/src/main/java/br/edu/ifpe/ubsaude/Data.kt`, e não em um arquivo próprio.

## Solicitação do professor

Síntese das informações disponíveis nesta tarefa: criar `Agendamento.kt` com a entidade anotada com `@Entity` e preparar um commit próprio dessa implementação, feito pela IDE. O texto literal original do professor não foi disponibilizado.

## Objetivo

Implementar a entidade Kotlin `Agendamento` com Room, de acordo com a definição confirmada no PRD vigente.

## Escopo

- Criar `src/app/src/main/java/br/edu/ifpe/ubsaude/Agendamento.kt`.
- Registrar esta solicitação em `docs/prompts/` e a execução em `docs/outputs/`.
- Remover a definição anterior de `Agendamento` de `Data.kt` somente para evitar uma classe duplicada após a extração.
- Não criar outras funcionalidades nem alterar a configuração Gradle.

## Restrições

- Não inventar campos, validações, relacionamentos ou regras de negócio.
- Não manter duas definições da entidade.
- Não ampliar a tarefa para DAOs, repositórios, telas, navegação, banco de dados ou migrações.
- Preservar alterações e arquivos existentes que não pertençam à tarefa.
- O resumo recebido mencionou `id: Long`, mas a tabela da entidade `Agendamento` no PRD vigente define `id: Int`, chave primária autogerada. A divergência deve ser registrada e não resolvida por preferência pessoal.

## Critérios de conclusão

- A entidade em arquivo próprio corresponde à definição confirmada no PRD.
- A solicitação e o resultado são registrados nos diretórios documentais indicados.
- As verificações efetivamente executadas, resultados e pendências são relatados.
- A implementação e a documentação ficam identificadas para commits separados, sem commit ou envio automático.