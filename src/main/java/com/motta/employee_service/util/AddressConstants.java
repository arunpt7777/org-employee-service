package com.motta.employee_service.util;

public class AddressConstants {
    public static final String LOG_MESSAGE_ADDRESS_NOT_FOUND = "Address id not found. Please enter different id";
    public static final String LOG_MESSAGE_ADDRESS_PERSISTED = "Address id = {} persisted";
    public static final String LOG_MESSAGE_ADDRESS_UPDATE_FAILED = "Updating scheme id = {} has failed.";
    public static final String LOG_FETCHING_ASSOCIATIONS_FAILED = "Failed fetching associations for scheme Id.";

    public static final String EXCEPTION_MESSAGE_ADDRESS_NOT_FOUND = "Address id = {} already Exists!";
    public static final String EXCEPTION_MESSAGE_INVALID_FROM_DATE = "From Date should be less than To Date";
    public static final String EXCEPTION_MESSAGE_ADDRESS_ID_IS_MANDATORY = "Address Id is mandatory";
    public static final String EXCEPTION_MESSAGE_ADDRESS_LINE1_IS_MANDATORY = "Address Line1 is mandatory";
    public static final String EXCEPTION_MESSAGE_ADDRESS_LINE2_IS_MANDATORY = "Address Line2 is mandatory";
    public static final String EXCEPTION_MESSAGE_ZIPCODE_LENGTH_INVALID = "Zipcode must be exactly {} characters long";
    public static final String EXCEPTION_MESSAGE_ADDRESS_NAME_IS_MANDATORY = "Address Name is mandatory";
    public static final String EXCEPTION_MESSAGE_ADDRESS_ID_LESS_THAN_INITIAL_VALUE = "Address Id must not be less than the initial value of: ";
    public static final String EXCEPTION_MESSAGE_TO_DATE_IS_MANDATORY = "Valid To Date is mandatory";
    public static final String EXCEPTION_MESSAGE_ADDRESS_TYPE_IS_MANDATORY = "Address Type is mandatory";
    public static final String EXCEPTION_MESSAGE_ADDRESS_AMOUNT_IS_MANDATORY = "Address Amount is mandatory";
    public static final String EXCEPTION_MESSAGE_SHARE_IS_MANDATORY = "Share  is mandatory";
    public static final String EXCEPTION_MESSAGE_COMMISSION_IS_MANDATORY = "Commission  is mandatory";
    public static final String EXCEPTION_MESSAGE_ASSOCIATIONS_NOT_FOUND = "Associations not found";

    public static final String URL_GET_ASSOCIATIONS_BY_ADDRESS_ID = "http://localhost:8900/getassociationsbyschemeid/";


}
