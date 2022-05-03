package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.util;

public class UserValidation
{
  public static boolean isValidUser(String email, String password)
  {
    return !email.isEmpty() && email
        .matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        && !password.isEmpty() && password.length() < 6;
  }
}
