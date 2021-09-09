# wooteco-utils

### Console.class

- `readLine()`
  - 반환 타입 : `String`
  - 콘솔입력 한줄 전체를 받아서 반환한다.

### Randoms.class
- `pickNumberInRange(final int startInclusive, final int endInclusive)`
  - 반환 타입 : `int`
  - start, end 범위 내에서 랜덤한 **정수 하나**를 반환한다.

- `pickNumberInList(final List<Integer> numbers)`
  - 반환 타입 : `int`
  - numbers 내에서 랜덤한 **정수 하나**를 반환한다.

- `pickUniqueNumbersInRange(final int startInclusive, final int endInclusive, final int count)`
  - 반환 타입 : `List<Integer>`
  - start, end 범위 내에서 count 개수 만큼의 **중복되지 않는** 랜덤한 정수 리스트를 반환한다.

- `shuffle(final List<T> list)`
  - 반환 타입 : List<T>
  - 리스트의 원소들을 섞어서 반환한다.
