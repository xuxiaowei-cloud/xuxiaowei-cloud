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
public class ${className} extends ${baseClassName} {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页，默认值：1
     */
    private long current = 1L;

    /**
     * 每页显示条数，默认值：10
     */
    private long size = 10L;

}