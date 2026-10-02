# DevOpsFlow

Automated CI/CD and container deployment dashboard.

## Run without Docker
```bash
mvn clean test
mvn spring-boot:run
```
Open http://localhost:8080.

## Flow
GitHub -> GitHub Actions -> Maven -> Tests -> Docker Build -> Deployment Status -> Health Check.

`Jenkinsfile` is included for Jenkins-based lab demonstrations.

## API
GET `/api/status`, `/api/health`, `/api/pipeline`, `/api/deployments`  
POST `/api/deploy`, `/api/rollback`