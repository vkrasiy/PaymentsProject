package org.payments.util.impl;

import com.google.common.hash.HashCode;
import org.payments.util.SignatureMaker;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SignManager implements SignatureMaker {
    private final String algorithm = "HmacMD5";
    private final String key = "key";

    public String getSignature(String data) {
        String signature = null;
        try {
            SecretKey MD5_KEY = new SecretKeySpec(key.getBytes(Charset.forName("UTF-8")), algorithm);
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(MD5_KEY);
            mac.update(data.getBytes(UTF_8));
            signature = HashCode.fromBytes(mac.doFinal()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }
}
