/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.io.input;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

import static org.junit.Assert.fail;

/**
 * Test {@link ProxyReader}. 
 *
 * @version $Id$
 */
public class ProxyReaderTest {

    @Test
    public void testNullCharArray() throws Exception {

        final ProxyReader proxy = new ProxyReaderImpl(new CustomNullReader(0));

        try {
            proxy.read((char[])null);
        } catch(final Exception e) {
            fail("Writing null String threw " + e);
        }

        try {
            proxy.read(null, 0, 0);
        } catch(final Exception e) {
            fail("Writing null String threw " + e);
        }
        proxy.close();
    }

    @Test
    public void testNullCharBuffer() throws Exception {

        final ProxyReader proxy = new ProxyReaderImpl(new CustomNullReader(0));

        try {
            proxy.read((CharBuffer)null);
        } catch(final Exception e) {
            fail("Writing null String threw " + e);
        }
        proxy.close();
    }

    /** ProxyReader implementation */
    private static class ProxyReaderImpl extends ProxyReader {
        ProxyReaderImpl(final Reader proxy) {
            super(proxy);
        }
    }

    /** Custom NullReader implementation */
    private static class CustomNullReader extends NullReader {
        CustomNullReader(final int len) {
            super(len);
        }
        @Override
        public int read(final char[] chars) throws IOException {
            return chars == null ? 0 : super.read(chars);
        }
        @Override
        public int read(final CharBuffer target) throws IOException {
            return target == null ? 0 : super.read(target);
        }
    }
}
