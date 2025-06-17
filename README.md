# 🧠 Spring QRCode Generator API

API REST construída com **Spring Boot** para gerar QR Codes sob demanda. A API permite gerar códigos QR a partir de textos, links ou qualquer string, salvando automaticamente os arquivos em um **bucket S3 da AWS**.

## 🚀 Funcionalidades

- ✅ Geração de QR Codes via chamada HTTP
- ✅ Upload automático dos QR Codes para um **S3 Bucket AWS**
- ✅ Retorno da URL pública ou privada do QR Code no S3
- ✅ Suporte a múltiplos formatos de imagem (PNG, SVG, JPEG)
- ✅ API containerizada com **Docker**
- ✅ Fácil integração com outros sistemas

## 🛠️ Tecnologias utilizadas

- Java 17+ / 21+
- Spring Boot
- Spring Web
- AWS SDK (S3)
- ZXing (geração de QR Codes)
- Docker
- Maven ou Gradle

## 📦 Como rodar com Docker

1. Clone este repositório:

    git clone https://github.com/seu-usuario/spring-qrcode-generator.git
    cd spring-qrcode-generator

2. Configure suas variáveis de ambiente AWS (arquivo `.env` ou direto no ambiente):

    AWS_ACCESS_KEY_ID=SUAS_CREDENCIAIS
    AWS_SECRET_ACCESS_KEY=SUA_CHAVE_SECRETA
    AWS_REGION=sa-east-1
    AWS_S3_BUCKET=nome-do-seu-bucket

3. Build da imagem Docker:

    docker build -t spring-qrcode-generator .

4. Execute o container:

    docker run -p 8080:8080 --env-file .env spring-qrcode-generator

A API estará disponível em:

    http://localhost:8080

## 🔗 Endpoints principais

### Gerar QR Code

**POST** `/api/qrcode`

#### 🔸 Request Body (JSON)

    {
      "text": "https://github.com/Kaue-Romero"
    }

#### 🔸 Response (200 OK)

    {
      "url": "https://<seu-bucket>.s3.<regiao>.amazonaws.com/qrcodes/uuid-qrcode.png"
    }

### 📂 Parâmetros

| Campo    | Tipo   | Descrição                                   |
|----------|--------|----------------------------------------------|
| text  | string | Texto, URL ou string para gerar o QR Code   |

## ☁️ Configuração AWS S3

Certifique-se de que:

- O bucket S3 existe.
- Suas credenciais têm permissões para `PutObject` e `GetObject` no bucket.
- As permissões públicas (se desejar links públicos) estão configuradas no bucket ou via política.

## 📜 Exemplo de Política IAM

    {
      "Version": "2012-10-17",
      "Statement": [
        {
          "Effect": "Allow",
          "Action": [
            "s3:PutObject",
            "s3:GetObject"
          ],
          "Resource": "arn:aws:s3:::nome-do-seu-bucket/*"
        }
      ]
    }

## 🏗️ Construção local (alternativa)

    ./mvnw spring-boot:run

## 🛡️ Segurança

- Variáveis sensíveis devem ser gerenciadas com cofres de segredo (AWS Secrets Manager, Doppler, Vault, etc.).

## ✨ Melhorias futuras

- 🔐 Autenticação de API
- 📊 Dashboard com estatísticas de uso
- 🗑️ Endpoint para deletar QR Codes do S3
- 🏷️ Customização de cores e tamanhos dos QR Codes

## 📄 Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
'''
