# Solicitação: revisão da entidade Serviço e registro do uso de IA

## Objetivo

Revisar a anotação `@Entity` da classe `Servico` conforme a arquitetura e os padrões do projeto, registrar o uso de inteligência artificial em `USO_DE_IA.md` e documentar a solicitação e sua execução nas pastas `docs/prompts/` e `docs/outputs/`.

## Escopo solicitado

- Inspecionar o estado do Git, a estrutura Android/Kotlin, o histórico de commits, as entidades Room existentes e a documentação.
- Localizar `Servico.kt` e verificar se `@Entity`, os imports e os campos persistidos estão corretos.
- Alterar o código somente se a análise técnica demonstrar necessidade.
- Validar compilação e testes disponíveis.
- Criar um commit exclusivo das alterações necessárias em `Servico.kt`, sem incluir documentação nem trabalho alheio.
- Criar `USO_DE_IA.md` na raiz com ferramentas, finalidades confirmadas, contribuições desta execução, validações, limitações, revisão humana e orientação de atualização.
- Registrar este pedido em `docs/prompts/` e o resultado em `docs/outputs/`.

## Restrições

- Não aplicar `@Entity` mecanicamente sem confirmar que a classe representa dados persistidos.
- Preservar alterações existentes e não incluir arquivos não relacionados no commit.
- Não inventar histórico, modelos, versões, atividades ou resultados de validação.
- Não alterar dependências Gradle sem necessidade e autorização.
- Não fazer amend, reescrever histórico ou enviar alterações ao remoto.

## Critério de segurança do commit

O commit só deve ser criado se houver uma alteração de código necessária e isolável. Se `Servico.kt` não existir, se a anotação já estiver correta ou se o commit exclusivo não puder ser garantido, registrar o motivo e não criar commit vazio ou misturado.