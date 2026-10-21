# UBS+

Aplicativo Android desenvolvido pelo Grupo Apollo como protótipo de apoio aos moradores que utilizam a UBS de Água Preta, Pernambuco. O projeto reúne telas de agendamento, serviços, perfil e acessibilidade.

## Estado atual

O aplicativo está em fase de protótipo. O formulário de agendamento e a tela de confirmação demonstram o fluxo da interface, mas não registram consultas na UBS. As entidades Room estão definidas em código, porém o banco de dados e a persistência ainda não foram conectados ao aplicativo. A tela de vacinas também não está disponível no fluxo atual.

Por isso, este protótipo não substitui o atendimento da unidade nem confirma consultas. Não há integração com sistemas do SUS, serviço remoto ou sincronização entre dispositivos.

## Tecnologias

- Kotlin
- Jetpack Compose e Material 3
- Navigation Compose
- Room: dependências e entidades adicionadas; persistência ainda não integrada

## Requisitos

- Android Studio com suporte ao Android Gradle Plugin usado pelo projeto
- Android SDK 37 para compilação
- Dispositivo ou emulador com Android 7.0 (API 24) ou superior

## Executar

Clone o repositório e abra a pasta `src` no Android Studio:

```bash
git clone https://github.com/jonatas472/agua-preta-ubs-2.git
cd agua-preta-ubs-2/src
```

Para gerar o APK de depuração pelo terminal:

```bash
# macOS ou Linux
./gradlew :app:assembleDebug

# Windows
gradlew.bat :app:assembleDebug
```

O APK será gerado em `app/build/outputs/apk/debug/app-debug.apk`.

## Documentação

- [Canvas do projeto](./CANVAS.md): problema, público, proposta e escopo.
- [Requisitos do produto](./PRD.md): requisitos, critérios de aceite e plano de testes.
- [Instruções para agentes](./AGENTS.md): convenções de trabalho do grupo.
- [Documentos de planejamento e relatórios](./docs/): materiais de apoio e registros das etapas.

## Equipe

Grupo Apollo, 3º ano do Ensino Médio: Jonatas Calado, Cecília Helena, Isi Luana e Marília Gabrielly.
