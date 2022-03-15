package com.github.feliortega.processor;

import com.github.feliortega.annotation.ApiCall;
import com.github.feliortega.type.Tuple;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

public class AnnotationProcessor extends AbstractProcessor {
  @Override
  public boolean process(Set<? extends TypeElement> types, RoundEnvironment roundEnvironment) {
    types.stream()
        .flatMap(type -> roundEnvironment.getElementsAnnotatedWith(type).stream()
            .map(element -> new Tuple<Element, ApiCall>(element, element.getAnnotation(ApiCall.class))))
        .map(t -> String.format("%s -> %s", t.a, t.b))
        .forEach(this::printInfo);

    return false;
  }
  public void printInfo(String message) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_11;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Set.of(ApiCall.class.getName());
  }
}
