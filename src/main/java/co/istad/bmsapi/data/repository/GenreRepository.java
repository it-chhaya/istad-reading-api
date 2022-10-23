package co.istad.bmsapi.data.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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


    @SelectProvider(type = GenreProvider.class, method = "buildSelectWhereIdSql")
    Genre selectWhereId(@Param("id") Integer id);


    @Delete("DELETE FROM genres WHERE id = #{id}")
    void deleteWhereId(@Param("id") Integer id);


    @Select("SELECT EXISTS(SELECT * FROM genres WHERE id = #{id})")
    boolean checkWhereId(@Param("id") Integer id);

}
