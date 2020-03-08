# notepad-service
----
Serwis do przechowywania notatek.

### Wymagane oprogramowanie
* JDK 1.8 or later
* Gradle 4+ or Maven 3.2+


### Uruchamianie
Gradle: `./gradlew bootRun`

Maven: `./mvnw spring-boot:run`

### Endpointy
```
# Pobieranie notatek

GET http://localhost:8080/api/notes

Zwracany
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

# Pobieranie pojedynczej notatki

GET http://localhost:8080/api/notes/{id}

Zwracany
{
  "id": 62132,
  "title": "Tytuł",
  "content": "Notatka 1"
}

# Dodawanie notatki

POST http://localhost:8080/api/notes
Content-Type: application/json
{
  "title": "Tytuł",
  "content": "Notatka"
}

Zwracany
{
  "id": 62132,
  "title": "Tytuł",
  "content": "Notatka"
}

# Edytowanie notatki

PATCH http://localhost:8080/api/notes/{id}
Content-Type: application/json
{
  "title": "Tytuł",
  "content": "Edytowana notatka"
}

Zwracany
{
  "id": 62132,
  "title": "Tytuł",
  "content": "Edytowana notatka"
}

# Usuwanie notatki

DELETE http://localhost:8080/api/notes/{id}
Content-Type: raw
{
    id
}

```