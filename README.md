1. 작업  순서
   - 상품 등록, 수정 로직 수정 및 의존성 정리 (진행중..)
   - 상품 관련 테스트 코드 작성
   - 외부몰 상품 갱신 및 연동 로직 수정 및 의존성 정리
   - 외부몰 관련 테스트 코드 작성
   - 상품 관련 배치처리 작성




---
# 프로젝트 구조

```
root/
├── core/                            # 코어 모듈 폴더
│   ├── core-api/                    # 공통 API 구성 요소 및 인터페이스 모듈
│   ├── core-batch/                  # 공통 배치 구성 요소 및 인터페이스 모듈
│   └── core-enum/                   # 공통 Enum 정의 모듈
├── domain/                          # 도메인 모듈 폴더
│   └── core-defaultProduct/                 # 도메인 비즈니스 로직 모듈
│   └── core-external/                 # 도메인 비즈니스 로직 모듈
│   └── core-git/                 # 도메인 비즈니스 로직 모듈
│   └── core-brand/                 # 도메인 비즈니스 로직 모듈
│   └── core-category/                 # 도메인 비즈니스 로직 모듈
├── storage/                         # 데이터 저장소 관련 모듈
│   ├── core-db/                     # RDS 접근 및 엔티티 관리 모듈
│   ├── core-cache/                  # 캐시 관리 모듈
│   └── core-index/                  # NOSQL 관리 모듈
├── support/                         # 지원 모듈 폴더
│   ├── logging/                     # 로깅 모듈
│   ├── monitoring/                     # 로깅 모듈
│   └── utils/                       # 공통 유틸리티 모듈
├── tests/                           # 테스트 모듈 폴더
│   └── api-docs/                    # API 문서화 및 테스트 모듈
│   └── bdd-tests/                   # 비즈니스 로직 자연어 처리 문서화
│   └── unit-tests/                   # 단위 테스트
├── external-client/                 # 외부 API 요청, 응답 로직 모듈
│   └── buyma/                       # buyma
│   └── oco/                         # oco
│   └── sellic/                      # sellic
│   └── git-hub/                     # git hub

├── build.gradle                     # Gradle 빌드 설정 파일
├── gradle.properties                # Gradle 프로퍼티 파일 (의존성 버전 관리)
└── settings.gradle                  # Gradle 설정 파일
```

---
