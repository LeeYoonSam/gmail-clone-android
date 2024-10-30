# Gmail clone app. MVI pattern with Jetpack Compose

**What You'll Learn:**

• Setting up the project and dependencies
• Understanding the MVI (Model-View-Intent) architecture
• Implementing the core modules for the Gmail clone
• Utilizing Jetpack Compose for the UI

**Resources:**

• GitHub Repository: https://bit.ly/3znarIK
• Access the full source code for this project and follow along!
• Figma Design File: [Figma](https://www.figma.com/design/kFGeKiRu1IrhkNXJSDYCdh/Gmail-UI-Mobile-Design-Template-2024!-(Community))
• [MockApi](https://mockapi.io/projects)

**Tools and Technologies Used:**

• Jetpack Compose
• Kotlin
• MVI Architecture
• Hilt for Dependency Injection
• Retrofit for Networking
• Glide for Image Loading

## [Part1](https://youtu.be/YY71b7-yTeg?si=WcmjlDkjCo3HbVUc)
Data, Domain 모듈을 먼저 구현하는 이유
- Foundation First (기초 우선) 
- Stability (안전성)
- Separation of concern (관심사 분리) 
- Flexibility (유연성)
- Testing (테스트)

### 1. 모듈 생성
- core, data, domain, presentation

### 2. data 모듈 패키지 생성
- di,dto, mapper, remote, repository

### 3. domain 모듈 패키지 생성
- model, repository, usecase

### 4. presenctaion 모듈 패키지 생성
- emaillist, emaildetails

### 5. Dependency 관리 - Version Catalog
- libs.versions.toml 에서 전체 버전 관리
- [versions], [libraries], [plugins] 세 가지로 나눠짐
- 디펜던시는 Group, Artifact, VersionRef 로 구성되어 있음 

## [Part2](https://youtu.be/Q8qwGdx1q7k?si=4qmZtzFExXjWtBxQ)
## [Part3](https://youtu.be/earJE0MBQ3g?si=TqUapIZCejSYuO1u)