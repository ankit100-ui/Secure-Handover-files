# College Demo

1. Start MySQL and run database/schema.sql.
2. Add MySQL Connector/J JAR to lib/.
3. Configure Database.java.
4. Set SECURE_HANDOVER_KEY.
5. Compile and run Main.
6. Register User A and User B.
7. Login as A.
8. Upload a PDF.
9. Use its document ID to create a transfer to B.
10. Give the generated handover code to B.
11. Login as B.
12. Enter transfer ID + handover code.
13. Show the decrypted file in received/.
14. Show History with RECEIVED status.

Viva line:
"I used a layered Core Java backend where controllers handle HTTP, services implement business logic, DAOs perform JDBC operations, and MySQL stores metadata. Uploaded files are encrypted with AES-256-GCM before storage."
