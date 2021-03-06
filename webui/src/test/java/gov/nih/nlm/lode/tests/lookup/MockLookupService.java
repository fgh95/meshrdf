package gov.nih.nlm.lode.tests.lookup;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import gov.nih.nlm.lode.model.DescriptorParams;
import gov.nih.nlm.lode.model.LookupService;
import gov.nih.nlm.lode.model.PairParams;
import gov.nih.nlm.lode.model.ResourceResult;
import uk.ac.ebi.fgpt.lode.exception.LodeException;


/**
 * This is a lookup service that records the last parameters it received and also the number of calls.
 * This is used to unit test the LookupController without actually touching Virtuoso.
 *
 * @author davisda4
 *
 */
public class MockLookupService implements LookupService {
    public DescriptorParams desc = null;
    public DescriptorParams term = null;
    public PairParams pair = null;
    public String descriptorUri = null;
    public String resourceUri = null;
    public int count = 0;
    public Set<String> methodsCalled = new HashSet<String>();

    @Override
    public Collection<ResourceResult> lookupDescriptors(DescriptorParams criteria) throws LodeException {
        methodsCalled.add("lookupDescriptors");
        count++;
        this.desc= criteria;
        return Arrays.asList(new ResourceResult[] {
           new ResourceResult("http://id.nlm.nih.gov/mesh/D1", "First label"),
           new ResourceResult("http://id.nlm.nih.gov/mesh/D2", "Later label")
        });
    }

    @Override
    public Collection<ResourceResult> lookupPairs(PairParams criteria) throws LodeException {
        methodsCalled.add("lookupPairs");
        count++;
        this.pair = criteria;
        return Arrays.asList(new ResourceResult[] {
            new ResourceResult("http://id.nlm.nih.gov/mesh/DQ1", "First label"),
            new ResourceResult("http://id.nlm.nih.gov/mesh/DQ2", "Later label")
         });
    }

    @Override
    public Collection<ResourceResult> lookupTerms(DescriptorParams criteria) throws LodeException {
        methodsCalled.add("lookupTerms");
        count++;
        this.term = criteria;
        return Arrays.asList(new ResourceResult[] {
            new ResourceResult("http://id.nlm.nih.gov/mesh/T1", "First label"),
            new ResourceResult("http://id.nlm.nih.gov/mesh/T2", "Later label")
         });
    }

    @Override
    public Collection<ResourceResult> allowedQualifiers(String descriptorUri) throws LodeException {
        methodsCalled.add("allowedQualifiers");
        count++;
        this.descriptorUri = descriptorUri;
        return Arrays.asList(new ResourceResult[] {
            new ResourceResult("http://id.nlm.nih.gov/mesh/Q1", "Qualifier One"),
            new ResourceResult("http://id.nlm.nih.gov/mesh/Q2", "Qualifier Two")
        });
    }

    @Override
    public Collection<String> lookupLabel(String resourceUri) throws LodeException {
        methodsCalled.add("lookupLabel");
        count++;
        this.resourceUri = resourceUri;
        return Arrays.asList(new String[] {
            "Mean Older Sibling Syndrome"

        });
    }

    public void clear() {
        count = 0;
        desc = null;
        pair = null;
        resourceUri = null;
        descriptorUri = null;
        methodsCalled.clear();
    }

    @Override
    public Collection<ResourceResult> lookupDescriptorConcepts(String descriptorUri) throws LodeException {
        methodsCalled.add("lookupDescriptorConcepts");
        count++;
        this.descriptorUri = descriptorUri;
        return Arrays.asList(new ResourceResult[] {
            new ResourceResult("http://id.nlm.nih.gov/mesh/M0483545", "Kidney Diseases, Cystic", true),
            new ResourceResult("http://id.nlm.nih.gov/mesh/M0012030", "Kidney, Cystic", false),
        });
    }

    @Override
    public Collection<ResourceResult> lookupDescriptorTerms(String descriptorUri) throws LodeException {
        methodsCalled.add("lookupDescriptorTerms");
        count++;
        this.descriptorUri = descriptorUri;
        return Arrays.asList(new ResourceResult[] {
            new ResourceResult("http://id.nlm.nih.gov/mesh/T63134", "Kidney Diseases, Cystic", true),
            new ResourceResult("http://id.nlm.nih.gov/mesh/T63136", "Cystic Kidney Diseases", false),
            new ResourceResult("http://id.nlm.nih.gov/mesh/T63135", "Cystic Renal Diseases", false),
            new ResourceResult("http://id.nlm.nih.gov/mesh/T023084", "Kidney, Cystic", false),
        });
    }

    @Override
    public Collection<ResourceResult> lookupDescriptorSeeAlso(String descriptorUri) throws LodeException {
        methodsCalled.add("lookupDescriptorSeeAlso");
        count++;
        this.descriptorUri = descriptorUri;
        return Arrays.asList(new ResourceResult[] {
            new ResourceResult("http://id.nlm.nih.gov/mesh/D064793", "Teratogenesis"),
            new ResourceResult("http://id.nlm.nih.gov/mesh/D013723", "Teratogens"),
        });
    }
}
