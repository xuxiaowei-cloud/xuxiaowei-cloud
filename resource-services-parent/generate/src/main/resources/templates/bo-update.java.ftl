package ${packageName};

<#if lombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
</#if>
<#if notEmpty>
import javax.validation.constraints.NotEmpty;
</#if>
<#if notNull>
import javax.validation.constraints.NotNull;
</#if>

/**
 * ${tableComment4j}
 *
 * @author ${author}
 * @since ${since}
 */
<#if lombokModel>
@Data
@EqualsAndHashCode(callSuper = true)
</#if>
public class ${className} extends ${boBaseClassName} {

    private static final long serialVersionUID = 1L;

<#list fields as field>
    <#if field.key == 'PRI'>
    /**
     * ${field.comment}
     */
        <#if field.validation??>
    ${field.validation}(message = "${field.message}")
        </#if>
    private ${field.propertyType} ${field.propertyName};

    </#if>
</#list>
<#if !lombokModel>
    <#list fields as field>
        <#if field.key == 'PRI'>
    public ${field.propertyType} get${field.capitalName}() {
        return ${field.propertyName};
    }

    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        this.${field.propertyName} = ${field.propertyName};
    }

        </#if>
    </#list>
</#if>
}
