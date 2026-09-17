# Architecture

Browser
→ HTTP
→ Core Java HttpServer
→ ApiHandler
→ Service Layer
→ DAO/JDBC
→ MySQL

File path:
Browser upload → FileService → AES-256-GCM → `storage/*.enc`

Receive path:
Receiver code → TransferService → PBKDF2 verification → decrypt → `received/` → SHA-256 verification record.

The project deliberately uses Core Java instead of Spring Boot so the student can explain the HTTP server, layered architecture, JDBC, SQL, authentication, encryption and file I/O in a viva.
