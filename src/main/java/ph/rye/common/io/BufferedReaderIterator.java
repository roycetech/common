/**
 *   Copyright 2016 Royce Remulla
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ph.rye.common.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.BiConsumer;

/**
 * @author royce
 *
 */
public class BufferedReaderIterator {


    private final transient BufferedReader reader;
    private final transient BiConsumer<Integer, String> body;

    private transient int index;


    public BufferedReaderIterator(final BufferedReader reader,
            final BiConsumer<Integer, String> body) {

        this.reader = reader;
        this.body = body;
        index = 0;
    }

    public void eachLine() {
        try {
            String line = reader.readLine();
            while (line != null) {
                body.accept(index, line);
                line = reader.readLine();
                index++;
            }

        } catch (final IOException e) {
            throw new ReaderException(e);
        }

    }

}

class ReaderException extends RuntimeException {
    /** */
    private static final long serialVersionUID = -257710506179020957L;

    public ReaderException(final Throwable throwable) {
        super(throwable);
    }
}
