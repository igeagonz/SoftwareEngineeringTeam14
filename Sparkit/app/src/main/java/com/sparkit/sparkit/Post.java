package com.sparkit.sparkit;

/**
 * Created by grantjohns on 4/1/16.
 */
public class Post
{
    String title;
    String email;
    String stAddress;
    String city;
    String state;
    int zip;
    String description;

    public Post(String T, String E, String SA, String C, String S, int Z, String D)
    {
        title = T;
        email = E;
        stAddress = SA;
        city  = C;
        state = S;
        zip = Z;
        description = D;
    }

}
