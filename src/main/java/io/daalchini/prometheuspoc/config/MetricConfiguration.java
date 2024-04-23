package io.daalchini.prometheuspoc.config;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.MeterTagAnnotationHandler;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

@Configuration
@EnableAspectJAutoProxy
public class MetricConfiguration {

    @Bean
    public CountedAspect countedAspect(MeterRegistry registry) {
        return new CountedAspect(registry);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        var aspect = new TimedAspect(registry);
        aspect.setMeterTagAnnotationHandler(
                new MeterTagAnnotationHandler(
                        _class -> Objects::toString,
                        _class -> evaluate::apply));

        return aspect;
    }

    private final BiFunction<String, Object, String> evaluate = (e, v) -> {
        var ctx = new StandardEvaluationContext(v);
        var exp = new SpelExpressionParser().parseExpression(e);

        return Optional.ofNullable(exp.getValue(ctx)).map(Object::toString).orElse("");
    };
}
