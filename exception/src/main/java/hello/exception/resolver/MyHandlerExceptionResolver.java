package hello.exception.resolver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if (ex instanceof IllegalArgumentException) {
            log.info("IllegalArgumentException resolver to 400");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
            return new ModelAndView();
        }
        return null;
    }
}
