---
title: Concepts
layout: page
resource: true
categories:
- Data Model
---
A concept is a class in MeSH RDF (meshv:Concept).  MeSH concepts are all assigned 'M' identifiers.  A MeSH Concept represents a unit of meaning.  Collections of concepts that may be useful for search and retrieval on a given topic are placed into the same MeSH Descriptor.  A concept is considered 'preferred' if its name is used by the descriptor to which it belongs. In MeSH RDF, concepts classes can be connected as follows:

**When the concept is the 'Subject'**


{:.data-table}
Subject | Predicate | Object
------- | --------- | -------
meshv:Concept | skos:narrower | meshv:Concept
meshv:Concept | meshv:semanticType | meshv:SemanticType
meshv:Concept | meshv:preferredTerm | meshv:Term
meshv:Concept | skos:broader | meshv:Concept
meshv:Concept | meshv:term | meshv:Term

**When the concept is the 'Object'**

{:.data-table}
Subject | Predicate | Object
------- | --------- | -------
meshv:Concept | skos:narrower | meshv:Concept
meshv:Concept | skos:broader | meshv:Concept
meshv:Qualifier | meshv:concept | meshv:Concept
meshv:Qualifier | meshv:preferredConcept | meshv:Concept
meshv:TopicalDescriptor | meshv:preferredConcept | meshv:Concept
meshv:TopicalDescriptor | meshv:concept | meshv:Concept
meshv:GeographicalDescriptor | meshv:preferredConcept | meshv:Concept
meshv:GeographicalDescriptor | meshv:concept | meshv:Concept
meshv:PublicationType | meshv:concept | meshv:Concept
meshv:PublicationType | meshv:preferredConcept | meshv:Concept
meshv:SupplementaryConceptRecord | meshv:preferredConcept | meshv:Concept
meshv:SupplementaryConceptRecord | meshv:concept | meshv:Concept
meshv:CheckTag | meshv:preferredConcept | meshv:Concept


### RDF Graph Diagram

![Concept RDF Graph Diagram](images/Concepts.png){: class="rdf-graph"}


### SPARQL

The RDF output above can be generated with the following <span class='invoke-sparql'>SPARQL query</span>:


```sparql
prefix mesh: <http://id.nlm.nih.gov/mesh/>
prefix meshv: <http://id.nlm.nih.gov/mesh/vocab#>

construct {
    mesh:D000001 meshv:preferredConcept ?prefcon .
    ?prefcon ?p ?o .
    ?prefcon meshv:semanticType $semtype .
    $semtype ?stp $sto .
}
from <http://id.nlm.nih.gov/mesh2014>
where {
    mesh:D000001 meshv:preferredConcept ?prefcon .
    ?prefcon ?p ?o .
    ?prefcon meshv:semanticType $semtype .
    $semtype ?stp $sto .

}
```

###MeSH RDF Data

```
<http://id.nlm.nih.gov/mesh/T195>
        a       <http://id.nlm.nih.gov/mesh/vocab#SemanticType> ;
        <http://www.w3.org/2000/01/rdf-schema#label>
                "Antibiotic" ;
        <http://purl.org/dc/terms/identifier>
                "T195" .

<http://id.nlm.nih.gov/mesh/D000001>
        <http://id.nlm.nih.gov/mesh/vocab#preferredConcept>
                <http://id.nlm.nih.gov/mesh/M0000001> .

<http://id.nlm.nih.gov/mesh/T109>
        a       <http://id.nlm.nih.gov/mesh/vocab#SemanticType> ;
        <http://www.w3.org/2000/01/rdf-schema#label>
                "Organic Chemical" ;
        <http://purl.org/dc/terms/identifier>
                "T109" .

<http://id.nlm.nih.gov/mesh/M0000001>
        a       <http://id.nlm.nih.gov/mesh/vocab#Concept> ;
        <http://www.w3.org/2000/01/rdf-schema#label>
                "Calcimycin" ;
        <http://id.nlm.nih.gov/mesh/vocab#CASN1_label>
                "4-Benzoxazolecarboxylic acid, 5-(methylamino)-2-((3,9,11-trimethyl-8-(1-methyl-2-oxo-2-(1H-pyrrol-2-yl)ethyl)-1,7-dioxaspiro(5.5)undec-2-yl)methyl)-, (6S-(6alpha(2S*,3S*),8beta(R*),9beta,11alpha))-" ;
        <http://id.nlm.nih.gov/mesh/vocab#preferredTerm>
                <http://id.nlm.nih.gov/mesh/T000002> ;
        <http://id.nlm.nih.gov/mesh/vocab#registryNumber>
                "37H9VM9WZL" ;
        <http://id.nlm.nih.gov/mesh/vocab#relatedRegistryNumber>
                "52665-69-7 (Calcimycin)" ;
        <http://id.nlm.nih.gov/mesh/vocab#semanticType>
                <http://id.nlm.nih.gov/mesh/T195> , <http://id.nlm.nih.gov/mesh/T109> ;
        <http://purl.org/dc/terms/identifier>
                "M0000001" ;
        <http://www.w3.org/2004/02/skos/core#narrower>
                <http://id.nlm.nih.gov/mesh/M0353609> ;
        <http://www.w3.org/2004/02/skos/core#scopeNote>
                "An ionophorous, polyether antibiotic from Streptomyces chartreusensis.
                It binds and transports CALCIUM and other divalent cations across membranes and uncouples oxidative phosphorylation while inhibiting ATPase of rat liver mitochondria.
                The substance is used mostly as a biochemical tool to study the role of divalent cations in various biological systems."
```


Note that this page does not describe Terms,
which are subordinate to Concepts, but most other things directly related to Concepts are here.

### MeSH XML

```xml
<DescriptorRecord DescriptorClass="1">
  <DescriptorUI>D000001</DescriptorUI>
  <DescriptorName>
    <String>Calcimycin</String>
  </DescriptorName>
  ...
  <ConceptList>
    <Concept PreferredConceptYN="Y">
      <ConceptUI>M0000001</ConceptUI>
      <ConceptName>
        <String>Calcimycin</String>
      </ConceptName>
      <CASN1Name>4-Benzoxazolecarboxylic acid,  ...</CASN1Name>
      <RegistryNumber>37H9VM9WZL</RegistryNumber>
      <ScopeNote>An ionophorous, ... </ScopeNote>
      <SemanticTypeList>
        <SemanticType>
          <SemanticTypeUI>T109</SemanticTypeUI>
          <SemanticTypeName>Organic Chemical</SemanticTypeName>
        </SemanticType>
        <SemanticType>
          <SemanticTypeUI>T195</SemanticTypeUI>
          <SemanticTypeName>Antibiotic</SemanticTypeName>
        </SemanticType>
      </SemanticTypeList>
      <RelatedRegistryNumberList>
        <RelatedRegistryNumber>52665-69-7 (Calcimycin)</RelatedRegistryNumber>
      </RelatedRegistryNumberList>
      <ConceptRelationList>
        <ConceptRelation RelationName="NRW">
          <Concept1UI>M0000001</Concept1UI>
          <Concept2UI>M0353609</Concept2UI>
        </ConceptRelation>
      </ConceptRelationList>
      ...
    </Concept>
    <Concept PreferredConceptYN="N">
      <ConceptUI>M0353609</ConceptUI>
      ...
    </Concept>
  </ConceptList>
</DescriptorRecord>
```

Note:  RelationAttributes (see the [MeSH documentation](http://www.nlm.nih.gov/mesh/xml_data_elements.html#RelationAttribute) are not
modelled in the RDF.