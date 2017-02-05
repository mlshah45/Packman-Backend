package com.packman.dao.implementation;

import com.packman.dao.daointerfaces.CredentialsDAO;
import com.packman.model.Agent;
import com.packman.model.Credentials;

/**
 * Created by RushikeshParadkar on 12/5/15.
 */
public class CredentialsDAOImpl extends GenericDAOImpl<Credentials, Long> implements CredentialsDAO {

    public boolean addCredentials(Credentials credentials) throws Exception {
        super.add(credentials);
        return true;
    }

    public boolean deleteCredentials(Credentials credentials) throws Exception {
        super.remove(credentials);
        return true;
    }
}
