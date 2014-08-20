# Blank-node sample queries

This file contains sample queries to explore the current use of blank nodes
in the MeSH RDF.

## BN type 1: EntryCombination

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?desc
    ?bn_ec
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?desc mesh:entryCombination ?bn_ec .
}
limit 10
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
PREFIX dcterms: <http://purl.org/dc/terms/>
select
    ?mh_id
    ?mh_label
    ?in_d_id
    ?in_d_label
    ?in_q_id
    ?in_q_label
    ?out_d_id
    ?out_d_label
    ?out_q_id
    ?out_q_label
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?desc mesh:entryCombination ?bn_ec .

    ?desc dcterms:identifier ?mh_id .
    ?desc rdfs:label ?mh_label .

    OPTIONAL {
        ?bn_ec mesh:ECINDescriptor ?in_d .
        ?in_d dcterms:identifier ?in_d_id .
        ?in_d rdfs:label ?in_d_label .
    }
    OPTIONAL {
        ?bn_ec mesh:ECOUTDescriptor ?out_d .
        ?out_d dcterms:identifier ?out_d_id .
        ?out_d rdfs:label ?out_d_label .
    }
    OPTIONAL {
        ?bn_ec mesh:ECINQualifier ?in_q .
        ?in_q dcterms:identifier ?in_q_id .
        ?in_q rdfs:label ?in_q_label .
    }
    OPTIONAL {
        ?bn_ec mesh:ECOUTQualifier ?out_q .
        ?out_q dcterms:identifier ?out_q_id .
        ?out_q rdfs:label ?out_q_label .
    }

    FILTER(?mh_id = 'D000005')
}
```

This BN could be avoided by using 2 DQPairs:

1. one for ECINDescriptor + ECINQualifier
2. one for ECOUTDescriptor + ECOUTQualifier

The DQPairs would be attached directly to the descriptor, with 2 different predicates

1. desc mesh:ECIN DQPairs
2. desc mesh:ECOUT DQPairs

NB:
- not all DQPairs are complete (the qualifier may be missing)
  e.g., ECOUT for D000005
- an unqualified DQPairs should be created for each descriptor


## BN type 2: ConceptRelation

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?desc
    ?bn_cr
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?desc mesh:concept ?concept .
    ?concept mesh:conceptRelation ?bn_cr .
}
limit 10
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    distinct
    ?mh_id
    ?mh_label
    ?bn_cr
    ?concept1_id
    ?concept1_label
    ?concept2_id
    ?concept2_label
    ?relation
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?desc mesh:concept ?concept .
    ?concept mesh:conceptRelation ?bn_cr .

    ?desc dcterms:identifier ?mh_id .
    ?desc rdfs:label ?mh_label .

    ?bn_cr mesh:concept1 ?concept1 .
    ?bn_cr mesh:concept2 ?concept2 .
    ?bn_cr mesh:relation ?relation .

    OPTIONAL {
        ?concept1 dcterms:identifier ?concept1_id .
        ?concept1 rdfs:label ?concept1_label .
    }

    OPTIONAL {
        ?concept2 dcterms:identifier ?concept2_id .
        ?concept2 rdfs:label ?concept2_label .
    }

    FILTER(?mh_id = 'D015242')
}
```

This BN could be avoided by using a direct relation between the concepts. Transform

    ?bn_cr mesh:concept1 ?concept1 .
    ?bn_cr mesh:concept2 ?concept2 .
    ?bn_cr mesh:relation ?relation .

into

    concept1 ?relation concept2

NB:
- would be much simpler
- we need to be careful with the direction of the relation
  *** skos:narrower means "has narrower concept", i.e., points to narrower concepts ***
- in the original implementation, the use of skos:narrower between C1 and C2
  in the conceptRelation BN means C2 is narrower than C1, i.e. C1 skos:narrower C2


