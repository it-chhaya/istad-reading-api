package co.istad.bmsapi.data.repository;

import org.apache.ibatis.annotations.*;

import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.data.provider.FileProvider;

import java.util.List;
import java.util.Optional;

public interface FileRepository {
    
    @InsertProvider(type = FileProvider.class, method = "buildInsertFileSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("file") File file);


    @SelectProvider(type = FileProvider.class, method = "buildSelectFileSql")
    @Results(id = "fileResultMap", value = {
            @Result(column = "is_enabled", property = "isEnabled")
    })
    List<File> select();


    @SelectProvider(type = FileProvider.class, method = "buildSelectFileByIDSql")
    @ResultMap("fileResultMap")
    Optional<File> selectByID(@Param("id") Long id);


    @SelectProvider(type = FileProvider.class, method = "buildSelectFileByUUIDSql")
    @ResultMap("fileResultMap")
    Optional<File> selectByUUID(@Param("uuid") String uuid);


    @DeleteProvider(type = FileProvider.class, method = "buildDeleteByUUIDSql")
    void deleteByUUID(@Param("uuid") String uuid);


    @Select("SELECT EXISTS(SELECT * FROM images WHERE id = #{id})")
    boolean existsFileID(@Param("id") Long id);

}
