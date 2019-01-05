package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.MockingObject.FakeObjectFactory;
import com.oims.futureprogram.model.Supervisor;
import com.oims.futureprogram.repository.SupervisorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SupervisorServiceImplTest {
    @InjectMocks
    private SupervisorServiceImpl supervisorService;

    @Mock
    private SupervisorRepository supervisorRepository;

    @Test
    public void getListSupervisorTest() {
        when(supervisorRepository.findAll()).thenReturn(Arrays.asList(
                Supervisor.builder().id((long)1).build(),
                Supervisor.builder().id((long)2).build()
        ));

        List<Supervisor> supervisors = supervisorService.getListSupervisor();

        Assert.assertEquals(supervisors, supervisorRepository.findAll());
    }

    @Test
    public void createSupervisorTest() {
        Supervisor supervisor = FakeObjectFactory.getFakeSupervisor();

        when(supervisorRepository.save(any(Supervisor.class))).thenReturn(supervisor);
        Supervisor returned = supervisorService.createSupervisor(supervisor);

        assertEquals(supervisor, returned);
    }
}