package com.z.admin.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.z.admin.entity.excel.ExportData;
import com.z.admin.entity.excel.ExportMergeData;
import com.z.admin.util.excel.handler.CustomCellWriteHandler;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zhy
 * @date 2022-10-21
 * @description easyExcel操作工具类
 */
public class EasyExcelUtil {

    /**
     * 常规导出单sheet
     *
     * @param response response
     * @param data     需要导出的数据集合
     * @param clazz    导出数据集合泛型
     * @param fileName 文件名
     */
    public static void exportData(HttpServletResponse response, List<ExportData<?>> data, Class<?> clazz, String fileName) throws Exception {
        // 设置contentType
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");


        EasyExcel.write(response.getOutputStream(), clazz)
                .registerWriteHandler(generateStrategy())
                .registerWriteHandler(new CustomCellWriteHandler())
                .sheet("sheet")
                .doWrite(data);
    }

    /**
     * 常规多sheet导出
     *
     * @param response response
     * @param data     导出数据集合
     * @param fileName 导出文件名称
     */
    public static void exportData(HttpServletResponse response, List<ExportData<?>> data, String fileName) throws Exception {

        // 设置contentType
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .registerWriteHandler(generateStrategy())
                .registerWriteHandler(new CustomCellWriteHandler())
                .build();//字段列宽

        for (int i = 0; i < data.size(); i++) {
            ExportData<?> exportData = data.get(i);
            WriteSheet writeSheet = EasyExcel.writerSheet(i, exportData.getSheetName()).head(exportData.getClazz()).build();
            excelWriter.write(exportData.getDataList(), writeSheet);
        }
        excelWriter.finish();
    }

    /**
     * 导出多sheet，合并单元格
     *
     * @param response response
     * @param data     导出数据集合 (可以通过设置CellWriteHandler指定合并规则)
     * @param fileName 导出文件名称
     */
    public static void exportMergeData(HttpServletResponse response, List<ExportMergeData> data, String fileName) throws Exception {

        // 设置contentType
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .registerWriteHandler(generateStrategy())
                .registerWriteHandler(new CustomCellWriteHandler())
                .build();

        for (int i = 0; i < data.size(); i++) {
            ExportMergeData exportData = data.get(i);
            ExcelWriterSheetBuilder writerSheetBuilder = EasyExcel.writerSheet(i, exportData.getSheetName()).head(exportData.getClazz());
            if (ObjectUtils.isNotEmpty(exportData.getFlag()) && exportData.getFlag()) {
                writerSheetBuilder.registerWriteHandler(exportData.getCellWriteHandler());
            }
            WriteSheet writeSheet = writerSheetBuilder.build();
            excelWriter.write(exportData.getDataList(), writeSheet);
        }
        excelWriter.finish();
    }

    /**
     * 格式设置
     */
    private static HorizontalCellStyleStrategy generateStrategy() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        //设置头字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 13);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        //设置头居中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置 垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //内容字体大小
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontHeightInPoints((short) 12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }
}
