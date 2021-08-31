package com.rick.log4j.factory.message;

import org.apache.logging.log4j.message.AbstractMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedMessage;

/**
 * @author rick
 * E-mail:sophie_zelmani@163.com
 * @version 2021/8/27 18:18
 */
public class CustomMessageFactory extends AbstractMessageFactory {

    /**
     * Instance of CustomMessageFactory.
     */
    public static final CustomMessageFactory INSTANCE = new CustomMessageFactory();

    /**
     * Constructs a message factory.
     */
    public CustomMessageFactory() {
        super();
    }

    /**
     * Creates {@link CustomMessageFactory} instances.
     *
     * @param message The message pattern.
     * @param params  The message parameters.
     * @return The Message.
     * @see MessageFactory#newMessage(String, Object...)
     */
    @Override
    public Message newMessage(final String message, final Object... params) {
        return new ParameterizedMessage(message, params);
    }

//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0) {
//        return new ParameterizedMessage(message, p0);
//    }
//
//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0, final Object p1) {
//        return new ParameterizedMessage(message, p0, p1);
//    }
//
//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0, final Object p1, final Object p2) {
//        return new ParameterizedMessage(message, p0, p1, p2);
//    }
//
//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
//        return new ParameterizedMessage(message, p0, p1, p2, p3);
//    }
//
//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
//        return new ParameterizedMessage(message, p0, p1, p2, p3, p4);
//    }
//
//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
//        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5);
//    }
//
//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5,
//                              final Object p6) {
//        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5, p6);
//    }
//
//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5,
//                              final Object p6, final Object p7) {
//        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5, p6, p7);
//    }
//
//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5,
//                              final Object p6, final Object p7, final Object p8) {
//        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
//    }
//
//    /**
//     * @since 2.6.1
//     */
//    @Override
//    public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5,
//                              final Object p6, final Object p7, final Object p8, final Object p9) {
//        return new ParameterizedMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
//    }
}
