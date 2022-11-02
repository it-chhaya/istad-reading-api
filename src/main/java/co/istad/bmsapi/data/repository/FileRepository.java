package co.istad.bmsapi.data.repository;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.data.provider.FileProvider;

public interface FileRepository {
    
    @InsertProvider(type = FileProvider.class, method = "buildInsertFileSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("file") File file);

}
