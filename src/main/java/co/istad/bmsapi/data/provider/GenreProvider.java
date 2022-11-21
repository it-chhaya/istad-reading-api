package co.istad.bmsapi.data.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import co.istad.bmsapi.api.genre.Genre;

public class GenreProvider {


    public String buildUpdateWhereIdSql() {
        return new SQL() {{
            UPDATE("genres");
            SET("title = #{genre.title}");
            SET("description = #{genre.description}");
            SET("icon = #{genre.icon.id}");
            WHERE("id = #{genre.id}");
        }}.toString();
    }
    
    public String buildSelectSql() {
        return new SQL() {{
            SELECT("*");
            FROM("genres");
            WHERE("is_enabled = TRUE");
            ORDER_BY("id DESC");
        }}.toString();
    }


    public String buildInsertSql(@Param("genre") Genre genre) {
        return new SQL() {{
            INSERT_INTO("genres");

            if (!genre.getTitle().isBlank()) {
                VALUES("title", "#{genre.title}");
            }
            
            VALUES("description", "#{genre.description}");
            VALUES("icon", "#{genre.icon.id}");
            VALUES("is_enabled", "#{genre.isEnabled}");
        }}.toString();
    }


    public String buildSelectWhereIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM("genres");
            WHERE("id = #{id}", "is_enabled = TRUE");
        }}.toString();
    }


    public String buildSelectGenreIconSql() {
        return new SQL() {{
            SELECT("i.id", "i.uuid", "i.extension", "i.size", "i.is_enabled");
            FROM("images AS i");
            WHERE("i.id = #{id}");
        }}.toString();
    }

}
