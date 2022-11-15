package co.istad.bmsapi.data.repository;

import co.istad.bmsapi.api.book.Book;
import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.genre.Genre;
import co.istad.bmsapi.data.provider.BookProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {

    @UpdateProvider(type = BookProvider.class, method = "buildUpdateCoverByIdSql")
    void updateCoverWhereId(@Param("id") Long id, @Param("coverId") Long coverId);

    @UpdateProvider(type = BookProvider.class, method = "buildUpdateStarRatingSql")
    void updateStarRating(@Param("id") Long id, @Param("starRating") Short starRating);

    @InsertProvider(type = BookProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("book") Book book);

    @UpdateProvider(type = BookProvider.class, method = "buildUpdateSql")
    void update(@Param("book") Book book);


    @SelectProvider(type = BookProvider.class, method = "buildSelectSql")
    @Results(id = "bookResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "star_rating", property = "starRating"),
            @Result(column = "date_published", property = "datePublished"),
            @Result(column = "is_enabled", property = "isEnabled"),
            @Result(column = "cover", property = "cover", one = @One(select = "selectBookCover")),
            @Result(column = "id", property = "genres", many = @Many(select = "selectBookGenresById"))
    })
    List<Book> select(@Param("book") Book book);


    @SelectProvider(type = BookProvider.class, method = "buildSelectByIdSql")
    @ResultMap("bookResultMap")
    Optional<Book> selectById(@Param("id") Long id);


    @Delete("DELETE FROM books WHERE id = #{id}")
    void deleteById(@Param("id") Long id);


    @Select("SELECT EXISTS (SELECT * FROM books WHERE id = #{id})")
    boolean existsById(@Param("id") Long id);


    @InsertProvider(type = BookProvider.class, method = "buildInsertBookGenreSql")
    void insertBookGenre(@Param("bookId") Long bookId, @Param("genreId") Integer genreId);


    @SelectProvider(type = BookProvider.class, method = "buildSelectBookCoverSql")
    @Result(column = "is_enabled", property = "isEnabled")
    File selectBookCover(@Param("id") Long id);


    @SelectProvider(type = BookProvider.class, method = "buildSelectBookGenresByIdSql")
    List<Genre> selectBookGenresById(@Param("bookId") Long id);


    @Delete("DELETE FROM books_genres WHERE book_id = #{bookId}")
    void deleteBookGenresById(@Param("bookId") Long id);
}
