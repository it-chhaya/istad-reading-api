package co.istad.bmsapi.data.provider;

import org.apache.ibatis.jdbc.SQL;

public class FileProvider {
    
    public String buildInsertFileSql() {
        return new SQL() {{
            INSERT_INTO("images");
            VALUES("uuid", "#{file.uuid}");
            VALUES("size", "#{file.size}");
            VALUES("extension", "#{file.extension}");
            VALUES("is_enabled", "#{file.isEnabled}");
        }}.toString();
    }


    public String buildSelectFileSql() {
        return new SQL() {{
            SELECT("*");
            FROM("images");
            WHERE("is_enabled = TRUE");
            ORDER_BY("id DESC");
        }}.toString();
    }


    public String buildSelectFileByIDSql() {
        return new SQL() {{
            SELECT("*");
            FROM("images");
            WHERE("id = #{id}", "is_enabled = TRUE");
        }}.toString();
    }


    public String buildSelectFileByUUIDSql() {
        return new SQL() {{
            SELECT("*");
            FROM("images");
            WHERE("uuid = #{uuid}", "is_enabled = TRUE");
        }}.toString();
    }


    public String buildDeleteByUUIDSql() {
        return new SQL() {{
            DELETE_FROM("images");
            WHERE("uuid = #{uuid}");
        }}.toString();
    }

}
