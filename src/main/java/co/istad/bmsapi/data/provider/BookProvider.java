package co.istad.bmsapi.data.provider;

import org.apache.ibatis.jdbc.SQL;

public class BookProvider {

    public String buildUpdateSql() {
        return new SQL() {{
            UPDATE("books");
            SET("title = #{book.title}");
            SET("description = #{book.description}");
            SET("author = #{book.author}");
            SET("date_published = #{book.datePublished}");
            SET("cover = #{book.cover.id}");
            SET("pdf = #{book.pdf}");
            SET("is_enabled = #{book.isEnabled}");
            WHERE("id = #{book.id}");
        }}.toString();
    }

    public String buildInsertSql() {
        return new SQL() {{
            INSERT_INTO("books");
            VALUES("title", "#{book.title}");
            VALUES("description", "#{book.description}");
            VALUES("author", "#{book.author}");
            VALUES("star_rating", "#{book.starRating}");
            VALUES("date_published", "#{book.datePublished}");
            VALUES("cover", "#{book.cover.id}");
            VALUES("pdf", "#{book.pdf}");
            VALUES("is_enabled", "#{book.isEnabled}");
        }}.toString();
    }


    public String buildInsertBookGenreSql() {
        return new SQL() {{
            INSERT_INTO("books_genres");
            VALUES("book_id", "#{bookId}");
            VALUES("genre_id", "#{genreId}");
        }}.toString();
    }

    
    public String buildSelectSql() {
        return new SQL() {{
            SELECT("*");
            FROM("books");
            WHERE("is_enabled = TRUE");
            ORDER_BY("id DESC");
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
