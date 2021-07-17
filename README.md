# util-library

## 유틸 라이브러리 정리

### RadomUtils.class
- `nextInt(final int startInclusive, final int endInclusive)`
  - 반환 타입 : `int`
  - start, end 범위내의 랜덤한 **정수 하나**를 반환

- `intsSet(final int startInclusive, final int endInclusive, final int count)`
  - 반환 타입 : `Set<Integer>`
  - start, end 범위내 count 개수 만큼의 **중복되지 않는** 랜덤한 정수 집합을 반환

- `shuffle(final List<T> list)`
  - 반환 타입 : <T> List
  - 리스트내 원소들을 섞어서 반환

### DateUtils.class
- `now()`
  - 반환 타입 : `LocalDateTime`
  - 현재 시간을 반환