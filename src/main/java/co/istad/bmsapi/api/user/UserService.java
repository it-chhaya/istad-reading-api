package co.istad.bmsapi.api.user;

import java.util.List;

import co.istad.bmsapi.api.user.web.UserDto;

public interface UserService {


    /**
     * Get user information from database by username or email
     * @param usernameOrEmail can be username or email which is the unique identifier of user
     * @return UserDto
     */
    UserDto getUserByUsernameOrEmail(String usernameOrEmail);



    /**
     * Get all user from database
     * @return List<UserDto>
     */
    List<UserDto> getAllUsers();



    /**
     * Get user by ID
     * @param id is the unique identifier of user
     * @return UserDto
     */
    UserDto getUserById(Long id);



    /**
     * Check user by email in database
     * @param email is the unique identifier of user
     * @return true/false
     */
    boolean checkUserEmail(String email);



    /**
     * Check user by username in database
     * @param username is the unique identifier of user
     * @return true/false
     */
    boolean checkUsername(String username);



    /**
     * Enable or Disable user by ID
     * @param id is the unique identifier of user
     * @param isEnabled is the status will be updating
     */
    UserDto enableAndDisableUser(Long id, Boolean isEnabled);



    /**
     * Delete user permanently from database
     * @param id is the unique identifier of user
     */
    UserDto deleteUserById(Long id);

}
