package com.github.feliortega.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class AnnotationsTest {
  @Test
  void itemsClient() {
    final Annotation[] annotations = ItemsClient.class.getAnnotations();

    Stream.of(annotations)
        .map(annotation -> ItemsClient.class
            .getAnnotation(annotation.annotationType()))
        .forEach(System.out::println);
  }

  @Test
  void restClient() {
    final Method[] methods = RestUtils.class.getMethods();

    Stream.of(methods)
        .map(method -> new Tuple<String, Optional<ApiCall>>(method.toString(), Optional.ofNullable(method.getAnnotation(ApiCall.class))))
        .map(value ->
            value.b.isPresent()
              ? String.format("%s \t %s", value.b.get().value(), value.a)
              : String.format("(no api call) \t %s", value.a)
        )
        .forEach(System.out::println);
  }

  class Tuple<A, B> {
    public final A a;
    public final B b;

    Tuple(A a, B b) {
      this.a = a;
      this.b = b;
    }

    public <C> Tuple<A, C> map(Function<B, C> function) {
      return new Tuple(a, function.apply(b));
    }

    @Override
    public String toString() {
      return String.format("(%s, %s)", a, b);
    }
  }
}
