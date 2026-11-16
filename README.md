# Sistema de Gerenciamento de Funcionários e Folha de Pagamento

Projeto acadêmico em Java 17, organizado em camadas, com armazenamento em memória e testes automatizados JUnit 5.

## Funcionalidades

- Cadastro, listagem, busca por código, CPF ou nome e remoção de funcionários.
- Cálculo polimórfico de remuneração para CLT, PJ e comissionado.
- Relatório individual e total da folha.
- Validação de CPF, campos obrigatórios, valores e percentuais.
- Exceções próprias para funcionário duplicado e não encontrado.
- Interface interativa de console.

## Regras de cálculo

- **CLT:** salário-base + benefício.
- **PJ:** valor por projeto × quantidade de projetos.
- **Comissionado:** salário-base + (vendas × percentual de comissão / 100).
- Os valores são arredondados para duas casas decimais com `HALF_UP`.
- O método `calcularTributos(percentual)` calcula um desconto sobre a remuneração bruta. A alíquota é fornecida na chamada porque o enunciado não define percentuais fiscais. Esse cálculo é separado da remuneração bruta exibida no relatório.

## Organização

```text
src/main/java/br/com/empresa/folha/
├── exception/   Exceções do domínio
├── model/       Funcionários e interfaces
├── repository/  Armazenamento em memória
├── service/     Regras e operações
├── view/        Interface de console
└── Main.java
src/test/java/   Testes JUnit 5 organizados pelos mesmos pacotes
```

## Executar

Requisitos: JDK 17+ e Maven 3.8+.

Na pasta do projeto:

```bash
mvn test
mvn package
mvn exec:java -Dexec.mainClass=br.com.empresa.folha.Main
```

Se o plugin `exec-maven-plugin` não estiver disponível, compile com `mvn package` e execute a classe `br.com.empresa.folha.Main` pela IDE, com o classpath de `target/classes`.

## Testes e TDD

Os testes cobrem validações, remuneração por categoria, polimorfismo, tributos, operações do repositório e serviço, sobrecarga de busca, exceções próprias e encerramento da interface. Para executá-los, use `mvn test`.

Para demonstrar o ciclo Red, Green e Refactor no Git, mantenha commits pequenos: primeiro os testes de uma regra, depois a implementação mínima que os faz passar e, por fim, a refatoração sem alterar o comportamento. O histórico do GitHub deve ser criado no repositório da equipe e publicado pela própria conta.
