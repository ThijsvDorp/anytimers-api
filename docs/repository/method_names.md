## [Derived Query Keywords and Examples](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html) 

### Distinct
- **Method:** `findDistinctByLastnameAndFirstname`
- **Query:** `select distinct …​ where x.lastname = ?1 and x.firstname = ?2`

### And
- **Method:** `findByLastnameAndFirstname`
- **Query:** `… where x.lastname = ?1 and x.firstname = ?2`

### Or
- **Method:** `findByLastnameOrFirstname`
- **Query:** `… where x.lastname = ?1 or x.firstname = ?2`

### Is, Equals
- **Method:** `findByFirstname`, `findByFirstnameIs`, `findByFirstnameEquals`
- **Query:** `… where x.firstname = ?1` (or `… where x.firstname IS NULL` if the argument is null)

### Between
- **Method:** `findByStartDateBetween`
- **Query:** `… where x.startDate between ?1 and ?2`

### LessThan
- **Method:** `findByAgeLessThan`
- **Query:** `… where x.age < ?1`

### LessThanEqual
- **Method:** `findByAgeLessThanEqual`
- **Query:** `… where x.age <= ?1`

### GreaterThan
- **Method:** `findByAgeGreaterThan`
- **Query:** `… where x.age > ?1`

### GreaterThanEqual
- **Method:** `findByAgeGreaterThanEqual`
- **Query:** `… where x.age >= ?1`

### After
- **Method:** `findByStartDateAfter`
- **Query:** `… where x.startDate > ?1`

### Before
- **Method:** `findByStartDateBefore`
- **Query:** `… where x.startDate < ?1`

### IsNull, Null
- **Method:** `findByAgeIsNull`, `findByAgeNull`
- **Query:** `… where x.age is null`

### IsNotNull, NotNull
- **Method:** `findByAgeIsNotNull`, `findByAgeNotNull`
- **Query:** `… where x.age is not null`

### Like
- **Method:** `findByFirstnameLike`
- **Query:** `… where x.firstname like ?1`

### NotLike
- **Method:** `findByFirstnameNotLike`
- **Query:** `… where x.firstname not like ?1`

### StartingWith
- **Method:** `findByFirstnameStartingWith`
- **Query:** `… where x.firstname like ?1` (parameter bound with appended `%`)

### EndingWith
- **Method:** `findByFirstnameEndingWith`
- **Query:** `… where x.firstname like ?1` (parameter bound with prepended `%`)

### Containing
- **Method:** `findByFirstnameContaining`
- **Query:** `… where x.firstname like ?1` (parameter bound wrapped in `%`)

### OrderBy
- **Method:** `findByAgeOrderByLastnameDesc`
- **Query:** `… where x.age = ?1 order by x.lastname desc`

### Not
- **Method:** `findByLastnameNot`
- **Query:** `… where x.lastname <> ?1`

### In
- **Method:** `findByAgeIn(Collection<Age> ages)`
- **Query:** `… where x.age in ?1`

### NotIn
- **Method:** `findByAgeNotIn(Collection<Age> ages)`
- **Query:** `… where x.age not in ?1`

### True
- **Method:** `findByActiveTrue()`
- **Query:** `… where x.active = true`

### False
- **Method:** `findByActiveFalse()`
- **Query:** `… where x.active = false`

### IgnoreCase
- **Method:** `findByFirstnameIgnoreCase`
- **Query:** `… where UPPER(x.firstname) = UPPER(?1)`

### Top, First
- **Method:** `findTop3ByOrderByAgeDesc`, `findFirstByOrderByLastnameAsc`
- **Query:** `… order by x.age desc limit 3`, `… order by x.lastname asc limit 1`

### Exists
- **Method:** `existsByEmail`
- **Query:** `… where x.email = ?1` (returns true if exists)

### AllIgnoreCase
- **Method:** `findByFirstnameAndLastnameAllIgnoreCase`
- **Query:** `… where lower(x.firstname) = lower(?1) and lower(x.lastname) = lower(?2)`

### NotEmpty, NotBlank (for collections/strings)
- **Method:** `findByAddressesNotEmpty`
- **Query:** `… where x.addresses is not empty`

### Count
- **Method:** `countByRole`
- **Query:** `select count(x) from User x where x.role = ?1`