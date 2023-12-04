package com.web.ControlCliente.dao;

import com.web.ControlCliente.domain.IPerson;
import org.springframework.data.repository.CrudRepository;

public interface IPersonDao extends CrudRepository<IPerson, Long> {
}
