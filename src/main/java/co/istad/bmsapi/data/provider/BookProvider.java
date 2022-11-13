package co.istad.bmsapi.data.provider;

import org.apache.ibatis.jdbc.SQL;

public class BookProvider {
    
    public String buildSelectSql() {
        return new SQL() {{
            SELECT("*");
            FROM("books");
            WHERE("is_enabled = TRUE");
        }}.toString();
    }

    public String buildSelectBookGenresSql() {
        return new SQL() {{
            SELECT("g.id, g.title, g.description, g.icon, g.is_enabled");
            FROM("genres AS g");
            INNER_JOIN("books_genres AS bg ON bg.genre_id = g.id");
            WHERE("bg.book_id = #{bookId}", "is_enabled = TRUE");
        }}.toString();
    }

    public String buildSelectBookCoverSql() {
        return new SQL() {{
            SELECT("i.id, i.uuid, i.extension, i.size, i.is_enabled");
            FROM("images AS i");
            WHERE("i.id = #{id}");
        }}.toString();
    }

}
