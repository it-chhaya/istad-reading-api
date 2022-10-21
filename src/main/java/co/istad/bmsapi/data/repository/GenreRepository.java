package co.istad.bmsapi.data.repository;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import co.istad.bmsapi.api.genre.Genre;
import co.istad.bmsapi.data.provider.GenreProvider;

@Repository
public interface GenreRepository {
    
    @SelectProvider(type = GenreProvider.class, method = "buildSelectSql")
    @Results(id = "genreResult", value = {
        @Result(column = "is_enabled", property = "isEnabled")
    })
    List<Genre> select();
    

    @InsertProvider(type = GenreProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("genre") Genre genre);


    @SelectProvider(type = GenreProvider.class, method = "buildSelectByIdSql")
    @ResultMap(value = "genreResult")
    Genre selectById(@Param("id") Integer id);
}
