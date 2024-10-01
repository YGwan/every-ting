<br>

# EVERY-TING

<br>

<p align="center">
<img width="562" alt="메인이미지" src="https://github.com/YGwan/every-ting/assets/50222603/23218f55-3c2b-4901-932b-24ead92f723f">
</p>

<br>

- 생성 AI를 이용한 대학생 전용 과팅 & 소개팅 어플리케이션
- Backend, Frontend, ML, Designer, PM으로 이루어진 팀 프로젝트 진행
- 대학교 이메일 인증을 통한 인증 처리, 생성 AI 모델인 styleGAN 알고리즘을 통한 프로필 사진 생성

<br>

## 사용 기술

<br>

- Back-end : Java 11, SpringBoot 2.7.3, JUnit5, JPA, AWS-S3
- Front-end : Flutter
- ML : Flask, nVIDIA-StyleGAN
- DB : Redis, MariaDB, Cache(Redis)
- Security : JWT, Encryption(SHA256 & SALT, AES)
- Monitoring : Actuator, Grafana, Prometheus
- Mail Service & notification : SMTP, Firebase FCM
- etc : ngrinder, Redisson, Notion, Slack

<br>

## 사용 기술 정리

<br>

- ### 캐시
  - Redis를 사용해서 전역 캐시 기법으로 캐시를 사용했습니다.
  - 읽기 전략은 **Look aside** 방식, 쓰기 전략은 **Write around** 방식을 사용했습니다.
  - 캐시 정책은 maxmemory(200MB), maxmemory-policy(LRU), backup down(RDB X, AOF X)
  - [조회 성능 향상을 위한 캐시 처리 with Redis](https://swmobenz.tistory.com/36)
  - Ngrinder을 통해 확인해 본 결과 성능 향상은 캐시 전과 후가 아래와 같이 향상된 것을 확인할 수 있습니다. (TPS 수치가 높고 Mean Test Time이 낮을수록 성능이 좋다.)
 
  <br>
  
    <img width="1387" alt="nGrinder test" src="https://github.com/YGwan/every-ting/assets/50222603/e6e238c7-752a-4326-b7ab-4b49064919c6">
 
<br>

- ### 메일 인증
  - Spring Mail & Google SMTP를 통해 메일 서비스를 구현했습니다.
  - 인증코드는 SecureRandom 클래스를 통해 구현하였고 인증 코드를 Redis에 ttl 설정을 두어 저장 & 인증시 Redis 값과 비교하는 식으로 인증 로직을 구현했습니다.
  - 메일 전송 로직을 비동기로 처리해 사용자 응답 시간을 단축하였고 비동기 처리시 생길 수 있는 장애를 대비 & 처리하기 위해 비동기 retry정책을 사용해 이를 구현했습니다.
  - [Spring Mail 인증 비동기 처리 & 비동기 retry 정책 적용](https://swmobenz.tistory.com/31)
 
<br>

- ### 동시성 처리
  - 과팅 시, 매칭 최대 횟수와 과팅 요청(보낸 것, 받은 것) 최대 횟수가 각각 존재했습니다.
  - 로직 처리 시 팀 별 매칭 및 요청 진행 횟수와 각각의 최대 횟수를 비교한 후 작다면 팀 별 진행 횟수를 +1 만큼 증가시키는 로직으로 처리해 동시성 문제가 발생했습니다.
  - 이러한 문제를 해결하기 위해 Redisson을 사용하여 팀 별 매칭 수 조회 시점에 fromTeamId와 toTeamId 값에 다중 분산락을 걸어 동시성 문제 해결했습니다.

<br>

- ### 푸시 알림
  - 앱의 푸시 알림 구현을 위해 Firebase에서 제공하는 FCM(Firebase Cloud Messaging) 서비스를 사용했습니다.
 
<br>

- ### 외부 API 통신
  - 외부 API 통신은 FeignClient을 사용해서 통신하였습니다.
  - 통신 시에 장애를 대비하기 위해 Connection-timeout과 Read-timeout(response-timeout)값을 설정해 이를 대비했습니다.
  - [외부 API 사용 :: 마지막까지 깔끔하게](https://swmobenz.tistory.com/33)

<br>

- ### 사진 처리
  - 사진 이미지는 AWS의 S3 버킷에 저장했습니다.
  - 그 후, 해당 S3 버킷 경로를 서버의 profile_photo로 저장하여 사진 파일을 관리했습니다.
 
<br>

- ### JWT 토큰
  - 토큰은 JWT Token을 사용하였고 기본적으로는 AccessToken을 프론트에서 해더에 보내 인증처리하는 식으로 구현하였고 자동 로그인 & 엑세스 토큰 만료시에는 RefreshToken을 사용해 처리하도록 개발하였습니다.
  - RefreshToken은 1회성을 보장하여 가장 최근에 생성된 RefreshToken값만 사용할 수 있도록 구현하여 보안을 강화했습니다.
 
<br>

- ### 암호화
  - 패스워드는 SHA256을 통해 단방향 암호화 처리를 진행하였고 salt을 매번 임의의 난수로 생성하여 레인보우 테이블 문제를 해결하였습니다. 로그인시 이에 대한 정보를 조합하여 인증 처리를 진행합니다.
  - 사용자 개인 데이터는 AES를 통한 양방향 암호화 처리로 진행했습니다. converter를 통해 DB에 저장될때는 암호화를 DB에서 조회될때는 복호화를 처리하여 데이터를 관리했습니다.
  - [DB 안 데이터는 개발자인 나조차도 모르게 저장해야한다.](https://swmobenz.tistory.com/30)
 
<br>

- ### 모니터링
  - 장애를 대비하기 위한 모니터링 시스템을 구축했습니다.
  - Spring Actuator, Prometheus, Grafana를 사용하여 모니터링 시스템을 구축했습니다.
 
    <img width="1435" alt="스크린샷 2024-04-08 오후 5 36 46" src="https://github.com/YGwan/every-ting/assets/50222603/7a004414-8955-4df6-a906-8f21e97ac8d2">


<br>

## 시스템 구조도

<br>

<img width="678" alt="structure" src="https://github.com/user-attachments/assets/3eed1b55-e699-45a7-9b4e-9b67ccdb84b4">

<br>

<br>

## DB - ERD

<br>

<img width="1104" alt="erd" src="https://github.com/YGwan/every-ting/assets/50222603/ddc7767e-d27a-4773-9fcb-d17cb99652e5">

<br>


