package io.swagger.petstore.constants;

public class EndPoints {

    /**
     * This is Endpoints of pet store api
     */
    public static final String CREATE_USER = "/user";
    public static final String GET_SINGLE_USER_BY_USERNAME = "/user/name";
    public static final String UPDATE_USER_BY_USERNAME = "/user/name";
    public static final String DELETE_USER_BY_USERNAME = "/user/name";
    public static final String GET_SINGLE_PET_BY_ID = "/pet/{id}";
    public static final String GET_All_PET_BY_IDS = "/pet";
    public static final String UPDATE_PET_BY_ID = "/pet/{id}";
    public static final String DELETE_PET_BY_ID = "/pet/{id}";
    public static final String GET_PET = "/pet";
}
