Controller Advice

Why?

왜 @Controller Advice를 사용하였는가?
문제는 이러헀다.

토이 프로젝트를 진행중 Controller단에서 예외를 터트려 이를 해당 예외에 대한 HttpservletResponse에 StatusCode를 담아 보내주고 싶었다.

하지만 코드가 복잡해지고 하드코딩을 하게 되면서 가독성이 떨어지고 예외가 제대로 터지지 않는 에러도 발생했었다.
Exception에 대해 제대로 공부하지 않고 사용을 해서 사용 방법 자체가 틀린지 몰랐었다.

선배의 조언으로 ExceptionHandler에 대해 공부해 보라는 이야기를 듣고 찾아본 내용을 토대로 정리를 해보겠다.

요청 매핑중 예외 발생시 처리

Spring boot로 프로젝트를 진행하면서 컨트롤러(@Controller) 단에서 예외가 발생시 어떻게 이를 처리를 할지 생각을 해봤다.

Exception이 터지면 다음 코드는 진행이 되지 않고 예외를 터트리며 끝나게 된다.
그렇다면 이 예외를 어떻게 response에 담을 것인가?

이는 DispatcherServlet이 HandlerExceptionResolver Bean 을 위임해 예외를 해결하고 처리하게 된다. 이를 통해 우리는 컨트롤러 단에서 터진 Exception으로 그게 맞는 요청이 가도록 처리할수 있다.

사용가능한 HandlerExceptionResolver

SimpleMappingExceptionResolver

예외 클래스 이름과 에러의 이름을 매핑 시켜준다. 브라우저 Application의 오류페이지를 렌더링할때 사용한다.
DefaultHandlerExceptionResolver

Spring Mvc에서 발생한 예외를 해결하고 Http 상태코드를 매핑해줌. (RestApi, EntityEceptionHandler의 예외의 대안이라 볼수있음)
ResponseStatusExceptionResolver

@ResponseStatus 어노테이션을 사용해 예외를 해결하고 Http상태코드를 매핑해준다.
ExceptionHandlerExceptionResolver

@Controller 또는 @ControllerAdvice 클래스에서 @ExceptionHandler 메서드를 호출해 예외 처리.
4가지의 ExceptionResolver중에 ExceptionHandlerExceptionResolver의 @ControllerAdvice에 대해 알아보자

@ControllerAdvice

일반적으로 @ExceptionHandler, @initBinder 및 @ModelAttribute 메서드는 @Controller 클래스계층에서 사용된다. 이를 전체에 적용해주고 싶다면 @ControllerAdvice나 @RestControllerAdvice 어노테이션을 추가해주면 된다.
@ControllerAdvice는 @Component 어노테이션이 붙어 있으며 이는 Spring bean 에 등록을 의미한다.

@RestControllerAdvice는 @ControllerAdvice + @ResponseBody로 구성되어 있다.

기본적으로 @ControllerAdvice는 모든 Controller 계층에 적용 되지만 다음 예제처럼 범위를 정할수도 있다.
// Target all Controllers annotated with @RestController
@ControllerAdvice(annotations = RestController.class)
public class ExampleAdvice1 {}

// Target all Controllers within specific packages
@ControllerAdvice("org.example.controllers")
public class ExampleAdvice2 {}

// Target all Controllers assignable to specific classes
@ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
public class ExampleAdvice3 {}
How?

@RestControllerAdvice를 선언 한 ControllerAdvice



따로 커스텀한 BadRequestException.class



위 같은 코드로 작성을 하게 되면 BadRequestException이 Controller 단에서 발생하게 된다면
@RestControllerAdvice 에 등록해둔 핸들러가 작동을해 헤더에는 httpstatus = 400을 리턴하고 바디에는 Message 를 담는다.



@ControllerAdvice도 비슷한 방법으로 사용이 가능하다.



---

#Reference

https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-controller-advice