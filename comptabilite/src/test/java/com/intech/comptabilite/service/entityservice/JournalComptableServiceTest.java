package com.intech.comptabilite.service.entityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.model.JournalComptable;

@SpringBootTest
public class JournalComptableServiceTest {
	@Autowired
	private JournalComptableService journalComptableService;
	
	@Mock
	JournalComptable journalComptable; 
	
	@Test
	public void getCompteComptableByNumero() {
        List<JournalComptable> myArrayList = new ArrayList<JournalComptable>(); 
        myArrayList.add(journalComptable);

    	when(journalComptable.getCode()).thenReturn("OK");

        
    	JournalComptable res = journalComptableService.getByCode(myArrayList, "OK");
		verify(journalComptable).getCode();

        assertEquals(journalComptable, res);      
        

	}

	@Test
	public void getCompteComptableByNumeroReturnNull() {
		
        List<JournalComptable> myArrayList = new ArrayList<JournalComptable>(); 
        myArrayList.add(journalComptable);

    	when(journalComptable.getCode()).thenReturn("A");

        
    	JournalComptable res = journalComptableService.getByCode(myArrayList, "OK");
		verify(journalComptable).getCode();

        assertEquals(null, res);     
        

	}
}
