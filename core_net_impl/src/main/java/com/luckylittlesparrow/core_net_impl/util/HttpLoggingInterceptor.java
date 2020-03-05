package com.luckylittlesparrow.core_net_impl.util;


import com.orhanobut.logger.Logger;
import okhttp3.*;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Modified version of OkHttp interceptor with {@link Logger} usage
 */
public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public enum Level {
        /**
         * No logs.
         */
        NONE,
        /**
         * Logs request and response lines.
         *
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         *
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         *
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    private volatile Level level = Level.NONE;

    /**
     * Change the level at which this interceptor logs.
     */
    public HttpLoggingInterceptor setLevel(Level level) {
        if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
        this.level = level;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        StringBuilder logMessage = new StringBuilder();
        String logRequestBodyMessage = "";
        String logResponseBodyMessage = "";
        Level level = this.level;

        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        String requestStartMessage = "--> "
                + request.method()
                + ' ' + request.url()
                + (connection != null ? " " + connection.protocol() : "");
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        logMessage.append(requestStartMessage);

        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody.contentType() != null) {
                    logMessage.append("\nContent-Type: ");
                    logMessage.append(requestBody.contentType());
                }
                if (requestBody.contentLength() != -1) {
                    logMessage.append("\nContent-Length: ");
                    logMessage.append(requestBody.contentLength());
                }
            }

            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    logMessage.append("\n");
                    logMessage.append(name);
                    logMessage.append(": ");
                    logMessage.append(headers.value(i));
                }
            }

            if (!logBody || !hasRequestBody) {
                logMessage.append("\n--> END ");
                logMessage.append(request.method());
            } else if (bodyHasUnknownEncoding(request.headers())) {
                logMessage.append("\n--> END ");
                logMessage.append(request.method());
                logMessage.append(" (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (isPlaintext(buffer)) {
                    logRequestBodyMessage = buffer.readString(charset);
                    logMessage.append(logRequestBodyMessage);
                    logMessage.append("\n--> END ");
                    logMessage.append(request.method());
                    logMessage.append(" (");
                    logMessage.append(requestBody.contentLength());
                    logMessage.append("-byte body)");
                } else {
                    logMessage.append("\n--> END ");
                    logMessage.append(request.method());
                    logMessage.append(" (binary ");
                    logMessage.append(requestBody.contentLength());
                    logMessage.append("-byte body omitted)");
                }
            }
        }

        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            logMessage.append("\n<-- HTTP FAILED: ");
            logMessage.append(e);
            Logger.e(logMessage.toString());
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        logMessage.append("\n<-- "
                + response.code()
                + (response.message().isEmpty() ? "" : ' ' + response.message())
                + ' ' + response.request().url()
                + " (" + tookMs + "ms" + (!logHeaders ? ", " + bodySize + " body" : "") + ')');

        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                logMessage.append("\n" );
                logMessage.append(headers.name(i) );
                logMessage.append(": ");
                logMessage.append(headers.value(i));
            }

            if (!logBody || !HttpHeaders.hasBody(response)) {
                logMessage.append("\n<-- END HTTP");
            } else if (bodyHasUnknownEncoding(response.headers())) {
                logMessage.append("\n<-- END HTTP (encoded body omitted)");
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Long gzippedLength = null;
                if ("gzip".equalsIgnoreCase(headers.get("Content-Encoding"))) {
                    gzippedLength = buffer.size();
                    GzipSource gzippedResponseBody = null;
                    try {
                        gzippedResponseBody = new GzipSource(buffer.clone());
                        buffer = new Buffer();
                        buffer.writeAll(gzippedResponseBody);
                    } finally {
                        if (gzippedResponseBody != null) {
                            gzippedResponseBody.close();
                        }
                    }
                }

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (!isPlaintext(buffer)) {
                    logMessage.append("\n");
                    logMessage.append("\n<-- END HTTP (binary ");
                    logMessage.append(buffer.size());
                    logMessage.append("-byte body omitted)");
                    Logger.d(logMessage.toString());
                    return response;
                }

                if (contentLength != 0) {
                    logResponseBodyMessage = buffer.clone().readString(charset);
                }

                if (gzippedLength != null) {
                    logMessage.append("\n<-- END HTTP (");
                    logMessage.append(buffer.size());
                    logMessage.append("-byte, ");
                    logMessage.append(gzippedLength);
                    logMessage.append("-gzipped-byte body)");
                } else {
                    logMessage.append("\n<-- END HTTP (");
                    logMessage.append(buffer.size());
                    logMessage.append("-byte body)");
                }
            }
        }
        Logger.d(logMessage.toString());
        Logger.json(logRequestBodyMessage);
        Logger.json(logResponseBodyMessage);
        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyHasUnknownEncoding(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null
                && !contentEncoding.equalsIgnoreCase("identity")
                && !contentEncoding.equalsIgnoreCase("gzip");
    }
}