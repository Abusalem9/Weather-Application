package org.freightfox.weatherreport.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.freightfox.weatherreport.constants.DBConstants;
import org.freightfox.weatherreport.model.LocationInfo;
import org.freightfox.weatherreport.model.OpenWeatherResponse;
import org.freightfox.weatherreport.sequence.TimeStampPrefixedSequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weather")
@Data
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class WeatherEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DBConstants.WEATHER_SEQUENCE)
    @GenericGenerator(name = DBConstants.WEATHER_SEQUENCE,
            strategy = "org.freightfox.weatherreport.sequence.TimeStampPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = TimeStampPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "WT"),
                    @org.hibernate.annotations.Parameter(name = TimeStampPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(name = "weather_id")
    private String weatherId;

    @Column(name = "pincode")
    private String pinCode;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "date")
    private LocalDate date;

    @Column(columnDefinition = "json")
    @Type(type = "jsonb")
    private OpenWeatherResponse weatherData;

    @Column(columnDefinition = "json")
    @Type(type = "jsonb")
    private LocationInfo locationInfo;
}
