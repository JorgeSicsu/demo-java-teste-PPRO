# Introdução

Esta é a primeira de um processo de entrevista técnica de 3 etapas:

1. Desafio prático. Implementar os requisitos de um sistema de faturamento imaginário.
2. Desafio de codificação ao vivo. Ampliar a solução prática com recursos adicionais, em um ambiente real.
3. Design e arquitetura do sistema. Design aberto de um sistema de faturamento de alta disponibilidade e nível de produção.

O processo de entrevista simula alguns dos problemas reais que enfrentamos e permite que você os resolva usando uma abordagem real.

## O que buscamos ao longo deste processo

**Código simples e autoexplicativo:** Gostamos de código limpo e simples. A solução que você produzir deve falar por si só -
vários parágrafos explicando a solução podem ser um sinal de que ela não é direta o suficiente. Não há necessidade de
tentar fazer a solução parecer "inteligente". Tente equilibrar a simplicidade do código com a eficiência relativa.

**Separação clara de interesses:** Mesmo em um exercício tão simples, gostaríamos de ver você separando claramente os interesses,
seguindo o princípio da responsabilidade única e estruturando seu código de acordo.

**Cobertura de Teste e TDD:** A solução deve ser desenvolvida "test-first" e deve ter cobertura de teste automatizada
adequada.

**Familiaridade com Java/Kotlin, incluindo simultaneidade:** Mesmo em uma tarefa simples, queremos ver evidências de que você
entende Java/Kotlin moderno. Preste atenção aos tipos de dados que você usa na tarefa e a quaisquer requisitos de simultaneidade
que possam surgir. Isso será particularmente importante durante a programação em par ao vivo, portanto, se você estiver se sentindo enferrujado
nesta área, pode ser uma boa ideia revisar este tópico com antecedência.

**Capacidade de explicar seu processo de pensamento, decisões e quaisquer compensações:** Trabalhar com colegas é uma das
coisas mais importantes que você fará. Queremos ver boas habilidades de colaboração, seja na programação em par
ou na explicação de suas decisões de arquitetura para outras pessoas.

## Dicas

* Estamos fornecendo projetos esqueleto para Java e Kotlin que contêm código básico. Você pode presumir que
o código fornecido aqui está correto e, se não estiver familiarizado com Spring, pode simplesmente segui-lo como exemplo.
Se preferir, você também pode começar do zero com seu próprio projeto, por exemplo, usando outro framework (crie um
subdiretório separado se fizer isso).
* Você pode optar por usar Java ou Kotlin
* Não faremos perguntas aprofundadas sobre Spring.
* Não há "pegadinhas" intencionais; se as tarefas parecerem simples, ótimo!
* Geralmente, é permitido pesquisar no Google e consultar documentação durante o processo de entrevista.

# Desafio para levar para casa

Faturamos nossos clientes semanalmente. Cada fatura cobra por transações que ocorreram durante um
**período de faturamento** específico, identificado por um **ano** e um **número de período**, da seguinte forma:

- Os períodos de faturamento são numerados consecutivamente dentro de um ano.

- O primeiro período de cobrança começa em 1º de janeiro com o período número 1.
- Cada **sábado** marca o início de um novo período de cobrança.
- Cada **primeiro dia do mês** marca o início de um novo período de cobrança.

Você pode consultar [calendário de exemplo para 2 meses de 2019](calendar.md) para uma representação visual do acima.

Escreva um código que atenda aos requisitos para os dois recursos abaixo. Esta tarefa foi projetada para ser um exercício de codificação simples
e não deve levar mais do que 2 a 3 horas.

## Requisitos não funcionais

- Siga uma abordagem TDD e garanta que o ciclo esteja claro a partir dos commits do git
- Envie a solução como uma PR no repositório fornecido e informe ao seu recrutador que ela está pronta para revisão
- Os recursos 1 e 2 devem ser capazes de lidar com chamadas frequentes, com múltiplas threads

## Recurso 1

Deve ser possível obter um período de cobrança para uma determinada data (local). O serviço deve retornar o respectivo
período de faturamento com periodId. Isso pode ser implementado como um serviço interno (sem necessidade de exposição via REST). Cabe a você
decidir quaisquer estruturas de dados relevantes.

### Exemplos

| Entrada | Resposta |
|---------------|-------------------|
| 24 de jan. de 2019 | periodId = 2019-4 |
| 2 de fev. de 2019 | periodId = 2019-7 |

## Recurso 2

