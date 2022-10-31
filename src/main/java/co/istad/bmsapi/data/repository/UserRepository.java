package co.istad.bmsapi.data.repository;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import co.istad.bmsapi.api.user.Role;
import co.istad.bmsapi.api.user.User;
import co.istad.bmsapi.data.provider.UserProvider;

@Repository
public interface UserRepository {

    @Select("SELECT * FROM users WHERE is_enabled = TRUE")
    @ResultMap("userResultMap")
    List<User> select();
    
    @SelectProvider(type = UserProvider.class, method = "buildSelectByUsernameSql")
    @Results(id = "userResultMap", value = {
        @Result(column = "id", property = "id"),
        @Result(column = "family_name", property = "familyName"),
        @Result(column = "given_name", property = "givenName"),
        @Result(column = "phone_number", property = "phoneNumber"),
        @Result(column = "is_enabled", property = "isEnabled"),
        @Result(column = "id", property = "roles", many = @Many(select = "selectUserRoles"))
    })
    User selectByUsername(@Param("username") String username);


    @SelectProvider(type = UserProvider.class, method = "buildSelectUserRolesSql")
    List<Role> selectUserRoles(@Param("id") Integer id);

}
