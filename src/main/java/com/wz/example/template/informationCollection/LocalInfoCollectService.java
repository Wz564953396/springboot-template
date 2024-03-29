package com.wz.example.template.informationCollection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalInfoCollectService {

    private static final String folderPath = "C:\\Users\\Administrator\\Desktop\\personal_terminal_information";
//    private static final String folderPath = "D:";

    private static final String[] titles = {"部门", "姓名", "厂商型号", "电脑序列号", "硬盘序列号", "CPU信息", "物理内存", "硬盘大小", "MAC地址", "资产状态"};


    public static void main(String[] args) throws IOException {

        File folder = new File(folderPath); // 创建文件夹对象
        File[] files = folder.listFiles(); // 获取文件夹中的所有文件

        if (files.length == 0) {
            System.out.println("没有读取到文件");
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Row row0 = sheet.createRow(0);
        Cell cell = row0.createCell(0);
        cell.setCellValue("电脑摸底情况");

        Row row1 = sheet.createRow(1);
        for (int i = 0; i < titles.length; i++) {
            row1.createCell(i).setCellValue(titles[i]);
        }

        int row = 2;
        int num = 1;

        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile() && fileName.endsWith("systeminfo.txt")) {
                System.out.println("文件名：" + fileName);
                System.out.println("文件内容：");

                List<String> content = out(file);

                Row rowOne = sheet.createRow(row++);
                String department = fileName.split("-")[0];
                String name = fileName.split("-")[1];
//                rowOne.createCell(0).setCellValue(num++);
                rowOne.createCell(0).setCellValue(department);
                rowOne.createCell(1).setCellValue(name);

//                厂商型号和电脑序列号
                String[] array2 = getComputerSerialNumberAndVersion(content);
                rowOne.createCell(2).setCellValue(array2[1]);
                rowOne.createCell(3).setCellValue(array2[0]);

//                硬盘序列号和大小
                String[] array = getHardDiskSerialNumberAndSize(content);
                rowOne.createCell(4).setCellValue(array[0]);
                rowOne.createCell(7).setCellValue(array[1]);
//                CPU信息
                rowOne.createCell(5).setCellValue(getCPU(content));
//                物理内存
                rowOne.createCell(6).setCellValue(getMemory(content));
//                MAC地址
                rowOne.createCell(8).setCellValue(getMacAddress(content));

                System.out.println("--------------------------------------");
            }
        }

        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\电脑摸底表格.xlsx");
        workbook.write(fileOut);
        fileOut.close();

    }

    private static List<String> out(File file) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String[] getComputerSerialNumberAndVersion(List<String> content) {

        String serialNumber = null;
        String version = null;
        boolean isPrepared = false;
        int index = 0;
        try {
            for (String line : content) {
                line = line.replace("\u0000", "");

                if (!isPrepared && StringUtils.hasLength(line) && line.contains("SerialNumber")
                        && line.contains("Version")) {
                    isPrepared = true;
                    index = line.indexOf("Version");
                    continue;
                }

                if (isPrepared && StringUtils.hasLength(line)) {
                    serialNumber = line.substring(0, index).trim();
                    version = line.substring(index, line.length()).split("-")[0].trim();
                    System.out.println("serialNumber: " + serialNumber);
                    System.out.println("version: " + version);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[]{serialNumber, version};
    }

    private static String getMacAddress(List<String> content) {
        StringBuilder sb = new StringBuilder();
        try {
            boolean isPrepared = false;
            String name = null;
            for (String line : content) {
                if ("无线局域网适配器 WLAN:".equals(line) || line.contains("无线局域网适配器 本地连接") || line.contains("以太网适配器 以太网")) {
                    name = line;
                    isPrepared = true;
                    continue;
                }
                if (isPrepared && line.contains("描述") && line.contains("Virtual")) {
                    isPrepared = false;
                    continue;
                }
                if (isPrepared && line.contains("物理地址")) {
                    System.out.println("MAC地址: " + line.split(":")[1].trim());
                    sb.append(name + line.split(":")[1].trim() + "\n");
                    isPrepared = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString().trim();
    }

    /**
     * 硬盘序列号和大小
     * @param content
     * @return
     */
    private static String[] getHardDiskSerialNumberAndSize(List<String> content) {
        StringBuilder serialNumber = new StringBuilder();
        StringBuilder size = new StringBuilder();
        boolean isPrepared = false;
        int row = 0;
        int index = 0;
        int index2 = 0;
        try {
            for (String line : content) {
                line = line.replace("\u0000", "");

                if (!isPrepared && StringUtils.hasLength(line) && line.contains("Index")
                        && line.contains("InterfaceType")
                        && line.contains("Model")
                        && line.contains("SerialNumber")
                        && line.contains("Size")) {
                    index = line.indexOf("SerialNumber");
                    index2 = line.indexOf("Size");
                    isPrepared = true;
                    continue;
                }

                if (isPrepared && StringUtils.hasLength(line)) {
                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        if (Integer.valueOf(matcher.group()).equals(row++)) {
                            serialNumber.append("\n" + line.substring(index).split(" ")[0]);
                            size.append(Long.valueOf(line.substring(index2).trim()) / 1000000000 + "G\n");
                        }
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("serialNumber: " + serialNumber.toString().trim());
        System.out.println("size: " + size.toString().trim());
        return new String[]{serialNumber.toString().trim(), size.toString()};
    }

    private static String getCPU(List<String> content) {
        String cpuInfo = null;
        boolean isPrepared = false;
        int index = 0;
        try {
            for (String line : content) {
                line = line.replace("\u0000", "");

                if (!isPrepared && StringUtils.hasLength(line) && line.contains("Name")
                        && line.contains("ProcessorId")) {
                    isPrepared = true;
                    index = line.indexOf("ProcessorId");
                    continue;
                }

                if (isPrepared && StringUtils.hasLength(line)) {
                    cpuInfo = line.substring(0, index).trim();
                    System.out.println("spuInfo: " + cpuInfo);
                    return cpuInfo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getMemory(List<String> content) {
        try {
            for (String line : content) {
                if (line.contains("物理内存总量:")) {
                    String str = line.split(":")[1].trim();
                    System.out.println("内存: " + str);

                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(str);
                    StringBuilder sb = new StringBuilder();
                    while (matcher.find()) {
                        sb.append(matcher.group());
                    }
                    return Integer.valueOf(sb.toString()) / 1000 + "GB";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
