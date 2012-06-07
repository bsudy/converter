/*
 * Moresby Coffee Bean
 *
 * Copyright (c) 2012, Barnabas Sudy (barnabas.sudy@gmail.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */
package com.moresby.converter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This utility class contains a couple of predefined {@link Converter}s and
 * a couple of method to help the conversion.
 *
 * @author Barnabas Sudy (barnabas.sudy@gmail.com)
 * @since 2012
 */
public final class Converters {

//    /**
//     * <p>Implementation of {@link Converter} which is able to convert a byte array to a hex encoded String.</p>
//     * @see Hex#encodeHexString(byte[])
//     */
//    public static final Converter<byte[], String> BYTEARRAY_TO_HEXSTRING = new Converter<byte[], String>() {
//
//        @Override
//        public String convert(final byte[] from) {
//            return Hex.encodeHexString(from);
//        }
//
//    };

//    /**
//     * <p>Implementation of {@link Converter} which is able to convert a Hex encoded String to a byte array.</p>
//     * @see Hex#decodeHex(char[])
//     */
//    public static final Converter<String, byte[]> HEXSTRING_TO_BYTEARRAY = new Converter<String, byte[]>() {
//
//        @Override
//        public byte[] convert(final String from) throws ConverterException {
//            try {
//                return Hex.decodeHex(from.toCharArray());
//            } catch (final DecoderException e) {
//                throw new ConverterException(e);
//            }
//        }
//    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert an integer to a byte[] containing the byte representation of the integer.</p>
     */
    public static final Converter<Integer, byte[]> INT_TO_BYTE = new Converter<Integer, byte[]>() {

        private static final int LENGTH = 4;

        @Override
        public byte[] convert(final Integer from) throws ConverterException {
            if (from == null) {
                return null;
            }
            final int value = from.intValue();
            final byte[] result = new byte[LENGTH];
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) (value >>> ((result.length - 1 - i) * 8));
            }
            return result;
        }

    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert an long to a byte[] containing the byte representation of the long.</p>
     */
    public static final Converter<Long, byte[]> LONG_TO_BYTE = new Converter<Long, byte[]>() {

        private static final int LENGTH = 8;

        @Override
        public byte[] convert(final Long from) throws ConverterException {
            if (from == null) {
                return null;
            }
            final long value = from.longValue();
            final byte[] result = new byte[LENGTH];
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) (value >>> ((result.length - 1 - i) * 8));
            }
            return result;
        }

    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert an double to a byte[] containing the byte representation of the double.</p>
     */
    public static final Converter<Double, byte[]> DOUBLE_TO_BYTE = new Converter<Double, byte[]>() {

        @Override
        public byte[] convert(final Double from) throws ConverterException {
            if (from == null) {
                return null;
            }
            return LONG_TO_BYTE.convert(Double.doubleToRawLongBits(from.doubleValue()));
        }

    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert an float to a byte[] containing the byte representation of the float.</p>
     */
    public static final Converter<Float, byte[]> FLOAT_TO_BYTE = new Converter<Float, byte[]>() {

        @Override
        public byte[] convert(final Float from) throws ConverterException {
            if (from == null) {
                return null;
            }
            return INT_TO_BYTE.convert(Float.floatToRawIntBits(from.floatValue()));
        }

    };


    /**
     * <p>Implementation of {@link Converter} which is able to convert a String to its byte[] representation in which the content is utf8 encoded.</p>
     */
    public static final Converter<String, byte[]> STRING_TO_UTF8 = new Converter<String, byte[]>() {

        @Override
        public byte[] convert(final String from) throws ConverterException {
            if (from == null) {
                return null;
            }
            try {
                return from.getBytes("UTF-8");
            } catch (final UnsupportedEncodingException e) {
                throw new ConverterException(e); //Should never happen.
            }
        }


    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert a String to Long using the {@link Long#valueOf(String)} method.</p>
     * <p>If the input value is <tt>null</tt> than the converter returns a <tt>null</tt>
     */
    public static final Converter<String, Long> STRING_TO_LONG = new Converter<String, Long>() {

        @Override
        public Long convert(final String from) throws ConverterException {
            if (from == null) {
                return null;
            }
            try {
                return Long.valueOf(from);
            } catch (final NumberFormatException e) {
                throw new ConverterException(e);
            }
        }

    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert a Long to a String.</p>
     * <p>If the input value is <tt>null</tt> than the converter returns a <tt>null</tt>
     */
    public static final Converter<Long, String> LONG_TO_STRING = new Converter<Long, String>() {

        @Override
        public String convert(final Long from) throws ConverterException {
            if (from == null) {
                return null;
            }
            return from.toString();
        }

    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert a String to Integer using the {@link Integer#valueOf(String)} method.</p>
     * <p>If the input value is <tt>null</tt> than the converter returns a <tt>null</tt>
     */
    public static final Converter<String, Integer> STRING_TO_INTEGER = new Converter<String, Integer>() {

        @Override
        public Integer convert(final String from) throws ConverterException {
            if (from == null) {
                return null;
            }
            try {
                return Integer.valueOf(from);
            } catch (final NumberFormatException e) {
                throw new ConverterException(e);
            }
        }

    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert a Long to a String.</p>
     * <p>If the input value is <tt>null</tt> than the converter returns a <tt>null</tt>
     */
    public static final Converter<Integer, String> INTEGER_TO_STRING = new Converter<Integer, String>() {

        @Override
        public String convert(final Integer from) throws ConverterException {
            if (from == null) {
                return null;
            }
            return from.toString();
        }

    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert a String to Double using the {@link Double#valueOf(String)} method.</p>
     * <p>If the input value is <tt>null</tt> than the converter returns a <tt>null</tt>
     */
    public static final Converter<String, Double> STRING_TO_DOUBLE = new Converter<String, Double>() {

        @Override
        public Double convert(final String from) throws ConverterException {
            if (from == null) {
                return null;
            }
            try {
                return Double.valueOf(from);
            } catch (final NumberFormatException e) {
                throw new ConverterException(e);
            }
        }

    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert a String to Float using the {@link Float#valueOf(String)} method.</p>
     * <p>If the input value is <tt>null</tt> than the converter returns a <tt>null</tt>
     */
    public static final Converter<String, Float> STRING_TO_FLOAT = new Converter<String, Float>() {

        @Override
        public Float convert(final String from) throws ConverterException {
            if (from == null) {
                return null;
            }
            try {
                return Float.valueOf(from);
            } catch (final NumberFormatException e) {
                throw new ConverterException(e);
            }
        }

    };

    /**
     * <p>Implementation of {@link Converter} which is able to convert a Long to a String.</p>
     * <p>If the input value is <tt>null</tt> than the converter returns a <tt>null</tt>
     */
    public static final Converter<Number, String> NUMBER_TO_STRING = new Converter<Number, String>() {

        @Override
        public String convert(final Number from) throws ConverterException {
            if (from == null) {
                return null;
            }
            return from.toString();
        }

    };


    /**
     * Trims a string using {@link String#trim()} method.
     */
    public static final Converter<String, String> TRIM = new Converter<String, String>() {

        @Override
        public String convert(final String from) throws ConverterException {
            if (from == null) {
                return null;
            }
            return from.trim();
        }

    };

    /**
     * Converts an array of object by the converter.
     *
     * @param <F> The type of the object which will be converted.
     * @param <T> The type of the object which will be converted to.
     * @param from The array of object to convert.
     * @param converter The converter which converts all the array elements
     * @return The new array.
     */
    public static <F, T> T[] convertArray(final F[] from, final Converter<F, T> converter, final Class<T> clazz) {
        if (from == null) {
            return null;
        }
        final List<T> result = new ArrayList<T>(from.length);
        for (int i = 0; i < from.length; i++) {
            result.add(converter.convert(from[i]));
        }

        Array.newInstance(clazz, from.length);
        @SuppressWarnings("unchecked")
        final T[] resultArray = result.toArray((T[]) Array.newInstance(clazz, from.length) );
        return resultArray;

    }


//    /**
//     * Converts a list of objects. The result will be in an ArrayList.
//     *
//     * TODO create ConvertList object and use that!
//     *
//     * @param <F> The type of the object which will be converted.
//     * @param <T> The type of the object which will be converted to.
//     * @param from The list of the convertable object.
//     * @param converter The converter which converts the elements of the list.
//     * @return The ArrayList of the converted objects.
//     */
//    public static <F, T> ArrayList<T> convertList(final Collection<F> from, final Converter<F, T> converter) {
//        if (from == null) {
//            return null;
//        }
//
//        final ArrayList<T> results = new ArrayList<T>(from.size());
//        for (final F fromEntity : from) {
//            results.add(converter.convert(fromEntity));
//        }
//
//        return results;
//
//    }

    /**
     * Converter which converts a Collection to a sorted ArrayList.
     * @param <T> The type of the collection.
     *
     * @author bsudy
     * @since 2012
     */
    public static class Sorter<T extends Comparable<? super T>> implements Converter<Collection<T>, ArrayList<T>> {

        @Override
        public ArrayList<T> convert(final Collection<T> from) throws ConverterException {
            final ArrayList<T> sortList = new ArrayList<T>(from);
            Collections.sort(sortList);
            return sortList;
        }

    }

    /**
     * Helper method which uses {@link Sorter} to sort a collection.
     * @param from The collection which should be sorted.
     * @return The sorted collection.
     */
    public static <T extends Comparable<? super T>> ArrayList<T> sort(final Collection<T> from) {
        return new Sorter<T>().convert(from);
    }

    /**
     * Sort converter that can be used in chains.
     */
    public static final Converter<Collection<? extends Comparable>, ArrayList<? extends Comparable>> SORT = new Sorter();


    /**
     * <p>One implementation of {@link Converter} interface which is able to convert the elements of a {@link Collection}.
     * The result will be yield into an ArrayList.</p>
     *
     * <p>The implementation needs an other converter which is able to convert the elements.</p>
     *
     * @param <F> The type of the elements of the input {@link Collection}.
     * @param <T> The type of the elements of the output {@link ArrayList}
     */
    public static final class CollectionConverterToArrayList<F, T> implements Converter<Collection<? extends F>, ArrayList<T>> {

        private final Converter<F, T> converter;

        /**
         * @param converter  The converter which converts the elements of the Collection.
         */
        public CollectionConverterToArrayList(final Converter<F, T> converter) {
            this.converter = converter;
        }

        /** {@inheritDoc} */
        @Override
        public ArrayList<T> convert(final Collection<? extends F> from) throws ConverterException {
            if (from == null) {
                return null;
            }

            final ArrayList<T> result = new ArrayList<T>();
            for (final F fromObject : from) {
                result.add(converter.convert(fromObject));
            }
            return result;
        }

    }


    /**
     * Convert the elements of a {@link Collection} an returns the result in a {@link List}.
     * @param converter The converter which converts the elements of the Collection.
     * @param from The collection.
     * @return The List of the converted objects.
     * @throws ConverterException
     */
    public static <F, T> List<T> convertList(final Converter<F, T> converter, final Collection<? extends F> from) throws ConverterException {
        final CollectionConverterToArrayList<F, T> listConverter = new CollectionConverterToArrayList<F, T>(converter);
        return listConverter.convert(from);

    }

    public static class Chain<F, T1, T> implements Converter<F, T> {

        private final Converter<F, T1> c1;
        private final Converter<T1, T> c2;

        public Chain(final Converter<F, T1> c1, final Converter<T1, T> c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        /** {@inheritDoc} */
        @Override
        public T convert(final F from) throws ConverterException {
            return c2.convert(c1.convert(from));
        }

    }

    public static <F, T1, T> Converter<F, T> chain(final Converter<F, T1> c1, final Converter<T1, T> c2) {
        return new Chain<F, T1, T>(c1, c2);
    }

    /** Hidden constructor of utility class. */
    private Converters() { /* NOP */ }

}
