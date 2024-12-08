package org.example.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Model.Person;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Account {
    // Account class for people managing the parking lot.
    // Variables : userName, password, accountStatus, person
    // Methods : resetPassword().
    private String userName;
    private String password;
    private String accountStatus;
    private Person person;

    public boolean resetPassword(){
        // logic to reset password
        return true;
    };

}
