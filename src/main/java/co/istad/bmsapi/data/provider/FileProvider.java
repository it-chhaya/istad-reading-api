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

}
