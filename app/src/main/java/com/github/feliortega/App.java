package com.github.feliortega;

import com.github.feliortega.annotation.ApiCall;
import com.github.feliortega.client.ItemsClient;
import com.github.feliortega.client.RestUtils;
import com.github.feliortega.type.Tuple;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

public class App {
  private static final @ApiCall String emptyString = "";

  public static void main(String[] cmdArgs) {
    System.out.println("ItemsClient");
    itemsClient().forEach(System.out::println);
    System.out.println(emptyString);

    System.out.println("RestClient");
    restClient().forEach(System.out::println);
  }

  public static Stream<String> itemsClient() {
    final Annotation[] annotations = ItemsClient.class.getAnnotations();

    return Stream.of(annotations)
        .map(annotation -> ItemsClient.class
            .getAnnotation(annotation.annotationType())
            .toString());
  }

  public static Stream<String> restClient() {
    final Method[] methods = RestUtils.class.getMethods();

    return Stream.of(methods)
        .map(method -> new Tuple<String, Optional<ApiCall>>(method.toString(), Optional.ofNullable(method.getAnnotation(ApiCall.class))))
        .map(value ->
            value.b.isPresent()
                ? String.format("%s \t %s", value.b.get().value(), value.a)
                : String.format("(no api call) \t %s", value.a)
        );
  }
}
