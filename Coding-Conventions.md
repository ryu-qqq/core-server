# Coding Conventions

## 자동화 가능한 규칙

### 1. **Spotless**
- **목적**: 코드 포맷 자동화.
- **적용 방법**:
	- `./gradlew spotlessCheck`로 규칙 위반 여부를 검사합니다.
	- `./gradlew spotlessApply`로 포맷 위반 사항을 자동 수정합니다.
- **설정 파일**: `rule-config/importorder`
- **규칙**:
	- Import 순서는 다음과 같습니다:
	  ```
	  com.ryuqq
	  java.
	  javax.
	  org.
	  net.
	  기타 com.*
	  ```

### 2. **Checkstyle**
- **목적**: 코드 스타일 검사.
- **적용 방법**:
	- `./gradlew checkstyleMain`: 메인 코드 검사.
	- `./gradlew checkstyleTest`: 테스트 코드 검사.
- **설정 파일**: `rule-config/checkstyle-rules.xml`
- **주요 규칙**:
	- 한 줄 최대 길이: 120자.
	- `*` import 금지.
	- CamelCase 네이밍 규칙 준수:
		- 클래스: UpperCamelCase
		- 메서드, 변수: lowerCamelCase

---

## 자동화되지 않는 규칙

자동화 도구로 적용하기 어려운 규칙들은 아래와 같이 문서화하여 팀원들이 이를 참고하여 코딩합니다.

### 1. 메서드 명 규칙
- 메서드 이름은 **동사**로 시작하며, 해당 동작을 명확히 나타내야 합니다.
	- 예시: `getUserById`, `updateUserDetails`

### 2. 클래스 명 규칙
- 클래스 이름은 **명사**로 작성하며, 해당 클래스의 역할을 명확히 나타내야 합니다.
	- 예시: `UserController`, `OrderService`

### 3. 주석 작성
- 모든 Public 메서드에는 Javadoc 스타일의 주석을 작성합니다.
	- 주석 예시:
	  ```java
	  /**
	   * 사용자의 ID로 사용자 정보를 가져옵니다.
	   *
	   * @param userId 사용자 ID
	   * @return 사용자 정보
	   */
	  public User getUserById(Long userId) {
		  // 구현
	  }
	  ```

---

## CI/CD에서의 자동화
- Spotless와 Checkstyle은 CI/CD 파이프라인에서 자동으로 실행되며, PR 또는 MR 시점에 규칙 위반 사항을 확인합니다.
- 예시 (GitLab CI/CD):
  ```yaml
  stages:
    - format-check
    - style-check

  spotless-check:
    stage: format-check
    script:
      - ./gradlew spotlessCheck
    only:
      - merge_requests

  checkstyle-check:
    stage: style-check
    script:
      - ./gradlew checkstyleMain checkstyleTest
    only:
      - merge_requests
