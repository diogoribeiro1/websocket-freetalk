## Free Talk App

Stacks:

- Java 17
- Spring
- WebSocket
- Kafka
- Redis
- H2 Database

How to run:
```bash
./gradlew clean build -x test

docker-compose up --build
```

|Serviço |	URL |
| -- | --- |
Freetalk app | 	http://localhost:8080
Kafka UI |	http://localhost:8085
Redis	| Porta 6379 (para debug se quiser)
H2 Console | 	http://localhost:8080/h2-console