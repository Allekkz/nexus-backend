# Usando imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

# Definindo diretório de trabalho dentro do container
WORKDIR /app

# Copiando o .jar gerado para dentro do container
COPY target/nexus-0.0.1-SNAPSHOT app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]