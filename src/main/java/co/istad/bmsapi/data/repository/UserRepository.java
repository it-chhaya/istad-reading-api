package co.istad.bmsapi.data.repository;

import java.util.List;
import java.util.Optional;

import co.istad.bmsapi.api.file.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import co.istad.bmsapi.api.user.Role;
import co.istad.bmsapi.api.user.User;
import co.istad.bmsapi.data.provider.UserProvider;

@Repository
public interface UserRepository {


    @Select("SELECT * FROM users WHERE email = #{email} AND verification_code = #{verificationCode}")
    @ResultMap("userResultMap")
    Optional<User> selectWhereEmailAndVerificationCode(@Param("email") String email, @Param("verificationCode") String verificationCode);

    @Update("UPDATE users SET verification_code = #{verificationCode} WHERE id = #{id}")
    void updateVerificationCodeWhereId(@Param("id") Long id, @Param("verificationCode") String verificationCode);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteWhereId(@Param("id") Long id);

    @Update("UPDATE users SET is_enabled = #{isEnabled} WHERE id = #{id}")
    void updateIsEnabledWhereId(@Param("id") Long id, @Param("isEnabled") Boolean isEnabled);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdatePasswordWhereIdSql")
    void updatePasswordWhereId(@Param("id") Long id, @Param("encodedPassword") String encodedPassword);

    @InsertProvider(type = UserProvider.class, method = "buildInsertUserRoleSql")
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Integer roleId);

    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(@Param("user") User user);

    @Select("SELECT EXISTS(SELECT * FROM users WHERE email = #{email})")
    boolean existsWhereEmail(@Param("templates/email") String email);

    @Select("SELECT EXISTS(SELECT * FROM users WHERE username = #{username})")
    boolean existsWhereUsername(@Param("username") String username);

    @Select("SELECT * FROM users WHERE is_enabled = TRUE")
    @ResultMap("userResultMap")
    List<User> select();

    @Select("SELECT * FROM users WHERE id = #{id} AND is_enabled = TRUE")
    @ResultMap("userResultMap")
    Optional<User> selectWhereId(@Param("id") Long id);

    @SelectProvider(type = UserProvider.class, method = "buildSelectByUsernameOrEmailSql")
    @Results(id = "userResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "family_name", property = "familyName"),
            @Result(column = "given_name", property = "givenName"),
            @Result(column = "phone_number", property = "phoneNumber"),
            @Result(column = "is_enabled", property = "isEnabled"),
            @Result(column = "profile", property = "profile", one = @One(select = "selectUserProfile")),
            @Result(column = "id", property = "roles", many = @Many(select = "selectUserRoles"))
    })
    Optional<User> selectWhereUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail, @Param("isEnabled") Boolean isEnabled);

    @SelectProvider(type = UserProvider.class, method = "buildSelectUserProfileSql")
    @Result(column = "is_enabled", property = "isEnabled")
    File selectUserProfile(@Param("id") Integer id);

    @SelectProvider(type = UserProvider.class, method = "buildSelectUserRolesSql")
    List<Role> selectUserRoles(@Param("id") Integer id);

}
