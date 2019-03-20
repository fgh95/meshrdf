package gov.nih.nlm.lode.tests.lookup;

import java.util.Arrays;
import java.util.Collection;

import gov.nih.nlm.lode.model.DescriptorCriteria;
import gov.nih.nlm.lode.model.LookupService;
import gov.nih.nlm.lode.model.PairCriteria;
import gov.nih.nlm.lode.model.ResourceAndLabel;
import uk.ac.ebi.fgpt.lode.exception.LodeException;


/**
 * This is a lookup service that records the last parameters it received and also the number of calls.
 * This is used to unit test the LookupController without actually touching Virtuoso.
 *
 * @author davisda4
 *
 */
public class MockLookupService implements LookupService {
    public DescriptorCriteria desc = null;
    public PairCriteria pair = null;
    public int count = 0;

    @Override
    public Collection<ResourceAndLabel> lookupDescriptors(DescriptorCriteria criteria) throws LodeException {
        count++;
        this.desc= criteria;
        return Arrays.asList(new ResourceAndLabel[] {
           new ResourceAndLabel("http://id.nlm.nih.gov/mesh/D1", "First label"),
           new ResourceAndLabel("http://id.nlm.nih.gov/mesh/D2", "Later label")
        });
    }

    @Override
    public Collection<ResourceAndLabel> lookupPairs(PairCriteria criteria) throws LodeException {
        count++;
        this.pair = criteria;
        return Arrays.asList(new ResourceAndLabel[] {
            new ResourceAndLabel("http://id.nlm.nih.gov/mesh/DQ1", "First label"),
            new ResourceAndLabel("http://id.nlm.nih.gov/mesh/DQ2", "Later label")
         });
    }

    public void clear() {
        count = 0;
        desc = null;
        pair = null;
    }
}
