# Bankpin User Server

### 개발툴
- IDE: [IntelliJ IDEA](https://www.jetbrains.com/ko-kr/idea/download/#section=windows) 참조 (유료는 별도 구매요청)
- DB: [MySQL Workbench](https://dev.mysql.com/downloads/workbench/) 참조 (편한 툴 사용, 유료는 별도 구매요청)
- 터미널: [ConEmu](https://conemu.github.io/) 참조 (편한 툴 사용, 유료는 별도 구매요청)


### 설정 참조
- Context path: `/user`
- Port(Local): `8507` (기본설정 됨, 변경가능)
- DataBase
  * MariaDB
  * IP: `211.184.225.81`
  * Port: `3306`
  * DB: `abc`
  * User: `abc`
- [ERD](http://211.184.225.81:3000/Dev-abc/abc-online/src/branch/main/%EB%B1%85%ED%81%AC%ED%95%80-%EA%B0%9C%EB%B0%9C%EB%AC%B8%EC%84%9C/ERD) 참조
- [ERMaster](https://sourceforge.net/projects/ermaster/files/)


### Package 네이밍 참조
- 로그인, 인증
  * auth

- 고객
  * cust

- 신용대출
  * inq
    
- 담보대출
  * mrtg

- 공통
  * common

- 은행
  * bank

- 관리자
  * admin

- 외부
  * ext
  * ext.coocon (쿠콘)


### 쿠콘 API 개발 참조
- URI
  * Send: `/user/api/v1/coocon/...`
  * Receive: `/user/coocon/callback/...`

- Controller
  * 쿠콘전달: `com.bankpin.user.ext.coocon.send.controller`
  * 쿠콘콜백`com.bankpin.user.ext.coocon.receive.controller`

- `쿠콘API플랫폼_핀테크업체_전체_개인대출_HTTP(json)전문방식_개발가이드_3.7.pdf` [뱅크핀-개발문서](http://211.184.225.81:3000/Dev-abc/abc-online/src/branch/main/%EB%B1%85%ED%81%AC%ED%95%80-%EA%B0%9C%EB%B0%9C%EB%AC%B8%EC%84%9C/%EC%BF%A0%EC%BD%98) 참조
