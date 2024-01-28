## 기술 스택
- Gradle - Groovy
- Amazon Corretto 11 (Java 11)
  - Springboot 2.7.0
- Spring Web, Spring Data JPA, H2, Lombok
- Eclipse IDE 23.12 version

## Amazon Corretto 11 설치
[Amazon Corretto 11 설치](https://docs.aws.amazon.com/ko_kr/corretto/latest/corretto-11-ug/windows-info.html)
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/56c39731-2eac-4b21-800f-90245c869d37)

## SpringBoot Project 생성
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/2786657b-b957-4f9b-9290-822f0b46f0e9)
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/bea68807-8e52-48fc-8654-a9dffdc09b41)
```
spring initilizer에서 프로젝트를 다운로드 할 때 gradlew라는 프로그램(gradle wrapper)을 같이 받았으므로
따로 그래들을 설치하지 않고 프로젝트 내의 그래들 래퍼를 사용한다.
```
## Dependency 라이브러리 추가
[MVN Repository](https://mvnrepository.com/search?q=google+guava)
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/a10ca654-6206-4ee4-bc06-3b0005d8b207)
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/43c25af8-3605-4fc2-90e2-143f5f809798)
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/a221e969-3b3a-4563-bfc9-0a14a7fcad46)

```
구글 구아바 라이브러리를 메이븐 레포지토리 홈페이지에서 찾고 usage 가장 사용 수가 많은 31.1-jre 버전의
그래들 코드를 복사해서 build.gradle의 dependency 부분에 추가하였다.
```
## 이클립스 Lombok 설치
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/81459ce5-5719-4774-87a5-9a18fb10cc43)
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/14364902-5a3d-4649-8ae6-3f2340fa0149)
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/6f6201d8-e209-4301-8e86-13f0cf65dcd7)
```
메이븐 레포지토리에서 lombok 1.18.22의 Jar를 눌러 jar파일을 다운로드한다. (압축풀기x)
다운로드 한 jar파일의 위치로 가서 java -jar lombok-1.18.22.jar 명령어를 치고 롬복 이클립스를 설치한다.
```
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/ee3a9632-1e3f-4dd1-a664-057b2157f839)
```
이클립스를 재시작하면 dependency에 annotationProcessor 어노테이션 프로세서 라이브러리가 추가된다.
이 라이브러리는 그래들이 인식하는 라이브러리이고 이클립스가 인식하는 라이브러리가 아니기 때문에
어노프테이션 프로세서 설정을 한다.
```
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/7dccda0a-dacb-4000-a601-a4fe5c08af92)
```
Demo 우클릭 proference 진입 java compiler - annotation processing - enable project specific settings 체크
```
## TodoEntity
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/396f1112-5056-423e-ab8d-b490a2f5aa5f)
```
모델과 엔티티를 한 클래스에 구현하고자 한다. 즉 데이터를 담는 역할과 데이터베이스의 테이블과 스키마를 표현하는
역할을 한 클래스 내에 구현한다.
```
## TodoDTO  
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/374f4cbc-57f8-42a7-9d0c-745bb5321143)
```
TodoDTO에는 데이터베이스에서 사용자를 구별하기 위한 userId를 제외한 Todo dto 클래스를 작성한다.
```
## ResponseDTO<T>
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/6879f0a4-4680-4ab3-a1f3-7070a6d4589d)
```
Todo 데이터를 하나만 반환하는 경우보다 리스트로 반환하는 경우가 많으므로 데이터를 리스트로 반환하도록 짰다.
또 다른 모델의 DTO 역시 ResponseDTO를 이용해 리턴할 수 있도록 자바 Generic을 이용하였다.
```
## RESTful API 제약조건
```
REST는 Representational State Transfer의 약자로 아키텍처 스타일이다.

REST 제약조건

Client-Server : 리소스를 관리하는 서버가 존재하고, 다수의 클라이언트가 리소스를 소비하기 위해 서버에 접근하는 구조
Stateless : 상태가 없다. 즉 이전 요청의 영향을 받지 않아야 한다. 이전 요청에서 login한 사실을 서버가 알고 있지 않아도
된다는 의미이다.
Cacheable : 서버에서 리소스를 리턴할 때 캐시가 가능한지 아닌지 명시할 수 있어야 한다.
Uniform Interface : 일관된 인터페이스(리턴 타입의 일관성, 요청의 형태와 응답의 형태가 일관적)
Layered System : 서버에 요청을 날릴 때 여러 개의 레이어(인증 서버, 캐싱 서버, 로드 밸런서)를 거치는 데 이 사이의 레이어들은
요청과 응답에 어떤 영향을 미치지 않는다.
Code-On-Demand : 선택사항이며 클라이언트는 서버에 코드를 요청할 수 있고 서버가 리턴한 코드를 실행할 수 있다.
REST는 HTTP와 다르다. REST는 HTTP를 이용해 구현하기 쉽고 REST 아키텍처를 구현할 때 사용하면 쉬운 프로토콜이 HTTP이다.
```
## TestController
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/5e647847-7858-460a-b97d-df61e9d264c6)
![image](https://github.com/mr-won/Todo_Backend/assets/58906858/a210e51d-02c1-44d4-bc50-e75ccdadb6bd)
```
SpringBoot-Stater-Web 패키지에서 제공하는 연결 작업의 어노테이션(@RestController, @GetMapping, @PostMapping, @PatchMapping 등)을
사용할 수 있다.

HTTP GET 요청을 대신하여 @RestController 어노테이션을 이용한 TestController를 구현하였다.
localhost:8080/test 로 접근하면 Hello World 가 출력된다. 브라우저에서 URL을 입력해 접근하는 GET 요청을
@RestController를 사용하여 구현하였다.
```
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/699bb3b4-6ef1-434a-9d79-eb1e0ffe0952)
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/86e50025-461c-4b19-9ee1-dbb6adb63130)
```
@PathVariable을 이용해서 경로로 들어오는 숫자 또는 문자를 변수에 매핑할 수 있다.
/test 경로 뒤에 오는 숫자를 리턴하였다.
```
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/d9da9af5-f85b-41dc-b48a-149078cd69ed)
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/c1ff236d-f213-4d48-8520-edef69e2db44)
```
또 다른 방법인 @RequestParam을 이용하여 ?id={id}와 같이 요청 매개변수로 넘어오는 값을 변수로 받아올 수 있다.
```
## TestRequestBodyDTO
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/ca6c6141-784b-495d-9d80-04ae0c76b468)
```
마지막으로 @RequestBody를 사용하여 객체처럼 복잡한 자료형을 요청에 보낼 수 있다.
요청 바디로 보낼 TestRequestDTO 클래스를 작성한다.
```
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/f2ca8afb-c45e-4e8e-b60e-212313f86572)
```
TestRequestBodyDTO를 요청 바디로 받는 testControllerRequestBody() 메서드를 TestController에 추가한다.
```
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/19c596fe-961a-40d3-a497-3f01a6e6b50e)
```
Postman 프로그램에서 /test/testRequestBody 경로로 TestRequestDTO와 같은 형태의 JSON 형식의 문자열을 요청 바디로
넘겨준다.

오브젝트인 TestRequestDTO를 JSON 형식의 문자열의 요청 바디로 사용하여 요청한다. 이 과정을 Serialization이라고 한다.

@RequestBody : 오브젝트를 JSON과 같은 형태의 문자열 요청 바디로 변환한다.
```
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/142de766-3821-421c-967f-b269a04bcd70)
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/697ac456-a0d6-46fb-9c83-69aa76269dfd)
```
이번에는 @ResponseBody를 이용하여 TestRequestDTO를 리턴하는 컨트롤러를 구현한다.
DTO 오브젝트를 JSON으로 리턴하여 결과를 나타내준다.

오브젝트를 JSON으로 변환하여 요청바디로 이용하거나 저장하는 일련의 모든 과정을 Serialization이라고 한다.
```
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/5c1ec8ab-4b61-4796-9696-d933231c839c)
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/e7b79331-6dda-44c7-a2c3-7edb766fa4a5)
```
@ResponseEntity를 이용하면 HTTP 응답의 Body 뿐만 아니라 status나 header를 조작할 수 있다.
http status를 400, 즉 400 Bad Request를 추가하여 status를 조작한다.
body의 내용은 @ResponseBody와 동일하다.
```
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/fa2b80e8-6f5a-4628-9b71-f0682d8d724c)
![image](https://github.com/wonchihyeon/Todo_Backend/assets/58906858/bc82632e-5328-43e4-a8de-af39dee8fd58)
```
todoController에는 200 ok http status를 반환하는 메서드를 작성하였다.
```







