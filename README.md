
COMANDOS

- LIMPA CACHE E EXECUTA O TESTE (Não executar o comando mvn clean test em si não tem implicações graves, especialmente se você já tiver 
feito um build limpo anteriormente ou se não estiver realizando mudanças significativas em seu código ou configuração de build.)
mvn clean test

- EXECUTA O TESTE
mvn test

- PARA GERAR O RELATÓRIO ALLURE
Depois de executar seus testes utilizar o comando para gerar o relatório com o Allure:
allure generate

- PARA ABRIR O RELATÓRIO
allure open

COMANDO PARA EXECUTAR O ARQUIVO testng.xml COM A SUÍTE DE TESTES 
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
mvn test -DsuiteXmlFile=testng.xml

IMPORTANTE
- ESTE PROJETO FOI FEITO NO VS CODE
- FORAM UTILIZADAS DUAS EXTENSÕES MUITO ÚTEIS
  - TestNg TestSuite Runner: UTILIZADA PARA EXECUTAR O ARQUIVO testng.xlm SEM A NECESSIDADE DE LINHA DE COMANDOS

  - Allure Support: URILIZADA PARA GERAR OS RELATÓRIO ALLURE APÓS EXECUTAR UM TESTE. A EXTENSÃO GERA NOVAS PASTAS COM O RELATÓRIO ALLURE NO PADRÃO TIMESTAMP.
  COMO UTILIZAR:
    APÓS EXECUTAR UM TESTE > CLICAR COM BOTÃO DIREITO NA PASTA allure-results > SELECIONAR A OPÇÃO ALLURE REPORT: GENERATE REPORT > SERÁ GERADA UMA PASTA COM TODOS OS DADOS DA ULTIMA EXECUÇÃO DE TESTES CONFORME OS ARQUIVOS .JSON > NOME EXEMPLO DE UMA PASTA "allure-report_2024-02-16_14-21-30".
OBS.: COM ESTAS DUAS EXTENSÕES NÃO HÁ A NECESSIDADE DE UTILIZAR OS COMANDOS allure generate E allure open.

Exemplo do relatório gerado: https://www.meussistemas.com/relatorio-testes/relatorioqa4/allure-report/
