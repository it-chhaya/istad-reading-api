package co.istad.bmsapi.data.provider;

import org.apache.ibatis.jdbc.SQL;

public class FileProvider {
    
    public String buildInsertSql() {
        return new SQL() {{
            INSERT_INTO("images");
            VALUES("uuid", "#{file.uuid}");
            VALUES("extension", "#{file.extension}");
            VALUES("size", "#{file.size}");
            VALUES("is_enabled", "#{file.isEnabled}");
        }}.toString();
    }

}
