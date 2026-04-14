package bo;

import static framework.properties.UserPropertyConst.USER_LOGIN;
import static framework.properties.UserPropertyConst.USER_PASSWORD;

import framework.properties.UserProperty;

public class UserFactory {

  public static User asUserWithSimplePermission() {
    return new User(UserProperty.getValueOf(USER_LOGIN),
        UserProperty.getValueOf(USER_PASSWORD));
  }

}
