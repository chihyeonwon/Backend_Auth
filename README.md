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












