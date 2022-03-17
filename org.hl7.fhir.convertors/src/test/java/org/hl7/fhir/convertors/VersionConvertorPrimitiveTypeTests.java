package org.hl7.fhir.convertors;

import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.Canonical10_40;
import org.hl7.fhir.convertors.factory.*;
import org.hl7.fhir.r4.model.CanonicalType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Stream;

public class VersionConvertorPrimitiveTypeTests {

  private static String[] BASE64_BINARY_STRINGS = {
     "dGhpcyBpcyB2YWxpZCBiYXNlNjQ="
  };

  private static String[] BOOLEAN_STRINGS = {
    "true", "false"
  };

  private static String[] DECIMAL_STRINGS = {
    BigDecimal.valueOf(-Double.MAX_VALUE).toPlainString(),
    "-12345",
    "-12.300",
    "-12.3000",
    "0",
    "0.0",
    "0.00",
    "12.300",
    "12.3000",
    "12345",
    BigDecimal.valueOf(Double.MAX_VALUE).toPlainString()
  };

  private static String[] ID_STRINGS = {
    "345e3443345343f3453.t34g5348t3-45t34"
  };
  
  private static String[] STRING_STRINGS = {
    "something", "<html>?</html>", "Han shot first", ""
  };

  private static String[] INTEGER_STRINGS = {
    Integer.toString(Integer.MIN_VALUE), "-12345", "0", "12345", Integer.toString(Integer.MAX_VALUE)
  };

  private static String[] OID_STRINGS = {
    "urn:oid:1.3.6.1",
    "urn:oid:1.3.6.1.4.1",
    "urn:oid:1.3.6.1.2.1.27",
    "URN:OID:0.9.2342.19200300.100.4"
  };

  private static String[] POSITIVE_INT_STRINGS = {
    "1", "12345", Integer.toString(Integer.MAX_VALUE)
  };
  
  private static String[] UNSIGNED_INT_STRINGS = {
    "0", "12345", Integer.toString(Integer.MAX_VALUE)
  };


  private static String[] URI_STRINGS = {
    "foo://example.com:8042/find/bassist?name=lemmy#birthdate"
  };

  private static String[] UUID_STRINGS = {
    "123e4567-e89b-12d3-a456-426614174000"
  };

  //Date, DateTime, and Instant
  private static String[] DEFAULT_DATE_PRECISION_STRINGS = {
    "1933", "1933-01", "1933-01-02"
  };

  private static String[] SECOND_DATE_PRECISION_STRINGS =
    Stream.concat(Arrays.stream(DEFAULT_DATE_PRECISION_STRINGS),Stream.of("1933-01-02T13:45:12"))
      .toArray(size -> (String[]) Array.newInstance(String.class, size));

  private static String[] MILLISECOND_DATE_PRECISION_STRINGS =
    Stream.concat(Arrays.stream(SECOND_DATE_PRECISION_STRINGS),Stream.of("1933-01-02T13:45:12.3", "1933-01-02T13:45:12.3456"))
      .toArray(size -> (String[]) Array.newInstance(String.class, size));

  private static String[] INSTANT_PRECISION_STRINGS = {
    "1933-01-02T13:45:12", "1933-01-02T13:45:12.3", "1933-01-02T13:45:12.3456"
  };
  
  private static <K,L>  Stream<Arguments> getFactoryConversionParams(Class<? extends VersionConvertorFactory> versionConverterFactoryClazz, Class<K> firstTypeClazz, Class<?> firstTypeMethodClazz, Class<L> secondTypeClazz, Class<?> secondTypeMethodClazz, String[] testStrings) {

    return Arrays.stream(testStrings).map( it -> Arguments.of(versionConverterFactoryClazz,
      firstTypeClazz, firstTypeMethodClazz, it,
      secondTypeClazz, secondTypeMethodClazz, it
    ));
  }

