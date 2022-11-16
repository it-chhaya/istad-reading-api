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

    @UpdateProvider(type = UserProvider.class, method = "buildUpdatePasswordWhereIdSql")
    void updatePasswordWhereId(@Param("id") Long id, @Param("encodedPassword") String encodedPassword);

    @InsertProvider(type = UserProvider.class, method = "buildInsertUserRoleSql")
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Integer roleId);

    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(@Param("user") User user);

    @Select("SELECT EXISTS(SELECT * FROM users WHERE email = #{email})")
    boolean existsWhereEmail(@Param("email") String email);

    @Select("SELECT EXISTS(SELECT * FROM users WHERE username = #{username})")
    boolean existsWhereUsername(@Param("username") String username);

    @Select("SELECT * FROM users WHERE is_enabled = TRUE")
    @ResultMap("userResultMap")
    List<User> select();


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
    Optional<User> selectByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);


    @SelectProvider(type = UserProvider.class, method = "buildSelectUserProfileSql")
    @Result(column = "is_enabled", property = "isEnabled")
    File selectUserProfile(@Param("id") Integer id);


    @SelectProvider(type = UserProvider.class, method = "buildSelectUserRolesSql")
    List<Role> selectUserRoles(@Param("id") Integer id);

}
