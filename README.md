#  ToDo List API + Frontend

Projeto fullstack para gerenciamento de tarefas (ToDo List), desenvolvido com **Spring Boot** no backend e **HTML + JavaScript puro** no frontend.

---

##  Funcionalidades

- Criar tarefas
- Listar tarefas
- Atualizar tarefas
- Deletar tarefas
- Controle de status:
  - `PENDENTE`
  - `EM_ANDAMENTO`
  - `CONCLUIDO`
  - `ATRASADO` (automático)
- Controle de datas:
  - Data de criação (automática)
  - Data limite
  - Data de conclusão (automática)
- Atualização automática de tarefas atrasadas (Scheduler)

---

##  Tecnologias

### Backend
- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- MapStruct
- Scheduler (`@Scheduled`)

### Frontend
- HTML5
- CSS
- JavaScript (Vanilla)

---

##  Como rodar o projeto

### 🔹 Backend

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/todo-list.git
```

2. Acesse a pasta:
```bash
cd todo-list
```

3. Execute o projeto:
```bash
./mvnw spring-boot:run
```

Ou rode pela sua IDE (IntelliJ / Eclipse)

---

### 🔹 Frontend

 **IMPORTANTE:** não abra o HTML com `file://`

Use um servidor local:

```bash
python -m http.server 5500
```

Acesse no navegador:
```
http://localhost:5500
```

---

##  Endpoints da API

###  Listar todos (paginado)
```
GET /todos
```

###  Listar todos (sem paginação)
```
GET /todos/all
```

###  Buscar por ID
```
GET /todos/findById/{id}
```

###  Buscar por nome
```
GET /todos/findByNome?nome=abc
```

###  Criar tarefa
```
POST /todos
```

#### Body:
```json
{
  "nome": "Nova tarefa",
  "descricao": "Descrição da tarefa",
  "status": "PENDENTE",
  "dataLimite": "2026-04-30T14:30:00"
}
```

---

###  Atualizar tarefa
```
PUT /todos/{id}
```

#### Body:
```json
{
  "nome": "Atualizada",
  "descricao": "Nova descrição",
  "status": "CONCLUIDO",
  "dataLimite": "2026-04-30T14:30:00"
}
```

---

###  Deletar tarefa
```
DELETE /todos/{id}
```

---

## ⏱️ Regras de negócio

- `dataCriacao` → gerada automaticamente
- `dataConclusao` → preenchida quando status = `CONCLUIDO`
- `ATRASADO` → definido automaticamente quando:
  - `dataLimite` < data atual
- Scheduler roda a cada 10 segundos para atualizar tarefas vencidas

---

##  Formato de data

Padrão utilizado:
```
yyyy-MM-dd'T'HH:mm:ss
```

Exemplo:
```
2026-04-28T14:30:00
```

---

##  Frontend

Interface simples com:
- formulário de criação/edição
- listagem de tarefas
- edição
- formatação de datas (`dd/MM/yyyy HH:mm:ss`)

---

##  Possíveis melhorias

- Autenticação (JWT)
- Paginação no frontend
- Filtros por status
- Interface moderna (React ou Vue)
- Deploy em nuvem

---

## 👨‍💻 Autor

Desenvolvido por **Brenno Siqueira**

---

## Desenvolvido para Estudo
