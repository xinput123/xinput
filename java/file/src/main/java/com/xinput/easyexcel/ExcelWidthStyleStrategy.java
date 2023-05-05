package com.xinput.easyexcel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * 自动设置列宽
 */
public class ExcelWidthStyleStrategy extends AbstractColumnWidthStyleStrategy {

    // 统计调用了多少次
    private static long count = 0;

    protected void setColumnWidth(WriteSheetHolder writeSheetHolder,
                                  List<WriteCellData<?>> cellDataList,
                                  Cell cell,
                                  Head head,
                                  Integer relativeRowIndex,
                                  Boolean isHead) {
        Sheet sheet = writeSheetHolder.getSheet();
        sheet.setColumnWidth(cell.getColumnIndex(), 3000);
        System.out.println(count++);
    }

}
