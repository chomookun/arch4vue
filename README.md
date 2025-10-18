# arch4vue

## Powershell에서 npm 권한오류 해결
Powershell을 관리자 권한으로 실행 후 아래 명령어 실행
```
Set-ExecutionPolicy Unrestricted
```

## Local Development

### Starts spring boot server
```shell
./gradlew bootRun
```

### Runs Vue dev server
```shell
cd src/main/vue
npm run serve
```
connect to http://localhost:8000

