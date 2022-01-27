package com.intech.comptabilite.service.entityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.model.EcritureComptable;
import com.intech.comptabilite.model.LigneEcritureComptable;
import com.intech.comptabilite.repositories.EcritureComptableRepository;
import com.intech.comptabilite.service.exceptions.NotFoundException;

@SpringBootTest
public class EcritureComptableServiceTest {
	
	@Autowired
	private EcritureComptableService ecritureComptableService;
	
	@Mock
	EcritureComptable mockEcritureComptable;
	

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }
    
    /* Pas le temps de comprendre le principe de repository 
     * @Test
    public void testGetEcritureComptable( ) throws NotFoundException {
    	String ref = "GG-2021/00001";
    	when(mockEcritureComptable.getReference()).thenReturn(ref);
    	repository.save(mockEcritureComptable);
    	EcritureComptable res = ecritureComptableService.getEcritureComptableByRef(ref);
		verify(mockEcritureComptable).getReference();
		
		assertEquals(mockEcritureComptable, res);

    }*/
	@Test 
	public void testGetTotalDebitEquals() {
    	LigneEcritureComptable line1 = this.createLigne(1, "200.50", null);
    	LigneEcritureComptable line2 = this.createLigne(1, "100.50", "33.00");
        LigneEcritureComptable[] mockList = { line1, line2};
        List<LigneEcritureComptable> list = Arrays.asList(mockList);

    	when(mockEcritureComptable.getListLigneEcriture()).thenReturn(list);
    	BigDecimal bg1 = new BigDecimal("301.00");

    
    	BigDecimal res = ecritureComptableService.getTotalDebit(mockEcritureComptable);
		verify(mockEcritureComptable).getListLigneEcriture();

    	assertEquals(bg1, res);

    }
    
    @Test 
    public void testGetTotalDebitEquals0() {
    	BigDecimal res = ecritureComptableService.getTotalDebit(mockEcritureComptable);
    	assertEquals(BigDecimal.ZERO, res);
    }

	@Test 
	public void testGetTotalCreditEquals() {
    	LigneEcritureComptable line1 = this.createLigne(1, "200.50", null);
    	LigneEcritureComptable line2 = this.createLigne(1, "100.50", "33.00");
        LigneEcritureComptable[] mockList = { line1, line2};
        List<LigneEcritureComptable> list = Arrays.asList(mockList);

    	when(mockEcritureComptable.getListLigneEcriture()).thenReturn(list);
    	System.out.println(mockEcritureComptable.getListLigneEcriture());
		verify(mockEcritureComptable).getListLigneEcriture();

    	BigDecimal res = ecritureComptableService.getTotalCredit(mockEcritureComptable);
    	BigDecimal bg1 = new BigDecimal("33.00");

    	assertEquals(bg1, res);

    }
    
    @Test 
    public void testGetTotalCreditEquals0() {
    	BigDecimal res = ecritureComptableService.getTotalCredit(mockEcritureComptable);
    	assertEquals(BigDecimal.ZERO, res);
    }
    
    @Test
    public void testIsEquilibree() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33.00"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301.00"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40.00", "7.00"));
        Assertions.assertTrue(ecritureComptableService.isEquilibree(vEcriture));

        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assertions.assertFalse(ecritureComptableService.isEquilibree(vEcriture));
    }

}
