<br>

# EVERY-TING

<br>

<p align="center">
<img width="562" alt="메인이미지" src="https://github.com/YGwan/every-ting/assets/50222603/23218f55-3c2b-4901-932b-24ead92f723f">
</p>

<br>

- 생성 AI를 이용한 대학생 전용 과팅 & 소개팅 어플리케이션
- 대학교 이메일 인증을 통한 인증 처리, 생성 AI 모델인 styleGAN 알고리즘을 통한 프로필 사진 생성

<br>

## 시스템 구조도

<br>

<img width="559" alt="스크린샷 2023-12-10 오후 11 59 12" src="https://github.com/YGwan/every-ting/assets/50222603/dbf6232c-c9b7-46fc-9d97-4d2340148576">

<br>

<br>

## 사용 기술

<br>

### 백엔드
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-039BE5?style=for-the-badge&logo=FCM&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

<br>

- 캐시 처리 & 인증 처리 등을 위해 Redis, SMTP(Simple Mail Transfer Protocol)를 사용했습니다.
- DB는 MariaDB를 사용했고 JPA를 사용하여 개발을 진행했습니다.
- 앱의 푸시 알림 구현을 위해 Firebase에서 제공하는 FCM(Firebase Cloud Messaging) 서비스를 사용했습니다.
- 백엔드 서버는 SpringBoot을 통해 개발하였고 멀티 모듈 방식으로 개발하였습니다.
- 모듈간의 데이터 통신은 Foreign Client를 통해 통신하였습니다.
- 사진 이미지는 AWS의 S3 버킷을 통해 저장하였고 서버 배포 또한 AWS를 통해 배포를 진행했습니다.
- 토큰은 JWT Token을 사용하였고 기본적으로는 AccessToken을 프론트에서 해더에 보내 인증처리하는 식으로 구현하였고 자동 로그인 & 엑세스 토큰 만료시에는 RefreshToken을 사용해 처리하도록 개발하였습니다.
- 패스워드는 SHA256을 통해 단방향 암호화 처리를 진행하였고 그밖의 사용자 개인 데이터는 AES를 통한 양방향 암호화 처리로 진행했습니다.

<br>

### 머신러닝
![Flask](https://img.shields.io/badge/flask-%23000.svg?style=for-the-badge&logo=flask&logoColor=white)
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)

### 프론트
![Flutter](https://img.shields.io/badge/Flutter-%2302569B.svg?style=for-the-badge&logo=Flutter&logoColor=white)

<br>
