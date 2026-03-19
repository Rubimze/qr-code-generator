![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![AWS S3](https://img.shields.io/badge/AWS_S3-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Status](https://img.shields.io/badge/STATUS-COMPLETED-brightgreen?style=for-the-badge)

# 📱 QR Code Generator API

Uma API REST desenvolvida em **Java** e **Spring Boot** para a geração de QR Codes dinâmicos, com upload automático e armazenamento na nuvem utilizando **Amazon S3**. O projeto é totalmente containerizado com **Docker**, garantindo facilidade no deploy e consistência de ambiente.

Este projeto foi construído para consolidar conhecimentos em desenvolvimento Back-end, integração com serviços Cloud (AWS) e boas práticas de conteinerização.

## 🌐 Demonstração e Front-end

Para tornar a experiência completa, este Back-end está integrado a uma interface Web (Front-end) desenvolvida em HTML/CSS e JavaScript Vanilla, que consome esta API em tempo real.

* 🔗 **Acesse a aplicação rodando na nuvem:** [https://qrcode-front-rust.vercel.app]
* 💻 **Veja o código-fonte do Front-end:** [https://github.com/Rubimze/qrcode-frontend](https://github.com/Rubimze/qrcode-frontend)

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.x** (Web)
* **AWS SDK (S3)** - Armazenamento de objetos na nuvem.
* **Google ZXing** - Biblioteca core para o processamento e geração da matriz do QR Code.
* **Docker** - Containerização da aplicação utilizando *Multi-stage build*.
* **Maven** - Gerenciamento de dependências.

## ⚙️ Arquitetura e Fluxo

1. O cliente envia uma requisição `POST` contendo o texto ou URL desejada.
2. O `QrCodeGeneratorService` utiliza o **ZXing** para processar o texto e transformá-lo em uma imagem PNG (`byte[]`) em memória.
3. O `S3StorageAdapter` recebe o arquivo gerado e faz o upload seguro para um bucket no **Amazon S3**.
4. A API retorna o Status `200 OK` com a URL pública gerada pela AWS para visualização imediata do QR Code.

## 🛠️ Como executar localmente

### Pré-requisitos
* **Docker** instalado e rodando.
* Conta na **AWS** com um bucket S3 criado e configurado para acesso público de leitura.
* Chaves de acesso do IAM (`AWS_ACCESS_KEY_ID` e `AWS_SECRET_ACCESS_KEY`) com permissão `AmazonS3FullAccess` ou permissão de `PutObject` no bucket selecionado.

### Passos para rodar

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/Rubimze/qr-code-generator.git](https://github.com/Rubimze/qr-code-generator.git)
   cd qr-code-generator
   ```

2. **Configure as Variáveis de Ambiente:**
   Crie um arquivo `.env` na raiz do projeto (este arquivo é ignorado pelo Git por segurança) e adicione suas credenciais da AWS:
   ```env
   AWS_ACCESS_KEY_ID=sua_chave_de_acesso_aqui
   AWS_SECRET_ACCESS_KEY=seu_secret_aqui
   ```
   *Nota: O nome da região e do bucket estão configurados no Dockerfile. Ajuste-os conforme a sua conta da AWS.*

3. **Gere o Build e execute o Container:**
   ```bash
   # Constrói a imagem Docker
   docker build -t qrcode-api:1.0 .

   # Roda o container passando as variáveis de ambiente e mapeando a porta
   docker run --env-file .env -p 8080:8080 qrcode-api:1.0
   ```

## 🔌 Endpoint da API

### `POST /qrcode`

**Payload (JSON):**
```json
{
  "text": "[https://www.linkedin.com/in/matheusrubim/](https://www.linkedin.com/in/matheusrubim/)"
}
```

**Resposta de Sucesso (200 OK):**
```json
{
  "qrCodeUrl": "[https://s3.us-east-1.amazonaws.com/seu-bucket/nome-do-arquivo-gerado.png](https://s3.us-east-1.amazonaws.com/seu-bucket/nome-do-arquivo-gerado.png)"
}
```

## 📸 Galeria de Telas

Abaixo está o fluxo completo da aplicação, desde o teste da API até o resultado final hospedado na nuvem.

<table width="100%">
  <tr>
    <td align="center" width="33%">
      <b>1. Teste no Insomnia</b><br><br>
      <img src="https://github.com/user-attachments/assets/c04f9558-7c31-45ff-bea6-c9fa5ce8c702" width="100%">
    </td>
    <td align="center" width="33%">
      <b>2. Objeto salvo no S3</b><br><br>
      <img src="https://github.com/user-attachments/assets/7d04f89d-ae56-4253-9bb0-b9ab8d15066a" width="100%">
    </td>
    <td align="center" width="33%">
      <b>3. QR Code gerado</b><br><br>
      <img src="https://github.com/user-attachments/assets/7ffac5af-4981-42bf-b122-90fd0c343d96" width="100%">
    </td>
  </tr>
</table>

## 💡 Créditos e Referências
A arquitetura base e as configurações de infraestrutura deste projeto foram inspiradas pelo excelente conteúdo da série "Projeto BACKEND do ZERO", da desenvolvedora Fernanda Kipper.
