package com.rick.log4j.converter;

import com.rick.log4j.marker.CustomMarker;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rick
 * E-mail:sophie_zelmani@163.com
 * @version 2021/8/25 16:26
 */
@Plugin(name = "SensitiveDataConverter", category = "Converter")
@ConverterKeys({"sc"})
public class CustomLogEventPatternConverter extends LogEventPatternConverter {

    /**
     * pattern for credit card NO.
     */
    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile("((?:(?:4\\d{3})|(?:5[1-5]\\d{2})|6(?:011|5[0-9]{2}))(?:-?|\\040?)(?:\\d{4}(?:-?|\\040?)){3}|(?:3[4,7]\\d{2})(?:-?|\\040?)\\d{6}(?:-?|\\040?)\\d{5})");

    /**
     * <p>
     * mask RegExp, to reserve the first three
     * and last four chars for search use.
     * </p>
     */
    private static final String MASK_REGEX = "(?<=.{3}).(?=.*....)";

    /**
     * constructor
     *
     * @param options
     */
    public CustomLogEventPatternConverter(String[] options) {
        super("sc", "sc");
    }

    /**
     * <p> Unlike most other Plugins, Converters do not use a PluginFactory.
     * Instead, each Converter is required to provide a static newInstance
     * method that accepts an array of Strings as the only parameter.
     * The String array are the values that are specified within the curly
     * braces that can follow the converter key.</p>
     *
     * @param options
     * @return
     */
    public static CustomLogEventPatternConverter newInstance(final String[] options) {
        return new CustomLogEventPatternConverter(options);
    }

    /**
     * @param event
     * @param toAppendTo
     */
    public void format(LogEvent event, StringBuilder toAppendTo) {
        String message = event.getMessage().getFormattedMessage();
        Marker marker = event.getMarker();
        if (Objects.isNull(marker)
                || CustomMarker.SENSITIVE_DATA_MARKER.name().compareToIgnoreCase(marker.getName()) != 0) {
            toAppendTo.append(message);
            return;
        }
        Matcher matcher = CREDIT_CARD_PATTERN.matcher(message);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String original = matcher.group();
            if (!Objects.isNull(original)) {
                String obfuscated = original.replaceAll(MASK_REGEX, "*");
                matcher.appendReplacement(sb, obfuscated);
            }
        }
        if (sb.length() != 0) {
            matcher.appendTail(sb);
            toAppendTo.append(sb.toString());
            return;
        }
        toAppendTo.append(message);
    }
}
