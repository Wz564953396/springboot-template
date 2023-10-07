package com.wz.example.template.service.informationCollection;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadHugeLog {

    private static final String folderPath = "C:\\Users\\Administrator\\Desktop\\1-1694592338585-content-cloud-exe_2";

    public static void main(String[] args) throws IOException {

        File file = new File(folderPath); // 创建文件夹对象

        String fileName = file.getName();
        if (file.isFile()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String line;
                BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\error.log"));

                List<String> list = new ArrayList<>();
                while ((line = br.readLine()) != null) {
//                    if (line.contains("error") || line.contains("ERROR")) {
//                        System.out.println(line);
                        list.add(line);
//                        bw.write(line);
//                        bw.newLine();
//                    }
                }
                bw.close();

                Map<String, Integer> frequencyMap = new HashMap<>();
                for (String str : list) {
                    frequencyMap.put(str, frequencyMap.getOrDefault(str, 0) + 1);
                }
                list.sort((s1, s2) -> {
                    int freq1 = frequencyMap.get(s1);
                    int freq2 = frequencyMap.get(s2);
                    if (freq1 == freq2) {
                        return s1.compareTo(s2); // 如果出现次数相同，按照字母顺序排序
                    } else {
                        return freq2 - freq1; // 按照出现次数降序排序
                    }
                });

                for (String str : list) {
                    System.out.println(str);
                }

                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("--------------------------------------");
        }
    }

}
