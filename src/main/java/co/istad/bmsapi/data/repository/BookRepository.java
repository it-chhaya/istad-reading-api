package co.istad.bmsapi.data.repository;

import co.istad.bmsapi.api.book.Book;
import co.istad.bmsapi.data.provider.BookProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {

    @InsertProvider(type = BookProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("book") Book book);


    @SelectProvider(type = BookProvider.class, method = "buildSelectSql")
    @Results(id = "bookResultMap", value = {
            @Result(column = "star_rating", property = "starRating"),
            @Result(column = "date_published", property = "datePublished"),
            @Result(column = "is_enabled", property = "isEnabled")
    })
    List<Book> select(@Param("book") Book book);


    @SelectProvider(type = BookProvider.class, method = "buildSelectByIdSql")
    Optional<Book> selectById(@Param("id") Long id);

}