  private static Stream<Arguments> getFactoryParams() {
      return Stream.of(
        //Base64Binary
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.Base64BinaryType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.Base64BinaryType.class, org.hl7.fhir.dstu3.model.Type.class,
          BASE64_BINARY_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.Base64BinaryType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.Base64BinaryType.class, org.hl7.fhir.r4.model.Type.class,
          BASE64_BINARY_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.Base64BinaryType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.Base64BinaryType.class, org.hl7.fhir.r5.model.DataType.class,
          BASE64_BINARY_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.Base64BinaryType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.Base64BinaryType.class, org.hl7.fhir.dstu3.model.Type.class,
          BASE64_BINARY_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.Base64BinaryType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.Base64BinaryType.class, org.hl7.fhir.r4.model.Type.class,
          BASE64_BINARY_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.Base64BinaryType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.Base64BinaryType.class, org.hl7.fhir.r5.model.DataType.class,
          BASE64_BINARY_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.Base64BinaryType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.Base64BinaryType.class, org.hl7.fhir.r4.model.Type.class,
          BASE64_BINARY_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.Base64BinaryType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.Base64BinaryType.class, org.hl7.fhir.r5.model.DataType.class,
          BASE64_BINARY_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.Base64BinaryType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.Base64BinaryType.class, org.hl7.fhir.r5.model.DataType.class,
          BASE64_BINARY_STRINGS),

        //Boolean
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.BooleanType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.BooleanType.class, org.hl7.fhir.dstu3.model.Type.class,
          BOOLEAN_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.BooleanType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.BooleanType.class, org.hl7.fhir.r4.model.Type.class,
          BOOLEAN_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.BooleanType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.BooleanType.class, org.hl7.fhir.r5.model.DataType.class,
          BOOLEAN_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.BooleanType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.BooleanType.class, org.hl7.fhir.dstu3.model.Type.class,
          BOOLEAN_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.BooleanType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.BooleanType.class, org.hl7.fhir.r4.model.Type.class,
          BOOLEAN_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.BooleanType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.BooleanType.class, org.hl7.fhir.r5.model.DataType.class,
          BOOLEAN_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.BooleanType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.BooleanType.class, org.hl7.fhir.r4.model.Type.class,
          BOOLEAN_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.BooleanType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.BooleanType.class, org.hl7.fhir.r5.model.DataType.class,
          BOOLEAN_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.BooleanType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.BooleanType.class, org.hl7.fhir.r5.model.DataType.class,
          BOOLEAN_STRINGS),

