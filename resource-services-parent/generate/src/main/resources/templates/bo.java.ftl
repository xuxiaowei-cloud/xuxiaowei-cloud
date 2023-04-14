package ${packageName};

<#if lombokModel>
import lombok.Data;

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
 * ${tableComment}
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
    /**
     * ${field.comment}
     */
    <#if field.validation??>
    ${field.validation}(message = "${field.message}")
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
<#if !lombokModel>
    <#list fields as field>
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

    </#list>
</#if>
}