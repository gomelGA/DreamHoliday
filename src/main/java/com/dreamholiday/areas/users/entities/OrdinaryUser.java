package com.dreamholiday.areas.users.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ordinary_user")
public class OrdinaryUser extends User {
}
