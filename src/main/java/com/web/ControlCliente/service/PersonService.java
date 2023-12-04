package com.web.ControlCliente.service;

import com.web.ControlCliente.dao.IPersonDao;
import com.web.ControlCliente.domain.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //clase que la pasa al CONTROLADOR DE DEPENDENCIAS
public class PersonService implements IPersonService {
    @Autowired
    private IPersonDao iPersonDao;

    @Override
    @Transactional(readOnly = true)//solo leeremos datos
    public List<IPerson> getPersons() {
        return (List<IPerson>) iPersonDao.findAll();
    }

    @Override
    @Transactional //en este caso necesitamos commit o rolback
    public void savePerson(IPerson IPerson) {
        iPersonDao.save(IPerson);
    }

    @Override
    @Transactional
    public void deletePerson(IPerson IPerson) {
        iPersonDao.delete(IPerson);

    }

    @Override
    @Transactional(readOnly = true)
    public IPerson getPerson(IPerson IPerson) {
        return iPersonDao.findById(IPerson.getId()).orElse(null);
    }
}
