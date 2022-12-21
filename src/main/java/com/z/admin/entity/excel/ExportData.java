package com.z.admin.entity.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportData<T> {

    private String sheetName;

    private Class<T> clazz;

    private List<T> dataList;
}
