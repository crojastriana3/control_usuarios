package com.web.ControlCliente.service;

import com.web.ControlCliente.domain.IPerson;

import java.util.List;

public interface IPersonService {
    public List<IPerson> getPersons();
    public void savePerson(IPerson IPerson);

    public void  deletePerson(IPerson IPerson);

    public IPerson getPerson(IPerson IPerson);

}
