# Secure Handover — College-Level Core Java

Frontend: HTML/CSS/JavaScript
Backend: Core Java HttpServer
Database: MySQL + JDBC
Security: PBKDF2 + AES-256-GCM + SHA-256

Features:
- Separate Login/Register
- Dashboard
- Upload + encryption
- Sender transfer
- Receiver verification
- Transfer history
- Audit/verification database records
- REST-style JSON endpoints

Required:
- JDK 17+
- MySQL 8+
- MySQL Connector/J in `lib/`

Compile in VS Code PowerShell:
```powershell
mkdir out -Force
javac -cp "lib/*" -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
```

Run:
```powershell
java -cp "out;lib/*" app.Main
```

Open:
`http://localhost:8080`

Set a Base64 32-byte `SECURE_HANDOVER_KEY` environment variable before running.
Change the MySQL password in `src/config/Database.java`.
