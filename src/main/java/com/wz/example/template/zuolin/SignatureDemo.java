package com.wz.example.template.zuolin;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class SignatureDemo {
    public static void main(String[] args) {
        // 申请一对appKey和相应的secretKey
        String appKey = "82f1adfb-795a-4b43-9b6f-fbfff544db67";
        String secretKey = "aZJ4+MpctY7TVt50MNy+lA/Y5uqK71uTySx++wD1AEBPv38+isoEzS9DN957ew/4OJYZwQPUlSbAcirBkS1qGQ==";

        // 把参数组成一个map，其中appKey也作为参数之一（注意secretKey不是参数）
        Map<String, String> params = new HashMap<>();
//        params.put("appKey", appKey);
//        params.put("nonce", "6613829");
//        params.put("orderField", "纳税额");
//        params.put("yearNames", "2023");
//        params.put("projectNames", "创新大厦");
//        params.put("timestamp", "1706062282182");
//        params.put("reportType", "2023");
//        params.put("topSize", "4");
//        params.put("namespaceId", "999967");
//        params.put("projectIds", "240111044332059932");

        params.put("appKey", appKey);
        params.put("nonce", "9490604");
        params.put("namespaceId", "999967");
        params.put("timestamp", "1706065179894");
        params.put("projectIds", "240111044332059932");
        params.put("specialNewEnterprisesName", "国家级专精特新企业,省级专精特新企业,市级专精特新企业");

        // 使用secretKey产生签名
        String realSignature = computeSignature(params, secretKey);

        // 验证签名是否符合预期
        String expectedSignature = "6z1/1m5U3uDLm4YyoMrWBg1oqWI=";
        System.out.println("Is signature valid: " + (expectedSignature.equals(realSignature)) + ", realSignature=" + realSignature);
    }

    public static String computeSignature(Map<String, String> params, String secretKey) {
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            byte[] rawKey = Base64.decodeBase64(secretKey);

            SecretKeySpec keySpec = new SecretKeySpec(rawKey, "HmacSHA1");
            mac.init(keySpec);

            List<String> keyList = new ArrayList<String>();
            params.forEach((key, value) -> keyList.add(key));
            Collections.sort(keyList);

            for(String key : keyList) {
                mac.update(key.getBytes("UTF-8"));
                String val = params.get(key);
                if(val != null && !val.isEmpty())
                    mac.update(val.getBytes("UTF-8"));
            }

            byte[] encryptedBytes = mac.doFinal();
            String signature = Base64.encodeBase64String(encryptedBytes);

            return signature;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
// 注意：appKey要作为参数之一进行签名，而secretKey不能作为签名参数之一。
