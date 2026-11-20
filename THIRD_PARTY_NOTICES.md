# Third Party Notices and Data Provenance

The following external dictionaries and linguistic datasets are included in this project, subject to their respective licenses and provenance terms:

## 1. Kaikki.org / English Wiktionary (Spanish Headword Dictionary)
- **File**: `app/src/main/assets/dictionary.json`
- **Source**: [Kaikki.org Spanish Dictionary Extracts](https://kaikki.org/dictionary/Spanish/) derived from English Wiktionary.
- **License**: Creative Commons Attribution-ShareAlike 4.0 International (CC BY-SA 4.0) and GNU Free Documentation License (GFDL).
- **Contents**: ~78,900 Spanish headwords with English glosses, parts of speech, noun genders, plural forms, gendered counterpart forms, conjugation tables, example sentences with translations, and synonyms/antonyms.
- **Transformation Steps**: Wiktionary JSONL dump was parsed, filtered to single-word headwords with verified glosses, structured into canonical `DictionaryEntry` JSON models, and cross-referenced for gendered counterparts and conjugations.

## 2. Irregular Verb Paradigm Reference
- **File**: `app/src/main/assets/irregular_verbs.json`
- **Source**: Public domain Spanish linguistic reference models (Real Academia Española / ASALE grammar paradigms).
- **License**: Public Domain / Permissive Educational Reference.
- **Contents**: Irregular stem-changing and irregular root verbs (present, preterite, imperfect, future) categorized by irregularity pattern (e.g. e->ie, o->ue, e->i, g-insertion, orthographic shift).

## 3. False Cognates (False Friends) Reference
- **File**: `app/src/main/assets/false_friends.json`
- **Source**: Curated public domain contrastive linguistic tables (Spanish-English false cognates).
- **License**: Public Domain / Permissive Educational Reference.
- **Contents**: High-frequency Spanish words resembling English words with divergent meanings, annotated with English looks-like terms, actual meanings, and disambiguation notes.

## 4. Ser vs. Estar Adjective Semantic Shifts
- **File**: `app/src/main/assets/ser_estar_adjectives.json`
- **Source**: Spanish descriptive grammar reference paradigms (RAE *Nueva gramática de la lengua española*).
- **License**: Public Domain / Permissive Educational Reference.
- **Contents**: Adjectives whose semantic interpretation changes depending on copular verb selection (*ser* = inherent/essential quality vs. *estar* = resultant/transitory state).

## 5. Verbal Prepositional Regimen
- **File**: `app/src/main/assets/preposition_pairings.json`
- **Source**: Public reference grammar on Spanish verbal government (*régimen verbal preposicional*).
- **License**: Public Domain / Permissive Educational Reference.
- **Contents**: Spanish verbs requiring bound prepositions (*soñar con*, *pensar en*, *depender de*, etc.) with idiomatic English translations.

## 6. Regional Lexical Variants
- **File**: `app/src/main/assets/regional_variants.json`
- **Source**: Pan-hispanic dialectology reference lists.
- **License**: Public Domain / Permissive Educational Reference.
- **Contents**: Lexical differences between common Latin American Spanish and Peninsular Spanish (Spain).
