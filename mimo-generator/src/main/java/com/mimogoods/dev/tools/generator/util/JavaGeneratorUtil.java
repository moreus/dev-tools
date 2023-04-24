package com.mimogoods.dev.tools.generator.util;

import com.mimogoods.dev.tools.generator.core.Section;
import com.mimogoods.dev.tools.generator.impl.CoreGeneratorContext;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import fj.P3;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

public class JavaGeneratorUtil {
    public static final String GETTER_PREFIX         = "get";
    public static final String AS_ARRAY              = "AsArray";
    public static final String ESCAPED_QUESTION_MARK = "\\?";
    public static final String OPEN_BRACKET          = "(";
    public static final String CLOSED_BRACKET        = ")";
    public static final String EXCLAMATION_MARK      = "!";
    public static final String DOT                   = ".";
    public static final String ELVIS_OPERATOR        = "?:";
    public static final String DOUBLE_QUOTE          = "\"";
    public static final String HASH_MARK             = "#";
    public static final String OPEN_BRACES           = "{";
    public static final String CLOSED_BRACES         = "}";
    public static final String OR_OPERATOR           = "|";
    public static final String AND_OPERATOR          = "&";
    public static final String RULES                 = "rules";
    public static final String REQUEST_CONTEXT       = "requestContext";
    public static final String NULL                  = "null";

    private JavaGeneratorUtil(){}

    public static String getMethodName(String parameters) {
        return getMethodName(parameters, EMPTY);
    }

    public static String getMethodName(String parameters, String suffix) {
        return String.format("%s%s%s", GETTER_PREFIX,
                Arrays.stream(parameters.replaceAll(ESCAPED_QUESTION_MARK, EMPTY).split(","))
                        .map(s -> capitalize(s.contains(DOT) ? s.substring(s.lastIndexOf(DOT) + 1) : s))
                        .collect(Collectors.joining("And")), suffix);
    }

    private static CodeBlock getNegateCode(final CodeBlock codeBlock, final boolean negate) {
        return negate ? CodeBlock.builder()
                .add("$1L$2L", EXCLAMATION_MARK, codeBlock)
                .build() : codeBlock;
    }

    public static CodeBlock getGetterArray(
            final Section section, String parameters,
            final CoreGeneratorContext coreGeneratorContext)
    {
        return Arrays.stream(parameters.split(","))
                .map(parameter -> getFieldAccessor(section, parameter, coreGeneratorContext, NULL))
                .reduce(getCodeBlockAccumulator("$1L,$2L"))
                .orElseThrow(IllegalArgumentException::new);
    }

    private static CodeBlock getParamCode(final List<P3<CodeBlock, Boolean, String>> paramProperties) {
        String[] varNames = {"a", "b", "c", "d", "e", "f"};
        final AtomicInteger optionalVarIndex = new AtomicInteger(0);
        return paramProperties.stream()
                .map(p -> p._2() ? p._1()
                        : CodeBlock.builder().add("$1L", varNames[optionalVarIndex.getAndIncrement()]).build())
                .reduce(getCodeBlockAccumulator("$1L,$2L")).orElseThrow(IllegalArgumentException::new);
    }

    private static BinaryOperator<CodeBlock> getCodeBlockAccumulator(final String format) {
        return (a, b) -> CodeBlock.builder().add(format, a, b).build();
    }

    private static Object getDefaultCode(final Object defaultValue) {
        return Objects.isNull(defaultValue) ? EMPTY :
                CodeBlock.builder().add(".orElse($1L)", defaultValue).build();
    }

    public static CodeBlock getFieldGetter(final CodeBlock accessor, final String sectionName) {
        return CodeBlock.builder()
                .add("$1L$2L", uncapitalize(sectionName), accessor)
                .build();
    }

    public static CodeBlock getFieldAccessor(
            final Section section,
            final String fieldPath,
            final CoreGeneratorContext coreGeneratorContext,
            final String defaultValue)
    {
        return wordToCodeBlock(fieldPath + ELVIS_OPERATOR + defaultValue,
                false, section, "this", null, null,
                coreGeneratorContext)._1();
    }

    public static MethodSpec generateParamGetter(
            final Section section, String parameters,
            final CoreGeneratorContext coreGeneratorContext)
    {
        parameters = parameters.replaceAll(SPACE, EMPTY);
        final String methodName = getMethodName(parameters, AS_ARRAY);
        final ArrayTypeName objectArrayTypeName = ArrayTypeName.of(Object.class);
        return MethodSpec.methodBuilder(methodName)
                .addStatement("return new $T {$L}", objectArrayTypeName,
                        getGetterArray(section, parameters, coreGeneratorContext))
                .returns(objectArrayTypeName)
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.DEFAULT)
                .build();
    }

    private static ClassName getClassName(final CoreGeneratorContext coreGeneratorContext, final String functionName) {
        final ClassName finalFunctionContainer;
        final String alias = substringBetween(functionName, OPEN_BRACES, CLOSED_BRACES);
        String fullName = coreGeneratorContext.getGeneratorContext()
                .getTypeAliases().get(alias);
        fullName = fullName == null ? alias : fullName;
        finalFunctionContainer = ClassName.get(substringBeforeLast(fullName, DOT), substringAfterLast(fullName, DOT));
        return finalFunctionContainer;
    }

    @SneakyThrows
    private static CodeBlock getConstantCode(
            final String word,
            final CoreGeneratorContext coreGeneratorContext)
    {
        if (word.contains(DOT)) {
            return CodeBlock.builder()
                    .add("$1L", getClassName(coreGeneratorContext, word),
                            substringAfter(word, DOT))
                    .build();
        }
        return CodeBlock.builder()
                .add("$1L", word)
                .build();
    }

    private static CodeBlock getLiteralCode(final String literal) {
        final String literalFormat = literal.startsWith(DOUBLE_QUOTE) ? "S" : "L";
        return CodeBlock.builder()
                .add(String.format("$1%s", literalFormat), cleanLiteral(literal))
                .build();
    }

    private static boolean isSymbolsOnly(final String string) {
        return containsOnly(string, OR_OPERATOR + AND_OPERATOR + OPEN_BRACKET + CLOSED_BRACKET);
    }

    public static String cleanLiteral(String literal) {
        return remove(literal, DOUBLE_QUOTE);
    }


    public static boolean isLiteral(String string) {
        return string.startsWith(DOUBLE_QUOTE)
                || string.equalsIgnoreCase(Boolean.TRUE.toString())
                || string.equalsIgnoreCase(Boolean.FALSE.toString())
                || string.equals(NULL)
                || Character.isDigit(string.charAt(0));
    }

    public static String camelToUnderscoreNotation(String camelNotatedString) {
        int strLen;
        if (camelNotatedString == null || (strLen = camelNotatedString.length()) == 0) {
            return camelNotatedString;
        }
        StringBuilder buf = new StringBuilder(strLen * 2);
        char ch = 'A';
        for (int i = 0; i < strLen; i++) {
            char prevCh = ch;
            ch = camelNotatedString.charAt(i);
            if (Character.isUpperCase(ch) && !Character.isUpperCase(prevCh)) {
                buf.append('_');
            }
            buf.append(Character.toUpperCase(ch));
        }
        return buf.toString();
    }
}
