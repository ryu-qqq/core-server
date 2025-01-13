# CoreServer: 프로젝트 개요

CoreServer 는 TDD(Test-Driven Development)를 기반으로 코드를 작성하며, 다음과 같은 목표를 가지고 설계되었습니다:

- 생성형 AI 활용:
  -	OpenAI를 활용하여 코드 리뷰 자동화 및 테스트 코드 생성.
  
코드 품질 향상과 개발 생산성 증대를 실현.
- MVP 개발:

  - 상품 관리 모듈을 중심으로 빠르게 MVP(Minimum Viable Product)를 개발.
  - 핵심 기능을 간결하게 구현하여 빠른 검증과 반복적 개선이 가능하도록 설계.


프로젝트의 핵심 가치는 다음과 같습니다:

1. TDD 실천: 테스트 중심의 개발 과정을 통해 코드의 신뢰성과 유지보수성을 확보.
2. 생산성 증대: 생성형 AI와 자동화 도구를 적극 활용하여 개발 효율성을 극대화.
3. 빠른 피드백 루프: MVP 방식으로 빠르게 구현하고, 개선을 반복.

CoreServer는 이 모든 과정을 통해 실제 환경에서의 개발 워크플로우를 최적화하고, 학습 및 실험을 병행할 수 있는 환경을 제공합니다.


---
# 프로젝트 구조

```
root/
├── core/                            # 코어 모듈 폴더
│   ├── core-api/                    # 공통 API 구성 요소 및 인터페이스 모듈
│   ├── core-batch/                  # 공통 배치 구성 요소 및 인터페이스 모듈
│   └── core-enum/                   # 공통 Enum 정의 모듈
├── domain/                          # 도메인 모듈 폴더
│   └── core-domain/                 # 도메인 비즈니스 로직 모듈
├── storage/                         # 데이터 저장소 관련 모듈
│   ├── core-db/                     # RDS 접근 및 엔티티 관리 모듈
│   ├── core-cache/                  # 캐시 관리 모듈
│   └── core-index/                  # NOSQL 관리 모듈
├── support/                         # 지원 모듈 폴더
│   ├── logging/                     # 로깅 모듈
│   └── utils/                       # 공통 유틸리티 모듈
├── tests/                           # 테스트 모듈 폴더
│   └── api-docs/                    # API 문서화 및 테스트 모듈
│   └── bdd-tests/                   # 비즈니스 로직 자연어 처리 문서화
│   └── unit-tests/                   # 단위 테스트
├── external-client/                 # 외부 API 요청, 응답 로직 모듈
├── build.gradle                     # Gradle 빌드 설정 파일
├── gradle.properties                # Gradle 프로퍼티 파일 (의존성 버전 관리)
└── settings.gradle                  # Gradle 설정 파일
```

---
