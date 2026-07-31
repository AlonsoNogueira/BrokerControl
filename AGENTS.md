# AGENTS.md — BrokerControl

Guia de referência para agentes de IA e desenvolvedores trabalharem neste repositório.
Leia este documento antes de fazer qualquer alteração no projeto.

---

## 1. Visão Geral do Projeto

**BrokerControl** é um SaaS voltado para **corretores de imóveis** que desejam gerenciar
suas operações de venda e aluguel em um só lugar. O produto permite que cada corretor:

- **Cadastre e acompanhe clientes** com contratos assinados (venda e aluguel).
- **Registre imóveis** que pertencem ao corretor (sob sua gestão/administração).
- **Acompanhe métricas financeiras** (comissões, receitas, contratos ativos, etc.).
- **Gerencie todo o ciclo do negócio imobiliário** de forma centralizada.

É um sistema **multi-tenant por usuário**: cada corretor enxerga e gerencia apenas os
seus próprios dados. Autenticação via **JWT**.

---

## 2. Stack Tecnológica

### API (Backend)
| Camada | Tecnologia |
|---|---|
| Linguagem | Java 25 |
| Framework | Spring Boot 4.1.0 |
| Persistência | Spring Data JPA + Hibernate |
| Segurança | Spring Security + JWT |
| Validação | Bean Validation (`spring-boot-starter-validation`) |
| Banco | PostgreSQL 18 |
| Build | Maven (wrapper `./mvnw`) |
| Boilerplate | Lombok |

### Web (Frontend)
| Camada | Tecnologia |
|---|---|
| Linguagem | TypeScript |
| Framework | React 19 |
| Build tool | Vite 6 |
| Server State | TanStack Query v5 |
| UI | shadcn/ui |
| Estilização | Tailwind CSS v4 |

### Infraestrutura
- **Docker** + **Docker Compose** para subir banco de dados, API e frontend.
- Variáveis de ambiente no arquivo `.env` na raiz (exemplo em `.env.exemple`).

---

## 3. Estrutura do Repositório

```
BrokerControl/
├── AGENTS.md                  # Este arquivo
├── .env                       # Variáveis de ambiente (NÃO commitar)
├── .env.exemple               # Modelo das variáveis de ambiente
├── .gitignore
├── Apps/
│   ├── API/
│   │   └── brokercontrol/     # Projeto Spring Boot (Java 25 + Maven)
│   │       ├── pom.xml
│   │       ├── mvnw
│   │       └── src/
│   │           ├── main/
│   │           │   ├── java/io/github/alnszzx/brokercontrol/
│   │           │   │   ├── BrokercontrolApplication.java
│   │           │   │   └── Domain/Model/
│   │           │   │       └── Broker.java
│   │           │   └── resources/application.yaml
│   │           └── test/
│   └── WEB/                   # Projeto React 19 + Vite (a criar)
└── Docs/                      # Documentação do produto (a preencher)
```

> **Atenção:** o projeto Spring Boot usa **pacotes com letras maiúsculas**
> (`io.github.alnszzx.brokercontrol.Domain.Model`). Siga o padrão existente ao
> criar novos pacotes: `Domain`, `Application`, `Infrastructure`, `Interface` (Architecture DDD/Hexagonal).

---

## 4. Como Rodar o Projeto

### 4.1 Pré-requisitos
- JDK 25
- Node.js 20+ (compatível com Vite 6)
- Docker + Docker Compose
- Maven (ou usar o `./mvnw`)

### 4.2 Configuração inicial
1. Copie `.env.exemple` para `.env` e preencha os valores:
   ```bash
   cp .env.exemple .env
   ```
2. Gere um segredo JWT seguro: 
   ```bash
   openssl rand -base64 64
   ```

### 4.3 API (Backend)
```bash
cd Apps/API/brokercontrol
./mvnw spring-boot:run
```
A API sobe na porta definida em `.env` (`PORT`, padrão `3000`).

### 4.4 Web (Frontend)
```bash
cd Apps/WEB
npm install
npm run dev
```

### 4.5 Docker Compose (banco + API + web)
```bash
docker compose up --build
```

### 4.6 Banco de dados
O PostgreSQL 18 roda em container via Docker Compose. Em desenvolvimento pode-se
também apontar para uma instância remota (ex.: Neon) via `DATABASE_URL` no `.env`.

---

## 5. Comandos de Build, Teste e Lint

### API
| Ação | Comando |
|---|---|
| Rodar | `cd Apps/API/brokercontrol && ./mvnw spring-boot:run` |
| Testes | `cd Apps/API/brokercontrol && ./mvnw test` |
| Build | `cd Apps/API/brokercontrol && ./mvnw clean package` |

### Web
| Ação | Comando |
|---|---|
| Rodar (dev) | `cd Apps/WEB && npm run dev` |
| Build | `cd Apps/WEB && npm run build` |
| Lint | `cd Apps/WEB && npm run lint` |
| Testes | `cd Apps/WEB && npm test` |

> **Regra:** sempre rodar lint e testes antes de concluir uma tarefa.

---

## 6. Convenções de Código

### Geral
- **Não adicionar comentários** no código, a menos que o usuário solicite.
- Seguir o estilo de código dos arquivos vizinhos existentes.
- Não commitar segredos/credenciais; o `.env` já está no `.gitignore`.
- Após alterações, sempre verificar build/lint/testes.

### API (Java / Spring Boot)
- Arquitetura em camadas (sugestão): `Domain` → `Application` → `Infrastructure` → `Interface`.
- Manter o padrão de nomes de pacotes com iniciais maiúsculas já existente.
- Usar **Lombok** (`@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`) nas entidades.
- IDs das entidades: `UUID` com `@GeneratedValue(strategy = GenerationType.UUID)`.
- Mapear entidades com anotações JPA (a entidade `Broker` atual ainda não possui `@Entity` — adicionar quando fizer parte do domínio).
- Configuração via `application.yaml`, consumindo variáveis de ambiente (`${VAR}`).
- `ddl-auto: update` em desenvolvimento.

### Web (React / TypeScript)
- Componentes de UI via **shadcn/ui** (pasta `components/ui`).
- Server state gerenciado exclusivamente com **TanStack Query v5**.
- Estilização com **Tailwind CSS v4**.
- Tipagem forte: tipos compartilhados para os contratos da API.

---

## 7. Conceitos de Domínio

| Conceito | Descrição |
|---|---|
| **Broker (Corretor)** | Usuário do sistema, dono dos dados (multi-tenant). |
| **Client (Cliente)** | Pessoa atendida pelo corretor, pode ter contratos. |
| **Property (Imóvel)** | Imóvel registrado sob gestão do corretor. |
| **Contract (Contrato)** | Contrato de venda ou aluguel assinado com o cliente. |
| **Metrics (Métricas)** | Indicadores financeiros do corretor (comissões, receita, ativos). |

---

## 8. Segurança e Boas Práticas
- Nunca logar/expor segredos (JWT secret, senhas, credenciais de banco).
- Não commitar o arquivo `.env`.
- Qualquer endpoint sensível deve exigir autenticação JWT (Spring Security).
- Usar `@Valid`/Bean Validation nas entradas de API.

---

## 9. Notas de Estado Atual
- **API:** projeto inicial criado com Spring Boot 4.1.0, contendo apenas a aplicação principal e a entidade `Broker` (ainda sem `@Entity` e sem repositórios/controllers).
- **Web:** diretório `Apps/WEB` criado, mas **ainda sem código**.
- **Docs:** diretório `Docs/` criado e vazio.
- **Docker Compose:** ainda não criado — deve contemplar banco (PostgreSQL 18), API e frontend.
