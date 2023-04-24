package com.mimogoods.dev.tools.commons.utils;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class EntityMapper
{
    private static ModelMapper entityMapper;

    private static void initMapper()
    {
        entityMapper = new ModelMapper();
        entityMapper.getConfiguration().setAmbiguityIgnored(true);

        // define converters
        Converter<Date, LocalDateTime> dateLocalDateTimeConverter = new AbstractConverter<Date, LocalDateTime>() {
            protected LocalDateTime convert(Date source) {
                return Converters.getLocalDateTime(source);
            }
        };
        Converter<String, Integer> stringIntegerConverter = new AbstractConverter<String, Integer>() {
            protected Integer convert(String source) {
                return Converters.getInteger(source);
            }
        };
        Converter<Integer, BigDecimal> integerBigDecimalConverter = new AbstractConverter<Integer, BigDecimal>() {
            protected BigDecimal convert(Integer source) {
                return Converters.getBigDecimal(source);
            }
        };
        Converter<Double, BigDecimal> doubleBigDecimalConverter = new AbstractConverter<Double, BigDecimal>() {
            protected BigDecimal convert(Double source) {
                return Converters.getBigDecimal(source);
            }
        };
        entityMapper.addConverter(dateLocalDateTimeConverter);
        entityMapper.addConverter(stringIntegerConverter);
        entityMapper.addConverter(integerBigDecimalConverter);


        Converter<BigDecimal, Integer> bigDecimalIntegerConverter = new AbstractConverter<BigDecimal, Integer>() {
            protected Integer convert(BigDecimal source) {
                return Converters.getInteger(source);
            }
        };
        Converter<BigDecimal, Double> bigDecimalDoubleConverter = new AbstractConverter<BigDecimal, Double>() {
            protected Double convert(BigDecimal source) {
                return Converters.getDouble(source);
            }
        };
        Converter<LocalDateTime, Date> localDateTimeDateConverter = new AbstractConverter<LocalDateTime, Date>() {
            protected Date convert(LocalDateTime source) {
                return Converters.getDate(source);
            }
        };
        entityMapper.addConverter(bigDecimalIntegerConverter);
        entityMapper.addConverter(bigDecimalDoubleConverter);
        entityMapper.addConverter(localDateTimeDateConverter);
    }

    public static ModelMapper getMapper()
    {
        if (entityMapper == null)
        {
            initMapper();
        }
        return entityMapper;
    }
}