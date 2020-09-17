# notepad-service
Service for storing notes.

### Requirements
* JDK 1.8 or later

### Getting started

#### Step 1: Clone this Repository
`git clone git@github.com:mateuszjanczak/notepad-service.git`

#### Step 2: Build and run with Maven Wrapper
`./mvnw spring-boot:run`

### Endpoints

#### All notes
```
GET http://localhost:8080/api/notes

Response
[
    {
      "id": 62132,
      "title": "Tytuł",
      "content": "Notatka 1"
    },
    {
      "id": 76234,
      "title": "Tytuł",
      "content": "Notatka 2"
    }
]
```

#### Single note
```
GET http://localhost:8080/api/notes/{id}

Response
{
  "id": 62132,
  "title": "Tytuł",
  "content": "Notatka 1"
}
```

#### Add a note
```
POST http://localhost:8080/api/notes
Content-Type: application/json

Request
{
  "title": "Tytuł",
  "content": "Notatka"
}

Response
{
  "id": 62132,
  "title": "Tytuł",
  "content": "Notatka"
}
```

#### Edit the note
```
PATCH http://localhost:8080/api/notes/{id}
Content-Type: application/json

Request
{
  "title": "Tytuł",
  "content": "Edytowana notatka"
}

Response
{
  "id": 62132,
  "title": "Tytuł",
  "content": "Edytowana notatka"
}
```

#### Remove the note
```
DELETE http://localhost:8080/api/notes/{id}
```
