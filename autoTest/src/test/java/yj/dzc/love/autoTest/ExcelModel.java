package yj.dzc.love.autoTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelModel {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			//读取文件到流
			FileInputStream file = new FileInputStream(new File("d:/test.xlsx"));
			//解析文件为Java类
			Workbook wb = new XSSFWorkbook(file);
			//获取第一个Excel表
			Sheet sheet = wb.getSheetAt(0);
			//循环获取行
			for(Row row:sheet) {
				//跳过第一行数据
				if(row.getRowNum()==0) 
					continue;
				//读取格中数据放入SQL语句里
				String sql = "update ldperson a set a.idexpdate= date'" + row.getCell(6).getStringCellValue() +  "' where a.customerno=(select appntno from lcappnt b where b.contno='"+ row.getCell(2).getStringCellValue() + "');";
				System.out.println(sql);
			}
			wb.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
