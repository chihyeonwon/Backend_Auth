## User 레이어 구현
#### UserEntity
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/c5a32add-d13e-490c-89ea-dd73b074e337)
```
유저는 id, username, password, role, authProvider로 구성된다.

password는 null이 허용된다는 점이 특징인데 이후에 OAuth를 이용해 SSO를 구현하기 때문에 null이 가능하도록 했다.
```
#### UserRepository
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/e1062798-6cd6-42af-89a1-656235f6f239)
```
TodoRepository와 마찬가지로 UserRepository로 필요한 쿼리메서드들을 작성했다.
```
#### UserService
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/a1fa7a9f-077d-456b-93ac-a5086ccfc5d4)
```
유저 데이터베이스에 저장된 유저를 가져올 때 사용하는 UserService를 작성한다.
UserRepository를 이용해서 사용자를 생성하는 create 메서드와 로그인 시 인증에 사용할 getByCredentials 메서드를
작성했다.
```
#### UserDTO
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/af116e11-b27c-4201-bed5-1606554a10b0)
```
UserDTO에 들어갈 내용은 token, username, password, id로 구성된다.
```
#### UserController
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/4a2d5185-7703-4a89-a37f-5c1173a93435)
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/bfd9fef5-470b-4259-bb37-f7032535f8ae)
```
UserController는 두 가지 기능을 제공한다. 회원가입을 위한 /sinup API 엔드포인트이고 다른 하나는
로그인을 위한 /signin API 엔드포인트이다.
```
#### 회원가입 API를 이용한 계정 생성
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/8834982c-4476-441e-9cd6-8212d3de4721)
#### 로그인 테스팅
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/c1ae971f-5374-4473-843c-c4daf6e52975)
```
회원가입과 로그인이 잘 작동하는 것을 확인할 수 있다. 하지만 이렇게 구현하였을 때 문제점은 세 가지가 있다.

1. 로그인만 되고 로그인 상태가 유지되지 않는다. REST API는 상태가 없으므로 로그인 상태를 기억하지 않기 때문
2. 유저의 로그인 여부 자체를 확인하지 않는다. 같은 Todo 리스트를 보게 된다.
3. 패스워드를 암호화하지 않아 보안 규정에 위배되는 사항이 발생한다.
```
## Spring Security 통합

#### jjwt 라이브러리 디펜던시에 추가
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/662b459c-1edc-486d-9f1a-14df401501d6)
```
build.gradle의 dependencies 부분에 jjwt 라이브러리를 추가한다.
```
#### TokenProvider
```java
public class TokenProvider {
  private static final String SECRET_KEY = "FlRpX30pMqDbiAkmlfArbrmVkDD4RqISskGZmBFax5oGVxzXXWUzTR5JyskiHMIV9M1Oicegkpi46AdvrcX1E6CmTUBc6IFbTPiD";

  public String create(UserEntity userEntity) {
    // 기한 지금으로부터 1일로 설정
    Date expiryDate = Date.from(
        Instant.now()
            .plus(1, ChronoUnit.DAYS));

  /*
  { // header
    "alg":"HS512"
  }.
  { // payload
    "sub":"40288093784915d201784916a40c0001",
    "iss": "demo app",
    "iat":1595733657,
    "exp":1596597657
  }.
  // SECRET_KEY를 이용해 서명한 부분
  Nn4d1MOVLZg79sfFACTIpCPKqWmpZMZQsbNrXdJJNWkRv50_l7bPLQPwhMobT4vBOG6Q3JYjhDrKFlBSaUxZOg
   */
    // JWT Token 생성
    return Jwts.builder()
        // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        // payload에 들어갈 내용
        .setSubject(userEntity.getId()) // sub
        .setIssuer("demo app") // iss
        .setIssuedAt(new Date()) // iat
        .setExpiration(expiryDate) // exp
        .compact();
  }


  public String validateAndGetUserId(String token) {
    // parseClaimsJws메서드가 Base 64로 디코딩 및 파싱.
    // 즉, 헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용 해 서명 후, token의 서명 과 비교.
    // 위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 날림
    // 그 중 우리는 userId가 필요하므로 getBody를 부른다.
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }


}
```
```
security 패키지 밑에 TokenProvider 클래스를 작성한다. 이 클래스는 유저 정보를 받아서 JWT를 생성한다.
```
#### UserController의 /signin에서 토큰 생성 및 UserDTO로 토큰 반환
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/27dcf523-4bc3-4b7b-b5db-131e36d6d85d)
```
signin 로그인 부분에 TokenProvider를 이용해 토큰을 생성한 후 UserDTO token에 반환한다.
```
#### 계정 생성 테스팅
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/0a924790-b62a-486a-a13c-6c98004d55f7)
#### 로그인 요청 테스팅
![image](https://github.com/chihyeonwon/Backend_Auth/assets/58906858/5d954c41-fe44-44ea-9669-c5880aa156aa)
```
signin에 HTTP POST 메서드 요청을 보낸 후 token 필드가 반한되는 것을 알 수 있다.
```











