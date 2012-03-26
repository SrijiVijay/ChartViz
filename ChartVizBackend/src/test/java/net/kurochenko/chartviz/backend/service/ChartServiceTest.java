package net.kurochenko.chartviz.backend.service;

import net.kurochenko.chartviz.backend.AbstractMockInit;
import net.kurochenko.chartviz.backend.dao.ChartDAO;
import net.kurochenko.chartviz.backend.dao.ChartDataDAO;
import net.kurochenko.chartviz.backend.entity.Chart;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
public class ChartServiceTest extends AbstractMockInit {

    private ChartServiceImpl chartService;
    @Mock private ChartDAO chartDAO;
    @Mock private ChartDataDAO chartDataDAO;

    private Chart chart = new Chart();


    @Before
    public void setUp() {
        chartService = new ChartServiceImpl();
        chartService.setChartDAO(chartDAO);
        chartService.setChartDataDAO(chartDataDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        chartService.create(null);        
    }

    @Test
    public void testCreate() {
        chartService.create(chart);
        verify(chartDAO).create(chart);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditNull() {
        chartService.edit(null);
    }

    @Test
    public void testEdit() {
        chartService.edit(chart);
        verify(chartDAO).edit(chart);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        chartService.remove(null);
    }
    
    @Test
    public void testRemove() {
        chartService.remove(chart);
        verify(chartDAO).remove(chart);
        verify(chartDataDAO).removeByChart(chart);
    }      

    @Test(expected = IllegalArgumentException.class)
    public void testFindNull() {
        chartService.find(null);
    }

    @Test
    public void testFind() {
        chartService.find(Long.MAX_VALUE);
        verify(chartDAO).find(Long.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindDTONull() {
        chartService.findDTO(null);
    }

    @Test
    public void testFindDTO() {
        when(chartDAO.find(Long.MAX_VALUE)).thenReturn(chart);

        chartService.findDTO(Long.MAX_VALUE);
        verify(chartDAO).find(Long.MAX_VALUE);
        verify(chartDataDAO).findAll(chart);
    }

    @Test
    public void testFindAll() {
        chartService.findAll();
        verify(chartDAO).findAll();
    }
}
