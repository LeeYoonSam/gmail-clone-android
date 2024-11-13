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
Presentation Layer
- Jectpack Compose 를 사용하여 UI 컴포넌트를 빌드

### Row, Column, Modifier
Row - 가로열 구성
Column - 세로열 구성
Modifier - 구성 가능한 동작이나 모양을 장식하거나 변경

### MVI(Model-View-Intent) Contract
핵심 개념은 `상태, 이벤트, 효과`로 이를 통해 예측 가능하고 일관된 방식으로 UI 를 관리 

**상태**는 사용자에게 표시되는 현재 UI 를 나타냅니다.
- 사용자는 여러 상태 중 하나만 볼 수 있습니다. 혼란 없이 앱의 명확하고 잘 정의된 상태를 볼 수 있습니다.

**효과**는 UI가 수행하는 일회성 작업입니다.
- 화면 간 탐색, 스낵바 표시와 같은 작업을 수행
- UI 상태를 직접 변경하지 않으며 일반적으로 장치 회전이나 화면 크기 조정과 같은 구성 변경 시 유지되지 않는 작업 

**이벤트**는 버튼과 같은 UI와의 사용자 상호 작용입니다.
- 클릭, 제스쳐는 뷰모델로 전송되어 처리되고 새 UI 상태 또는 트리거로 변환 됩니다.

상태 효과와 이벤트에 대한 인터페이스 계약을 만드는 것으로 시작
- MVI 구성 요소의 구조를 정의하고 구현 전체에서 일관성을 보장하는 데 도움이 될 것입니다.

[별도의 인터페이스 구성](core-ui/src/main/java/com/ys/coreui/mvi/MVIContract.kt)
- 다양한 사용자 상호 작용을 처리하기 위해 이벤트라는 함수를 정의할 수 있는 이벤트도 설정 합니다.
- 상태 업데이트와 탐색 또는 스낵바 표시와 같은 일회성 효과를 관리하기 위해 상태 흐름과 공유 흐름을 설정 합니다.


## [Part3](https://youtu.be/earJE0MBQ3g?si=TqUapIZCejSYuO1u)