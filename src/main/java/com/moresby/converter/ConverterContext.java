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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO javadoc.
 *
 * @author Barnabas Sudy (barnabas.sudy@gmail.com)
 * @since 2012
 */
public class ConverterContext {

    /** The default priority of the converters. */
    private static final int DEFAULT_PRIORITY = 0;

    public enum Routing {
        AUTO,
        BRUTE_FORCE,
        NONE
    }

    public enum Search {
        DEPTH_FIRST,
        BREADTH_FIRST
    }

    private final Map<Class<?>, Map<Class<?>, List<Converter<?, ?>>>> converters = new HashMap<Class<?>, Map<Class<?>, List<Converter<?, ?>>>>();

    public <F, T> void registerConverter(final Converter<F, T> converter) {
        registerConverter(converter, DEFAULT_PRIORITY);
    }

    public <F, T> void registerConverter(final Converter<F, T> converter, final int prirority) {
        final Type[] genericInterfaces = converter.getClass().getGenericInterfaces();
        for (final Type genericInterface : genericInterfaces) {
            System.out.println("GenericInterface: " + genericInterface);

            for (final Type argument : ((ParameterizedType)genericInterface).getActualTypeArguments()) {
                System.out.println("argument: " + argument);
            }
        }
    }

    public <F, T> T convert(final F from) {
        return null;
    }
}
