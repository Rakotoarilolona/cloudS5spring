package com.carte.clouds5spring.service;

import com.carte.clouds5spring.dto.FirebaseUserDTO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FirebaseUserService 
{
    public List<UserRecord> getAllFirebaseUsers() throws FirebaseAuthException 
    {
        List<UserRecord> users = new ArrayList<>();

        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);

        while (page != null) {
            for (UserRecord user : page.getValues()) {
                users.add(user);
            }
            page = page.getNextPage();
        }

        return users;
    }

    public List<FirebaseUserDTO> getAllFirebaseUsersDTO() throws FirebaseAuthException 
    {
        List<FirebaseUserDTO> result = new ArrayList<>();

        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);

        while (page != null) 
        {
            for (UserRecord user : page.getValues()) 
            {
                result.add(
                    new FirebaseUserDTO(
                        user.getUid(),
                        user.getEmail(),
                        user.getDisplayName(),
                        user.getUserMetadata().getCreationTimestamp() == 0 ? null :
                            new Date(user.getUserMetadata().getCreationTimestamp()),
                        user.getCustomClaims().getOrDefault("role", "utilisateur").toString()
                    )
                );
            }
            page = page.getNextPage();
        }

        return result;
    }

}
