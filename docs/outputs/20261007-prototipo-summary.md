# Resumo da Entrega: Protótipo UBS+

**Data:** 07/10/2026
**Responsável:** Agent (Grupo Apollo)

## 1. Descrição do Protótipo
Implementação da interface de usuário (UI) funcional para o aplicativo UBS+, focando na acessibilidade e navegação básica entre as funcionalidades do MVP.

## 2. Telas Implementadas
- **Home:** Menu principal com acesso rápido a agendamentos, serviços e mural de vacinas.
- **Agendamento:** Formulário para coleta de dados do paciente e detalhes da consulta.
- **Meus Agendamentos:** Visualização de consultas marcadas (atualmente simulada).
- **Guia de Serviços:** Listagem informativa de horários e especialidades da UBS.
- **Mural "Verdade ou Mito":** Cards educativos para combate à desinformação sobre vacinação.

## 3. Detalhes Técnicos
- **Navegação:** Implementada via `androidx.navigation:navigation-compose`.
- **UI:** Construída inteiramente com Jetpack Compose e Material Design 3.
- **Persistência (Infra):** Criado arquivo `Data.kt` com as entidades Room prontas para implementação da lógica de banco de dados.
- **Recursos:** Textos centralizados em `strings.xml` para fácil manutenção.

## 4. Próximos Passos
- Implementar o `AppDatabase` e os `DAO`s para persistência real dos dados.
- Adicionar lógica de `try/catch` no salvamento de agendamentos.
