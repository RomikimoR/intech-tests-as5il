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
import com.intech.comptabilite.repositories.CompteComptableRepository;

@SpringBootTest
public class CompteComptableServiceTest {
	
	@Autowired
	private CompteComptableService compteComptableService;
	
	@Mock
	CompteComptable compteComptable;
	
	@Mock
	CompteComptableRepository repository;
	
	/* Sorry toujours pareil pas l'habitude de manipuler la data avec des repository
	 * @Test
	public void getCompteComptableList() {
        
        List<CompteComptable> res = compteComptableService.getListCompteComptable();
        assertEquals(repository.findAll(), res);     

	}*/
	
	@Test
	public void getCompteComptableByNumero() {
        List<CompteComptable> myArrayList = new ArrayList<CompteComptable>(); 
        myArrayList.add(compteComptable);

    	when(compteComptable.getNumero()).thenReturn(21);

        
        CompteComptable res = compteComptableService.getByNumero(myArrayList, 21);
		verify(compteComptable).getNumero();

        assertEquals(compteComptable, res);      
        

	}

	@Test
	public void getCompteComptableByNumeroReturnNull() {
		
        List<CompteComptable> myArrayList = new ArrayList<CompteComptable>(); 
        myArrayList.add(compteComptable);

    	when(compteComptable.getNumero()).thenReturn(21);

        
        CompteComptable res = compteComptableService.getByNumero(myArrayList, 1);
		verify(compteComptable).getNumero();

        assertEquals(null, res);     
        

	}
}