Precisamos expor uma API REST que retornará todos os períodos dentro de um ano, com suas respectivas datas de início e término
(inclusive).

Como exemplo, para 2019, devemos obter os seguintes períodos na resposta (crie uma estrutura apropriada para
a resposta):

```
periodId=2019-1, from=2019-01-01, to=2019-01-04
periodId=2019-2, from=2019-01-05, to=2019-01-11
periodId=2019-3, from=2019-01-12, to=2019-01-18
periodId=2019-4, from=2019-01-19, to=2019-01-25
periodId=2019-5, from=2019-01-26, to=2019-01-31
periodId=2019-6, from=2019-02-01, to=2019-02-01
periodId=2019-7, from=2019-02-02, to=2019-02-08
periodId=2019-8, from=2019-02-09, to=2019-02-15
periodId=2019-9, from=2019-02-16, to=2019-02-22
periodId=2019-10, from=2019-02-23, to=2019-02-28
periodId=2019-11, from=2019-03-01, to=2019-03-01
periodId=2019-12, from=2019-03-02, to=2019-03-08
periodId=2019-13, from=2019-03-09, to=2019-03-15
periodId=2019-14, from=2019-03-16, to=2019-03-22
periodId=2019-15, from=2019-03-23, to=2019-03-29
periodId=2019-16, from=2019-03-30, to=2019-03-31
periodId=2019-17, from=2019-04-01, to=2019-04-05
periodId=2019-18, from=2019-04-06, to=2019-04-12
periodId=2019-19, from=2019-04-13, to=2019-04-19
periodId=2019-20, from=2019-04-20, to=2019-04-26
periodId=2019-21, from=2019-04-27, to=2019-04-30
periodId=2019-22, from=2019-05-01, to=2019-05-03
periodId=2019-23, from=2019-05-04, to=2019-05-10
periodId=2019-24, from=2019-05-11, to=2019-05-17
periodId=2019-25, from=2019-05-18, to=2019-05-24
periodId=2019-26, from=2019-05-25, to=2019-05-31
periodId=2019-27, from=2019-06-01, to=2019-06-07
periodId=2019-28, from=2019-06-08, to=2019-06-14
periodId=2019-29, from=2019-06-15, to=2019-06-21
periodId=2019-30, from=2019-06-22, to=2019-06-28
periodId=2019-31, from=2019-06-29, to=2019-06-30
periodId=2019-32, from=2019-07-01, to=2019-07-05
periodId=2019-33, from=2019-07-06, to=2019-07-12
periodId=2019-34, from=2019-07-13, to=2019-07-19
periodId=2019-35, from=2019-07-20, to=2019-07-26
periodId=2019-36, from=2019-07-27, to=2019-07-31
periodId=2019-37, from=2019-08-01, to=2019-08-02
periodId=2019-38, from=2019-08-03, to=2019-08-09
periodId=2019-39, from=2019-08-10, to=2019-08-16
periodId=2019-40, from=2019-08-17, to=2019-08-23
periodId=2019-41, from=2019-08-24, to=2019-08-30
periodId=2019-42, from=2019-08-31, to=2019-08-31
periodId=2019-43, from=2019-09-01, to=2019-09-06
periodId=2019-44, from=2019-09-07, to=2019-09-13
periodId=2019-45, from=2019-09-14, to=2019-09-20
periodId=2019-46, from=2019-09-21, to=2019-09-27
periodId=2019-47, from=2019-09-28, to=2019-09-30
periodId=2019-48, from=2019-10-01, to=2019-10-04
periodId=2019-49, from=2019-10-05, to=2019-10-11
periodId=2019-50, from=2019-10-12, to=2019-10-18
periodId=2019-51, from=2019-10-19, to=2019-10-25
periodId=2019-52, from=2019-10-26, to=2019-10-31
periodId=2019-53, from=2019-11-01, to=2019-11-01
periodId=2019-54, from=2019-11-02, to=2019-11-08
periodId=2019-55, from=2019-11-09, to=2019-11-15
periodId=2019-56, from=2019-11-16, to=2019-11-22
periodId=2019-57, from=2019-11-23, to=2019-11-29
periodId=2019-58, from=2019-11-30, to=2019-11-30
periodId=2019-59, from=2019-12-01, to=2019-12-06
periodId=2019-60, from=2019-12-07, to=2019-12-13
periodId=2019-61, from=2019-12-14, to=2019-12-20
periodId=2019-62, from=2019-12-21, to=2019-12-27
periodId=2019-63, from=2019-12-28, to=2019-12-31
```