        //Code
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.CodeType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.CodeType.class, org.hl7.fhir.dstu3.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.CodeType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.CodeType.class, org.hl7.fhir.r4.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.CodeType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.CodeType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.CodeType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.CodeType.class, org.hl7.fhir.dstu3.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.CodeType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.CodeType.class, org.hl7.fhir.r4.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.CodeType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.CodeType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.CodeType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.CodeType.class, org.hl7.fhir.r4.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.CodeType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.CodeType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.CodeType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.CodeType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),

        //Decimal
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.DecimalType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.DecimalType.class, org.hl7.fhir.dstu3.model.Type.class,
          DECIMAL_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.DecimalType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.DecimalType.class, org.hl7.fhir.r4.model.Type.class,
          DECIMAL_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.DecimalType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.DecimalType.class, org.hl7.fhir.r5.model.DataType.class,
          DECIMAL_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.DecimalType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.DecimalType.class, org.hl7.fhir.dstu3.model.Type.class,
          DECIMAL_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.DecimalType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.DecimalType.class, org.hl7.fhir.r4.model.Type.class,
          DECIMAL_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.DecimalType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.DecimalType.class, org.hl7.fhir.r5.model.DataType.class,
          DECIMAL_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.DecimalType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.DecimalType.class, org.hl7.fhir.r4.model.Type.class,
          DECIMAL_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.DecimalType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.DecimalType.class, org.hl7.fhir.r5.model.DataType.class,
          DECIMAL_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.DecimalType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.DecimalType.class, org.hl7.fhir.r5.model.DataType.class,
          DECIMAL_STRINGS),


        //Id
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.IdType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.IdType.class, org.hl7.fhir.dstu3.model.Type.class,
          ID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.IdType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.IdType.class, org.hl7.fhir.r4.model.Type.class,
          ID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.IdType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.IdType.class, org.hl7.fhir.r5.model.DataType.class,
          ID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.IdType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.IdType.class, org.hl7.fhir.dstu3.model.Type.class,
          ID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.IdType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.IdType.class, org.hl7.fhir.r4.model.Type.class,
          ID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.IdType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.IdType.class, org.hl7.fhir.r5.model.DataType.class,
          ID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.IdType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.IdType.class, org.hl7.fhir.r4.model.Type.class,
          ID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.IdType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.IdType.class, org.hl7.fhir.r5.model.DataType.class,
          ID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.IdType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.IdType.class, org.hl7.fhir.r5.model.DataType.class,
          ID_STRINGS),

        //Integer
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.IntegerType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.IntegerType.class, org.hl7.fhir.dstu3.model.Type.class,
          INTEGER_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.IntegerType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.IntegerType.class, org.hl7.fhir.r4.model.Type.class,
          INTEGER_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.IntegerType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.IntegerType.class, org.hl7.fhir.r5.model.DataType.class,
          INTEGER_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.IntegerType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.IntegerType.class, org.hl7.fhir.dstu3.model.Type.class,
          INTEGER_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.IntegerType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.IntegerType.class, org.hl7.fhir.r4.model.Type.class,
          INTEGER_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.IntegerType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.IntegerType.class, org.hl7.fhir.r5.model.DataType.class,
          INTEGER_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.IntegerType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.IntegerType.class, org.hl7.fhir.r4.model.Type.class,
          INTEGER_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.IntegerType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.IntegerType.class, org.hl7.fhir.r5.model.DataType.class,
          INTEGER_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.IntegerType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.IntegerType.class, org.hl7.fhir.r5.model.DataType.class,
          INTEGER_STRINGS),
        
        //Markdown
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.MarkdownType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.MarkdownType.class, org.hl7.fhir.dstu3.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.MarkdownType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.MarkdownType.class, org.hl7.fhir.r4.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.MarkdownType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.MarkdownType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.MarkdownType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.MarkdownType.class, org.hl7.fhir.dstu3.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.MarkdownType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.MarkdownType.class, org.hl7.fhir.r4.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.MarkdownType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.MarkdownType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.MarkdownType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.MarkdownType.class, org.hl7.fhir.r4.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.MarkdownType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.MarkdownType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.MarkdownType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.MarkdownType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),

        //Oid
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.OidType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.OidType.class, org.hl7.fhir.dstu3.model.Type.class,
          OID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.OidType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.OidType.class, org.hl7.fhir.r4.model.Type.class,
          OID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.OidType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.OidType.class, org.hl7.fhir.r5.model.DataType.class,
          OID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.OidType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.OidType.class, org.hl7.fhir.dstu3.model.Type.class,
          OID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.OidType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.OidType.class, org.hl7.fhir.r4.model.Type.class,
          OID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.OidType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.OidType.class, org.hl7.fhir.r5.model.DataType.class,
          OID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.OidType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.OidType.class, org.hl7.fhir.r4.model.Type.class,
          OID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.OidType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.OidType.class, org.hl7.fhir.r5.model.DataType.class,
          OID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.OidType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.OidType.class, org.hl7.fhir.r5.model.DataType.class,
          OID_STRINGS),
        
        //PositiveInt
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.PositiveIntType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.PositiveIntType.class, org.hl7.fhir.dstu3.model.Type.class,
          POSITIVE_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.PositiveIntType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.PositiveIntType.class, org.hl7.fhir.r4.model.Type.class,
          POSITIVE_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.PositiveIntType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.PositiveIntType.class, org.hl7.fhir.r5.model.DataType.class,
          POSITIVE_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.PositiveIntType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.PositiveIntType.class, org.hl7.fhir.dstu3.model.Type.class,
          POSITIVE_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.PositiveIntType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.PositiveIntType.class, org.hl7.fhir.r4.model.Type.class,
          POSITIVE_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.PositiveIntType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.PositiveIntType.class, org.hl7.fhir.r5.model.DataType.class,
          POSITIVE_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.PositiveIntType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.PositiveIntType.class, org.hl7.fhir.r4.model.Type.class,
          POSITIVE_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.PositiveIntType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.PositiveIntType.class, org.hl7.fhir.r5.model.DataType.class,
          POSITIVE_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.PositiveIntType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.PositiveIntType.class, org.hl7.fhir.r5.model.DataType.class,
          POSITIVE_INT_STRINGS),
        
        //String
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.StringType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.StringType.class, org.hl7.fhir.dstu3.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.StringType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.StringType.class, org.hl7.fhir.r4.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.StringType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.StringType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.StringType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.StringType.class, org.hl7.fhir.dstu3.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.StringType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.StringType.class, org.hl7.fhir.r4.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.StringType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.StringType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.StringType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.StringType.class, org.hl7.fhir.r4.model.Type.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.StringType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.StringType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.StringType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.StringType.class, org.hl7.fhir.r5.model.DataType.class,
          STRING_STRINGS),

        //UnsignedInt
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.UnsignedIntType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.UnsignedIntType.class, org.hl7.fhir.dstu3.model.Type.class,
          UNSIGNED_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.UnsignedIntType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.UnsignedIntType.class, org.hl7.fhir.r4.model.Type.class,
          UNSIGNED_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.UnsignedIntType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.UnsignedIntType.class, org.hl7.fhir.r5.model.DataType.class,
          UNSIGNED_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.UnsignedIntType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.UnsignedIntType.class, org.hl7.fhir.dstu3.model.Type.class,
          UNSIGNED_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.UnsignedIntType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.UnsignedIntType.class, org.hl7.fhir.r4.model.Type.class,
          UNSIGNED_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.UnsignedIntType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.UnsignedIntType.class, org.hl7.fhir.r5.model.DataType.class,
          UNSIGNED_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.UnsignedIntType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.UnsignedIntType.class, org.hl7.fhir.r4.model.Type.class,
          UNSIGNED_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.UnsignedIntType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.UnsignedIntType.class, org.hl7.fhir.r5.model.DataType.class,
          UNSIGNED_INT_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.UnsignedIntType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.UnsignedIntType.class, org.hl7.fhir.r5.model.DataType.class,
          UNSIGNED_INT_STRINGS),
        
        //Uri
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.UriType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.UriType.class, org.hl7.fhir.dstu3.model.Type.class,
          URI_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.UriType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.UriType.class, org.hl7.fhir.r4.model.Type.class,
          URI_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.UriType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.UriType.class, org.hl7.fhir.r5.model.DataType.class,
          URI_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.UriType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.UriType.class, org.hl7.fhir.dstu3.model.Type.class,
          URI_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.UriType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.UriType.class, org.hl7.fhir.r4.model.Type.class,
          URI_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.UriType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.UriType.class, org.hl7.fhir.r5.model.DataType.class,
          URI_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.UriType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.UriType.class, org.hl7.fhir.r4.model.Type.class,
          URI_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.UriType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.UriType.class, org.hl7.fhir.r5.model.DataType.class,
          URI_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.UriType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.UriType.class, org.hl7.fhir.r5.model.DataType.class,
          URI_STRINGS),

        //Uuid
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.UuidType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.UuidType.class, org.hl7.fhir.dstu3.model.Type.class,
          UUID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.UuidType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.UuidType.class, org.hl7.fhir.r4.model.Type.class,
          UUID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.UuidType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.UuidType.class, org.hl7.fhir.r5.model.DataType.class,
          UUID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.UuidType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.UuidType.class, org.hl7.fhir.dstu3.model.Type.class,
          UUID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.UuidType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.UuidType.class, org.hl7.fhir.r4.model.Type.class,
          UUID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.UuidType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.UuidType.class, org.hl7.fhir.r5.model.DataType.class,
          UUID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.UuidType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.UuidType.class, org.hl7.fhir.r4.model.Type.class,
          UUID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.UuidType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.UuidType.class, org.hl7.fhir.r5.model.DataType.class,
          UUID_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.UuidType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.UuidType.class, org.hl7.fhir.r5.model.DataType.class,
          UUID_STRINGS),
        
        //Date
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.DateType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.DateType.class, org.hl7.fhir.dstu3.model.Type.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.DateType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.DateType.class, org.hl7.fhir.r4.model.Type.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.DateType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.DateType.class, org.hl7.fhir.r5.model.DataType.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.DateType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.DateType.class, org.hl7.fhir.dstu3.model.Type.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.DateType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.DateType.class, org.hl7.fhir.r4.model.Type.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.DateType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.DateType.class, org.hl7.fhir.r5.model.DataType.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.DateType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.DateType.class, org.hl7.fhir.r4.model.Type.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.DateType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.DateType.class, org.hl7.fhir.r5.model.DataType.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.DateType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.DateType.class, org.hl7.fhir.r5.model.DataType.class,
          DEFAULT_DATE_PRECISION_STRINGS),

        //Date DateTime
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.DateType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.DateTimeType.class, org.hl7.fhir.dstu3.model.Type.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.DateType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.DateTimeType.class, org.hl7.fhir.r4.model.Type.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.DateType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.DateTimeType.class, org.hl7.fhir.r5.model.DataType.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.DateTimeType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.DateType.class, org.hl7.fhir.dstu3.model.Type.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.DateTimeType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.DateType.class, org.hl7.fhir.r4.model.Type.class,
          DEFAULT_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.DateTimeType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.DateType.class, org.hl7.fhir.r5.model.DataType.class,
          DEFAULT_DATE_PRECISION_STRINGS),

        //DateTime
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.DateTimeType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.DateTimeType.class, org.hl7.fhir.dstu3.model.Type.class,
          MILLISECOND_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.DateTimeType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.DateTimeType.class, org.hl7.fhir.r4.model.Type.class,
          MILLISECOND_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.DateTimeType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.DateTimeType.class, org.hl7.fhir.r5.model.DataType.class,
          MILLISECOND_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.DateTimeType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.DateTimeType.class, org.hl7.fhir.dstu3.model.Type.class,
          MILLISECOND_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.DateTimeType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.DateTimeType.class, org.hl7.fhir.r4.model.Type.class,
          MILLISECOND_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.DateTimeType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.DateTimeType.class, org.hl7.fhir.r5.model.DataType.class,
          MILLISECOND_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.DateTimeType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.DateTimeType.class, org.hl7.fhir.r4.model.Type.class,
          MILLISECOND_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.DateTimeType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.DateTimeType.class, org.hl7.fhir.r5.model.DataType.class,
          MILLISECOND_DATE_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.DateTimeType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.DateTimeType.class, org.hl7.fhir.r5.model.DataType.class,
          MILLISECOND_DATE_PRECISION_STRINGS),

        //Instant
        getFactoryConversionParams(VersionConvertorFactory_10_30.class,
          org.hl7.fhir.dstu2.model.InstantType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.dstu3.model.InstantType.class, org.hl7.fhir.dstu3.model.Type.class,
          INSTANT_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_40.class,
          org.hl7.fhir.dstu2.model.InstantType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r4.model.InstantType.class, org.hl7.fhir.r4.model.Type.class,
          INSTANT_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_10_50.class,
          org.hl7.fhir.dstu2.model.InstantType.class, org.hl7.fhir.dstu2.model.Type.class,
          org.hl7.fhir.r5.model.InstantType.class, org.hl7.fhir.r5.model.DataType.class,
          INSTANT_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_30.class,
          org.hl7.fhir.dstu2016may.model.InstantType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.dstu3.model.InstantType.class, org.hl7.fhir.dstu3.model.Type.class,
          INSTANT_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_40.class,
          org.hl7.fhir.dstu2016may.model.InstantType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r4.model.InstantType.class, org.hl7.fhir.r4.model.Type.class,
          INSTANT_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_14_50.class,
          org.hl7.fhir.dstu2016may.model.InstantType.class, org.hl7.fhir.dstu2016may.model.Type.class,
          org.hl7.fhir.r5.model.InstantType.class, org.hl7.fhir.r5.model.DataType.class,
          INSTANT_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_40.class,
          org.hl7.fhir.dstu3.model.InstantType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r4.model.InstantType.class, org.hl7.fhir.r4.model.Type.class,
          INSTANT_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_30_50.class,
          org.hl7.fhir.dstu3.model.InstantType.class, org.hl7.fhir.dstu3.model.Type.class,
          org.hl7.fhir.r5.model.InstantType.class, org.hl7.fhir.r5.model.DataType.class,
          INSTANT_PRECISION_STRINGS),
        getFactoryConversionParams(VersionConvertorFactory_40_50.class,
          org.hl7.fhir.r4.model.InstantType.class, org.hl7.fhir.r4.model.Type.class,
          org.hl7.fhir.r5.model.InstantType.class, org.hl7.fhir.r5.model.DataType.class,
          INSTANT_PRECISION_STRINGS)
      ).flatMap(i -> i);
  }

  @ParameterizedTest(name = "Test index: {index} ConvertorFactory={0} First Class={1} First Value={3} Second Class={4} Second Value={6}")
  @MethodSource("getFactoryParams")
  public <K, L> void testValidFactoryConversion(Class<VersionConvertorFactory> versionConverterFactoryClazz, Class<K> firstTypeClazz, Class<?> firstTypeMethodClazz, String firstString, Class<L> secondTypeClazz, Class<?> secondTypeMethodClazz, String secondString) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
    testValidFactoryConversion(versionConverterFactoryClazz, firstTypeClazz, firstTypeMethodClazz, firstString, secondTypeClazz, secondString);
    testValidFactoryConversion(versionConverterFactoryClazz, secondTypeClazz, secondTypeMethodClazz, secondString, firstTypeClazz, firstString);
  }

  public <K, L> void testValidFactoryConversion(Class<VersionConvertorFactory> versionConverterFactoryClazz, Class<K> srcTypeClazz, Class<?> srcTypeMethodClazz, String srcString, Class<L> tgtTypeClazz, String tgtString) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    K srcInstance = srcTypeClazz.getDeclaredConstructor().newInstance();

    Method srcSetValueAsStringMethod = srcTypeClazz.getMethod("setValueAsString", String.class);
    Method srcGetValueAsString =  srcTypeClazz.getMethod("getValueAsString");
    Method srcHasValue =  srcTypeClazz.getMethod("hasValue");

    Method convertTypeMethod = versionConverterFactoryClazz.getMethod("convertType", srcTypeMethodClazz);

    Method tgtGetValueAsString = tgtTypeClazz.getMethod("getValueAsString");
    srcSetValueAsStringMethod.invoke(srcInstance, srcString);

    String srcValueAsString = (String) srcGetValueAsString.invoke(srcInstance);
    Assertions.assertEquals(srcString, srcValueAsString);

    boolean srcHasValueReturn = (boolean) srcHasValue.invoke(srcInstance);

    L tgtInstance = (L) convertTypeMethod.invoke(null, srcInstance);

    if (srcHasValueReturn) {
      String tgtValueAsString = (String) tgtGetValueAsString.invoke(tgtInstance);
      Assertions.assertEquals(tgtString, tgtValueAsString);
    } else {
      Assertions.assertNull(tgtInstance);
    }
  }


  private static Stream<Arguments> getDirectConversionParams() {
      return Stream.of(
              Arguments.of(org.hl7.fhir.r4.model.CanonicalType.class, org.hl7.fhir.dstu2.model.Reference.class,
                (Function<org.hl7.fhir.r4.model.CanonicalType, org.hl7.fhir.dstu2.model.Reference>) Canonical10_40::convertCanonicalToReference,
                "/test", "/test")
      );
  }


  @Disabled
  @ParameterizedTest(name = "Test index: {index} Source Class={1} First Value={3} Second Class={4} Second Value={6}")
  @MethodSource("getDirectConversionParams")
  public <K, L> void testDirectConversion(Class<K> srcTypeClazz, Class<L> targetTypeClazz, Function<K, L> convertFunction, String srcString, String tgtString) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    K srcInstance = srcTypeClazz.getDeclaredConstructor().newInstance();

    Method srcSetValueAsStringMethod = srcTypeClazz.getMethod("setValueAsString", String.class);
    Method srcGetValueAsString =  srcTypeClazz.getMethod("getValueAsString");
    Method srcHasValue =  srcTypeClazz.getMethod("hasValue");

     srcSetValueAsStringMethod.invoke(srcInstance, srcString);

    L tgtInstance =  convertFunction.apply(srcInstance);
  }

}
