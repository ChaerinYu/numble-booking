package com.numble.booking.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : Compress
 * Description : String 압축, 압축 해제 util
 * Gzip 기반으로 압축 및 압축 해제를 수행한다.
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-12 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-12
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Compress {
    public static String compressAndReturnB64(String text) throws IOException {
        return new String(Base64.getEncoder().encode(compress(text)));
    }

    public static String decompressB64(String b64Compressed) throws IOException {
        byte[] decompressedBArray = decompress(Base64.getDecoder().decode(b64Compressed));
        return new String(decompressedBArray, StandardCharsets.UTF_8);
    }

    public static byte[] compress(String text) throws IOException {
        return compress(text.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] compress(byte[] bArray) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (DeflaterOutputStream dos = new DeflaterOutputStream(os)) {
            dos.write(bArray);
        }
        return os.toByteArray();
    }

    public static byte[] decompress(byte[] compressedTxt) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (OutputStream ios = new InflaterOutputStream(os)) {
            ios.write(compressedTxt);
        }
        return os.toByteArray();
    }
}
