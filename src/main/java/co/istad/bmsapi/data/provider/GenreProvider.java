package co.istad.bmsapi.data.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import co.istad.bmsapi.api.genre.Genre;

public class GenreProvider {
    
    public String buildSelectSql() {
        return new SQL() {{
            SELECT("*");
            FROM("genres");
            WHERE("is_enabled = TRUE");
        }}.toString();
        // select * from genres where is_enabled = true
    }


    public String buildInsertSql(@Param("genre") Genre genre) {
        return new SQL() {{
            // YOUR SQL
            INSERT_INTO("genres");

            if (!genre.getTitle().isBlank()) {
                VALUES("title", "#{genre.title}");
            }
            
            VALUES("description", "#{genre.description}");
            VALUES("icon", "#{genre.icon}");
            VALUES("is_enabled", "#{genre.isEnabled}");
        }}.toString();
    }


    public String buildSelectByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM("genres");
            WHERE("id = #{id}");
        }}.toString();
    }

}
