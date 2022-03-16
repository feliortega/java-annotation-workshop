package com.github.feliortega;

import com.github.feliortega.annotation.ApiCall;
import com.github.feliortega.client.ItemsClient;
import com.github.feliortega.client.RestUtils;
import com.github.feliortega.type.Tuple;
import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.stream.Stream;

public class App {
  private static final @ApiCall String emptyString = "";

  public static void main(String[] cmdArgs) {
    System.out.println("ItemsClient");
    getClassAnnotations(ItemsClient.class).forEach(System.out::println);

    System.out.println(emptyString);

    System.out.println("RestClient");
    getMethodAnnotation(RestUtils.class).forEach(System.out::println);
  }

  private static Stream<? extends Annotation> getClassAnnotations(Class<?> c) {
    return Stream.of(c.getAnnotations())
        .map(annotation -> c
            .getAnnotation(annotation.annotationType()));
  }

  private static Stream<String> getMethodAnnotation(Class<?> c) {
    return Stream.of(c.getMethods())
        .flatMap(method -> Stream.of(method.getAnnotations()).map(annotation ->
            new Tuple<String, Optional<? extends Annotation>>(method.toString(), Optional.ofNullable(method.getAnnotation(annotation.getClass())))))
        .map(value -> value.b.isPresent()
            ? String.format("%s \t %s", value.b.get(), value.a)
            : String.format("(no api call) \t %s", value.a)
        );
  }
}
