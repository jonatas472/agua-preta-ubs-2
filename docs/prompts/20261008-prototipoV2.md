Você é um assistente de desenvolvimento dentro do Android Studio.

IMPORTANTE:
Este projeto NÃO deve ser recriado do zero.

Já existe um protótipo Android do aplicativo "Água Preta UBS" neste projeto. Sua tarefa é adaptar o protótipo existente para seguir o novo design visual apresentado pelo grupo.

Antes de modificar qualquer coisa:

1. Leia o PRD existente no projeto.
2. Analise a estrutura atual do projeto.
3. Analise as telas e componentes que já existem.
4. Identifique quais telas podem ser reutilizadas e quais precisam ser modificadas.
5. Não apague funcionalidades existentes sem explicar o motivo.

==================================================
CONTEXTO DO PROJETO
==================================================

Somos estudantes do 3º ano do Ensino Médio e estamos começando a aprender desenvolvimento Android.

Portanto:

- O código deve ser simples.
- A implementação deve ser didática.
- Evite arquiteturas avançadas desnecessárias.
- Evite abstrações difíceis de entender.
- Não faça alterações desnecessárias.
- Explique brevemente as alterações realizadas.
- O código precisa ser compreensível para alunos iniciantes.

O projeto utiliza:

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Room

O objetivo desta etapa é principalmente adaptar a INTERFACE e a NAVEGAÇÃO do protótipo existente ao novo design.

==================================================
REGRA PRINCIPAL
==================================================

NÃO crie um novo projeto.

NÃO substitua o projeto atual por uma implementação diferente.

NÃO transforme o projeto Android em uma aplicação Web.

O projeto atual em Kotlin + Jetpack Compose deve continuar sendo a base.

O design fornecido serve como REFERÊNCIA VISUAL.

Reproduza a aparência do design utilizando componentes Android/Jetpack Compose.

==================================================
IDENTIDADE VISUAL
==================================================

O aplicativo deve utilizar a identidade visual observada no novo design:

Nome:
UBS+

Identificação visual:
UBS • Água Preta

Localização exibida:
Água Preta - Pernambuco

A aparência geral deve utilizar:

- Verde escuro como cor principal;
- Verde claro para áreas secundárias;
- Fundo claro, próximo de branco/creme;
- Cards com cantos arredondados;
- Botões arredondados;
- Ícones simples;
- Tipografia limpa;
- Alto contraste;
- Bastante legibilidade.

Não utilize cores aleatórias.

Não utilize gradientes desnecessários.

Não sobrecarregue as telas com elementos gráficos.

==================================================
TELA PRINCIPAL
==================================================

Adaptar a tela principal existente para seguir o novo design.

Estrutura visual:

TOPO:

- Ícone de menu no canto superior esquerdo.
- Texto pequeno indicando:
  "UBS • Água Preta"
- Ícone de notificação no canto superior direito.
- Logo da UBS centralizada.
- Texto:
  "Água Preta - Pernambuco"

PRÓXIMO AGENDAMENTO:

Criar um card grande verde para o próximo agendamento.

O card deve possuir efeito de camadas/cascata na parte inferior, semelhante ao design de referência.

O card deve apresentar:

"Próximo Agendamento"

Nome do paciente.

Data de registro.

Data e horário do atendimento, quando disponíveis.

Adicionar ícone de calendário no canto superior direito.

Adicionar uma pequena indicação de navegação/detalhes no canto inferior direito.

IMPORTANTE:

Se não houver agendamento, apresentar um estado vazio apropriado sem quebrar a interface.

Não inventar dados reais.

==================================================
AÇÕES PRINCIPAIS
==================================================

Abaixo do card de agendamento:

Título:

"O que você precisa hoje?"

Criar um botão principal:

"Agendar Consulta"

Esse botão deve possuir:

- Fundo verde claro;
- Ícone de calendário/consulta;
- Texto;
- Seta no lado direito;
- Cantos arredondados.

Abaixo dele, criar dois botões menores:

"Serviços e Horários"

