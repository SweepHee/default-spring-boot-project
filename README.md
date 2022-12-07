# Springboot project default

### 개발환경(테스트 서버)
- Java: Java 11.0.16
- MariaDB: 10.1.48
- Tomcat: 9.0.16
- Nginx: 1.14.0
- Ubuntu: 18.04.6 LTS


### 개발툴
- IDE: [IntelliJ IDEA](https://www.jetbrains.com/ko-kr/idea/download/#section=windows) 참조 (유료는 별도 구매요청)

### 설정 참조
- Context path: `/user`
- Port(Local): `8507` (기본설정 됨, 변경가능)
- Deploy: WAR
- DataBase
  * MariaDB

### Controller 네이밍
- View 를 사용하는 경우: XxxController
- API 를 사용하는 경우: ApiXxxController


### Service Method 네이밍
- Method
  * list, xxxList
  * detail, xxxDetail
  * add, xxxAdd
  * update, xxxUpdate
  * remove, xxxRemove


- URL Mapping
  * list, xxx-list
  * detail, xxx-detail
  * add, xxx-add
  * update, xxx-update
  * remove, xxx-remove

