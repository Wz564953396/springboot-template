package com.wz.example.template.service.informationCollection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalInfoCollectService {

    private static final String folderPath = "C:\\Users\\Administrator\\Desktop\\personal_terminal_information";
//    private static final String folderPath = "D:";


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
        row1.createCell(0).setCellValue("序号");
        row1.createCell(1).setCellValue("部门");
        row1.createCell(2).setCellValue("员工姓名");
        row1.createCell(3).setCellValue("电脑硬盘序列号");
        row1.createCell(4).setCellValue("电脑MAC地址");
        row1.createCell(5).setCellValue("电脑情况");
        row1.createCell(6).setCellValue("电脑配置");
        row1.createCell(7).setCellValue("预估购买年限");

        int row = 2;
        int num = 1;

        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile() && fileName.endsWith("systeminfo.txt")) {
                System.out.println("文件名：" + fileName);
                System.out.println("文件内容：");

                out(file);

                Row rowOne = sheet.createRow(row++);
                String department = fileName.split("-")[0];
                String name = fileName.split("-")[1];
                rowOne.createCell(0).setCellValue(num++);
                rowOne.createCell(1).setCellValue(department);
                rowOne.createCell(2).setCellValue(name);
                String[] array = getSerialNumberAndSize(file);
                rowOne.createCell(3).setCellValue(array[0]);
                rowOne.createCell(4).setCellValue(getMacAddress(file));

                StringBuilder computerConfig = new StringBuilder();
                computerConfig.append("处理器:" + getCPU(file));
                computerConfig.append("\n内存:" + getMemory(file));
                computerConfig.append("\n硬盘:" + array[1]);
                rowOne.createCell(6).setCellValue(computerConfig.toString());

                System.out.println("--------------------------------------");
            }
        }

        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\电脑摸底表格.xlsx");
        workbook.write(fileOut);
        fileOut.close();

    }

    private static void out(File file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getMacAddress(File file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line;

            boolean isPrepared = false;
            while ((line = br.readLine()) != null) {
                if ("无线局域网适配器 WLAN:".equals(line)) {
                    isPrepared = true;
                }
                if (isPrepared && line.contains("物理地址")) {
                    System.out.println("MAC地址: " + line.split(":")[1].trim());
                    return line.split(":")[1].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String[] getSerialNumberAndSize(File file) {
        StringBuilder serialNumber = new StringBuilder();
        long size = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line;

            boolean isPrepared = false;
            int row = 0;
            int index = 0;
            int index2 = 0;
            while ((line = br.readLine()) != null) {
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
                            size = size + Long.valueOf(line.substring(index2).trim());
                        }
                    } else {
                        break;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("serialNumber: " + serialNumber.toString().trim());
        System.out.println("size: " + size / 1000000000);
        return new String[]{serialNumber.toString().trim(), (size / 1000000000) + "G"};
    }

    private static String getCPU(File file) {
        String cpuInfo = null;
        long size = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line;

            boolean isPrepared = false;
            int index = 0;
            while ((line = br.readLine()) != null) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getMemory(File file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line;

            while ((line = br.readLine()) != null) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