## BN type 3: indexingData

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?scr
    ?bn_indexingData
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?scr a mesh:SupplementaryConceptRecord .
    ?scr mesh:indexingData ?bn_indexingData .
}
limit 10
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?scr_id
    ?scr_label
    ?bn_indexingData
    ?indexingDescriptor_id
    ?indexingDescriptor_label
    ?indexingQualifier_id
    ?indexingQualifier_label
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?scr a mesh:SupplementaryConceptRecord .
    ?scr mesh:indexingData ?bn_indexingData .

    ?scr dcterms:identifier ?scr_id .
    ?scr rdfs:label ?scr_label .

    ?scr mesh:indexingData ?bn_indexingData .
    ?bn_indexingData mesh:indexingDescriptor ?indexingDescriptor .

    OPTIONAL {
        ?bn_indexingData mesh:indexingQualifier ?indexingQualifier .
    }

    OPTIONAL {
        ?indexingDescriptor dcterms:identifier ?indexingDescriptor_id .
        ?indexingDescriptor rdfs:label ?indexingDescriptor_label .
    }

    OPTIONAL {
        ?indexingQualifier dcterms:identifier ?indexingQualifier_id .
        ?indexingQualifier rdfs:label ?indexingQualifier_label .
    }

#   FILTER(?scr = mesh:C002199)
}
limit 50
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?scr_id
    ?scr_label
    ?bn_indexingData
    ?indexingDescriptor_id
    ?indexingDescriptor_label
    ?indexingQualifier_id
    ?indexingQualifier_label
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?scr a mesh:SupplementaryConceptRecord .
    ?scr mesh:indexingData ?bn_indexingData .

    ?scr dcterms:identifier ?scr_id .
    ?scr rdfs:label ?scr_label .

    ?scr mesh:indexingData ?bn_indexingData .
    ?bn_indexingData mesh:indexingDescriptor ?indexingDescriptor .

    OPTIONAL {
        ?bn_indexingData mesh:indexingQualifier ?indexingQualifier .
    }

    OPTIONAL {
        ?indexingDescriptor dcterms:identifier ?indexingDescriptor_id .
        ?indexingDescriptor rdfs:label ?indexingDescriptor_label .
    }

    OPTIONAL {
        ?indexingQualifier dcterms:identifier ?indexingQualifier_id .
        ?indexingQualifier rdfs:label ?indexingQualifier_label .
    }

    FILTER(!bound(?indexingQualifier))
}
limit 50
```


This BN could be avoided by using DQPairs for indexingDescriptor + indexingQualifier.
The DQPair would be attached directly to the SCR, through the predicate indexingInformation
(or something equivalent).

NB:
- not all DQPairs are complete (the qualifier may be missing)
    e.g., indexingQualifier for C006900
- an unqualified DQPairs should be created for each descriptor


## BN type 4: mappedData

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?scr
    ?bn_mappedData
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?scr a mesh:SupplementaryConceptRecord .
    ?scr mesh:mappedData ?bn_mappedData .
}
limit 10
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?scr_id
    ?scr_label
    ?bn_mappedData
    ?isMappedToDescriptor_id
    ?isMappedToDescriptor_label
    ?isMappedToQualifier_id
    ?isMappedToQualifier_label
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?scr a mesh:SupplementaryConceptRecord .
    ?scr mesh:mappedData ?bn_mappedData .

    ?scr dcterms:identifier ?scr_id .
    ?scr rdfs:label ?scr_label .

    ?scr mesh:mappedData ?bn_mappedData .
    ?bn_mappedData mesh:isMappedToDescriptor ?isMappedToDescriptor .

    OPTIONAL {
        ?bn_mappedData mesh:isMappedToQualifier ?isMappedToQualifier .
    }

    OPTIONAL {
        ?isMappedToDescriptor dcterms:identifier ?isMappedToDescriptor_id .
        ?isMappedToDescriptor rdfs:label ?isMappedToDescriptor_label .
    }

    OPTIONAL {
        ?isMappedToQualifier dcterms:identifier ?isMappedToQualifier_id .
        ?isMappedToQualifier rdfs:label ?isMappedToQualifier_label .
    }

#   FILTER(?scr = mesh:C002199)
}
limit 50
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?scr_id
    ?scr_label
    ?bn_mappedData
    ?isMappedToDescriptor_id
    ?isMappedToDescriptor_label
    ?isMappedToQualifier_id
    ?isMappedToQualifier_label
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?scr a mesh:SupplementaryConceptRecord .
    ?scr mesh:mappedData ?bn_mappedData .

    ?scr dcterms:identifier ?scr_id .
    ?scr rdfs:label ?scr_label .

    ?scr mesh:mappedData ?bn_mappedData .
    ?bn_mappedData mesh:isMappedToDescriptor ?isMappedToDescriptor .

    OPTIONAL {
        ?bn_mappedData mesh:isMappedToQualifier ?isMappedToQualifier .
    }

    OPTIONAL {
        ?isMappedToDescriptor dcterms:identifier ?isMappedToDescriptor_id .
        ?isMappedToDescriptor rdfs:label ?isMappedToDescriptor_label .
    }

    OPTIONAL {
        ?isMappedToQualifier dcterms:identifier ?isMappedToQualifier_id .
        ?isMappedToQualifier rdfs:label ?isMappedToQualifier_label .
    }

    FILTER(!bound(?isMappedToQualifier))
}
limit 50
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?scr
    ?bn_mappedData
    ?p
    ?o
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?scr a mesh:SupplementaryConceptRecord .
    ?scr mesh:mappedData ?bn_mappedData .
    ?bn_mappedData ?p ?o .
}
limit 10
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?scr_id
    ?scr_label
    ?bn_mappedData
    ?isMappedToDescriptor_id
    ?isMappedToDescriptor_label
    ?isDescriptorStarred
    ?isMappedToQualifier_id
    ?isMappedToQualifier_label
    ?isQualifierStarred
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?scr a mesh:SupplementaryConceptRecord .
    ?scr mesh:mappedData ?bn_mappedData .

    ?scr dcterms:identifier ?scr_id .
    ?scr rdfs:label ?scr_label .

    ?scr mesh:mappedData ?bn_mappedData .
    ?bn_mappedData mesh:isMappedToDescriptor ?isMappedToDescriptor .

    OPTIONAL {
        ?bn_mappedData mesh:isMappedToQualifier ?isMappedToQualifier .
    }

    OPTIONAL {
        ?bn_mappedData mesh:isDescriptorStarred ?isDescriptorStarred .
    }

    OPTIONAL {
        ?bn_mappedData mesh:isQualifierStarred ?isQualifierStarred .
    }

    OPTIONAL {
        ?isMappedToDescriptor dcterms:identifier ?isMappedToDescriptor_id .
        ?isMappedToDescriptor rdfs:label ?isMappedToDescriptor_label .
    }

    OPTIONAL {
        ?isMappedToQualifier dcterms:identifier ?isMappedToQualifier_id .
        ?isMappedToQualifier rdfs:label ?isMappedToQualifier_label .
    }

    FILTER(?scr = mesh:C002199)
}
limit 50
```

