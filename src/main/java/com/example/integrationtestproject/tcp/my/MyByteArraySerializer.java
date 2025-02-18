package com.example.integrationtestproject.tcp.my;

import org.springframework.integration.ip.tcp.serializer.AbstractPooledBufferByteArraySerializer;
import org.springframework.integration.ip.tcp.serializer.SoftEndOfStreamException;
import org.springframework.integration.mapping.MessageMappingException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyByteArraySerializer extends AbstractPooledBufferByteArraySerializer {

    public static final MyByteArraySerializer INSTANCE = new MyByteArraySerializer();

    public static final int STX = 0x7E;

    public static final int ETX = 0x7E;

    @Override
    public byte[] doDeserialize(InputStream inputStream, byte[] buffer) throws IOException {
        int bite = inputStream.read();
        if (bite < 0) {
            throw new SoftEndOfStreamException("Stream closed between payloads");
        }
        int n = 0;
        try {
            if (bite != STX) {
                throw new MessageMappingException("Expected STX to begin message");
            }
            while ((bite = inputStream.read()) != ETX) {
                checkClosure(bite);
                buffer[n++] = (byte) bite;
                int maxMessageSize = getMaxMessageSize();
                if (n >= maxMessageSize) {
                    throw new IOException("ETX not found before max message length: " + maxMessageSize);
                }
            }
            return copyToSizedArray(buffer, n);
        }
        catch (IOException | RuntimeException ex) {
            publishEvent(ex, buffer, n);
            throw ex;
        }
    }

    @Override
    public void serialize(byte[] bytes, OutputStream outputStream) throws IOException {
        outputStream.write(STX);
        outputStream.write(bytes);
        outputStream.write(ETX);
    }

}
