# wooteco-utils

## 유틸 라이브러리 정리

### WoowaScanner.class

- `nextInt()`
  - 반환 타입 : `int`
  - 콘솔입력 한줄 전체를 숫자로 변환한뒤 반환한다.

- `nextLine()`
  - 반환 타입 : `String`
  - 콘솔입력 한줄 전체를 받아서 반환한다.

### Randoms.class
- `pick(final int startInclusive, final int endInclusive)`
  - 반환 타입 : `int`
  - start, end 범위내의 랜덤한 **정수 하나**를 반환한다.

- `List<Integer> notDuplicatedPicks(final int startInclusive, final int endExclusive, final int count)`
  - 반환 타입 : `List<Integer>`
  - start, end 범위내 count 개수 만큼의 **중복되지 않는** 랜덤한 정수 리스트를 반환한다.
  - startInclusive는 범위에 포함, endExclusive는 포함되지 않음

- `shuffle(final List<T> list)`
  - 반환 타입 : List<T>
  - 리스트의 원소들을 섞어서 반환한다.

### DateUtils.class
- `now()`
  - 반환 타입 : `LocalDateTime`
  - 현재 시간을 반환