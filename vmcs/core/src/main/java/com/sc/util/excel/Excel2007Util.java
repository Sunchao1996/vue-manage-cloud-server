package com.sc.util.excel;

import com.sc.util.string.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * excel工具，读取2007文件格式
 */
public class Excel2007Util {

	/**
	 * 读取Excel数据内容
	 * @return List 包含单元格数据内容的Map对象
	 */

	public static List<String[]> readExcelContent(String fileName, int dataIndex) {
		Workbook wb;
		Sheet sheet;
		Row row;
		InputStream is = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			is = new FileInputStream(fileName);
			wb = WorkbookFactory.create(is);
			sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();// 得到总行数
			row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells(); // 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = dataIndex - 1; i <= rowNum + dataIndex - 1; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					String[] values = new String[colNum];
					for (int j = 0; j < colNum; j++) {
						values[j] = getCellFormatValue(row.getCell(j)).trim();
					}
					list.add(values);
				}
			}
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	@SuppressWarnings("deprecation")
	private static String getCellFormatValue(Cell cell) {
		String cellValue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) { // 如果当前Cell的Type为NUMERIC
				case Cell.CELL_TYPE_BLANK:
					cellValue = "";
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						cellValue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
					} else {
						double val = cell.getNumericCellValue();
						if (String.valueOf(val).toUpperCase().indexOf('E') > -1) {
							cellValue = String.valueOf(new DecimalFormat("#.######").format(val));
						} else {
							cellValue = StringUtil.formatNumber(String.valueOf(val));
						}
					}
					break;
				case Cell.CELL_TYPE_STRING:
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = cell.getRichStringCellValue().toString();
					break;
				default:
					cellValue = "";// 默认的Cell值
			}
		}
		return cellValue;
	}


	public static void writeExcel(String[][] data, String srcFile, String file_name, HttpServletResponse response, int titleRows, int rowHeight) {
		OutputStream os = null;
		try {
			// Workbook wb = new SXSSFWorkbook(500);
			InputStream in = new FileInputStream(srcFile);
			Workbook work = new HSSFWorkbook(in);
			Sheet sheet = work.getSheetAt(0);

			// 数据样式
			Font fontData = work.createFont();
			fontData.setFontHeightInPoints((short) 10);
			fontData.setFontName("宋体");
			fontData.setBoldweight((short) 1);

			CellStyle cellDataStyle = work.createCellStyle();
			cellDataStyle.setFont(fontData);
			cellDataStyle.setBorderBottom((short) 1);
			cellDataStyle.setBorderLeft((short) 1);
			cellDataStyle.setBorderRight((short) 1);
			cellDataStyle.setBorderTop((short) 1);
			cellDataStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellDataStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			cellDataStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellDataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellDataStyle.setWrapText(true);

			Font fontDataBold = work.createFont();
			fontDataBold.setFontHeightInPoints((short) 10);
			fontDataBold.setFontName("宋体");
			fontDataBold.setBoldweight(Font.BOLDWEIGHT_BOLD);
			CellStyle cellDataStyleBold = work.createCellStyle();
			cellDataStyleBold.setFont(fontDataBold);
			cellDataStyleBold.setBorderBottom((short) 1);
			cellDataStyleBold.setBorderLeft((short) 1);
			cellDataStyleBold.setBorderRight((short) 1);
			cellDataStyleBold.setBorderTop((short) 1);
			cellDataStyleBold.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellDataStyleBold.setFillForegroundColor(HSSFColor.WHITE.index);
			cellDataStyleBold.setAlignment(CellStyle.ALIGN_CENTER);
			cellDataStyleBold.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellDataStyleBold.setWrapText(true);

			// 得到行，并填充数据和表格样式
			for (int i = 0; i < data.length; i++) {
				// Row row = sheet.createRow(i + 1);// 得到行
				Row row = sheet.createRow(i + titleRows);// 得到行
				// row.setHeight((short) 1000);
				row.setHeight((short) rowHeight);
				for (int j = 0; j < data[i].length; j++) {
					Cell cell = row.createCell(j);// 得到第0个单元格
					cell.setCellValue(data[i][j]);// 填充值
					cell.setCellStyle(cellDataStyle);// 填充样式
				}
			}
			response.setContentType("application/msexcel");
			response.reset();
			response.setHeader("content-disposition", "attachment; filename=" + new String(file_name.getBytes("GBK"), "ISO-8859-1") + ".xls");
			System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.POILogger");
			os = response.getOutputStream();
			work.write(os);
			os.flush();
			work.close();
		} catch (FileNotFoundException e) {
			System.out.println("文件路径错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件输入流错误");
			e.printStackTrace();
		}
	}

}
