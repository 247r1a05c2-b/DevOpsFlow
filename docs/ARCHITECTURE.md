# Architecture

GitHub -> GitHub Actions / Jenkins -> Maven -> Automated Tests -> Docker -> Deployment -> Health Check -> Dashboard.

GitHub Actions runs on a GitHub-hosted runner, so the developer laptop does not need Docker installed for CI.

Jenkinsfile is included for the DevOps lab workflow.