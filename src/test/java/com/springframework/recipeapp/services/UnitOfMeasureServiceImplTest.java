package com.springframework.recipeapp.services;

import com.springframework.recipeapp.commands.UnitOfMeasureCommand;
import com.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.springframework.recipeapp.domain.UnitOfMeasure;
import com.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository repository;

    AutoCloseable closeable;

    @Before
    public void setUp() throws Exception {
        closeable = MockitoAnnotations.openMocks(this);

        service = new UnitOfMeasureServiceImpl(repository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void listAllUoms() {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        when(repository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        //then
        assertEquals(2, commands.size());
        verify(repository, times(1)).findAll();
    }
}
