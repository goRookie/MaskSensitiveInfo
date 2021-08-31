package com.rick.log4j.factory.event;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.impl.LogEventFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rick
 * E-mail:sophie_zelmani@163.com
 * @version 2021/8/26 10:51
 */
public class CustomLogEventFactory implements LogEventFactory {
//public class CustomLogEventFactory extends ReusableLogEventFactory {

    private static final CustomLogEventFactory instance = new CustomLogEventFactory();
    /**
     * <p>
     * mask RegExp, to reserve the first three
     * and last four chars for search use.
     * </p>
     */
    private static final String MASK_REGEX = "(?<=.{3}).(?=.*....)";
    /**
     * Credit Card Type                 Prefix                          Length
     * American Express	                34, or 37	                    15
     * MasterCard	                    51 through 55	                16
     * Visa	                            4	                            13 or 16
     * Diners Club and Carte Blanche	36,38,or 300 through 305	    14
     * Discover	                        6011	                        16
     * JCB	                            2123 or 1800	                15
     * JCB	                            3	                            16
     */
    private Pattern creditCardPattern = Pattern.compile("((?:(?:4\\d{3})|(?:5[1-5]\\d{2})|6(?:011|5[0-9]{2}))(?:-?|\\040?)(?:\\d{4}(?:-?|\\040?)){3}|(?:3[4,7]\\d{2})(?:-?|\\040?)\\d{6}(?:-?|\\040?)\\d{5})");

    /**
     * @return
     */
    @SuppressWarnings("unused")
    public static CustomLogEventFactory getInstance() {
        return instance;
    }


    /**
     * Creates a log event.
     *
     * @param loggerName The name of the Logger.
     * @param marker     An optional Marker.
     * @param fqcn       The fully qualified class name of the caller.
     * @param level      The event Level.
     * @param message    The Message.
     * @param properties Properties to be added to the log event.
     * @param throwable  An optional Throwable.
     * @return The LogEvent.
     */
    public LogEvent createEvent(String loggerName, Marker marker, String fqcn, Level level,
                                Message message, List<Property> properties, Throwable throwable) {

        String formattedMessage = message.getFormattedMessage();
        String obfuscatedMsg = obfuscateIfNecessary(creditCardPattern, formattedMessage);
//        if (message instanceof FormattedMessage) {
//            FormattedMessage old = (FormattedMessage) message;
//            FormattedMessage fm = new FormattedMessage(message);
//        }
//
//        if (message instanceof LocalizedMessage) {
//            LocalizedMessage old = (LocalizedMessage) message;
//            LocalizedMessage lm = new LocalizedMessage()
//        }
//
//        if (message instanceof MapMessage) {
//        }
//
//        if (message instanceof MessageFormatMessage) {
//        }
//
//        if (message instanceof ObjectArrayMessage) {
//        }
//
//        if (message instanceof ObjectMessage) {
//        }
//
//        if (message instanceof ParameterizedMessage) {
//        }
//
//        if (message instanceof SimpleMessage) {
//        }
//
//        if (message instanceof StringFormattedMessage) {
//        }
//
//        if (message instanceof StructuredDataMessage) {
//        }
//
//        if (message instanceof ThreadDumpMessage) {
//        }


//        if (message instanceof ReusableSimpleMessage) {
//            ReusableSimpleMessage rsm = (ReusableSimpleMessage) message;
//            rsm.set(obfuscatedMsg);
//        }
//        if (message instanceof ReusableParameterizedMessage) {
//            ReusableParameterizedMessage rpm =(ReusableParameterizedMessage) message;
//            ParameterizedMessage pm = new ParameterizedMessage(rpm.getFormat(), rpm.getParameters(), rpm.getThrowable());
//            message = pm;
//        }
//        if (message instanceof ReusableObjectMessage) {
//            ReusableObjectMessage rom = (ReusableObjectMessage) message;
//            rom.set(obfuscatedMsg);
//        }
//        mle.setMessage(message);
//        return mle;


        Message handledMsg = new SimpleMessage(obfuscatedMsg);
        return new Log4jLogEvent(loggerName, marker, fqcn, level, handledMsg, properties, throwable).asBuilder().build();
    }

    /**
     * <p>
     *  obfuscate the digital chars
     *  which exist in the given string with '*' character
     *  except the first 4 chars ahead and last 4 chars behind.
     * </p>
     *
     * examples:
     * 4111-2222-3333-4444  -> 4111-****-****-4444
     * 4111222233334444     -> 4111********4444
     * 4111 2222 3333 4444  -> 4111********4444
     *
     * @param sensitiveData
     * @return obfuscated string
     */
    @SuppressWarnings("unused")
    private String obfuscate(String sensitiveData) {
        char[] chars = sensitiveData.toCharArray();
        int prefixLength = 4;// reserve first four chars
        int suffixLength = 4;// reserve last four chars
        if ((chars.length <= prefixLength + suffixLength)) {
            return sensitiveData;
        }
        for (int i = prefixLength; i < chars.length - suffixLength; i++) {
            if (isDigital(chars[i])) {
                chars[i] = '*';
            }
        }
        return new String(chars);
    }

    /**
     * obfuscate the digital chars which exist in the given string
     * with '*' character except the first {@link <code>prefixLength</code>}
     * chars ahead and last {@link <code>suffixLength</code>} chars at tail.
     * <p>
     * eg.
     * 4111-2222-3333-4444  -> 411************4444
     * 4111222233334444     -> 411*********4444
     * 4111 2222 3333 4444  -> 411************4444
     *
     * @param sensitiveData
     * @return obfuscated string
     */
    private String obfuscateByRegex(String sensitiveData) {
        return sensitiveData.replaceAll(MASK_REGEX, "*");
    }

    /**
     * @param ch
     * @return whether the given char is numeric
     */
    private boolean isDigital(char ch) {
        return (ch >= 48 && ch <= 57);
    }

    /**
     * @param formattedMsg #{@link Message#getFormattedMessage()}
     * @return obfuscated string.
     */
    private String obfuscateIfNecessary(Pattern pattern, String formattedMsg) {
        Matcher matcher = pattern.matcher(formattedMsg);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String original = matcher.group();
            if (!Objects.isNull(original)) {
//                String obfuscated = obfuscate(original);
                String obfuscated = obfuscateByRegex(original);
                matcher.appendReplacement(sb, obfuscated);
            }
        }
        if (sb.length() != 0) {
            matcher.appendTail(sb);
            return sb.toString();
        }
        return formattedMsg;
    }
}
