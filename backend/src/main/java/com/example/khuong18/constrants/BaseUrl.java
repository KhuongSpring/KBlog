package com.example.khuong18.constrants;

public class BaseUrl {
    public static class User {
        public static final String PREFIX = "/user";
        public static final String GET_USERS = PREFIX;
        public static final String GET_USER_BY_USERNAME = PREFIX + "/username/{userName}";
        public static final String GET_USER_BY_ID = PREFIX + "/id/{id}";
        public static final String UPDATE_USER = PREFIX;
        public static final String UPLOAD_AVATAR = PREFIX + "/upload_avatar";
        public static final String SEARCH_USERS_BY_KEYWORD = PREFIX + "/search/{keyword}";
        public static final String FOLLOW_USER = PREFIX + "/follow";
        public static final String UNFOLLOW_USER = PREFIX + "/unfollow";
        public static final String CHECK_FOLLOW_USER = PREFIX + "/follow/check-follow";
        public static final String SHOW_FOLLOW_USER = PREFIX + "/follow/show-follow";
    }

    public static class Post {
        public static final String PREFIX = "/post";
        public static final String GET_POSTS = PREFIX;
    }
}
