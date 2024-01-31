# 공연 예약/결제 시스템 

- [🚙 도메인 다이어그램](#-도메인-다이어그램)
- [🚌 도메인](#-도메인)
  - [☁ User](#-User)
  - [☁ Business](#-Business)
  - [☁ Venue](#-Venue)
  - [☁ Seat](#-Seat)
  - [☁ Performance](#-Performance)
  - [☁ PricePolicy](#-PricePolicy)
  - [☁ PerformanceSeat](#-PerformanceSeat)
  - [☁ Order](#-Order)
  - [☁ OrderItem](#-OrderItem)
  - [☁ Ticket](#-Ticket)
  - [☁ Delivery](#-Delivery)
- [🚗 패키지 구조](#-패키지-구조)
  - [☁ common: 공통 패키지](#-common--공통-패키지)
  - [☁ web: 웹 관련 패키지](#-web--웹-관련-패키지)
  - [☁ 도메인 별 패키지 규칙](#-도메인-별-패키지-규칙)
- [🚓 모듈 설명](#-모듈-설명)
  - [☁ Security](#-Security)
  - [☁ Querydsl](#-Querydsl)
  - [☁ Swagger](#-Swagger)
  - [☁ Test](#-Test)
- [🚕 라이브러리 설명](#-라이브러리-설명)

---
---

## 🚙 도메인 다이어그램
![diagram](./booking-diagram.png)

--- 

## 🚌 도메인
### ☁ User
### ☁ Business
### ☁ Venue
### ☁ Seat
### ☁ Performance
### ☁ PricePolicy
### ☁ PerformanceSeat
### ☁ Order
### ☁ OrderItem
### ☁ Ticket
### ☁ Delivery


---

## 🚗 패키지 구조

### ☁ common: 공통 패키지
- 💧 base: Domain Entity의 Base Class
- 💧 config: Configuration Class
- 💧 type: Enum
- 💧 util: Util Class

### ☁ web: 웹 관련 패키지
- 💧 api: API
- 💧 config: Web Configuration Class
- 💧 security: Spring Security

### ☁ 도메인 별 패키지 규칙
- 💧 domain: Entity
- 💧 exception: Exception
- 💧 repository: Repository
- 💧 service: Service
- 💧 type: Enum
- 💧 value: DTO, VO


---

## 🚓 모듈 설명
### ☁ Security

### ☁ Querydsl

### ☁ Swagger

### ☁ Test


---

## 🚕 라이브러리 설명


---
