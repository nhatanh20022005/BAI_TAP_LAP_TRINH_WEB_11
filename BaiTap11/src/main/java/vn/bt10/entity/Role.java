package vn.bt10.entity;

/**
 * Enumeration of user roles.  The two roles defined here correspond to
 * standard users and administrators.  Administrators have access to
 * management pages for categories and products, while ordinary users
 * are limited to viewing user pages.
 */
public enum Role {
    USER,
    ADMIN
}