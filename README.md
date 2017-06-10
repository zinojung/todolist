# Todo List
NAVER BOOSTCAMP 지원 과제였습니다.

## 기능
1. **할 일 등록하기**
	- 할 일을 등록하는 input box가 있다.
	- 커서를 두고 입력한 후 enter키를 누른다.
	- 하단에 페이지 갱신 없이 글이 등록된다.
	- 빈 문자이면 등록되지 않는다.
	- 새로 고침을 해도 같아야 한다.
2. **할 일 리스트**
	- 페이지가 로드되면, 등록된 할 일 리스트를 보여진다.
	- 최신 할 일이 앞에 보여진다.
3. **할 일 완료하기**
	- 버튼을 클릭 시 이 일은 완료한 일로 상태가 변경된다.
	- 리스트에서 취소선이 그어진다.
	- css의 `completed` 클래스를 활용한다.
	- 새로 고침을 해도 같아야 한다.
5. **할 일 삭제하기**
	- 리스트에서 텍스트에 마우스 오버하면 삭제하기(X) 버튼이 보인다.
	- 이 버튼을 클릭하면 해당 글은 삭제되어 리스트에서 페이지 갱신없이 보이지 않는다.
	- 새로 고침을 해도 같아야 한다.
6. **할 일 전체 갯수 표시**
	- 아직 완료하지 못한 할 일의 갯수를 보여준다.
	- 할 일 등록하기, 완료하기, 삭제하기을 할 때 할 일의 갯수는 동기화 되어야 한다.
7. **할 일 리스트를 필터링**
	- 기본은 `ALL`로 모든 할 일이 보인다.
	- `Active`클릭 시 아직 완료하지 못한 일이 보인다
	- `Completed`를 클릭 시 완료한 일이 보인다.
	- `hash`가 `url`에 표현되면 안된다.
	- 필터링할 때는 페이지 갱신이 없어야 한다.
	- 새로 고침을 할 때는 항상 `ALL`상태로 보인다.
8. **완료한 일 삭제**
	- 클릭 시 이미 완료한 일을 리스트에서 삭제한다.
	- 새로 고침을 해도 같아야 한다.

## 조건
### FE
- 라이브러리는 jQuery만 사용한다.
- 파일들을 합치거나 난독화하지 않는다. 별도의 빌드도구(grunt, gulp, webpack, rollup등...)를 사용하지 않아도 된다.
- babel이나 Typescript등을 이용하여 ES5로 변환하지 않는다. ES5로 작성한다.
- 별도의 코딩 컨벤션은 필요 없다. 다만, 일관된 자신의 코딩 컨벤션을 준수한다.
- `app.js`에 자바스크립트를 작성한다. 필요에 의해 다른 파일들(js, css..)을 만들어도 상관없다.
- 최신 크롬만 동작하면 된다.

### Server
- UI는 정적파일인 HTML과 JavaScript에만 맡긴다. 즉 JSP와 같은 서버사이드 렌더링을 사용하지 않는다.
- Java의 일반적인 코딩 컨벤션을 따른다.
	- 클래스명은 UpperCamelCase, 멤버 변수명,메서드명은 lowerCamelCase로 선언한다.
	- 상수 이름은 대문자로 쓰고 "_"로 구분한다.
	- 중괄호의 시작 위치, 들여쓰기 문자는 `TodoApplication.java`를 참조하여 맞춘다.
- `src/main/java` 아래에 있는 코드에는 `System.out.println()`이 남아있지 않아야한다.
- 과제의 기본틀에서 제공하는데로 Spring MVC + Spring Boot를 이용해서 구현한다.
- DB와 스키마는 어플리케이션이 실행되는 시점에 자동생성된다.
	- server 디렉토리에서 `./mvnw exec:java` 명령을 내리면 DB에 접속할 수 있다. DB접속 정보는 `server/src/main/resources/application.properties`를 참조한다.
- `TodoController`, `TodoService`, `TodoDao` 3개의 계층으로 어플리케이션을 구성한다.
	- 계층 간의 의존성은 생성자를 통해 주입한다.
- 1개 이상의 테스트 메서드를 담은 테스트 클래스 1개를 필수로 작성한다.

#### `TodoController`
- 패키지는 `kr.or.connect.todo.api`으로 선언한다.
- `/api/todos`의 경로에 REST 스타일로 API를 정의한다.
- `@RestController`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` 애너테이션을 활용한다.

#### `TodoService`
- 패키지는 `kr.or.connect.todo.service`으로 선언한다.

#### `TodoDao`
- 패키지는 `kr.or.connect.todo.persistence`로 선언한다.
- Spring JDBC의 `SimpleJdbcInsert`, `NamedParameterJdbcTemplate`을 이용해서 구현한다.
- CRUD 동작을 실행하는 역할에만 충실하도록 구현한다.
- SQL구문은 `TodoSqls` 클래스에 상수로 선언한다.
