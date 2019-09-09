package cn.blocks.commonutils.utils;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @description
 *         excel utils
 *
 * @author Somnus丶y
 * @date 2019/9/9
 */
public class ExcelUtils {


    /**
     * @description
     *          excel导出
     *
     * @param response, data, titleList
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/9
     */
    public static void exportExcel(HttpServletResponse response,List<String[]> data, List<String> titleList) {
        try {
            //实例化HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建一个Excel表单，参数为sheet的名字
            HSSFSheet sheet = workbook.createSheet("Data");
            //设置表头
            setTitle(workbook, sheet, titleList);
            //设置单元格并赋值
            setData(sheet, data);
            //设置浏览器下载
            setBrowser(response, workbook, String.valueOf(System.currentTimeMillis()));
        } catch (Exception e) {
            LogUtils.error("导出excel报错，错误为：", e);
        }
    }

    /**
     * @description
     *          setTitle
     *
     * @param workbook, sheet, titleList
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/9
     */
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, List<String> titleList) {
        try {
            HSSFRow row = sheet.createRow(0);
            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i <= titleList.size(); i++) {
                sheet.setColumnWidth(i, 15 * 256);
            }
            //设置为居中加粗,格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-mm-dd hh:MM:dd"));
            //创建表头名称
            HSSFCell cell;
            for (int j = 0; j < titleList.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(titleList.get(j));
                cell.setCellStyle(style);
            }
        } catch (Exception e) {
            LogUtils.error("设置头报错，错误为：", e);
        }
    }



    /**
     * @description
     *          setData
     *
     * @param sheet, data
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/9
     */
    private static void setData(HSSFSheet sheet, List<String[]> data) {
        try{
            int rowNum = 1;
            for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow(rowNum);
                for (int j = 0; j < data.get(i).length; j++) {
                    row.createCell(j).setCellValue(data.get(i)[j]);
                }
                rowNum++;
            }

        }catch (Exception e){
            LogUtils.error("导出数据赋值失败，异常为：", e);
        }
    }



    /**
     * @description
     *          setBrowser 浏览器下载
     *
     * @param response, workbook, fileName
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/9
     */
    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        try {
            //清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            LogUtils.error("浏览器下载失败，错误为：", e);
        }

    }


}
