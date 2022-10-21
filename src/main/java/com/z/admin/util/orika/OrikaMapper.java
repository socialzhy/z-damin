package com.z.admin.util.orika;

import com.z.admin.util.orika.converter.BigDecimalConverters;
import com.z.admin.util.orika.converter.BooleanConverters;
import com.z.admin.util.orika.converter.LinkedHashMapToTConverters;
import com.z.admin.util.orika.converter.ListConverters;
import lombok.Getter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * 自定义 Orika 配置，参考自 #{@link ConfigurableMapper}，没有直接继承 ConfigurableMapper 的原因是需要定制初始化过程
 *
 * @author trang
 */
@Getter
public class OrikaMapper {

    private static final Boolean DEFAULT_USE_BUILTIN_BOOLEAN_CONVERTERS = Boolean.TRUE;
    private static final Boolean DEFAULT_USE_BUILTIN_LIST_CONVERTERS = Boolean.TRUE;
    private static final String DEFAULT_DELIMITER = ",";

    /** 是否启用内置的 Boolean Converters，默认开启 */
    private final Boolean useBuiltinListConverters;
    /** 是否启用内置的 List Converters，默认开启 */
    private final Boolean useBuiltinBooleanConverters;
    /** 启用 List Converters 时使用的分隔符，默认为 "," */
    private final String delimiter;
    /** Orika MapperFactory */
    private DefaultMapperFactory factory;
    /** OrikaMapper 初始化标识，用 volatile 来保证多线程环境下的可见性 */
    private volatile boolean initialized;

    public OrikaMapper() {
        this(DEFAULT_USE_BUILTIN_BOOLEAN_CONVERTERS, DEFAULT_USE_BUILTIN_LIST_CONVERTERS, DEFAULT_DELIMITER);
    }

    public OrikaMapper(Boolean useBuiltinBooleanConverters, Boolean useBuiltinListConverters, String delimiter) {
        this.useBuiltinBooleanConverters = useBuiltinBooleanConverters;
        this.useBuiltinListConverters = useBuiltinListConverters;
        this.delimiter = delimiter;
        init();
    }

    private synchronized void init() {
        try {
            if (!initialized) {
                initialized = true;
                DefaultMapperFactory.Builder factoryBuilder = new DefaultMapperFactory.Builder();
                configureFactoryBuilder(factoryBuilder);
                factory = factoryBuilder.build();
                configure(factory);
                configureConverterFactory(factory.getConverterFactory());
            }
        } catch (Exception e) {
            initialized = false;
            throw new CopierException("create orika mapper-factory failed.", e);
        }
    }

    protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {
        factoryBuilder.mapNulls(false);
    }

    protected void configure(MapperFactory factory) {}

    protected void configureConverterFactory(ConverterFactory converterFactory) {
        if (useBuiltinBooleanConverters) {
            converterFactory.registerConverter(new BooleanConverters.BooleanToByteConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToShortConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToIntegerConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToLongConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToDoubleConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToFloatConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToBigDecimalConverter());
            converterFactory.registerConverter(new BigDecimalConverters.IntegerToBigDecimalConverter());
            converterFactory.registerConverter(new LinkedHashMapToTConverters<>());
        }
        if (useBuiltinListConverters && delimiter != null) {
            converterFactory.registerConverter(new ListConverters.ByteListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.ShortListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.IntegerListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.LongListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.DoubleListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.FloatListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.BigDecimalListToStringConverter(delimiter));
        }
    }

}