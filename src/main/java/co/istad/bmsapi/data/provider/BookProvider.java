package co.istad.bmsapi.data.provider;

import co.istad.bmsapi.api.book.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BookProvider {

    public String buildInsertSql(@Param("book")Book book) {
        return new SQL() {{
            INSERT_INTO("books");
            VALUES("title", "#{book.title}");
            VALUES("description", "#{book.description}");
            VALUES("author", "#{book.author}");
            VALUES("star_rating", "#{book.starRating}");
            VALUES("cover", "#{book.cover.id}");
            VALUES("pdf", "#{book.pdf}");
            VALUES("date_published", "#{book.datePublished}");
            VALUES("is_enabled", "TRUE");
        }}.toString();
    }

    public String buildSelectSql(@Param("book")Book book) {
        return new SQL() {{
            SELECT("*");
            FROM("books");

            if (book != null) {
                WHERE("title ILIKE '%' || #{book.title} || '%'", "is_enabled = TRUE");
                OR();
                WHERE("author ILIKE '%' || #{book.author} || '%'", "is_enabled = TRUE");
                OR();
                WHERE("star_rating = #{book.starRating}", "is_enabled = TRUE");
            }

        }}.toString();
    }


    public String buildSelectByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM("books");
            WHERE("id = #{id}");
        }}.toString();
    }

}
