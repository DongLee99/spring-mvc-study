package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, get = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username = {}, age = {}",memberName, memberAge);
        return "ok"; //responsebody로 인해 뷰가 실행 되지 않고 바디에 담겨서 json
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String memberName,
                                 int memberAge) {
        log.info("username = {}, age = {}",memberName, memberAge);
        return "ok"; //responsebody로 인해 뷰가 실행 되지 않고 바디에 담겨서 json
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(value = "username", required = true) String memberName,
                                 @RequestParam(value = "age", required = true) int memberAge) {
        log.info("username = {}, age = {}",memberName, memberAge);
        return "ok"; //responsebody로 인해 뷰가 실행 되지 않고 바디에 담겨서 json
    }
}
