package com.z.admin.entity.excel;

import com.alibaba.excel.write.handler.CellWriteHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExportMergeData<T> extends ExportData<T>{

    private Boolean flag;

    private CellWriteHandler cellWriteHandler;

}
