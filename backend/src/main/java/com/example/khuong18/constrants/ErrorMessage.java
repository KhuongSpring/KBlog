package com.example.khuong18.constrants;

public class ErrorMessage {
    public static class User {
        public static final String ERR_USERNAME_EXISTED = "Username existed!";
        public static final String ERR_EMAIL_EXISTED = "Email existed!";
        public static final String ERR_USER_NOT_FOUND = "User not found!";
        public static final String ERR_ID_NOT_VALID = "Id not valid!";
        public static final String ERR_EMAIL_NOT_VALID = "Email not valid!";
        public static final String ERR_USERNAME_NOT_VALID = "Username not valid!";
        public static final String ERR_PASSWORD_NOT_VALID = "Password not valid!";
        public static final String ERR_FULL_NAME_NOT_VALID = "Full name not valid!";
        public static final String ERR_BIO_NOT_VALID = "Bio not valid!";
    }

    public static class Auth {
        public static final String ERR_LOGIN_FAIL = "Username or password wrong!";
    }

    public static class Post {
        public static final String ERR_CONTENT_EMPTY = "Content must not empty!";
        public static final String ERR_TYPE_NOT_VALID = "Post type not valid!";
        public static final String ERR_CREATE_TIME_NOT_VALID = "Create time not valid!";
        public static final String ERR_OWNER_ID_NOT_VALID = "Owner id not valid!";
    }
}
