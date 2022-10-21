package com.z.admin.util.orika.converter;

import com.z.admin.util.orika.MapperUtil;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

import java.util.LinkedHashMap;

public class LinkedHashMapToTConverters<T> extends CustomConverter<LinkedHashMap, T> {
     @Override
    public T convert(LinkedHashMap source, Type<? extends T> type, MappingContext mappingContext) {
        return MapperUtil.map(source,type.getRawType());
    }
}