"Acessibilidade"

Os dois devem possuir ícones e seguir o mesmo padrão visual.

==================================================
NAVEGAÇÃO INFERIOR
==================================================

Adicionar uma barra de navegação inferior semelhante ao design.

Itens:

- Início
- Agendamentos
- Serviços
- Perfil

Cada item deve possuir:

- Ícone;
- Texto pequeno;
- Estado selecionado visualmente destacado.

A navegação deve utilizar Navigation Compose.

Não criar uma navegação paralela ou desnecessária.

==================================================
TELA AGENDAR CONSULTA
==================================================

Adaptar a tela de agendamento existente.

No topo:

- Botão de voltar.
- Título:
  "Agendar Consulta"

Descrição:

"Preencha os dados abaixo para marcar sua consulta na UBS de Água Preta."

Campos:

- Nome completo
- Número do cartão SUS
- Data
- Horário
- Serviço

Os campos devem ser grandes e fáceis de tocar.

Cada campo pode possuir um pequeno ícone relacionado ao seu conteúdo.

Utilizar aparência semelhante ao design:

- Fundo claro;
- Bordas suaves;
- Cantos arredondados;
- Texto de exemplo dentro dos campos;
- Espaçamento organizado.

Botão:

"Confirmar agendamento"

Utilizar o verde principal.

Não criar campos que não estejam previstos no PRD.

==================================================
TELA DE CONFIRMAÇÃO
==================================================

Depois de um agendamento bem-sucedido, apresentar uma tela semelhante ao design.

Mostrar:

Ícone grande de confirmação.

Título:

"Consulta agendada!"

Texto:

"Guarde seus dados e compareça à UBS de Água Preta no horário de atendimento."

Criar um card:

"Resumo do agendamento"

Mostrar as informações disponíveis do agendamento.

No final:

Botão:

"Voltar ao início"

==================================================
TELA MEUS AGENDAMENTOS
==================================================

Criar/adaptar a tela de agendamentos.

Título:

"Meus agendamentos"

Apresentar os agendamentos em formato de lista.

Cada item pode mostrar:

- Nome;
- Cartão SUS;
- Data de registro;
- Informações do agendamento;
- "Ver detalhes".

Separar visualmente os itens com linhas ou cards simples.

No final, permitir:

"Agendar Consulta"

Esse botão deve levar para a tela de agendamento.

Se não houver agendamentos, mostrar uma mensagem amigável de estado vazio.

==================================================
TELA SERVIÇOS E HORÁRIOS
==================================================

Criar/adaptar a tela:

"Serviços e horários"

Apresentar os serviços de forma simples.

Seguir o padrão visual do design.

Cada serviço deve apresentar:

- Ícone;
- Nome do serviço;
- Dias de atendimento;
- Horário.

Exemplos visuais observados no design:

Clínico Geral
Segunda a Sexta - 08:00 às 17:00

Odontologia
Terça e Quinta - 09:00 às 16:00

Vacinação
Segunda a Sexta - 08:00 às 17:00

Pré-Natal
Quarta-feira - 08:00 às 12:00

Esses dados podem ser tratados como dados locais de exemplo.

Não criar API ou servidor.

==================================================
TELA PERFIL
==================================================

Criar/adaptar a tela:

"Perfil"

Mostrar:

- Ícone de perfil;
- Nome do usuário;
- Cartão SUS;
- Informação sobre o último agendamento realizado neste aparelho.

A aparência deve ser simples, seguindo o design fornecido.

Não criar sistema de login.

==================================================
TELA ACESSIBILIDADE
==================================================

Criar/adaptar uma tela simples de acessibilidade.

Opções observadas no design:

"Texto ampliado"

"Alto contraste"

As opções devem possuir aparência de botões/cartões simples.

IMPORTANTE:

Se implementar essas opções exigir alterações complexas, primeiro explique.

Não adicionar bibliotecas externas.

==================================================
MENU
==================================================

Criar/adaptar o menu mostrado no design.

O menu deve permitir acessar:

