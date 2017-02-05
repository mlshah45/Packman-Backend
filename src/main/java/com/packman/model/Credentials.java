package com.packman.model;

import com.packman.model.DataBaseSchemas.CredentialsSchema;

import javax.persistence.*;

/**
 * Model Class for Credentials.
 *
 * Created by RushikeshParadkar on 12/5/15.
 */
@Entity
@Table(name = CredentialsSchema.CREDENTIAL_TABLE)
public class Credentials {

    @Id
    @Column(name = CredentialsSchema.CREDENTIAL_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long credentialId;

    @Column(name = CredentialsSchema.CREDENTIAL_PASSWORD)
    private String password;

    /**
     * Default Constructor
     */
    public Credentials(){

    }

    /**
     * Parameterized Constructor for Credentials.
     *
     * @param password - person password entered by the user (person).
     */
    public Credentials(String password){
        this.password = password;
    }

    /**
     * Getter for Credential Id.
     *
     * @return credentialId - primary key of this table.
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCredentialId() {
        return credentialId;
    }

    /**
     * Getter for the person password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password.
     *
     * @param password - password entered by the user (person).
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
