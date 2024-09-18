package com.motta.employee_service.util;

public class EmployeeConstants {
    public static final String LOG_MESSAGE_EMPLOYEE_NOT_FOUND = "Employee id not found. Please enter different id";
    public static final String LOG_MESSAGE_EMPLOYEE_PERSISTED = "Employee id = {} persisted";
    public static final String LOG_MESSAGE_EMPLOYEE_UPDATE_FAILED = "Updating scheme id = {} has failed.";

    public static final String EXCEPTION_MESSAGE_INVALID_PHONE_NUMBER = "Phone number should be within valid length of: ";
    public static final String EXCEPTION_MESSAGE_INVALID_AGE = "Age should be within valid range of {} and {}";
    public static final String EXCEPTION_MESSAGE_EMPLOYEE_ADDRESSES_NOT_FOUND = "Employee addresses not found";
    public static final String EXCEPTION_MESSAGE_EMPLOYEE_NOT_FOUND = "Employee id = {} already Exists!";
    public static final String EXCEPTION_MESSAGE_EMPLOYEE_ID_IS_MANDATORY = "Employee Id is mandatory";
    public static final String EXCEPTION_MESSAGE_FIRST_NAME_IS_MANDATORY = "First name is mandatory";
    public static final String EXCEPTION_MESSAGE_LAST_NAME_IS_MANDATORY = "Last name is mandatory";
    public static final String EXCEPTION_MESSAGE_GENDER_IS_MANDATORY = "Gender is mandatory";
    public static final String EXCEPTION_MESSAGE_EMAIL_IS_MANDATORY = "Email is mandatory";
    public static final String EXCEPTION_MESSAGE_SALARY_ID_IS_MANDATORY = "Salary Id is mandatory";
    public static final String EXCEPTION_MESSAGE_EMPLOYEE_NUMBER_IS_MANDATORY = "Employee number is mandatory";
    public static final String EXCEPTION_MESSAGE_EMPLOYEE_ID_LESS_THAN_INITIAL_VALUE = "Employee Id must not be less than the initial value of: ";

    public static final String URL_GET_ASSOCIATIONS_BY_EMPLOYEE_ID = "http://localhost:8900/getassociationsbyschemeid/";

    public static final String SPRING_BATCH_FILE_DELIMITER = ",";
}
