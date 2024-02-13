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

#### 로그인 테스팅

```
회원가입과 로그인이 잘 작동하는 것을 확인할 수 있다. 하지만 이렇게 구현하였을 때 문제점은 세 가지가 있다.

1. 로그인만 되고 로그인 상태가 유지되지 않는다. REST API는 상태가 없으므로 로그인 상태를 기억하지 않기 때문
2. 유저의 로그인 여부 자체를 확인하지 않는다. 같은 Todo 리스트를 보게 된다.
3. 패스워드를 암호화하지 않아 보안 규정에 위배되는 사항이 발생한다.
```




