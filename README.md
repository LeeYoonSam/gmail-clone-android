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

### TESTING PYRAMID
조직과 개발자가 자동화된 테스트의 올바른 균형을 찾도록 안내하여 고품질 소프트웨어를 식별하는데 도움이되는 모델 입니다.

- End to End: **10%**
  - 전체 애플리케이션 기능이 사용자 관점에서 예상되는지 확인하는 실제 사용자 상호 작용을 시뮬레이션
  - 실행 속도 느림
- Integration Test: **20%**
  - 모듈 간의 상호 작용을 확인하여 더 넓은 범위를 제공
  - 실행 속도 중간
- Unit Test: **70%**
  - 실행이 빠르고 즉각적인 피드백을 제공
  - 실행 속도 빠름

유지 관리 가능한 코드 기반을 달성할 수 있다고 제안하는 피라미드처럼 테스트의 균형을 유지하여 개발을 보다 효율적이고 비용 효율적이며 강력하게 만드는 구조화된 테스트 접근 방식을 제공

### What is Testing
효과적인 테스트를 통해 앱 안전성과 신뢰성을 보장하는 방법을 알아 봅니다.

- Ensure correctness of functionality(기능의 정확성 보장)
- Improving code quality(코드 품질 향상)
- Reducing development cost(개발 비용 절감)
- Quality and reliability(품질 및 신뢰성)
- Risk mitigation(위험 완화)

**Details:**
- 프로그램이 예상대로 정확하게 작동하는지 확인
- 요구 사항을 충족하는지 확인
- 다양한 상황에서 올바르게 작동하여 더욱 깨끗하고 유지 관리하기 쉬운 코드로 이어집니다.
- 버그를 잡아 초기 테스트를 통해 시간과 비용을 절약
- 프로덕션에서 비용이 많이 드는 수정을 방지하며 개발자와 이해 관계자에게 앱이 안정적이고 사용할 준비가 되었다는 확신을 줍니다.

### Why do we write test case?
- 개발 프로세스를 더욱 원활하게 만들고 보다 안정적인 애플리케이션을 만드는 투자
- 테스트를 통해 코드 기반이 커짐에 따라 버그의 위험이 기하급수적으로 증가
- 잠재적인 문제는 관리 가능한 상태로 유지되어 더 나은 품질 관리와 원활한 프로젝트 확장을 보장하여 테스트에 시간을 투자함으로써 소프트웨어가 발전함에 따라 안정성을 유지하는데 도움이 됩니다.

### What you will Learn
- JUnit5
  - 3개의 모듈로 구성
    - Jupiter - Provides annotations
    - Vintage - Allows backward compatibility
    - Platform - Acts as a foundation
  - JUnit4 의 차이점
    - 클래스 수준 수명 주기 의미가 있는 테스트 전후와 같은 핵심 주석이 있는 단일 모놀리식 프레임워크
    - 설정 및 톤 메서드는 클래스당 한번 실행, 이는 AssertEquals 및 AssertTrue와 같은 기본 어설션을 사용 하지만 매개변수화된 테스트에 대한 지원이 제한적이며 기본 병렬 테스트 실행이 부족
  - JUnit5 를 사용하면 향상된 기능을 사용 할 수 있고 더욱 효율적이고 효과적으로 만들 수 있다.
- MockK
- Kotest
- Compose UI testing
- Compose screenshot
- Code Coverage

### Test Double
- 모든 종류의 대체 용어를 가리키는 일반적인 용어
- 실제 물건 대신 사용 테스트하는 동안 코드의 다양한 부분을 시뮬레이션하여 테스트하려는 동작을 격리하는 데 도움이 되며 테스트가 외부 구성 요소에 의존하지 않고 특정 논리에 초점을 맞추도록 보장
- Mark, Stub, Mock을 포함한 여러 유형의 테스트 더블이 존재

### MockK
- Modern mocking library specifically designed for Kotlin
- Create mocks, stubs, and spies
- Support for Coroutine
- MockK supports the use of annotations like @MockK, @RelaxedMockK, and @InjectMockKs
- MockK allows you to capture arguments
- MockK allows you to verify the number of calls made to a mock or that specific interactions occurred.

### Kotest
- Kotest is a flexible and powerful Kotlin testing framework
- multiple testing styles
- Offers a rich set of built-in assertions(shouldBe, shouldThrow, etc.)
- Allows you to perform property-based testing, where tests are automatically run with a variety of generated inputs to explore edge cases.
- Supports tags for organizing and running specific subsets of test.
- Improve readability and expressiveness

### Android - Screenshot Test
기본 아이디어
- UI의 기본 이미지를 캡처한 다음 변경 사항이 있을 때마다 새 이미지와 비교하여 시각적으로 다른점이 없는지 확인합니다.
- 스크린 테스트는 느릴 수 있기 때문에 일반적으로 밤새 실행되도록 예약된 별도의 파이프라인에서 실행 됩니다.

**사용하기**

gradle.properties
```properties
android.experimental.enableScreenshotTest=true
```

build.gradle
```kotlin
plugins {
    ...
    alias(libs.plugins.screenshot)
}

android {
    ...
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {
    ...
    screenshotTestImplementation(libs.androidx.compose.ui.tooling)
}
```
