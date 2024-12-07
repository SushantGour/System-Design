package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Model.Location;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private Location location;
    private String email;
    private String phoneNumber;
}
