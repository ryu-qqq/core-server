1. 레코드 패턴을 활용한 간결하고 안전한 복사 방식 컨벤션
   컨벤션: Immutable Record 복사 및 상태 변경 규칙
   모든 도메인 객체는 record로 정의한다.
   레코드는 불변 객체로 설계되며, 상태 변경 시 새로운 인스턴스를 생성한다.
   상태 변경 메서드 제공:
   	with<PropertyName> 메서드 컨벤션을 따라 필요한 속성을 변경한 새 객체를 반환한다.
   변경되지 않는 속성은 기존 값을 유지한다.
   빌더 패턴 사용 가능:
   	복잡한 객체 초기화가 필요한 경우 빌더 패턴을 제공한다.


---
예시: Immutable Record와 안전한 복사
java
복사
편집
import java.math.BigDecimal;
import java.util.List;

public record Product(
Long id,
Long productGroupId,
boolean soldOut,
boolean displayed,
int quantity,
BigDecimal additionalPrice,
List<OptionContext> options,
boolean deleted
) {
// 상태 변경 메서드
public Product withSoldOut(boolean soldOut) {
return new Product(id, productGroupId, soldOut, displayed, quantity, additionalPrice, options, deleted);
}

    public Product withQuantity(int quantity) {
        return new Product(id, productGroupId, soldOut, displayed, quantity, additionalPrice, options, deleted);
    }
    
    public Product withAdditionalPrice(BigDecimal additionalPrice) {
        return new Product(id, productGroupId, soldOut, displayed, quantity, additionalPrice, options, deleted);
    }
}


---

2. 생성자를 임의로 만들지 못하게 제한하기
   레코드의 생성자를 제어하려면 팩토리 메서드와 private 생성자를 활용하여 객체 생성 경로를 제한할 수 있습니다.

예시: 생성자 제한과 팩토리 메서드
java
복사
편집
import java.math.BigDecimal;
import java.util.List;

public record Product(
Long id,
Long productGroupId,
boolean soldOut,
boolean displayed,
int quantity,
BigDecimal additionalPrice,
List<OptionContext> options,
boolean deleted
) {
// private 생성자
private Product {
if (id != null && id <= 0) {
throw new IllegalArgumentException("ID는 양수여야 합니다.");
}
if (quantity < 0) {
throw new IllegalArgumentException("수량은 음수일 수 없습니다.");
}
}

    // 팩토리 메서드
    public static Product createNewProduct(
        Long productGroupId,
        int quantity,
        BigDecimal additionalPrice
    ) {
        return new Product(null, productGroupId, false, true, quantity, additionalPrice, List.of(), false);
    }
}

이점: 개발자는 팩토리 메서드만 사용해 객체를 생성하도록 유도되며, 불변성과 상태 검증이 보장됩니다.


---

3. 등록 및 업데이트를 위한 Command 패턴 또는 상태 플래그
   두 접근법 모두 실용적이지만, 프로젝트의 요구사항에 따라 선택해야 합니다.

Command 패턴
장점: 등록과 업데이트를 완전히 분리하여 의도를 명확히 전달.
단점: 코드가 다소 길어질 수 있음.
java
복사
편집
// 등록을 위한 Command 객체
public record CreateProductCommand(
Long productGroupId,
int quantity,
BigDecimal additionalPrice
) {}

// 업데이트를 위한 Command 객체
public record UpdateProductCommand(
Long id,
boolean soldOut,
int quantity,
BigDecimal additionalPrice
) {}

// 사용 예시
Product product = Product.createNewProduct(command.productGroupId(), command.quantity(), command.additionalPrice());
product = product.withQuantity(updateCommand.quantity());


---


최종 컨벤션 요약
레코드는 불변성을 유지하며 상태 변경은 with<PropertyName> 방식으로 처리한다.
팩토리 메서드로 객체 생성을 강제하고, 생성자는 private으로 제한한다.
등록과 업데이트 구분:
로직이 복잡하다면 Command 패턴을 사용.
상태 관리가 중요하다면 상태 플래그를 도입.
객체의 상태 검증은 팩토리 메서드와 생성자에서 처리한다.
위와 같은 컨벤션을 통해 코드의 일관성을 유지하고 유지보수성을 높일 수 있습니다.
