package JavaApiManageFile.helpers;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZipConversion {
    public static byte[] compress(byte[] file) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(file);
        deflater.finish();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(file.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            byteArrayOutputStream.write(tmp, 0, size);
        }
        try {
            byteArrayOutputStream.close();
        } catch (Exception ignored) {
        }
        return byteArrayOutputStream.toByteArray();
    }
    public static byte[] decompress(byte[] file) {
        Inflater inflater = new Inflater();
        inflater.setInput(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(file.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                byteArrayOutputStream.write(tmp, 0, count);
            }
            byteArrayOutputStream.close();
        } catch (Exception ignored) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
