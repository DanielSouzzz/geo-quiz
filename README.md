# Sistema de Consulta de CEP - Spring Boot com Docker Compose

Este projeto é um sistema de consulta de CEP desenvolvido em **Java com Spring Boot**, que consome a API pública [ViaCEP](https://viacep.com.br/) para retornar informações de endereços. Ele conta com uma interface simples em HTML/CSS/JS para consulta e utiliza **Docker Compose** para orquestrar a aplicação.

---

## 🚀 Funcionalidades

- Consulta de endereço a partir de um CEP informado.
- Retorno de informações como:
  - Logradouro
  - Bairro
  - Complemento
  - Cidade
  - UF
- Integração com a API pública ViaCEP.
- Interface web simples para interação.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Maven**
- **Docker e Docker Compose**
- **API ViaCEP**
- **HTML, CSS e JavaScript (frontend)**

---

## 📋 Requisitos

- **Docker** e **Docker Compose** instalados
- **JDK 17**
- **Maven**

---

## ⚙️ Configuração do Projeto

1. Clone o repositório.
2. Faça o build do projeto com o Docker:
   ```bash
   docker-compose up --build
   ```
3. A aplicação estará disponível em:  
   👉 **http://localhost:8080** (backend)  
   👉 **http://localhost:8080/index.html** (frontend)

Para encerrar o ambiente:
```bash
docker-compose down
```

---

## 📌 Endpoints da API

### 🔹 Consultar CEP
```bash
curl --location 'http://localhost:8080/api/cep/88015570'
```

**Resposta Exemplo:**
```json
{
  "logradouro": "Rua Felipe Schmidt",
  "bairro": "Centro",
  "complemento": "",
  "localidade": "Florianópolis",
  "uf": "SC"
}
```

---

## 🌐 Interface Web

O sistema possui uma página simples em **HTML/CSS/JS** para consulta de CEP.  
Basta abrir o navegador em: [http://localhost:8080/index.html](http://localhost:8080/index.html)

Na página, o usuário digita o CEP e obtém os dados de endereço automaticamente.

---

## 🔒 Segurança

- O endpoint `/api/cep/{cep}` é **público**.
- Outros endpoints (se adicionados no futuro) podem ser configurados para exigir autenticação.

---

## 🐳 Docker

### Dockerfile
```dockerfile
FROM maven:3.8.5-amazoncorretto-17 AS build
WORKDIR /usr/src/app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# execução
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /usr/src/app/target/ViaCep-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### docker-compose.yml
```yaml
version: "3.8"
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - JAVA_OPTS=-Xmx512m
    volumes:
      - .:/usr/src/app
```

---

## 📖 Exemplo de Uso na Interface Web

1. Digite um CEP válido, por exemplo: `88015-570`.
2. Clique em **Consultar**.
3. Veja os dados retornados:
   - Logradouro: Rua Felipe Schmidt
   - Bairro: Centro
   - Cidade: Florianópolis
   - Estado: SC

---

## ✅ Conclusão

Este projeto é uma aplicação simples e prática para consulta de CEP utilizando **Spring Boot, ViaCEP e Docker**. Ele pode servir de base para estudos ou ser expandido para sistemas maiores que necessitem de consulta de endereços.
