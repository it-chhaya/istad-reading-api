package co.istad.bmsapi.data.repository;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import co.istad.bmsapi.api.book.Book;
import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.genre.Genre;
import co.istad.bmsapi.data.provider.BookProvider;

@Repository
public interface BookRepository {
    
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


    @SelectProvider(type = BookProvider.class, method = "buildSelectBookGenresSql")
    List<Genre> selectBookGenres(@Param("bookId") Long bookId);


    @SelectProvider(type = BookProvider.class, method = "buildSelectBookCoverSql")
    File selectBookCover(@Param("id") Long id);

}
