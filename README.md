
# Proyecto de Gestión de Tickets PanalSuite

## Intregrantes

- [Jose Corredor](https://github.com/MementoMoriReload)
- [Matias Yanac](https://github.com/Matiasdyv)

## Endpoints

#### AuthController
#### Login

```http
  POST /auth/login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. Correo|
| `password` | `string` | **Required**. Contraseña|

#### UserController
#### Get all Users

```http
  GET /api/v1/user
```

#### Get User by ID

```http
  GET /api/v1/user/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `Long` | **Required**. Id de usuario |


#### Post User

```http
  POST /api/v1/user/
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Required**. Nombre|
| `email` | `string` | **Required**. Correo|
| `password` | `string` | **Required**. Contraseña|
| `department` | `string` |  Correo|
| `Role` | `UserRole(Enum)` |  Rol|

#### Update User Role

```http
  PATCH /api/v1/user/{id}/role
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Role` | `UserRole(Enum)` |  **Required**. Rol|

#### Delete User by ID

```http
  DELETE /api/v1/user/{id}/
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `Long` | **Required**. Id de usuario |

#### TicketController
#### Get all Tickets
```http
  GET /api/v1/ticket
```
#### Get Ticket by ID

```http
  GET /api/v1/ticket/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `ticketId` | `Long` | **Required**. Id de ticket |

#### Post Ticket

```http
  POST /api/v1/ticket/
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `title` | `string` | **Required**. Titulo|
| `description` | `string` | **Required**. Descripcion|
| `location` | `string` | **Required**. Ubicación|
| `category` | `TicketCategory(Enum)` | **Required**. Categoria|
| `priority` | `TicketPriority(Enum)` | Prioridad|
| `requesterId` | `Long` | **Required**. ID del solicitante del Ticket|
| `assignedID` | `Long` | ID del resolutor asignado al Ticket|


#### Post Ticket comment

```http
  POST /api/v1/ticket/{id}/comment
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `author` | `string` | **Required**. Autor|
| `Text` | `string` | **Required**. Contenido del comentario|

#### Update Ticket Status

```http
  PATCH /api/v1/user/{id}/role
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `status` | `TicketStatus(Enum)` |  **Required**. Estado|

#### Assign Ticket Resolver

```http
  PATCH /api/v1/user/{id}/assign
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `assignedId` | `Long` | ID del resolutor asignado al Ticket|

#### Delete Ticket by Id 

```http
  DELETE /api/v1/ticket/{id}/
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `ticketId` | `Long` | **Required**. Id de ticket |

## Documentación Swagger

```
PLACEHOLDER
```
## 

![Logo](https://thumb.wikimedia.org/wikipedia/commons/thumb/a/aa/Logo_DuocUC.svg/1280px-Logo_DuocUC.svg.png?utm_source=es.wikipedia.org&utm_campaign=index&utm_content=thumbnail)

