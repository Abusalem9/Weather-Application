package org.freightfox.weatherreport.sequence;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

@Component
public class TimeStampPrefixedSequenceIdGenerator extends SequenceStyleGenerator {

    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    private static final String VALUE_PREFIX_DEFAULT = "";
    private static final String NUMBER_FORMAT_DEFAULT = "%d";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private String valuePrefix;
    private String numberFormat;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return valuePrefix + String.format(numberFormat, super.generate(session, object));
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
    }

}