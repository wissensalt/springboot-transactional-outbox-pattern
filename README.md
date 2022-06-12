#springboot-transactional-outbox-patter

Implementation of https://microservices.io/patterns/data/transactional-outbox.html using springboot

###start
```bash
sh start.sh
```

###stop
```bash
sh stop.sh
```

###Batch Insert Data
```bash
curl http://localhost:8080/generate?total_accounts=100&total_login_history_per_accounts=15
### true
```

###Simulate Rollback Transaction
```bash
curl -X DELETE http://localhost:8080/delete-rollback
### {"timestamp":"2022-06-12T10:59:10.074+00:00","status":500,"error":"Internal Server Error","path":"/delete-rollback"}
```

###Simulate Success Transaction
```bash
curl -X DELETE http://localhost:8080/delete
### {"timestamp":"2022-06-12T10:59:10.074+00:00","status":500,"error":"Internal Server Error","path":"/delete-rollback"}
```