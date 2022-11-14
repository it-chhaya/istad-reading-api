package co.istad.bmsapi.data.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import co.istad.bmsapi.api.book.Book;
import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.genre.Genre;
import co.istad.bmsapi.data.provider.BookProvider;

@Repository
public interface BookRepository {

    @Delete("DELETE FROM books_genres WHERE book_id = #{bookId}")
    void deleteBookGenre(@Param("bookId") Long bookId);

    @UpdateProvider(type = BookProvider.class, method = "buildUpdateSql")
    void update(@Param("book") Book book);

    @InsertProvider(type = BookProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("book") Book book);


    @InsertProvider(type = BookProvider.class, method = "buildInsertBookGenreSql")
    void insertBookGenre(@Param("bookId") Long bookId, @Param("genreId") Integer genreId);
    

    @SelectProvider(type = BookProvider.class, method = "buildSelectSql")
    @Results(id = "bookResultMap", value = {
        @Result(column = "id", property = "id"),
        @Result(column = "star_rating", property = "starRating"),
        @Result(column = "date_published", property = "datePublished"),
        @Result(column = "is_enabled", property = "isEnabled"),
        @Result(column = "cover", property = "cover", one = @One(select = "selectBookCover")),
        @Result(column = "id", property = "genres", many = @Many(select = "selectBookGenres"))
    })
    List<Book> select();


    @Select("SELECT * FROM books WHERE id = #{id}")
    @ResultMap("bookResultMap")
    Book selectById(@Param("id") Long id);


    @SelectProvider(type = BookProvider.class, method = "buildSelectBookGenresSql")
    List<Genre> selectBookGenres(@Param("bookId") Long bookId);


    @SelectProvider(type = BookProvider.class, method = "buildSelectBookCoverSql")
    File selectBookCover(@Param("id") Long id);

}