[Similar to indexingData]

This BN could be avoided by using  DQPairs  for isMappedToDescriptor + isMappedToQualifier.
The DQPair would be attached directly to the SCR, through the predicate isMappedToInformation
(or something equivalent).

NB:
- not all DQPairs are complete (the qualifier may be missing)
  e.g., isMappedToQualifier for C001107
- an unqualified DQPairs should be created for each descriptor
- one main difference with indexingData is the presence of isDescriptorStarred/isQualifierStarred
  e.g., isQualifierStarred for C002199
  The issue here is that this is not a property of the DQPair or the SCR, but this particular SCR-DQPair
  Possible solutions:
    * singleton property for the association between the SCR and the DQPair, to which the star informatino would be attached
    * materialize the SCR-DQPair association with a URI (equivalent to the original BN)


## BN type 5: termData

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?concept
    ?term
    ?bn_termData
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?concept mesh:term ?term .
    ?term mesh:termData ?bn_termData .
}
limit 10
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?concept
    ?term
    ?bn_termData
    ?p
    ?o
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?concept mesh:term ?term .
    ?term mesh:termData ?bn_termData .
    ?bn_termData ?p ?o .
}
order by ?bn_termData ?p
limit 50
```

```sqarql
PREFIX mesh: <http://id.nlm.nih.gov/mesh/>
select
    ?term
    count(distinct ?concept) as ?nb_concepts
from <http://mor.nlm.nih.gov/mesh2014-v1>
where {
    ?concept mesh:term ?term .
}
order by desc(?nb_concepts)
limit 50
```


This BN seems unnecessary.

Assuming the term properties are independent of the concept to which the term is attached,
they could be directly attached to the term entity. It looks like terms are not attached
to more than one concept. Therefore, term properties could be directly attached to the term entity.

Unclear why it was modeled this way.

From the XSLT comments (line 463):

> Need to address: This relation was created in order to stick with the XML representation of MeSH.

