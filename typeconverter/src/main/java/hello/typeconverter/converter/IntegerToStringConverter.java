package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IntegerToStringConverter implements Converter<Integer, String> {

    @Override
    public String convert(Integer source) {
        log.info("converter source={}", source);
        return Integer.toString(source);
    }

    @Override
    public <U> Converter<Integer, U> andThen(Converter<? super String, ? extends U> after) {
        return null;
    }
}
