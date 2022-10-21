package com.z.admin.util.orika.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

import java.math.BigDecimal;

public class BigDecimalConverters {
    public static class IntegerToBigDecimalConverter extends CustomConverter<Object, BigDecimal> {
        @Override
        public BigDecimal convert(Object o, Type<? extends BigDecimal> type, MappingContext mappingContext) {
            return new BigDecimal(o.toString());
        }
    }
}