- Agendar Consulta
- Meus agendamentos
- Serviços e horários
- Acessibilidade
- Perfil

Adicionar botão para fechar o menu.

O menu deve utilizar os recursos existentes do Jetpack Compose.

Não criar uma biblioteca externa apenas para isso.

==================================================
VACINAS / MURAL
==================================================

O novo design fornecido pelo grupo NÃO apresenta mais a opção "Mural Verdade ou Mito".

Portanto:

NÃO adicionar o Mural de Vacinas à interface principal.

NÃO adicionar um botão de Mural de Vacinas no menu.

Se ainda existir código referente a essa tela no protótipo atual, NÃO apague automaticamente.

Primeiro verifique se sua remoção é necessária para adaptar a interface.

Se for necessário remover alguma rota ou componente, explique antes.

==================================================
DADOS E ENTIDADES
==================================================

Manter a estrutura de dados existente do projeto.

As entidades planejadas pelo grupo são:

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

Não criar estruturas complexas desnecessárias.

Não alterar o modelo de dados sem explicar.

==================================================
ROOM
==================================================

O projeto utiliza Room para persistência local.

Caso o Room ainda não esteja completamente implementado no protótipo, não tente construir toda a arquitetura de uma vez apenas para modificar o design.

Prioridade desta etapa:

1. Interface;
2. Navegação;
3. Fluxo visual;
4. Compatibilidade com a estrutura existente.

A implementação completa dos dados pode continuar posteriormente.

==================================================
REGRAS DE DESENVOLVIMENTO
==================================================

NÃO:

- alterar o Gradle sem autorização;
- alterar versões de dependências sem autorização;
- adicionar bibliotecas novas;
- criar login;
- criar servidor;
- criar API;
- integrar ConecteSUS;
- criar chat;
- criar funcionalidades que não estejam no PRD;
- substituir o projeto inteiro;
- apagar arquivos sem explicar;
- criar arquitetura avançada sem necessidade.

IMPORTANTE:

O projeto atualmente utiliza compileSdk 37 para atender às dependências existentes.

Não altere esse valor sem autorização.

==================================================
ORGANIZAÇÃO DO TRABALHO
==================================================

Trabalhe em etapas pequenas.

ANTES de modificar os arquivos:

1. Analise o projeto atual.
2. Liste os arquivos que precisam ser modificados.
3. Explique o que será alterado em cada arquivo.
4. Aguarde autorização caso a alteração seja estrutural ou grande.

Depois:

5. Faça somente as alterações necessárias.
6. Informe os arquivos modificados.
7. Explique brevemente o que foi feito.
8. Informe se alguma parte do design não pôde ser reproduzida exatamente.

==================================================
PRIORIDADE
==================================================

A prioridade é:

1. Manter o projeto existente funcionando.
2. Reproduzir o novo design.
3. Manter a navegação simples.
4. Manter o código compreensível para iniciantes.
5. Não criar complexidade desnecessária.

PRINCÍPIO:

SIMPLICIDADE > COMPLEXIDADE

O resultado final deve parecer visualmente próximo ao design fornecido pelo grupo, mas continuar sendo um aplicativo Android desenvolvido em Kotlin + Jetpack Compose.

Analise o protótipo visual que desenvolvi no Lovable:

LINK DO LOVABLE: https://ubs-patient-path.lovable.app

Use esse protótipo como referência visual para adaptar o aplicativo Água Preta UBS existente. Preserve a estrutura atual do projeto Android e utilize Kotlin, Jetpack Compose e Material 3.

Primeiro, verifique se consegue acessar o link. Se não conseguir visualizar o protótipo, me avise e solicite capturas de tela das páginas necessárias. Não invente detalhes que não conseguiu observar.

Utilize também a logo localizada em `app/src/main/res/drawable/logo_ubs.png`. Não crie outra logo nem altere a identidade visual original.

Antes de modificar qualquer arquivo, apresente um plano das alterações que pretende realizar.
Utilize a versão mais recente do protipo apresentado no link anexado.

