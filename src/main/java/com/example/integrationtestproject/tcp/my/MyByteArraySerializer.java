package com.example.integrationtestproject.tcp.my;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.ip.tcp.serializer.AbstractPooledBufferByteArraySerializer;
import org.springframework.integration.ip.tcp.serializer.SoftEndOfStreamException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class MyByteArraySerializer extends AbstractPooledBufferByteArraySerializer {

    @Override
    public byte[] doDeserialize(InputStream inputStream, byte[] buffer) throws IOException {
        int n = 0;
        try {
            while ((n = inputStream.read(buffer)) > 0) {
                int maxMessageSize = getMaxMessageSize();
                if (n >= maxMessageSize) {
                    throw new IOException("Max message length: " + maxMessageSize);
                }
                return copyToSizedArray(buffer, n);
            }
        }
        catch (IOException | RuntimeException ex) {
            publishEvent(ex, buffer, n);
            throw ex;
        }
        throw new SoftEndOfStreamException("Stream closed between payloads");
    }

    @Override
    public void serialize(byte[] bytes, OutputStream outputStream) throws IOException {
        outputStream.write(bytes);
    }

}
