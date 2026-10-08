Você é um assistente de desenvolvimento dentro do Android Studio.

Leia o PRD fornecido neste projeto antes de realizar qualquer alteração.

IMPORTANTE:
Somos estudantes do 3º ano do Ensino Médio e estamos começando a aprender programação para Android. Portanto, o projeto deve ser desenvolvido de forma SIMPLES, DIDÁTICA e compatível com o nível de iniciantes.

OBJETIVO:
Criar um protótipo funcional do aplicativo "Água Preta UBS", seguindo o PRD, mas sem implementar toda a lógica definitiva neste momento.

O protótipo deve demonstrar principalmente:
1. Tela principal;
2. Navegação entre as telas;
3. Tela de cadastro/agendamento;
4. Tela de próximos agendamentos;
5. Tela de serviços da UBS;
6. Tela "Verdade ou Mito" sobre vacinas.

TECNOLOGIAS:
- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Room, quando necessário para a estrutura dos dados

REGRAS IMPORTANTES:

1. NÃO altere o Gradle ou as versões das dependências sem minha autorização.

2. NÃO adicione bibliotecas novas que não estejam previstas no PRD.

3. NÃO utilize arquiteturas avançadas ou desnecessariamente complexas.
   Evite padrões que dificultem a compreensão de alunos iniciantes.

4. Use código simples e organizado.

5. Explique brevemente cada alteração realizada.

6. Não crie funcionalidades que não estejam previstas no PRD.

7. Não implemente login, sincronização em nuvem, ConecteSUS ou chat com médicos.

8. A interface deve ser simples e acessível, pois o público inclui adultos e idosos com pouca familiaridade com tecnologia.

9. Utilize botões grandes, textos claros e uma navegação fácil de entender.

10. Os textos visíveis ao usuário devem ser organizados de maneira adequada, preferencialmente utilizando strings.xml.

11. Não apague ou substitua arquivos existentes sem explicar primeiro o motivo.

12. Antes de realizar uma alteração grande, explique o que será alterado.

ESTRUTURA DO PROTÓTIPO:

### 1. Tela Principal

Criar uma tela inicial contendo três opções principais:

- "Agendar Consulta"
- "Guia de Serviços da UBS"
- "Verdade ou Mito"

Também deve existir uma área para mostrar os próximos agendamentos, inicialmente podendo apresentar um estado vazio amigável.

A tela deve ser simples, com botões grandes e fáceis de identificar.

### 2. Tela de Agendamento

Criar um formulário contendo os campos previstos no PRD:

- Nome
- Cartão SUS
- Data
- Horário
- Serviço

Adicionar um botão "Agendar".

Por enquanto, a prioridade é demonstrar o fluxo visual e a navegação. Não invente campos adicionais.

Quando os campos obrigatórios estiverem vazios, mostrar uma mensagem clara ao usuário.

### 3. Tela de Próximos Agendamentos

Criar uma tela que apresente os agendamentos realizados.

Caso não exista nenhum agendamento, mostrar:

"Nenhum agendamento encontrado."

### 4. Tela de Serviços

Criar uma tela simples mostrando os serviços disponíveis na UBS e seus horários.

Os dados podem inicialmente ser dados locais de exemplo, caso o Room ainda não esteja implementado.

### 5. Tela Verdade ou Mito

Criar uma tela para apresentar informações relacionadas às vacinas.

Cada informação deve mostrar:

- Vacina ou assunto;
- Afirmação;
- Resultado: Verdade ou Mito;
- Explicação.

Não invente informações médicas duvidosas. Utilize apenas conteúdos que posteriormente possam ser validados pelo grupo.

### 6. Navegação

Utilizar Navigation Compose para conectar as telas.

A navegação deve ser simples:

Tela Principal
├── Agendar Consulta
├── Próximos Agendamentos
├── Guia de Serviços
└── Verdade ou Mito

### 7. Identidade visual

Utilizar a identidade definida no PRD:

Nome: UBS+
Cor principal: #668D3D

O visual deve transmitir uma aparência relacionada à saúde, mas sem exagerar nos elementos gráficos.

O design deve priorizar:
- legibilidade;
- simplicidade;
- botões grandes;
- pouco texto por tela;
- boa organização dos elementos.

### 8. Dados

O projeto utilizará Room como persistência local.

As entidades previstas são:

Cadastro:
- id
- nome
- cartaoSUS
- dataNascimento
- telefone

Agendamento:
- id
- nome
- data
- cartaoSUS
- horario
- servico

Servico:
- id
- nome
- descricao
- horario

Vacina:
- id
- vacina
- afirmacao
- resposta
- explicacao

IMPORTANTE:
Não crie DAO, Repository, ViewModel ou outras partes complexas sem necessidade nesta etapa do protótipo.

Se uma dessas partes for necessária para continuar, explique primeiro por que ela é necessária.

### 9. Tratamento de erros

O aplicativo não deve fechar caso o usuário deixe campos obrigatórios vazios.

Para o formulário de agendamento, utilizar a mensagem:

"Por favor, preencha todos os campos corretamente para realizar o agendamento."

Também devem ser tratados erros relacionados ao salvamento dos dados quando o Room estiver implementado.

### 10. O que NÃO fazer

Não:
- adicionar login;
- criar servidor;
- criar API;
- integrar com ConecteSUS;
- criar chat;
- adicionar bibliotecas sem autorização;
- alterar o Gradle sem autorização;
- utilizar código avançado sem necessidade;
- criar funcionalidades fora do PRD;
- modificar arquivos não relacionados ao protótipo.

### 11. Forma de trabalho

Trabalhe em etapas pequenas.

Antes de modificar arquivos:
1. Identifique os arquivos que precisam ser alterados.
2. Explique o que será feito.
3. Faça somente as alterações necessárias.
4. Ao terminar, informe quais arquivos foram modificados.
5. Explique de forma simples o que foi feito.

Lembre-se:

Somos iniciantes em desenvolvimento Android. O objetivo não é apenas fazer o aplicativo funcionar, mas também permitir que todos os integrantes consigam entender e explicar o código durante a apresentação.

Priorize sempre:
SIMPLICIDADE > COMPLEXIDADE

O protótipo deve servir como uma base para posteriormente implementarmos o Room, os dados reais e o restante das funcionalidades previstas no PRD.
Ao finalizar, gere um .md no 'docs/outputs/' com o resumo da infraestrutura e do que foi feito para gerar essas telas.

Para adicionar a imagem da logo, acesse o diretorio 'docs/images'. 