package ${packageName};

<#if datePattern>
import cn.hutool.core.date.DatePattern;
</#if>
<#if jsonFormat>
import com.fasterxml.jackson.annotation.JsonFormat;
</#if>
<#if lombokModel>
import lombok.Data;
</#if>
<#if length>
import org.hibernate.validator.constraints.Length;
</#if>

<#if notEmpty>
import javax.validation.constraints.NotEmpty;
</#if>
<#if notNull>
import javax.validation.constraints.NotNull;
</#if>
import java.io.Serializable;
<#if localDate>
import java.time.LocalDate;
</#if>
<#if localDateTime>
import java.time.LocalDateTime;
</#if>
<#if localTime>
import java.time.LocalTime;
</#if>

/**
 * ${tableComment4j}
 *
 * @author ${author}
 * @since ${since}
 */
<#if lombokModel>
@Data
</#if>
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list fields as field>
    <#if field.key != 'PRI' && !boIgnorePropertyNames?seq_contains(field.propertyName)>
    /**
     * ${field.comment}
     */
        <#if field.validation??>
    ${field.validation}(message = "${field.message}")
        </#if>
        <#if field.length??>
    @Length(max = ${field.length})
        </#if>
        <#if field.propertyType == 'LocalTime'>
    @JsonFormat(pattern = DatePattern.NORM_TIME_PATTERN)
        </#if>
        <#if field.propertyType == 'LocalDate'>
    @JsonFormat(pattern = DatePattern.NORM_DATE_PATTERN)
        </#if>
        <#if field.propertyType == 'LocalDateTime'>
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_MINUTE_PATTERN)
        </#if>
    private ${field.propertyType} ${field.propertyName};

    </#if>
</#list>
<#if !lombokModel>
    <#list fields as field>
        <#if field.key != 'PRI' && !boIgnorePropertyNames?seq_contains(field.propertyName)>
            <#if field.nullColumn = 'NO' && field.propertyType = 'Boolean'>
    public boolean is${field.capitalName}() {
        return ${field.propertyName};
    }
            <#else>
    public ${field.propertyType} get${field.capitalName}() {
        return ${field.propertyName};
    }
            </#if>

    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        this.${field.propertyName} = ${field.propertyName};
    }

        </#if>
    </#list>
</#if>
}