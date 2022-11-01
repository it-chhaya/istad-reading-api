package co.istad.bmsapi.data.provider;

import co.istad.bmsapi.api.book.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BookProvider {

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
