package co.istad.bmsapi.data.repository;

import java.util.List;
import java.util.Optional;

import co.istad.bmsapi.api.file.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import co.istad.bmsapi.api.genre.Genre;
import co.istad.bmsapi.data.provider.GenreProvider;

@Repository
public interface GenreRepository {


    @UpdateProvider(type = GenreProvider.class, method = "buildUpdateWhereIdSql")
    void updateWhereId(@Param("genre") Genre genre);

    @SelectProvider(type = GenreProvider.class, method = "buildSelectSql")
    @Results(id = "genreResult", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "is_enabled", property = "isEnabled"),
            @Result(column = "icon", property = "icon", one = @One(select = "selectGenreIcon"))
    })
    List<Genre> select();


    @InsertProvider(type = GenreProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("genre") Genre genre);


    @SelectProvider(type = GenreProvider.class, method = "buildSelectWhereIdSql")
    @ResultMap("genreResult")
    Optional<Genre> selectWhereId(@Param("id") Integer id);


    @Delete("DELETE FROM genres WHERE id = #{id}")
    void deleteWhereId(@Param("id") Integer id);


    @Select("SELECT EXISTS(SELECT * FROM genres WHERE id = #{id})")
    boolean checkWhereId(@Param("id") Integer id);


    int countWhereInId(@Param("ids") List<Integer> ids);


    @SelectProvider(type = GenreProvider.class, method = "buildSelectGenreIconSql")
    File selectGenreIcon(@Param("id") Long id);

}
